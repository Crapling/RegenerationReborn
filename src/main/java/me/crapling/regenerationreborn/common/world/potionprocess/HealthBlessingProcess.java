package me.crapling.regenerationreborn.common.world.potionprocess;

import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class HealthBlessingProcess {

    private int potionID;

    public void setPotionEffectID(int potionID){
        this.potionID = potionID;
    }
    public PotionEffect setPotionEffectParameter(int duration, int amplifier) {
     return new PotionEffect(this.potionID, duration, amplifier);
    }
    public PotionEffect[] getBadEffects(int level){
     switch(level){
         case 1:
             PotionEffect[] badEffects1 = {new PotionEffect(Potion.moveSlowdown.id, 200, 0), new PotionEffect(Potion.digSlowdown.id, 200, 0)};
             return badEffects1;
         case 2:
             PotionEffect[] badEffects2 = {new PotionEffect(Potion.moveSlowdown.id, 300, 0), new PotionEffect(Potion.digSlowdown.id, 200, 0),new PotionEffect(Potion.hunger.id, 200, 3)};
             return badEffects2;
         case 3:
             PotionEffect[] badEffects3 = {new PotionEffect(Potion.moveSlowdown.id, 300, 0), new PotionEffect(Potion.digSlowdown.id, 200, 0),new PotionEffect(Potion.hunger.id, 200, 2), new PotionEffect(Potion.confusion.id, 300, 0)};
             return badEffects3;
         case 4:
             PotionEffect[] badEffects4 = {new PotionEffect(Potion.moveSlowdown.id, 300, 0), new PotionEffect(Potion.digSlowdown.id, 200, 0),new PotionEffect(Potion.hunger.id, 200, 2), new PotionEffect(Potion.confusion.id, 300, 0), new PotionEffect(Potion.weakness.id, 400, 0), new PotionEffect(Potion.blindness.id, 200, 0)};
             return badEffects4;
     }
     return null;
    }
}
