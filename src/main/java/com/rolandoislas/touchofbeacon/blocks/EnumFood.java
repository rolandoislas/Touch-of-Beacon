package com.rolandoislas.touchofbeacon.blocks;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.IStringSerializable;

/**
 * Created by Rolando on 10/31/2016.
 */
public enum EnumFood implements IStringSerializable {
	BEEF(0, new CraftingItems(4, Items.COOKED_BEEF)),
	CHICKEN(1, new CraftingItems(4, Items.COOKED_CHICKEN)),
	FISH(2, new CraftingItems(4, Items.COOKED_FISH)),
	LAMB(3, new CraftingItems(4, Items.COOKED_MUTTON)),
	RABBIT(4, new CraftingItems(4, Items.COOKED_RABBIT)),
	BEEF_RAW(5, new CraftingItems(4, Items.BEEF)),
	CHICKEN_RAW(6, new CraftingItems(4, Items.CHICKEN)),
	FISH_RAW(7, new CraftingItems(4, Items.FISH)),
	LAMB_RAW(8, new CraftingItems(4, Items.MUTTON)),
	RABBIT_RAW(9, new CraftingItems(4, Items.RABBIT)),
	POTATO(10, new CraftingItems(9, Items.POTATO)),
	CARROT(11, new CraftingItems(9, Items.CARROT)),
	APPLE(12, new CraftingItems(9, Items.APPLE)),
	BEETROOT(13, new CraftingItems(9, Items.BEETROOT)),
	PORK(14, new CraftingItems(4, Items.COOKED_PORKCHOP)),
	PORK_RAW(15, new CraftingItems(4, Items.PORKCHOP));

	private final int meta;
	private CraftingItems cratingItems;

	EnumFood(int meta, CraftingItems craftingItems) {
		this.meta = meta;
		this.cratingItems = craftingItems;
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

	public CraftingItems getCratingItems() {
		return cratingItems;
	}

	public static class CraftingItems {
		private int amount;
		private Item item;

		CraftingItems(int amount, Item item) {
			this.amount = amount;
			this.item = item;
		}

		public int getAmount() {
			return amount;
		}

		public Item getItem() {
			return item;
		}
	}
}
