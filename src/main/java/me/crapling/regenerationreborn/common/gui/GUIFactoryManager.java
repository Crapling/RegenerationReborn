package me.crapling.regenerationreborn.common.gui;

import cpw.mods.fml.client.IModGuiFactory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;

import java.util.Set;

public class GUIFactoryManager implements IModGuiFactory{
    public void initialize(Minecraft mcInstance){
    }
    public Class<? extends GuiScreen> mainConfigGuiClass(){
        return ConfigGUI.class;
    }
    public Set<RuntimeOptionCategoryElement> runtimeGuiCategories(){
        return null;
    }
    public RuntimeOptionGuiHandler getHandlerFor(RuntimeOptionCategoryElement element){
        return null;
    }
}
