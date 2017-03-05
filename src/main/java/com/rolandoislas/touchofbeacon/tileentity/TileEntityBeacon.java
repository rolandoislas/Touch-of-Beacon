package com.rolandoislas.touchofbeacon.tileentity;

import com.rolandoislas.touchofbeacon.api.TouchOfBeaconApi;
import com.rolandoislas.touchofbeacon.registry.ModOreDictionary;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.oredict.OreDictionary;

/**
 * Created by Rolando on 10/31/2016.
 */
public class TileEntityBeacon extends net.minecraft.tileentity.TileEntityBeacon {
	private boolean isComplete;

	@Override
	public void updateBeacon() {
		super.updateBeacon();
		if (this.world == null)
			return;
		checkBeaconBlocks();
		feedPlayers();
	}

	private void checkBeaconBlocks() {
		// Check if there is a path to the sky
		if (!this.world.canBlockSeeSky(this.getPos())) {
			isComplete = false;
			return;
		}
		// Check a single block - tier zero
		if (getBlockMetadata() == 0) {
			Block block = this.world.getBlockState(new BlockPos(this.getPos().down())).getBlock();
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
					Block block = this.world.getBlockState(checkPos).getBlock();
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
		if (item.isEmpty())
			return false;
		int[] ids = OreDictionary.getOreIDs(item);
		for (int id : ids)
			if (id == foodBlockId)
				return true;
		return false;
	}

	private void feedPlayers() {
		if (this.isComplete && !this.world.isRemote) {
			double radius = this.getBlockMetadata() * 10d + 10d;
			TouchOfBeaconApi.applyFedToPlayersAround(world, pos, radius, getBlockMetadata());
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
}
