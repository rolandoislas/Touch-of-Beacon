package com.rolandoislas.touchofbeacon.registry;

import com.rolandoislas.touchofbeacon.blocks.EnumFood;
import com.rolandoislas.touchofbeacon.blocks.EnumTier;
import com.rolandoislas.touchofbeacon.items.ItemBeacon;
import com.rolandoislas.touchofbeacon.items.ItemFood;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Rolando on 10/30/2016.
 */
public class ModItems {
	public static final ItemBlock BEACON = new ItemBeacon(ModBlocks.BEACON);
	public static final ItemBlock FOOD = new ItemFood(ModBlocks.FOOD);

	public static void register() {
		registerItems();
		registerTextures();

	}

	private static void registerTextures() {
		for (EnumTier tier : EnumTier.values())
			ModelLoader.setCustomModelResourceLocation(BEACON, tier.getMetadata(),
					new ModelResourceLocation(BEACON.getRegistryName(), "tier=" + tier.getName()));
		for (EnumFood food : EnumFood.values())
			ModelLoader.setCustomModelResourceLocation(FOOD, food.getMeta(),
					new ModelResourceLocation(FOOD.getRegistryName(), "food=" + food.getName()));
	}

	private static void registerItems() {
		GameRegistry.register(BEACON);
		GameRegistry.register(FOOD);
	}
}
