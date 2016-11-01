package com.rolandoislas.touchofbeacon.proxy;

import com.rolandoislas.touchofbeacon.registry.ModItems;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * Created by Rolando on 10/30/2016.
 */
public class ClientProxy extends CommonProxy {
	@Override
	public void preInit(FMLPreInitializationEvent event) {
		super.preInit(event);
		ModItems.registerTextures();
	}
}
