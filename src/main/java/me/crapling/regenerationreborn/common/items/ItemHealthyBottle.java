package me.crapling.regenerationreborn.common.items;

import me.crapling.regenerationreborn.common.RegenerationReborn;
import me.crapling.regenerationreborn.common.registry.RegistryWorld;
import me.crapling.regenerationreborn.common.world.potionprocess.HealthBlessingProcess;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.world.World;

import java.awt.*;

public class ItemHealthyBottle extends ItemFood {

    private PotionEffect effect;


    public ItemHealthyBottle(int healtAmount, float saturationModifier, boolean wolvesFavourite, PotionEffect effect) {
        super(healtAmount, saturationModifier, wolvesFavourite);
        this.setAlwaysEdible();
        this.effect = effect;
    }
    public int getMaxItemUseDuration(ItemStack p_77626_1_){
        return 8;
    }

    protected void onFoodEaten(ItemStack itemStack, World world, EntityPlayer p) {
        super.onFoodEaten(itemStack, world, p);

        HealthBlessingProcess process = new HealthBlessingProcess();

        if(!world.isRemote) {
            try  {
                new Robot().mouseWheel(-100);
            }catch (AWTException e){
                e.printStackTrace();
                System.out.println(RegenerationReborn.MODNAME + " Ooops! Something went wrong please report O.o");
            }
            p.setHealth(p.getHealth() + 1);
            process.setPotionEffectID(this.effect.getPotionID());
            if (!p.isPotionActive(effect.getPotionID())) {
                p.addPotionEffect(process.setPotionEffectParameter(effect.getDuration(), effect.getAmplifier()));
            } else {
                int duration = p.getActivePotionEffect(RegistryWorld.healthBlessing).getDuration() + effect.getDuration();
                switch (p.getActivePotionEffect(RegistryWorld.healthBlessing).getAmplifier()) {
                    case 0:
                        p.addPotionEffect(process.setPotionEffectParameter(duration, 1));
                        for(int i = 0;i<process.getBadEffects(1).length;i++ ){
                            p.addPotionEffect(process.getBadEffects(1)[i]);
                        }
                        break;
                    case 1:
                        p.addPotionEffect(process.setPotionEffectParameter(duration, 2));
                        for(int i = 0;i<process.getBadEffects(2).length;i++ ){
                            p.addPotionEffect(process.getBadEffects(2)[i]);
                        }
                        break;
                    case 2:
                        p.addPotionEffect(process.setPotionEffectParameter(duration, 3));
                        for(int i = 0;i<process.getBadEffects(3).length;i++ ){
                            p.addPotionEffect(process.getBadEffects(3)[i]);
                        }
                        break;
                    case 3:
                        p.addPotionEffect(process.setPotionEffectParameter(duration, 4));
                        for(int i = 0;i<process.getBadEffects(4).length;i++ ){
                            p.addPotionEffect(process.getBadEffects(4)[i]);
                        }
                        break;
                    default:
                       p.attackEntityFrom(EntityDamageSource.magic, Float.MAX_VALUE);
                }
            }
        }
            }
        }



