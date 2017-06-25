package com.rolandoislas.touchofbeacon.registry;

import com.rolandoislas.touchofbeacon.TouchOfBacon;
import com.rolandoislas.touchofbeacon.tileentity.TileEntityBeacon;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Rolando on 10/31/2016.
 */
public class TileEntities {

	public static void register() {
		GameRegistry.registerTileEntity(TileEntityBeacon.class, String.format("%s.beacon", TouchOfBacon.MODID));
	}
}
