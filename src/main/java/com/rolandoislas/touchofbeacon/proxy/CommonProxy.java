package com.rolandoislas.touchofbeacon.proxy;

import com.rolandoislas.touchofbeacon.registry.*;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * Created by Rolando on 10/30/2016.
 */
public class CommonProxy {
	public void preInit(FMLPreInitializationEvent event) {
		ModBlocks.register();
		ModItems.registerItems();
		Potions.register();
	}

	public void init(FMLInitializationEvent event) {

	}

	public void postInit(FMLPostInitializationEvent event) {
		ModOreDictionary.register();
		Recipes.register();
		TileEntities.register();
	}
}
