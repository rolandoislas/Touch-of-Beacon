package com.rolandoislas.touchofbeacon.tileentity;

import com.rolandoislas.touchofbeacon.registry.ModOreDictionary;
import com.rolandoislas.touchofbeacon.registry.Potions;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.oredict.OreDictionary;

import java.util.List;

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
			int x = this.pos.getX();
			int y = this.pos.getY();
			int z = this.pos.getZ();
			AxisAlignedBB axisalignedbb = new AxisAlignedBB(x, y, z, x + 1, y + 1, z + 1).expandXyz(radius)
					.addCoord(0, this.worldObj.getHeight(), 0);
			List<EntityPlayer> list = this.worldObj.getEntitiesWithinAABB(EntityPlayer.class, axisalignedbb);

			for (EntityPlayer entityplayer : list) {
				entityplayer.addPotionEffect(new PotionEffect(Potions.FOOD,
						(5 + 5 * getBlockMetadata()) * 20, getBlockMetadata(), true, true));
			}
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
