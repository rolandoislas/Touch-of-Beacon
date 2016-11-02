package com.rolandoislas.touchofbeacon.registry;

import com.rolandoislas.touchofbeacon.TouchOfBacon;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

/**
 * Created by Rolando on 11/2/2016.
 */
public class ModCreativeTabs {
	public static final CreativeTabs MAIN = new CreativeTabs(CreativeTabs.getNextID(), TouchOfBacon.MODID) {
		public Item getTabIconItem()
		{
			return Item.getItemFromBlock(ModBlocks.BEACON);
		}
	};
}
