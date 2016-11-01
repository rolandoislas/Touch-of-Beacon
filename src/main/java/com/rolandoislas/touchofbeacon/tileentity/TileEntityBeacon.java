package com.rolandoislas.touchofbeacon.tileentity;

import com.rolandoislas.touchofbeacon.blocks.BlockFood;
import com.rolandoislas.touchofbeacon.blocks.EnumTier;
import com.rolandoislas.touchofbeacon.potion.PotionFood;
import com.rolandoislas.touchofbeacon.registry.Potions;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionAttackDamage;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.FoodStats;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

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
			isComplete = this.worldObj.getBlockState(new BlockPos(this.getPos().down())).getBlock()
					instanceof BlockFood;
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
					if (!(block instanceof BlockFood)) {
						isComplete = false;
						return;
					}
				}
			}
		}
		isComplete = true;
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
}
