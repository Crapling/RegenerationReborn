package me.crapling.regenerationreborn.common.registry;

import cpw.mods.fml.common.registry.GameRegistry;
import me.crapling.regenerationreborn.common.RegenerationReborn;
import me.crapling.regenerationreborn.common.world.event.EventHooks;
import me.crapling.regenerationreborn.common.world.generation.OreGen;
import me.crapling.regenerationreborn.common.world.potion.HealthBlessing;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraftforge.common.MinecraftForge;

public class RegistryWorld {

    public static final Potion healthBlessing =  new HealthBlessing(RegenerationReborn.potionHealthBlessingID, false, 0).setIconIndex(0, 0).setPotionName("potion.healthBlessing");

    public static void register(){

        GameRegistry.registerWorldGenerator(new OreGen(), 0);

        MinecraftForge.EVENT_BUS.register(new EventHooks());
        MinecraftForge.addGrassSeed(new ItemStack(RegistryItem.seedsSpeciesX), 1);
    }
}
