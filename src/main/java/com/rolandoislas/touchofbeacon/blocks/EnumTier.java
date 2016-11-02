package com.rolandoislas.touchofbeacon.blocks;

import net.minecraft.util.IStringSerializable;

/**
 * Created by Rolando on 10/31/2016.
 */
public enum EnumTier implements IStringSerializable {
	ZERO(0, 6, 0, new CraftingItems("blockGlass", "torch", "plankWood")),
	ONE(1, 10, 0, new CraftingItems("blockGlass", "torch", "stone")),
	TWO(2, 20, 0, new CraftingItems("blockGlass", "glowstone", "blockIron")),
	THREE(3, 20, 20, new CraftingItems("blockGlass", "sealantern", "blockDiamond"));

	private final int meta;
	private int foodLevel;
	private float saturationLevel;
	private CraftingItems craftingItems;

	EnumTier(int meta, int foodLevel, int saturationLevel, CraftingItems craftingItems) {
		this.meta = meta;
		this.foodLevel = foodLevel;
		this.saturationLevel = saturationLevel;
		this.craftingItems = craftingItems;
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

	public CraftingItems getCraftingItems() {
		return craftingItems;
	}

	public static class CraftingItems {
		private final String cover;
		private final String center;
		private final String base;

		CraftingItems(String cover, String center, String base) {
			this.cover = cover;
			this.center = center;
			this.base = base;
		}

		public String getCover() {
			return cover;
		}

		public String getCenter() {
			return center;
		}

		public String getBase() {
			return base;
		}
	}
}
