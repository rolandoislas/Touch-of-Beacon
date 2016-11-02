package com.rolandoislas.touchofbeacon.registry;

import com.rolandoislas.touchofbeacon.blocks.EnumFood;
import com.rolandoislas.touchofbeacon.blocks.EnumTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

/**
 * Created by Rolando on 10/30/2016.
 */
public class Recipes {

	public static void register() {
		/* Item specifications to recipe should be chars not strings. */

		// Beacon
		for (EnumTier tier : EnumTier.values()) {
			EnumTier.CraftingItems craft = tier.getCraftingItems();
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.BEACON, 1, tier.getMetadata()),
					"CCC", "CMC", "BBB",
					'C', craft.getCover(), 'M', craft.getCenter(), 'B', craft.getBase()));
		}

		// Food blocks
		for (EnumFood food : EnumFood.values()) {
			EnumFood.CraftingItems craft = food.getCratingItems();
			Item[] items = new Item[craft.getAmount()];
			for (int slot = 0; slot < craft.getAmount(); slot++)
				items[slot] = craft.getItem();
			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.FOOD, 1, food.getMeta()), items));
			// Block back to items
			GameRegistry.addRecipe(new ShapelessOreRecipe(
					new ItemStack(craft.getItem(), craft.getAmount()),
					new ItemStack(ModItems.FOOD, 1, food.getMeta())));
		}
	}
}
