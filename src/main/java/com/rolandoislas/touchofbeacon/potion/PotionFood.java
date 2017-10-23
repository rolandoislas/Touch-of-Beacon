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

import java.awt.*;

/**
 * Created by Rolando on 10/31/2016.
 */
public class PotionFood extends Potion {
	ResourceLocation icon = new ResourceLocation(TouchOfBacon.MODID, "textures/icons/potion_fed.png");

	public PotionFood() {
		super(false, new Color(155, 63, 68).getRGB());
		this.setRegistryName("fed");
		this.setPotionName("effect." + TouchOfBacon.MODID + ".food");
		this.setBeneficial();
	}

	@Override
	public void performEffect(EntityLivingBase entity, int power) {
		if (!(entity instanceof EntityPlayer))
			return;
		EntityPlayer player = (EntityPlayer) entity;
		EnumTier tier = EnumTier.getTierFromMeta(power);
		if (player.getFoodStats().getFoodLevel() < tier.getFoodLevel())
			player.getFoodStats().addStats(1, 0);
		if (player.getFoodStats().getSaturationLevel() < tier.getSaturationLevel())
			player.getFoodStats().addStats(1, 1);
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
