package com.rolandoislas.touchofbeacon.blocks;

import com.rolandoislas.touchofbeacon.TouchOfBacon;
import com.rolandoislas.touchofbeacon.data.Constants;
import com.rolandoislas.touchofbeacon.registry.ModCreativeTabs;
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
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Loader;

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
		this.setCreativeTab(ModCreativeTabs.MAIN);
	}

	@Override
	//@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item itemIn, CreativeTabs tab, NonNullList<ItemStack> list) {
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
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player,
									EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		if (world.isRemote)
			return true;
		TileEntity beacon = world.getTileEntity(pos);
		if (!(beacon instanceof TileEntityBeacon))
			return false;
		if (!((TileEntityBeacon)beacon).isComplete())
			player.sendMessage(new TextComponentTranslation(
					TouchOfBacon.MODID + ".message.beacon.missing_base"
			));
		if (!((TileEntityBeacon)beacon).hasWater() && Loader.isModLoaded(Constants.MOD_ID_THOUGH_AS_NAILS))
			player.sendMessage(new TextComponentTranslation(
					TouchOfBacon.MODID + ".message.beacon.missing_water",
					getMetaFromState(world.getBlockState(pos)) + 1
			));
		return true;
	}
}
