package me.crapling.regenerationreborn.common;


import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import me.crapling.regenerationreborn.common.registry.RegistryBlock;
import me.crapling.regenerationreborn.common.registry.RegistryCrafting;
import me.crapling.regenerationreborn.common.registry.RegistryItem;
import me.crapling.regenerationreborn.common.registry.RegistryWorld;
import me.crapling.regenerationreborn.common.world.event.EventHooks;
import net.minecraft.potion.Potion;
import net.minecraftforge.common.MinecraftForge;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;

@Mod(modid = RegenerationReborn.MODID, version = RegenerationReborn.VERSION, name = RegenerationReborn.MODNAME, guiFactory = RegenerationReborn.GUIFACTORY)
public class RegenerationReborn {

    public static final String MODID = "regenerationreborn";
    public static final String VERSION = "0.11";
    public static final String MODNAME = "Regeneration Reborn";
    public static final String GUIFACTORY = "me.crapling." + MODID + ".common.gui.GUIFactoryManager";

    public static final String ERR = RegenerationReborn.MODNAME + " Ooops! Something went wrong please report O.o";

    @SidedProxy(clientSide = "me.crapling.regenerationreborn.client.ClientProxy", serverSide = "me.crapling.regenerationreborn.common.CommonProxy")
    public static CommonProxy proxy;

    @Mod.Instance(value = MODID)
    public static RegenerationReborn instance;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent e){
        new Config().initConfiguration(e);
        extendPotionIDs();

        RegistryBlock.register();
        RegistryItem.register();
        RegistryWorld.register();
        RegistryCrafting.register();

        MinecraftForge.EVENT_BUS.register(new EventHooks());
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent e){
        FMLCommonHandler.instance().bus().register(new EventHooks());
        proxy.registerRenderers();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e){
    }

    private void extendPotionIDs() {
        if (Potion.potionTypes.length < 128) {
            Field field;
            try {
                field = Potion.class.getDeclaredField("potionTypes");
                Field modField = Field.class.getDeclaredField("modifiers");
                modField.setAccessible(true);
                modField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
                field.set(null, Arrays.copyOf(Potion.potionTypes, 128));
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
                System.out.println(ERR);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                System.out.println(ERR);
            }
        }
    }
}
