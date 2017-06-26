package me.crapling.regenerationreborn.common.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import me.crapling.regenerationreborn.common.registry.RegistryItem;
import net.minecraft.block.BlockCrops;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;

public class BlockCropSpeciesX extends BlockCrops {

    @SideOnly(Side.CLIENT)
    private IIcon[] icons;

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta)
    {
        if (meta < 7)
        {
            if (meta == 6)
            {
                meta = 5;
            }

            return this.icons[meta >> 1];
        }
        else
        {
            return this.icons[3];
        }
    }
    protected Item func_149866_i() {

        return RegistryItem.seedsSpeciesX;
    }
    protected Item func_149865_P() {

        return RegistryItem.fernSpeciesX;
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        this.icons = new IIcon[4];

        for (int i = 0; i < this.icons.length; ++i)
        {
            this.icons[i] = iconRegister.registerIcon(this.getTextureName() + "_stage_" + i);
        }
    }
}
