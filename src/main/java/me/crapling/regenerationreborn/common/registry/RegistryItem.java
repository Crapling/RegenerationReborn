package me.crapling.regenerationreborn.common.registry;

import cpw.mods.fml.common.registry.GameRegistry;
import me.crapling.regenerationreborn.common.RegenerationReborn;
import me.crapling.regenerationreborn.common.items.ItemHealthyBottle;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSeeds;
import net.minecraft.potion.PotionEffect;

public class RegistryItem {

    public static Item fernSpeciesX = new Item().setCreativeTab(CreativeTabs.tabMaterials).setUnlocalizedName("fernSpeciesX").setTextureName(RegenerationReborn.MODID + ":fern_speciesx");
    public static Item mysterium = new Item().setCreativeTab(CreativeTabs.tabMaterials).setUnlocalizedName("mysterium").setTextureName(RegenerationReborn.MODID + ":mysterium");
    public static Item seedsSpeciesX = new ItemSeeds(RegistryBlock.cropSpeciesX , Blocks.farmland).setCreativeTab(CreativeTabs.tabMaterials).setUnlocalizedName("seedsSpeciesX").setTextureName(RegenerationReborn.MODID + ":seeds_speciesx");
    public static Item healthyBottle = new ItemHealthyBottle(1, 1.0F, false, new PotionEffect(RegistryWorld.healthBlessing.id, 220, 0)).setCreativeTab(CreativeTabs.tabFood).setUnlocalizedName("healthyBottle").setTextureName(RegenerationReborn.MODID + ":healthy_bottle");

    public static void register() {
        GameRegistry.registerItem(fernSpeciesX, fernSpeciesX.getUnlocalizedName());
        GameRegistry.registerItem(seedsSpeciesX, seedsSpeciesX.getUnlocalizedName());
        GameRegistry.registerItem(mysterium, mysterium.getUnlocalizedName());
        GameRegistry.registerItem(healthyBottle, healthyBottle.getUnlocalizedName());
    }
}
