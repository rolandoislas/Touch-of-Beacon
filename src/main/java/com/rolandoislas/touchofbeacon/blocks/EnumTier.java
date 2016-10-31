package com.rolandoislas.touchofbeacon.blocks;

import net.minecraft.util.IStringSerializable;

/**
 * Created by Rolando on 10/31/2016.
 */
public enum EnumTier implements IStringSerializable {
	ZERO(0), ONE(1), TWO(2), THREE(3);

	private final int meta;

	EnumTier(int meta) {
		this.meta = meta;
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
}
