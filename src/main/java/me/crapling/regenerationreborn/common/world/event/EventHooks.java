package me.crapling.regenerationreborn.common.world.event;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import me.crapling.regenerationreborn.common.Config;
import me.crapling.regenerationreborn.common.RegenerationReborn;
import me.crapling.regenerationreborn.common.registry.RegistryWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHealEvent;

import java.util.Random;

public class EventHooks{

    private boolean interruptHealing = true;
    private int ticksExisted = 0;
    private World world;

    @SubscribeEvent
    public void onConfigChanged(ConfigChangedEvent e) {
        if (e.modID.equals(RegenerationReborn.MODID)) {
            Config.syncConfig();
        }
    }

    @SubscribeEvent
    public void onJoin(EntityJoinWorldEvent e) {
        if (e.entity instanceof EntityPlayer) {
            if (!Config.isNaturalRegenerationActive) {
                e.world.getGameRules().setOrCreateGameRule("naturalRegeneration", "false");
            } else {
                e.world.getGameRules().setOrCreateGameRule("naturalRegeneration", "true");
            }
        }
    }
    @SubscribeEvent
    public void onKill(LivingDeathEvent e) {

        Entity entity = e.entity;
        Entity dmgSource = e.source.getSourceOfDamage();

        Random rand = new Random();
        double chance = rand.nextFloat();

        if (dmgSource instanceof EntityPlayer) {
            if (entity.isCreatureType(EnumCreatureType.monster, false)) {
                String[] potionParameterMob = Config.mobKillMeleePotionEffect.split("\\|", 3);
                if (chance <= Config.mobKillMeleePotionEffectChance) {
                    if(Config.allowHealingOnMobKill) interruptHealing = false;
                    ((EntityPlayer) dmgSource).addPotionEffect(new PotionEffect(Integer.parseInt(potionParameterMob[0]), Integer.parseInt(potionParameterMob[1]), Integer.parseInt(potionParameterMob[2])));
                }
            }
        }
    }

    @SubscribeEvent
     public void onHeal(LivingHealEvent e) {
       if (e.entityLiving instanceof EntityPlayer && !e.entityLiving.isPotionActive(RegistryWorld.healthBlessing) && !Config.isNormalHealthRegenerationActive) {
                e.setCanceled(interruptHealing);
                interruptHealing = true;
       }
       }
}

