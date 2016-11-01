package com.rolandoislas.touchofbeacon.blocks;

import net.minecraft.util.IStringSerializable;

/**
 * Created by Rolando on 10/31/2016.
 */
public enum EnumFood implements IStringSerializable {
	BEEF(0), CHICKEN(1), FISH(2), LAMB(3), RABBIT(4),
	BEEF_RAW(5), CHICKEN_RAW(6), FISH_RAW(7), LAMB_RAW(8), RABBIT_RAW(9),
	POTATO(10), CARROT(11), APPLE(12), BEETROOT(13),
	PORK(14), PORK_RAW(15);

	private final int meta;

	EnumFood(int meta) {
		this.meta = meta;
	}

	@Override
	public String getName() {
		return this.name().toLowerCase();
	}

	public static EnumFood getFoodFromMeta(int meta) {
		for (EnumFood food : values())
			if (food.getMeta() == meta)
				return food;
		return BEEF;
	}

	public int getMeta() {
		return meta;
	}

	public String getUnlocalizedName() {
		return getName();
	}
}
