package me.crapling.regenerationreborn.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

import java.util.Random;

public class BlockStructure extends Block{

    private Item drops;
    private int meta;
    private int minDrop;
    private int maxDrop;

    public BlockStructure(Material material, String toolClass, int harvestLevel) {
        super(material);
        setHarvestLevel(toolClass, harvestLevel);
    }

    /**
     * Constructor for a block that should drop a specific amount of one item
     * @param material block material
     * @param toolClass example "pickaxe"
     * @param harvestLevel 0=wood/gold 1=stone 2=iron 3=diamond
     * @param drops the item that should be dropped
     * @param meta null
     * @param minDrop minimum amount of drops
     * @param maxDrop maximum amount of drops
     */
    public BlockStructure(Material material, String toolClass, int harvestLevel, Item drops, int meta, int minDrop, int maxDrop) {
        super(material);
        this.drops = drops;
        this.meta = meta;
        this.minDrop = minDrop;
        this.maxDrop = maxDrop;
        setHarvestLevel(toolClass, harvestLevel);
    }

    public Item getItemDropped(int meta, Random random, int fortune) {
        return this.drops;
    }

    public int damageDropped(int metadata) {
        return this.meta;
    }

    public int quantityDropped(int meta, int fortune, Random random) {
        if (this.minDrop >= this.maxDrop)
            return this.minDrop;
        return this.minDrop + random.nextInt(this.maxDrop - this.minDrop + fortune + 1);
    }
}
