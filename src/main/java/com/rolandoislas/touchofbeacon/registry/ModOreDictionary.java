package com.rolandoislas.touchofbeacon.registry;

import com.rolandoislas.touchofbeacon.blocks.EnumFood;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rolando on 11/1/2016.
 */
public class ModOreDictionary {
	public static final String FOOD_BLOCK = "foodBlock";

	public static void register() {
		// Food block
		List<ItemStack> foodBlockItems = new ArrayList<ItemStack>();
		ModItems.FOOD.getSubItems(ModItems.FOOD, ModItems.FOOD.getCreativeTab(), foodBlockItems);
		for (ItemStack item : foodBlockItems) {
			OreDictionary.registerOre(FOOD_BLOCK, item);
			OreDictionary.registerOre(FOOD_BLOCK + getSubItemOreDictSuffix(item), item);
		}
	}

	private static String getSubItemOreDictSuffix(ItemStack item) {
		String suffix = "";
		String name = EnumFood.getFoodFromMeta(item.getMetadata()).getUnlocalizedName();
		String[] nameParts = name.split("_");
		for (String part : nameParts)
			suffix += part.substring(0, 1).toUpperCase() + part.substring(1);
		return suffix;
	}
}