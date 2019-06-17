package me.crapling.regenerationreborn.common.items;

import me.crapling.regenerationreborn.common.Config;
import me.crapling.regenerationreborn.common.RegenerationReborn;
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
                System.out.println(RegenerationReborn.ERR);
            }
        }
        if (!world.isRemote) {
            System.out.println("Max Health:" + p.getMaxHealth());
            p.setHealth(p.getHealth() + Config.addPlayerHealth);

            process.setPotionEffectID(this.effect.getPotionID());

            if (!p.isPotionActive(effect.getPotionID())) {
                p.addPotionEffect(process.setPotionEffectParameter(effect.getDuration(), effect.getAmplifier()));

                for (int i = 0; i < Config.potionEffectArray.length; i++) {
                    if (Integer.parseInt(Config.potionEffectArray[i].substring(0, 1)) == 0) {
                        String potionEffect = Config.potionEffectArray[i];
                        String[] potionParameter = potionEffect.split("\\|", 4);
                        p.addPotionEffect(new PotionEffect(Integer.parseInt(potionParameter[1]), Integer.parseInt(potionParameter[2]), Integer.parseInt(potionParameter[3])));
                    }
                }
            } else {
                int sumDuration = p.getActivePotionEffect(RegistryWorld.healthBlessing).getDuration() + effect.getDuration();
                int sumAmplifier = p.getActivePotionEffect(RegistryWorld.healthBlessing).getAmplifier() + 1;
                p.addPotionEffect(process.setPotionEffectParameter(sumDuration, sumAmplifier));

                for (int i = 0; i < Config.potionEffectArray.length; i++) {
                    if (Integer.parseInt(Config.potionEffectArray[i].substring(0, 1)) == p.getActivePotionEffect(RegistryWorld.healthBlessing).getAmplifier()) {
                        String potionEffect = Config.potionEffectArray[i];
                        String[] potionParameter = potionEffect.split("\\|", 4);
                        p.addPotionEffect(new PotionEffect(Integer.parseInt(potionParameter[1]), Integer.parseInt(potionParameter[2]), Integer.parseInt(potionParameter[3])));
                    }
                }
            }
            if (Config.getGlassBottleBack) {
                p.inventory.addItemStackToInventory(new ItemStack(Items.glass_bottle));
            }

            Random rand = new Random();
            double chance = rand.nextFloat();

            if (chance <= Config.mysteriumChance) {
                p.inventory.addItemStackToInventory(new ItemStack(RegistryItem.mysterium));
            }
        }
    }
}


