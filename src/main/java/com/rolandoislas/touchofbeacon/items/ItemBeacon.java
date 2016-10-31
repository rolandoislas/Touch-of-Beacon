package com.rolandoislas.touchofbeacon.items;

import com.rolandoislas.touchofbeacon.blocks.EnumTier;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

/**
 * Created by Rolando on 10/30/2016.
 */
public class ItemBeacon extends ItemBlock {
	public ItemBeacon(Block block) {
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
		return super.getUnlocalizedName() + "." + EnumTier.getTierFromMeta(item.getMetadata()).getUnlocalizedName();
	}
}
