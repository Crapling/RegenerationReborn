package me.crapling.regenerationreborn.common.world.generation;

import cpw.mods.fml.common.IWorldGenerator;
import me.crapling.regenerationreborn.common.registry.RegistryBlock;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;

import java.util.Random;

public class OreGen implements IWorldGenerator {

    public void generate(Random random, int x, int z, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
        switch (world.provider.dimensionId) {
            case 0:
                generateOverworld(world, random, x, z);
                break;
            case 1:
                generateNether(world, random, x, z);
                break;
            case -1:
                generateEnd(world, random, x, z);
                break;
        }
    }

    public void generateOverworld(World world, Random random, int x, int z) {
        genOre(RegistryBlock.oreMysterium, Blocks.stone, world, random, x, z, 2, 6, 10, 0, 36);
    }
    public void generateNether(World world, Random random, int x, int z){
    }
    public void generateEnd(World world, Random random, int x, int z) {

    }
    public void genOre(Block block, Block generateIn, World world, Random random, int x, int z, int minVein, int maxVein, int chance, int minY, int maxY){
        int veinSize = minVein + random.nextInt(maxVein - minVein);
        int heightRange = maxY - minY;
        WorldGenMinable gen = new WorldGenMinable(block, veinSize, generateIn);
        for(int i = 0; i<chance; i++){
            int xRandom = x * 16 + random.nextInt(16);
            int yRandom = random.nextInt(heightRange) + minY;
            int zRandom = z * 16 +random.nextInt(16);
            gen.generate(world, random, xRandom, yRandom, zRandom);
        }
    }
}
