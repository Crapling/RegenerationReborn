package me.crapling.regenerationreborn.common;


import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import me.crapling.regenerationreborn.common.registry.RegistryBlock;
import me.crapling.regenerationreborn.common.registry.RegistryCrafting;
import me.crapling.regenerationreborn.common.registry.RegistryItem;
import me.crapling.regenerationreborn.common.registry.RegistryWorld;
import net.minecraft.potion.Potion;
import net.minecraftforge.common.config.Configuration;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;

@Mod(modid = RegenerationReborn.MODID, version = RegenerationReborn.VERSION, name = RegenerationReborn.MODNAME)
public class RegenerationReborn {

    public static final String MODID = "regenerationreborn";
    public static final String VERSION = "0.1";
    public static final String MODNAME = "Regeneration Reborn";

    public static boolean isNaturalRegenerationActive;
    public static int potionHealthBlessingID;

    public Configuration config;

    @SidedProxy(clientSide = "me.crapling.regenerationreborn.client.ClientProxy", serverSide = "me.crapling.regenerationreborn.common.CommonProxy")
    public static CommonProxy proxy;

    @Mod.Instance(value = MODID)
    public static RegenerationReborn instance;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent e){
        initConfiguration(e);
        extendPotionIDs();

        RegistryBlock.register();
        RegistryItem.register();
        RegistryWorld.register();
        RegistryCrafting.register();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent e){
        proxy.registerRenderers();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e){
    }

    private void extendPotionIDs() {
        if (Potion.potionTypes.length < 128) {
            Field field = null;
            try {
                field = Potion.class.getDeclaredField("potionTypes");
                Field modField = Field.class.getDeclaredField("modifiers");
                modField.setAccessible(true);
                modField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
                field.set(null, Arrays.copyOf(Potion.potionTypes, 128));
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
                System.out.println(RegenerationReborn.MODNAME + " Ooops! Something went wrong please report O.o");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                System.out.println(RegenerationReborn.MODNAME + " Ooops! Something went wrong please report O.o");
            }
        }
    }
    private void initConfiguration(FMLPreInitializationEvent e){
        config = new Configuration(new File("config/RegenerationReborn.cfg"));
        config.load();
        isNaturalRegenerationActive = config.get("world functions", "NaturalRegeneration", true).getBoolean(true);
        potionHealthBlessingID = config.get("potions effects", "HealthBlessingID", 120).getInt();
        config.save();
    }
}
