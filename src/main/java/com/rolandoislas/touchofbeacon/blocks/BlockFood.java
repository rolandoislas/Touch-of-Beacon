package com.rolandoislas.touchofbeacon.blocks;

import com.rolandoislas.touchofbeacon.TouchOfBacon;
import com.rolandoislas.touchofbeacon.registry.ModCreativeTabs;
import net.minecraft.block.BlockSlime;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;

import java.util.List;

/**
 * Created by Rolando on 10/30/2016.
 */
public class BlockFood extends BlockSlime {
	public static final PropertyEnum<EnumFood> FOOD_PROPERTY_ENUM = PropertyEnum.create("food", EnumFood.class);

	public BlockFood() {
		super();
		this.setUnlocalizedName(TouchOfBacon.MODID + ".food");
		this.setRegistryName("food");
		this.setDefaultState(this.blockState.getBaseState().withProperty(FOOD_PROPERTY_ENUM, EnumFood.BEEF));
		this.setHardness(0.6f);
		this.setSoundType(SoundType.SLIME);
		this.setCreativeTab(ModCreativeTabs.MAIN);
	}

	@Override
	public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list) {
		for (EnumFood food : EnumFood.values())
			list.add(new ItemStack(itemIn, 1, food.getMeta()));
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, FOOD_PROPERTY_ENUM);
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(FOOD_PROPERTY_ENUM, EnumFood.getFoodFromMeta(meta));
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(FOOD_PROPERTY_ENUM).getMeta();
	}

	@Override
	public int damageDropped(IBlockState state) {
		return state.getValue(FOOD_PROPERTY_ENUM).getMeta();
	}

	@Override
	public boolean isToolEffective(String type, IBlockState state) {
		return type.equals("axe");
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return true;
	}

	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.SOLID;
	}
}
