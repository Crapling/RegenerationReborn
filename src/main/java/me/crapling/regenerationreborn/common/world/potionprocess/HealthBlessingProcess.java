package me.crapling.regenerationreborn.common.world.potionprocess;

import net.minecraft.potion.PotionEffect;

public class HealthBlessingProcess {

    private int potionID;

    public static String[] potionEffectArray = {"0|17|160|3","0|2|160|2", "0|15|200|0", "1|17|260|4", "1|2|260|2", "1|4|260|2", "1|15|200|0", "2|2|300|3", "2|4|300|3", "2|17|300|4", "2|15|200|0", "3|2|360|4", "3|4|360|5", "3|17|300|5", "3|9|360|0", "3|15|200|0", "4|2|300|4", "4|4|300|5", "4|17|400|4", "4|9|400|0", "4|18|400|3", "4|15|200|0","4|20|60|1", "5|7|20|19"};

    public void setPotionEffectID(int potionID){
        this.potionID = potionID;
    }

    public PotionEffect setPotionEffectParameter(int duration, int amplifier) {
     return new PotionEffect(this.potionID, duration, amplifier);
    }
}
