package com.rolandoislas.touchofbeacon.registry;

import com.rolandoislas.touchofbeacon.TouchOfBacon;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionType;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.NonNullList;

/**
 * Created by Rolando on 11/2/2016.
 */
public class ModCreativeTabs {
	public static final CreativeTabs MAIN = new CreativeTabs(CreativeTabs.getNextID(), TouchOfBacon.MODID) {
		private Item[] potionContainers = new Item[]{
				Items.LINGERING_POTION,
				Items.TIPPED_ARROW,
				Items.SPLASH_POTION,
				Items.POTIONITEM
		};
		private PotionType[] potionTypes = new PotionType[]{
				Potions.TYPE_FOOD,
				Potions.TYPE_FOOD_1,
				Potions.TYPE_FOOD_2,
				Potions.TYPE_FOOD_3,
				Potions.TYPE_QUENCH,
				Potions.TYPE_QUENCH_1,
				Potions.TYPE_QUENCH_2,
				Potions.TYPE_QUENCH_3
		};

		public ItemStack getTabIconItem() {
			return new ItemStack(Item.getItemFromBlock(ModBlocks.BEACON));
		}

		@Override
		public void displayAllRelevantItems(NonNullList<ItemStack> items) {
			super.displayAllRelevantItems(items);
			displayAllRelevantPotions(items);
		}

		private void displayAllRelevantPotions(NonNullList<ItemStack> items) {
			for (Item potionContainer : potionContainers) {
				for (PotionType potionType : potionTypes) {
					items.add(PotionUtils.addPotionToItemStack(new ItemStack(potionContainer), potionType));
				}
			}
		}
	};
}
