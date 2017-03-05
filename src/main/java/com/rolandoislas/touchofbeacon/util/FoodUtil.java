package com.rolandoislas.touchofbeacon.util;

import com.rolandoislas.touchofbeacon.registry.Potions;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

/**
 * Created by Rolando on 3/4/2017.
 */
public class FoodUtil {
	/**
	 * Apply the Fed potion effect to players in a radius
	 * @param world
	 * @param pos origin block
	 * @param radius radius of player search
	 * @param potency effect to apply
	 */
	public static void applyFedToPlayersAround(World world, BlockPos pos, double radius, int potency) {
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		AxisAlignedBB axisalignedbb = new AxisAlignedBB(x, y, z, x + 1, y + 1, z + 1).expandXyz(radius)
				.addCoord(0, world.getHeight(), 0);
		List<EntityPlayer> list = world.getEntitiesWithinAABB(EntityPlayer.class, axisalignedbb);

		for (EntityPlayer entityplayer : list) {
			applyFedToPlayer(entityplayer, potency);
		}
	}

	/**
	 * Apply an ambient Fed potion effect to a player.
	 * The effect duration is 5 seconds + 5 per potency level.
	 * @param player player that will receive the effect
	 * @param potency tier of effect to apply
	 */
	public static void applyFedToPlayer(EntityPlayer player, int potency) {
		if (player.getActivePotionEffect(Potions.FOOD) == null ||
				player.getActivePotionEffect(Potions.FOOD).getIsAmbient())
			player.addPotionEffect(new PotionEffect(Potions.FOOD, (5 + 5 * potency) * 20,
					potency, true, true));
	}
}
