package com.rolandoislas.touchofbeacon.registry;

import com.rolandoislas.touchofbeacon.TouchOfBacon;
import com.rolandoislas.touchofbeacon.blocks.EnumFood;
import com.rolandoislas.touchofbeacon.blocks.EnumTier;
import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
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
			ItemStack item = new ItemStack(ModItems.BEACON, 1, tier.getMetadata());
			ShapedOreRecipe recipe = new ShapedOreRecipe(new ResourceLocation(TouchOfBacon.MODID),
					item,
					"CCC", "CMC", "BBB",
					'C', craft.getCover(), 'M', craft.getCenter(), 'B', craft.getBase());
			recipe.setRegistryName(item.getUnlocalizedName());
			ForgeRegistries.RECIPES.register(recipe);
		}

		// Food blocks
		for (EnumFood food : EnumFood.values()) {
			EnumFood.CraftingItems craft = food.getCratingItems();
			Item[] items = new Item[craft.getAmount()];
			for (int slot = 0; slot < craft.getAmount(); slot++)
				items[slot] = craft.getItem();
			ItemStack item = new ItemStack(ModItems.FOOD, 1, food.getMeta());
			ShapelessOreRecipe blockRecipe = new ShapelessOreRecipe(new ResourceLocation(TouchOfBacon.MODID), item,
					items);
			blockRecipe.setRegistryName(String.format("%s.item", item.getUnlocalizedName()));
			ForgeRegistries.RECIPES.register(blockRecipe);
			// Block back to items
			ShapelessOreRecipe itemRecipe = new ShapelessOreRecipe(new ResourceLocation(TouchOfBacon.MODID),
					new ItemStack(craft.getItem(), craft.getAmount()), item);
			itemRecipe.setRegistryName(String.format("%s.block", item.getUnlocalizedName()));
			ForgeRegistries.RECIPES.register(itemRecipe);
		}
		// Satiety Potions
		PotionHelper.addMix(Potions.TYPE_FOOD, ModItems.FOOD, Potions.TYPE_FOOD);
		PotionHelper.addMix(Potions.TYPE_FOOD_1, ModItems.FOOD, Potions.TYPE_FOOD_1);
		PotionHelper.addMix(Potions.TYPE_FOOD_2, ModItems.FOOD, Potions.TYPE_FOOD_2);
		PotionHelper.addMix(Potions.TYPE_FOOD_3, ModItems.FOOD, Potions.TYPE_FOOD_3);

		// Levitation Potions
		PotionHelper.addMix(PotionTypes.AWKWARD, Items.FEATHER, Potions.TYPE_LEVITATION);
		PotionHelper.addMix(Potions.TYPE_LEVITATION, Items.REDSTONE, Potions.TYPE_LEVITATION_LONG);
		PotionHelper.addMix(Potions.TYPE_LEVITATION, Items.GLOWSTONE_DUST, Potions.TYPE_LEVITATION_STRONG);
		PotionHelper.addMix(Potions.TYPE_LEVITATION_STRONG, Items.GLOWSTONE_DUST,
				Potions.TYPE_LEVITATION_EXTRA_STRONG);
	}
}
