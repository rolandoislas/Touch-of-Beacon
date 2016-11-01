package com.rolandoislas.touchofbeacon.registry;

import com.rolandoislas.touchofbeacon.blocks.BlockBeacon;
import com.rolandoislas.touchofbeacon.blocks.BlockFood;
import net.minecraft.block.Block;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Rolando on 10/30/2016.
 */
public class ModBlocks {
	public static final Block BEACON = new BlockBeacon();
	public static final Block FOOD = new BlockFood();

	public static void register() {
		GameRegistry.register(BEACON);
		GameRegistry.register(FOOD);
	}
}
