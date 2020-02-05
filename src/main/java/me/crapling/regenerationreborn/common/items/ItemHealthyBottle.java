package me.crapling.regenerationreborn.common.items;

import me.crapling.regenerationreborn.common.Config;
import me.crapling.regenerationreborn.common.Helper;
import me.crapling.regenerationreborn.common.registry.RegistryItem;
import me.crapling.regenerationreborn.common.registry.RegistryWorld;
import me.crapling.regenerationreborn.common.world.potionprocess.HealthBlessingProcess;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

import java.awt.*;
import java.util.Random;

public class ItemHealthyBottle extends ItemFood {

    private PotionEffect effect;

    public ItemHealthyBottle(int healthAmount, float saturationModifier, boolean wolvesFavourite, PotionEffect effect) {
        super(healthAmount, saturationModifier, wolvesFavourite);
        this.setAlwaysEdible();
        this.effect = effect;
    }

    public int getMaxItemUseDuration(ItemStack p_77626_1_) {
        return Config.consumeDuration;
    }

    public EnumAction getItemUseAction(ItemStack p_77661_1_) {
        return EnumAction.drink;
    }

    protected void onFoodEaten(ItemStack itemStack, World world, EntityPlayer p) {
        super.onFoodEaten(itemStack, world, p);

            HealthBlessingProcess process = new HealthBlessingProcess();
            if (world.isRemote) {
                try {
                    Robot robot = new Robot();
                    if (Config.scrollRight) {
                        robot.mouseWheel(100);
                    } else {
                        robot.mouseWheel(-100);
                    }
                } catch (AWTException e) {
                    e.printStackTrace();
                    System.out.println(Helper.ERR);
                }
            }else{

            int sumDuration;
            int sumAmplifier;

            p.setHealth(p.getHealth() + Config.addPlayerHealthOnBlessing);
            process.setPotionEffectID(this.effect.getPotionID());

            if(!p.isPotionActive(effect.getPotionID())) {
                sumDuration = effect.getDuration();
                sumAmplifier = effect.getAmplifier();
            }else{
                sumDuration = p.getActivePotionEffect(RegistryWorld.healthBlessing).getDuration() + effect.getDuration();
                sumAmplifier = p.getActivePotionEffect(RegistryWorld.healthBlessing).getAmplifier() + 1;
            }
            p.addPotionEffect(process.setPotionEffectParameter(sumDuration, sumAmplifier));

            for (String effect : Config.potionSideEffectArray) {
                if (Integer.parseInt(effect.substring(0, 1)) == p.getActivePotionEffect(RegistryWorld.healthBlessing).getAmplifier()) {
                    String[] potionParameter = effect.split("\\|");
                    if(Helper.getRandomDouble() <= Double.parseDouble(potionParameter[4])) {
                p.addPotionEffect(new PotionEffect(Integer.parseInt(potionParameter[1]), Integer.parseInt(potionParameter[2]), Integer.parseInt(potionParameter[3])));
            }
            }
        }
        if (Config.getGlassBottleBack) {
                    p.inventory.addItemStackToInventory(new ItemStack(Items.glass_bottle));
                    }

            if (Helper.getRandomDouble() <= Config.mysteriumChance) {
                p.inventory.addItemStackToInventory(new ItemStack(RegistryItem.mysterium));
            }
        }
    }
}


