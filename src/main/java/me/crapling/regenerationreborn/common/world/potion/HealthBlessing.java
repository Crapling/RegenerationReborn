package me.crapling.regenerationreborn.common.world.potion;

import me.crapling.regenerationreborn.common.RegenerationReborn;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;

public class HealthBlessing extends Potion {

    public static final ResourceLocation resLocatation = new ResourceLocation(RegenerationReborn.MODID, "/textures/gui/inventory.png".substring(1));

    public HealthBlessing(int id, boolean badEffect, int potionColor) {
        super(id, badEffect, potionColor);
    }
    public Potion setIconIndex(int x, int y) {
        super.setIconIndex(x, y);
        return (Potion) this;
    }
    public int getStatusIconIndex() {
        ITextureObject texture = Minecraft.getMinecraft().renderEngine.getTexture(resLocatation);
        Minecraft.getMinecraft().renderEngine.bindTexture(resLocatation);

        return super.getStatusIconIndex();
    }
}

