package com.rolandoislas.touchofbeacon.registry;

import com.rolandoislas.touchofbeacon.items.ItemBeacon;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Rolando on 10/30/2016.
 */
public class ModItems {
	public static final ItemBlock BEACON = new ItemBeacon(ModBlocks.BEACON);
	//public static final ItemBlock FOOD = new ItemBeacon(ModBlocks.FOOD);

	public static void register() {
		// Textures
		ModelLoader.setCustomModelResourceLocation(BEACON, 0, new ModelResourceLocation(
				BEACON.getRegistryName(), "inventory_0"));
		ModelLoader.setCustomModelResourceLocation(BEACON, 1, new ModelResourceLocation(
				BEACON.getRegistryName(), "inventory_1"));
		ModelLoader.setCustomModelResourceLocation(BEACON, 2, new ModelResourceLocation(
				BEACON.getRegistryName(), "inventory_2"));
		ModelLoader.setCustomModelResourceLocation(BEACON, 3, new ModelResourceLocation(
				BEACON.getRegistryName(), "inventory_3"));
		// Items
		GameRegistry.register(BEACON);
		//GameRegistry.register(FOOD);
	}
}
