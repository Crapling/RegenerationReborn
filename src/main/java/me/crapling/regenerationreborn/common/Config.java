package me.crapling.regenerationreborn.common;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import me.crapling.regenerationreborn.common.world.potionprocess.HealthBlessingProcess;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class Config {

    public static Configuration config;

    private static String CATEGORY_POTIONID = "potionids";
    public static String CATEGORY_CONSUME_KEY_FEATURES = "health bottle key features";
    public static String CATEGORY_CONSUME_ADV_FEATURES = "health bottle advanced features";
    public static String CATEGORY_WORLD = "world functions";

    public static String[] potionEffectArray;

    public static String mobKillMeleePotionEffect;

    public static boolean isNaturalRegenerationActive;
    public static boolean isNormalHealthRegenerationActive;

    public static boolean allowHealingOnMobKill;
    public static boolean scrollRight;
    public static boolean getGlassBottleBack;


    public static int potionHealthBlessingID;
    public static int addPlayerFood;
    public static int addPlayerHealth;
    public static int consumeDuration;

    public static double mysteriumChance;
    public static double mobKillMeleePotionEffectChance;
    public static double addSaturation;

    public static void initConfiguration(FMLPreInitializationEvent e) {
        config = new Configuration(new File("config/RegenerationReborn.cfg"));
        config.load();
        syncConfig();
    }

    public static void syncConfig() {

        potionEffectArray = HealthBlessingProcess.potionEffectArray;

        isNaturalRegenerationActive = config.get(CATEGORY_WORLD, "NaturalRegeneration", true, "Turns Natural Regeneration on(true) or off(false). Please note that Natural Regeneration only works with Health Blessing active(without deactivating that configuration)[default: true]").getBoolean();
        isNormalHealthRegenerationActive = config.get(CATEGORY_WORLD, "NormalRegeneration", false, "Deactivates the effect of regenerating only while the effect Health Blessing is active[default: false]").getBoolean();
        allowHealingOnMobKill = config.get(CATEGORY_WORLD, "MobKillAllowsHeal", false, "Allows healing by killing a mob without Health Blessing active(when using healing potion effects)[default: false]").getBoolean();
        getGlassBottleBack = config.get(CATEGORY_CONSUME_KEY_FEATURES, "GetGlassBottleBack", true, "If false the player doesn't get a glass bottle back after drinking the Health o' Bottle[default: true]").getBoolean();

        potionHealthBlessingID = config.get(CATEGORY_POTIONID, "HealthBlessingID", 120, "Please assign a free potion id[default: 120]").getInt();

        addPlayerFood = config.get(CATEGORY_CONSUME_KEY_FEATURES, "HealPlayerFoodBarAmount", 0, "The amount of food a player gets from consuming the Health o' Bottle[default: 0 , use 1 to get half of a food symbol]").getInt();
        addPlayerHealth = config.get(CATEGORY_CONSUME_KEY_FEATURES, "HealPlayerHeartsAmount", 2, "The amount of health a player gets from consuming the Health o' Bottle[default: 2 , which is one Heart]").getInt();
        consumeDuration = config.get(CATEGORY_CONSUME_KEY_FEATURES, "ConsumeDuration", 6, "The amount of time it takes to consume the Health o' Bottle[default: 6]").getInt();

        mysteriumChance = config.get(CATEGORY_CONSUME_KEY_FEATURES, "MysteriumChance", 0.25, "Sets the percentage chance to get Mysterium from drinking Health o' Bottle[default: 0.25D, which is 25%]").getDouble();

        addSaturation = config.get(CATEGORY_CONSUME_KEY_FEATURES, "SetSaturation", 0.0, "Sets the Saturation of one drunken Health o' Bottle[default: 0.0(For example an apple has a saturation of 0.3)]").getDouble();

        mobKillMeleePotionEffectChance = config.get(CATEGORY_WORLD, "MobKillMeleePotionEffectChance", 0.1, "Chance to get the specified potion effect by killing a mob with melee attack[default: 0.1]").getDouble();

        scrollRight = config.get(CATEGORY_CONSUME_ADV_FEATURES, "ScrollRight", true, "Scrolls right(true) or left(false) after the player drunk the Health o' Bottle[default: true]").getBoolean();
        potionEffectArray = config.get(CATEGORY_CONSUME_ADV_FEATURES, "SideEffects", potionEffectArray, "The player gets those side effects after he had drunken the Health o' Bottle(1st Number: if player has Health Blessing Effect 0, every side effect with 0 also gets active[with Health Blessing 1, every effect with 1 gets active], 2nd Number: the Potion Effect ID, 3rd Number: the Duration in ticks[20 ticks = 1 second], 4th Number: the Strength of the Potion Effect)[to get defaults write down all changes you did and delete this config file]").getStringList();

        mobKillMeleePotionEffect = config.get(CATEGORY_WORLD, "MobKillMeleePotionEffect", "6|1|1", "potion effect the player gets after killing a mob with melee(you can set the chance in this config)[default: 6|1|1]").getString();

        if (config.hasChanged()) {
            config.save();
        }
    }
}
