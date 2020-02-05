package me.crapling.regenerationreborn.common.world.event;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import me.crapling.regenerationreborn.common.Config;
import me.crapling.regenerationreborn.common.Helper;
import me.crapling.regenerationreborn.common.registry.RegistryWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class EventHooks{

    private boolean interruptHealing = true;

    @SubscribeEvent
    public void onConfigChanged(ConfigChangedEvent e) {
        if (e.modID.equals(Helper.MODID)) {
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
    public void onDamage(LivingHurtEvent e){
        if(e.entity instanceof EntityPlayer && e.source.getSourceOfDamage() instanceof Entity && Helper.getRandomDouble() <= Config.armorPierceDamageChance){
            EntityPlayer p = (EntityPlayer) e.entity;
            if(e.ammount >= Config.minDamageToPierce &&  p.getTotalArmorValue() >= Config.minArmorBarPointsToPierce){
                    p.setHealth(p.getHealth() - Config.maxArmorPiercingDamage);
                }
            }
        }

    @SubscribeEvent
    public void onKill(LivingDeathEvent e) {

        Entity entity = e.entity;
        Entity dmgSource = e.source.getEntity();

        if (dmgSource instanceof EntityPlayer && entity.isCreatureType(EnumCreatureType.monster, false) && Helper.getRandomDouble()<= Config.mobKillMeleeEffectChance) {

            EntityPlayer p = (EntityPlayer) dmgSource;
            interruptHealing = false;

            if(Helper.getRandomDouble() <= Config.mobKillHealChance) {
                p.setHealth(p.getHealth() + Config.mobKillHealAmount);
            }
            for (String effect : Config.mobKillMeleePotionEffect) {
                String[] potionParameter = (effect.split("\\|"));
                if (Helper.getRandomDouble() <= Double.parseDouble(potionParameter[3])) {
                    p.addPotionEffect(new PotionEffect(Integer.parseInt(potionParameter[0]), Integer.parseInt(potionParameter[1]), Integer.parseInt(potionParameter[2])));
                }
            }
            interruptHealing = true;
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

