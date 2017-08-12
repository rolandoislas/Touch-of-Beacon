package com.rolandoislas.touchofbeacon.registry;

import net.minecraft.init.Blocks;
import net.minecraftforge.oredict.OreDictionary;

/**
 * Created by Rolando on 11/1/2016.
 */
public class ModOreDictionary {
	public static final String FOOD_BLOCK = "blockFood";

	public static void register() {
		// Register sea lantern with ore dict since it does not have an entry
		OreDictionary.registerOre("sealantern", Blocks.SEA_LANTERN);
		// Food block
		OreDictionary.registerOre(FOOD_BLOCK, ModBlocks.FOOD);
	}
}
