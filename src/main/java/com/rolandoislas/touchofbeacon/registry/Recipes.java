package com.rolandoislas.touchofbeacon.registry;

import com.rolandoislas.touchofbeacon.blocks.EnumFood;
import com.rolandoislas.touchofbeacon.blocks.EnumTier;
import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionHelper;
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
		// Satiety Potions
		PotionHelper.ItemPredicateInstance foodBlockPredicate = new PotionHelper.ItemPredicateInstance(ModItems.FOOD);
		PotionHelper.registerPotionTypeConversion(PotionTypes.AWKWARD, foodBlockPredicate, Potions.TYPE_FOOD);
		PotionHelper.registerPotionTypeConversion(Potions.TYPE_FOOD, foodBlockPredicate, Potions.TYPE_FOOD_1);
		PotionHelper.registerPotionTypeConversion(Potions.TYPE_FOOD_1, foodBlockPredicate, Potions.TYPE_FOOD_2);
		PotionHelper.registerPotionTypeConversion(Potions.TYPE_FOOD_2, foodBlockPredicate, Potions.TYPE_FOOD_3);
		PotionHelper.registerPotionTypeConversion(Potions.TYPE_FOOD_3, foodBlockPredicate, PotionTypes.THICK);

		// Levitation Potions
		PotionHelper.ItemPredicateInstance featherPredicate = new PotionHelper.ItemPredicateInstance(Items.FEATHER);
		PotionHelper.ItemPredicateInstance redstonePredicate = new PotionHelper.ItemPredicateInstance(Items.REDSTONE);
		PotionHelper.ItemPredicateInstance glowstonePredicate =
				new PotionHelper.ItemPredicateInstance(Items.GLOWSTONE_DUST);
		PotionHelper.registerPotionTypeConversion(PotionTypes.AWKWARD, featherPredicate, Potions.TYPE_LEVITATION);
		PotionHelper.registerPotionTypeConversion(Potions.TYPE_LEVITATION, redstonePredicate,
				Potions.TYPE_LEVITATION_LONG);
		PotionHelper.registerPotionTypeConversion(Potions.TYPE_LEVITATION, glowstonePredicate,
				Potions.TYPE_LEVITATION_STRONG);
		PotionHelper.registerPotionTypeConversion(Potions.TYPE_LEVITATION_STRONG, glowstonePredicate,
				Potions.TYPE_LEVITATION_EXTRA_STRONG);
	}
}
