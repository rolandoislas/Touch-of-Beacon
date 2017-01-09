package com.rolandoislas.touchofbeacon.registry;

import com.rolandoislas.touchofbeacon.TouchOfBacon;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * Created by Rolando on 11/2/2016.
 */
public class ModCreativeTabs {
	public static final CreativeTabs MAIN = new CreativeTabs(CreativeTabs.getNextID(), TouchOfBacon.MODID) {
		public ItemStack getTabIconItem() {
			return new ItemStack(Item.getItemFromBlock(ModBlocks.BEACON));
		}
	};
}
