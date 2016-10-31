package com.rolandoislas.touchofbeacon.proxy;

import com.rolandoislas.touchofbeacon.registry.ModBlocks;
import com.rolandoislas.touchofbeacon.registry.ModItems;
import com.rolandoislas.touchofbeacon.registry.Recipes;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * Created by Rolando on 10/30/2016.
 */
public class CommonProxy {
	public void preInit(FMLPreInitializationEvent event) {
		ModBlocks.register();
		ModItems.register();
	}

	public void init(FMLInitializationEvent event) {

	}

	public void postInit(FMLPostInitializationEvent event) {
		Recipes.register();
	}
}
