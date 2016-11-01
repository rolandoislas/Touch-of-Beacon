package com.rolandoislas.touchofbeacon.potion;

import com.rolandoislas.touchofbeacon.TouchOfBacon;
import com.rolandoislas.touchofbeacon.blocks.EnumTier;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;

/**
 * Created by Rolando on 10/31/2016.
 */
public class PotionFood extends Potion {
	public PotionFood() {
		super(false, 1);
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
			player.getFoodStats().setFoodSaturationLevel(tier.getSaturationLevel());
	}

	@Override
	public boolean isReady(int duration, int amplifier) {
		return true;
	}
}
