package com.rolandoislas.touchofbeacon.potion;

import com.rolandoislas.touchofbeacon.TouchOfBacon;
import com.rolandoislas.touchofbeacon.blocks.EnumTier;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import toughasnails.api.TANCapabilities;
import toughasnails.api.stat.capability.IThirst;

import java.awt.*;

public class PotionQuench extends Potion {
    private ResourceLocation icon = new ResourceLocation(TouchOfBacon.MODID, "textures/icons/potion_quench.png");

    public PotionQuench() {
        super(false, new Color(63, 103, 155).getRGB());
        this.setRegistryName("quenched");
        this.setPotionName("effect." + TouchOfBacon.MODID + ".quenched");
        this.setBeneficial();
    }

    @Override
    public void performEffect(EntityLivingBase entity, int power) {
        //noinspection ConstantConditions
        if (!(entity instanceof EntityPlayer) || TANCapabilities.THIRST == null)
            return;
        EntityPlayer player = (EntityPlayer) entity;
        EnumTier tier = EnumTier.getTierFromMeta(power);
        IThirst thirst = player.getCapability(TANCapabilities.THIRST, null);
        if (thirst.getHydration() < tier.getFoodLevel())
            thirst.setHydration(tier.getFoodLevel());
        if (thirst.getThirst() < tier.getFoodLevel())
            thirst.setThirst(tier.getFoodLevel());
    }

    @Override
    public boolean isReady(int duration, int amplifier) {
        return true;
    }

    @Override
    public void renderInventoryEffect(int x, int y, PotionEffect effect, Minecraft mc) {
        mc.renderEngine.bindTexture(icon);
        Gui.drawModalRectWithCustomSizedTexture(x + 6, y + 7, 0, 0, 16, 16, 16, 16);
    }

    @Override
    public void renderHUDEffect(int x, int y, PotionEffect effect, Minecraft mc, float alpha) {
        mc.renderEngine.bindTexture(icon);
        Gui.drawModalRectWithCustomSizedTexture(x + 4, y + 4, 0, 0, 16, 16, 16, 16);
    }
}
