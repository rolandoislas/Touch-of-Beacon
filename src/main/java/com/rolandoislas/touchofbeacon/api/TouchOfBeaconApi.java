package com.rolandoislas.touchofbeacon.api;

import com.rolandoislas.touchofbeacon.util.FoodUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Created by Rolando on 3/4/2017.
 */
public class TouchOfBeaconApi {
	/**
	 * Apply an ambient Fed potion effect to players in a radius.
	 * The effect duration is 5 seconds + 5 per potency level.
	 * @param world world of origin block
	 * @param pos origin block
	 * @param radius radius of player search
	 * @param potency tier of effect to apply
	 */
	public static void applyFedToPlayersAround(World world, BlockPos pos, double radius, int potency) {
		FoodUtil.applyFedToPlayersAround(world, pos, radius, potency);
	}

	/**
	 * Apply an ambient Fed potion effect to a player.
	 * The effect duration is 5 seconds + 5 per potency level.
	 * @param player player that will receive the effect
	 * @param potency tier of effect to apply
	 */
	public static void applyFedToPlayer(EntityPlayer player, int potency) {
		FoodUtil.applyFedToPlayer(player, potency);
	}
}
