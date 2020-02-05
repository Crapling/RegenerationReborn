package me.crapling.regenerationreborn.common.registry;

import cpw.mods.fml.common.registry.GameRegistry;
import me.crapling.regenerationreborn.common.Helper;
import me.crapling.regenerationreborn.common.blocks.BlockCropSpeciesX;
import me.crapling.regenerationreborn.common.blocks.BlockStructure;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class RegistryBlock {

    public static Block cropSpeciesX = new BlockCropSpeciesX().setBlockName("cropSpeciesX").setBlockTextureName(Helper.MODID + ":fern_speciesx_crop");
    public static Block blockMysterium = new BlockStructure(Material.rock, "pickaxe", 2).setBlockName("blockMysterium").setBlockTextureName(Helper.MODID + ":mysterium_block").setStepSound(Block.soundTypeStone).setResistance(5.0F).setHardness(1.0F).setCreativeTab(CreativeTabs.tabBlock);
    public static Block oreMysterium = new BlockStructure(Material.rock, "pickaxe", 2, RegistryItem.mysterium, 0, 1, 4).setBlockName("oreMysterium").setBlockTextureName(Helper.MODID + ":mysterium_ore").setStepSound(Block.soundTypePiston).setResistance(10000.0F).setHardness(2.0F).setCreativeTab(CreativeTabs.tabBlock);

    public static void register(){
        GameRegistry.registerBlock(cropSpeciesX, cropSpeciesX.getUnlocalizedName());
        GameRegistry.registerBlock(blockMysterium, blockMysterium.getUnlocalizedName());
        GameRegistry.registerBlock(oreMysterium, oreMysterium.getUnlocalizedName());
}
}
