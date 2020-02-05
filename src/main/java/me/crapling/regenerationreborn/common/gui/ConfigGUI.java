package me.crapling.regenerationreborn.common.gui;

import cpw.mods.fml.client.config.DummyConfigElement;
import cpw.mods.fml.client.config.GuiConfig;
import cpw.mods.fml.client.config.IConfigElement;
import me.crapling.regenerationreborn.common.Config;
import me.crapling.regenerationreborn.common.Helper;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;

import java.util.ArrayList;
import java.util.List;

public class ConfigGUI extends GuiConfig{
    public ConfigGUI(GuiScreen parent){
        super(parent, getConfigElements(), Helper.MODID, true, false, "RegenerationReborn Configuration");

    }

    private static List<IConfigElement> getConfigElements(){
     List<IConfigElement> list = new ArrayList<IConfigElement>();

     list.add(categoryElement(Config.CATEGORY_WORLD, "World Functions", "In here you can edit configurations that have impact on the World!"));
     list.add(categoryElement(Config.CATEGORY_CONSUME_KEY_FEATURES, "Main Health o' Bottle Configuration", "In here you can edit main configurations relating to the Health o' Bottle!"));
     list.add(categoryElement(Config.CATEGORY_CONSUME_ADV_FEATURES, "Advanced Health o' Bottle Configuration", "In here you can edit advanced configurations relating to the Health o' Bottle!"));
     return list;
    }
    private static IConfigElement categoryElement(String category, String name, String categoryDescription){
        return new DummyConfigElement.DummyCategoryElement(name, categoryDescription, new ConfigElement(Config.config.getCategory(category)).getChildElements());
    }
}
