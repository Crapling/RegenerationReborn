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
    public static String CATEGORY_GENERATION = "world generation";

    public static String[] potionSideEffectArray;

    public static String[] mobKillMeleePotionEffect;

    private static String[] standardMobKillMeleePotionEffect = {"8|100|3|0.7", "1|140|2|0.8" , "11|80|0|0.5"};

    public static boolean isNaturalRegenerationActive;
    public static boolean isNormalHealthRegenerationActive;

    public static boolean scrollRight;
    public static boolean getGlassBottleBack;

    public static int potionHealthBlessingID;
    public static int addPlayerFood;
    public static int addPlayerHealthOnBlessing;
    public static int consumeDuration;
    public static int mobKillHealAmount;
    public static int healthBlessingDuration;
    public static int minDamageToPierce;
    public static int maxArmorPiercingDamage;
    public static int minArmorBarPointsToPierce;

    public static double mysteriumChance;
    public static double mobKillHealChance;
    public static double armorPierceDamageChance;
    public static double mobKillMeleeEffectChance;
    public static double addSaturation;

    public static void initConfiguration(FMLPreInitializationEvent e) {
        config = new Configuration(new File("config/RegenerationReborn.cfg"));
        config.load();
        syncConfig();
    }

    public static void syncConfig() {

        potionSideEffectArray = HealthBlessingProcess.potionSideEffectArray;

        //health bottle advanced features
        //boolean
        scrollRight = config.get(CATEGORY_CONSUME_ADV_FEATURES, "ScrollRight", true, "Scrolls right(true) or left(false) after the player drank the Health o' Bottle[default: true]").getBoolean();
        //int
        healthBlessingDuration = config.get(CATEGORY_CONSUME_ADV_FEATURES, "HealthBlessingDuration", 300, "Changes the duration of the Health Blessing effect, when the player drank a Health o' Bottle [default: 300 (value in ticks)]").getInt();
        //stringlist
        potionSideEffectArray = config.get(CATEGORY_CONSUME_ADV_FEATURES, "SideEffects", potionSideEffectArray, "The player gets inflicted by those side effects after he had drunken the Health o' Bottle(1st Number: if player has Health Blessing Effect 0, every side effect with 0 also gets active[with Health Blessing 1, every effect with 1 gets active], 2nd Number: the Potion Effect ID, 3rd Number: the Duration in ticks[20 ticks = 1 second], 4th Number: the Strength of the Potion Effect, 5th Number: the chance to get the effect)[to get defaults delete this config file]").getStringList();

        //health bottle key features
        //boolean
        getGlassBottleBack = config.get(CATEGORY_CONSUME_KEY_FEATURES, "GetGlassBottleBack", true, "If false the player doesn't get a glass bottle back after drinking the Health o' Bottle[default: true]").getBoolean();
        //int
        addPlayerFood = config.get(CATEGORY_CONSUME_KEY_FEATURES, "HealPlayerFoodBarAmount", 0, "The amount of food a player restores through the consume of the Health o' Bottle[default: 0 , use 1 to get half of a food symbol]").getInt();
        addPlayerHealthOnBlessing = config.get(CATEGORY_CONSUME_KEY_FEATURES, "HealPlayerHeartsAmount", 4, "The amount of health a player restores through the consume of the Health o' Bottle[default: 4 , which is two Heart symbols]").getInt();
        consumeDuration = config.get(CATEGORY_CONSUME_KEY_FEATURES, "ConsumeDuration", 4, "The amount of time it takes to consume the Health o' Bottle[default: 4]").getInt();
        //double
        mysteriumChance = config.get(CATEGORY_CONSUME_KEY_FEATURES, "MysteriumChance", 0.25, "Sets the percentage chance to get Mysterium from drinking a Health o' Bottle[default: 0.25, which is 25%]").getDouble();
        addSaturation = config.get(CATEGORY_CONSUME_KEY_FEATURES, "SetSaturation", 0.0, "Sets the Saturation of one drunk Health o' Bottle[default: 0.0(For example an apple has a saturation of 0.3)]").getDouble();

        //potionids
        //int
        potionHealthBlessingID = config.get(CATEGORY_POTIONID, "HealthBlessingID", 120, "Assign a free potion ID for Health Blessing[default: 120]").getInt();

        //world functions
        //boolean
        isNaturalRegenerationActive = config.get(CATEGORY_WORLD, "NaturalRegeneration", true, "Turns Natural Regeneration on(true) or off(false). Please note that Natural Regeneration only works with Health Blessing active[default: true]").getBoolean();
        isNormalHealthRegenerationActive = config.get(CATEGORY_WORLD, "NormalRegeneration", false, "Deactivates the effect of regenerating only while the effect Health Blessing is active[default: false]").getBoolean();
        //int
        mobKillHealAmount = config.get(CATEGORY_WORLD, "MobKillHealAmount", 4, "Amount of health the player restores by killing a Mob[default: 4]").getInt();
        maxArmorPiercingDamage = config.get(CATEGORY_WORLD, "MaximumPierceDamage", 1, "Amount of damage the player gets when pierced[default: 1]").getInt();
        minDamageToPierce = config.get(CATEGORY_WORLD, "MinimumNormalDamageToPierce", 8, "Minimum damage the player has to get to receive pierced damage[default: 8, which would be a hit from an Enderman]").getInt();
        minArmorBarPointsToPierce = config.get(CATEGORY_WORLD, "MinimumArmorBarPointsToPierce", 20, "Minimum Armor Points the player has to have in order to receive pierced damage[default: 20, is equal to full diamond armor]").getInt();
        //double
        mobKillMeleeEffectChance = config.get(CATEGORY_WORLD, "MobKillMeleeEffectChance", 0.3, "Overall chance to get the specified Potion Effects and being healed after killing a mob(chances per potion effect are also configurable)[default: 0.3]").getDouble();
        mobKillHealChance= config.get(CATEGORY_WORLD, "MobKillHealChance", 1.0, "Defines the chance for being healed when killing a Mob[default: 1.0]").getDouble();
        armorPierceDamageChance = config.get(CATEGORY_WORLD, "ArmorPierceDamageChance", 0.4, "Defines the chance for being pierced through your armor[default: 0.4]").getDouble();

        //stringlist
        mobKillMeleePotionEffect = config.get(CATEGORY_WORLD, "MobKillMeleePotionEffect", standardMobKillMeleePotionEffect, "If a player kills a mob he inflicts himself with the specified potion effects underneath(1st Number: the Potion Effect ID, 2nd Number: the Duration in ticks[20 ticks = 1 second], 3rd Number: the Strength of the Potion Effect, 4th Number: the chance to get the effect)[to get defaults delete this config file]").getStringList();

        //world generation

        if (config.hasChanged()) {
            config.save();
        }
    }
}
