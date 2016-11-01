package com.rolandoislas.touchofbeacon.items;

import com.rolandoislas.touchofbeacon.blocks.EnumFood;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

/**
 * Created by Rolando on 10/31/2016.
 */
public class ItemFood extends ItemBlock {
	public ItemFood(Block block) {
		super(block);
		this.setUnlocalizedName(block.getUnlocalizedName());
		this.setRegistryName(block.getRegistryName());
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
	}

	@Override
	public int getMetadata(int damage) {
		return damage;
	}

	@Override
	public String getUnlocalizedName(ItemStack item) {
		return super.getUnlocalizedName() + "." + EnumFood.getFoodFromMeta(item.getMetadata()).getUnlocalizedName();
	}
}
