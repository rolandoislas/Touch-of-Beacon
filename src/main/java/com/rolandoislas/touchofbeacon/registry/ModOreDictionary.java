package com.rolandoislas.touchofbeacon.registry;

import com.rolandoislas.touchofbeacon.blocks.EnumFood;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rolando on 11/1/2016.
 */
public class ModOreDictionary {
	public static final String FOOD_BLOCK = "blockFood";

	public static void register() {
		// Register sea lantern with ore dict since it does not have an entry
		OreDictionary.registerOre("sealantern", Blocks.SEA_LANTERN);
		// Food block
		NonNullList<ItemStack> foodBlockItems = NonNullList.create();
		ModItems.FOOD.getSubItems(ModItems.FOOD.getCreativeTab(), foodBlockItems);
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
