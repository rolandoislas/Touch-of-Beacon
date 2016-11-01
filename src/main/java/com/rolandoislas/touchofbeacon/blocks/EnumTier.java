package com.rolandoislas.touchofbeacon.blocks;

import net.minecraft.util.IStringSerializable;

/**
 * Created by Rolando on 10/31/2016.
 */
public enum EnumTier implements IStringSerializable {
	ZERO(0, 6, 0), ONE(1, 10, 0), TWO(2, 20, 0), THREE(3, 20, 20);

	private final int meta;
	private int foodLevel;
	private float saturationLevel;

	EnumTier(int meta, int foodLevel, int saturationLevel) {
		this.meta = meta;
		this.foodLevel = foodLevel;
		this.saturationLevel = saturationLevel;
	}

	@Override
	public String getName() {
		return this.name().toLowerCase();
	}

	public int getMetadata() {
		return this.meta;
	}

	public static EnumTier getTierFromMeta(int metadata) {
		for (EnumTier tier : values())
			if (tier.getMetadata() == metadata)
				return tier;
		return ZERO;
	}

	public String getUnlocalizedName() {
		return "tier_" + getName();
	}

	public int getFoodLevel() {
		return foodLevel;
	}

	public float getSaturationLevel() {
		return saturationLevel;
	}
}
