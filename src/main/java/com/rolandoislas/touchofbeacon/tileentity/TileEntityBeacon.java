package com.rolandoislas.touchofbeacon.tileentity;

import com.rolandoislas.touchofbeacon.api.TouchOfBeaconApi;
import com.rolandoislas.touchofbeacon.data.Constants;
import com.rolandoislas.touchofbeacon.registry.ModOreDictionary;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.oredict.OreDictionary;

/**
 * Created by Rolando on 10/31/2016.
 */
public class TileEntityBeacon extends net.minecraft.tileentity.TileEntityBeacon {
	private boolean isComplete;

	@Override
	public void updateBeacon() {
		super.updateBeacon();
		if (this.worldObj == null)
			return;
		checkBeaconBlocks();
		feedPlayers();
		quenchPlayers();
	}

	private void quenchPlayers() {
		if (this.isComplete && !this.worldObj.isRemote && hasWater() &&
				Loader.isModLoaded(Constants.MOD_ID_THOUGH_AS_NAILS)) {
			TouchOfBeaconApi.applyQuenchedToPlayersAround(worldObj, pos, getRadius(), getBlockMetadata());
		}
	}

	public boolean hasWater() {
		int radius = getBlockMetadata() + 1;
		int diameter = radius * 2 + 1;
		BlockPos start = getPos().west(radius).north(radius);
		for (int z = 0; z < diameter; z++) {
			for (int x = 0; x < diameter; x++) {
				BlockPos checkPos = start.east(x).south(z);
				if (checkPos.equals(getPos()))
					continue;
				if (!worldObj.getBlockState(checkPos).getBlock().equals(Blocks.WATER))
					return false;
			}
		}
		return true;
	}

	private void checkBeaconBlocks() {
		// Check if there is a path to the sky
		if (!this.worldObj.canBlockSeeSky(this.getPos())) {
			isComplete = false;
			return;
		}
		// Check a single block - tier zero
		if (getBlockMetadata() == 0) {
			Block block = this.worldObj.getBlockState(new BlockPos(this.getPos().down())).getBlock();
			isComplete = isCompatibleBase(block);
			return;
		}
		// Check levels for tiers one through three
		for (int level = 1; level <= getBlockMetadata(); level++) {
			int levelSize = level * 2 + 1;
			int startX = this.getPos().getX() - (levelSize - 1) / 2;
			int startZ = this.getPos().getZ() - (levelSize - 1) / 2;
			int y = this.getPos().getY() - level;
			for (int x = 0; x < levelSize; x++) {
				for (int z = 0; z < levelSize; z++) {
					BlockPos checkPos = new BlockPos(startX + x, y, startZ + z);
					Block block = this.worldObj.getBlockState(checkPos).getBlock();
					if (!isCompatibleBase(block)) {
						isComplete = false;
						return;
					}
				}
			}
		}
		isComplete = true;
	}

	private boolean isCompatibleBase(Block block) {
		int foodBlockId = OreDictionary.getOreID(ModOreDictionary.FOOD_BLOCK);
		ItemStack item = new ItemStack(block);
		if (item.getItem() == null)
			return false;
		int[] ids = OreDictionary.getOreIDs(item);
		for (int id : ids)
			if (id == foodBlockId)
				return true;
		return false;
	}

	private void feedPlayers() {
		if (this.isComplete && !this.worldObj.isRemote) {
			double radius = this.getBlockMetadata() * 10d + 10d;
			TouchOfBeaconApi.applyFedToPlayersAround(worldObj, pos, radius, getBlockMetadata());
		}
	}

	@Override
	public float shouldBeamRender() {
			return isComplete ? 1 : 0;
	}

	@Override
	public AxisAlignedBB getRenderBoundingBox() {
		return INFINITE_EXTENT_AABB;
	}

	public double getRadius() {
		return getBlockMetadata() * 10d + 10d;
	}

	public boolean isComplete() {
		return isComplete;
	}
}
