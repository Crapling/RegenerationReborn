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
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = Helper.MODID, version = Helper.VERSION, name = Helper.MODNAME, guiFactory = Helper.GUIFACTORY)
public class RegenerationReborn {

    @SidedProxy(clientSide = "me.crapling.regenerationreborn.client.ClientProxy", serverSide = "me.crapling.regenerationreborn.common.CommonProxy")
    public static CommonProxy proxy;

    @Mod.Instance(value = Helper.MODID)
    public static RegenerationReborn instance;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent e){
        Config.initConfiguration(e);
        new Helper().extendPotionIDs();

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
}
