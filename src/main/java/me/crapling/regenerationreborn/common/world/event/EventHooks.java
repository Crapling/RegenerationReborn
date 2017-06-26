package me.crapling.regenerationreborn.common.world.event;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import me.crapling.regenerationreborn.common.RegenerationReborn;
import me.crapling.regenerationreborn.common.registry.RegistryWorld;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHealEvent;

public class EventHooks {

    @SubscribeEvent
    public void onJoin(EntityJoinWorldEvent e) {
        if(e.entity instanceof EntityPlayer){
        if(!RegenerationReborn.isNaturalRegenerationActive){
                e.world.getGameRules().setOrCreateGameRule("naturalRegeneration", "false");
            }else {
                e.world.getGameRules().setOrCreateGameRule("naturalRegeneration", "true");
            }
            }
            }
    @SubscribeEvent
    public void onHeal(LivingHealEvent e) {
        if (e.entityLiving instanceof EntityPlayer && !e.entityLiving.isPotionActive(RegistryWorld.healthBlessing)) {
            e.setCanceled(true);
        }
        }

    }

