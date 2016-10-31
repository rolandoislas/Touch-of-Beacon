package com.rolandoislas.touchofbeacon.blocks;

import com.rolandoislas.touchofbeacon.TouchOfBacon;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.List;

/**
 * Created by Rolando on 10/30/2016.
 */
public class BlockBeacon extends net.minecraft.block.BlockBeacon {
	public static final PropertyEnum<EnumTier> TIER_PROPERTY_ENUM = PropertyEnum.create("tier", EnumTier.class);
	public BlockBeacon() {
		super();
		this.setUnlocalizedName(TouchOfBacon.MODID + ".beacon");
		this.setRegistryName("beacon");
		this.setDefaultState(this.blockState.getBaseState().withProperty(TIER_PROPERTY_ENUM, EnumTier.ZERO));
	}

	@Override
	//@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list) {
		for (int i = 0; i < 4; i++)
			list.add(new ItemStack(itemIn, 1, i));
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, TIER_PROPERTY_ENUM);
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(TIER_PROPERTY_ENUM, EnumTier.getTierFromMeta(meta));
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(TIER_PROPERTY_ENUM).getMetadata();
	}

	@Override
	public int damageDropped(IBlockState state) {
		return state.getValue(TIER_PROPERTY_ENUM).getMetadata();
	}
}
