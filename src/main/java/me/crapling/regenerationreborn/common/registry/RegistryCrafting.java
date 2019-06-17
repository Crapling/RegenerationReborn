package me.crapling.regenerationreborn.common.registry;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class RegistryCrafting {

public static void register(){
    GameRegistry.addRecipe(new ItemStack(RegistryBlock.blockMysterium), new Object[] {"XXX", "XXX", "XXX", 'X', RegistryItem.mysterium});
    GameRegistry.addShapelessRecipe(new ItemStack(RegistryItem.healthyBottle), new Object[]{RegistryItem.mysterium, RegistryItem.fernSpeciesX,RegistryItem.fernSpeciesX, Items.glass_bottle});
    GameRegistry.addShapelessRecipe(new ItemStack(RegistryItem.mysterium, 9,0), new Object[]{RegistryBlock.blockMysterium});
}
}
