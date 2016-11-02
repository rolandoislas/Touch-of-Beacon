package com.rolandoislas.touchofbeacon.blocks;

import com.rolandoislas.touchofbeacon.TouchOfBacon;
import com.rolandoislas.touchofbeacon.tileentity.TileEntityBeacon;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;
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
		this.setLightLevel(1);
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

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityBeacon();
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		return false;
	}
}
