package me.crapling.regenerationreborn.common.world.potionprocess;

import net.minecraft.potion.PotionEffect;

public class HealthBlessingProcess {

    private int potionID;

    public static String[] potionSideEffectArray = {"0|17|160|2|0.6","0|2|160|1|0.6", "0|15|200|0|0.6", "1|17|260|3|0.6", "1|2|260|1|0.6", "1|4|260|1|0.6", "1|15|200|0|0.6", "2|2|300|2|0.6", "2|4|300|2|0.6", "2|17|300|3|0.6", "2|15|200|0|0.6", "3|2|360|3|0.6", "3|4|360|4|0.6", "3|17|300|4|0.6", "3|9|360|0|0.6", "3|15|200|0|0.6", "4|2|300|3|0.6", "4|4|300|4|0.6", "4|17|400|3|0.6", "4|9|400|0|0.6", "4|18|400|2|0.6", "4|15|200|0|0.6","4|20|60|0|0.6", "5|7|20|19|1.0"};

    public void setPotionEffectID(int potionID){
        this.potionID = potionID;
    }

    public PotionEffect setPotionEffectParameter(int duration, int amplifier) {
     return new PotionEffect(this.potionID, duration, amplifier);
    }
}
