package me.crapling.regenerationreborn.common;

import net.minecraft.potion.Potion;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Random;

public class Helper {

    public static final String MODID = "regenerationreborn";
    public static final String VERSION = "0.11";
    public static final String MODNAME = "Regeneration Reborn";
    public static final String GUIFACTORY = "me.crapling." + MODID + ".common.gui.GUIFactoryManager";

    public static final String ERR = MODNAME + " Ooops! Something went wrong please report O.o";

    public static double getRandomDouble() {
        return new Random().nextFloat();
    }

    public void extendPotionIDs() {
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