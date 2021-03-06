package me.crapling.regenerationreborn.common.registry;

import cpw.mods.fml.common.registry.GameRegistry;
import me.crapling.regenerationreborn.common.Config;
import me.crapling.regenerationreborn.common.Helper;
import me.crapling.regenerationreborn.common.items.ItemHealthyBottle;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSeeds;
import net.minecraft.potion.PotionEffect;

public class RegistryItem {

    public static Item fernSpeciesX = new Item().setCreativeTab(CreativeTabs.tabMaterials).setUnlocalizedName("fernSpeciesX").setTextureName(Helper.MODID + ":fern_speciesx");
    public static Item mysterium = new Item().setCreativeTab(CreativeTabs.tabMaterials).setUnlocalizedName("mysterium").setTextureName(Helper.MODID + ":mysterium");
    public static Item seedsSpeciesX = new ItemSeeds(RegistryBlock.cropSpeciesX , Blocks.farmland).setCreativeTab(CreativeTabs.tabMaterials).setUnlocalizedName("seedsSpeciesX").setTextureName(Helper.MODID + ":seeds_speciesx");
    static Item healthyBottle = new ItemHealthyBottle(Config.addPlayerFood, (float)Config.addSaturation, false, new PotionEffect(RegistryWorld.healthBlessing.id, Config.healthBlessingDuration, 0)).setCreativeTab(CreativeTabs.tabFood).setUnlocalizedName("healthyBottle").setTextureName(Helper.MODID + ":healthy_bottle");

    public static void register() {
        GameRegistry.registerItem(fernSpeciesX, fernSpeciesX.getUnlocalizedName());
        GameRegistry.registerItem(seedsSpeciesX, seedsSpeciesX.getUnlocalizedName());
        GameRegistry.registerItem(mysterium, mysterium.getUnlocalizedName());
        GameRegistry.registerItem(healthyBottle, healthyBottle.getUnlocalizedName());
    }
}
