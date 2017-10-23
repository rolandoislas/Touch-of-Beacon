package com.rolandoislas.touchofbeacon.util;

import com.rolandoislas.touchofbeacon.registry.Potions;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
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
	 * @param world world of block pos
	 * @param pos origin block
	 * @param radius radius of player search
	 * @param potency effect to apply
	 */
	@SuppressWarnings("unused")
	public static void applyFedToPlayersAround(World world, BlockPos pos, double radius, int potency) {
		applyEffectToPlayersAround(Potions.FOOD, world, pos, radius, potency);
	}

	/**
	 * Apply an ambient Fed potion effect to a player.
	 * The effect duration is 5 seconds + 5 per potency level.
	 * @param player player that will receive the effect
	 * @param potency tier of effect to apply
	 */
	@SuppressWarnings("unused")
	public static void applyFedToPlayer(EntityPlayer player, int potency) {
		applyEffectToPlayer(Potions.FOOD, player, potency);
	}

	/**
	 * Apply the Fed potion effect to players in a radius
	 * @param world world of block pos
	 * @param pos origin block
	 * @param radius radius of player search
	 * @param potency effect to apply
	 */
	@SuppressWarnings("unused")
	public static void applyQuenchedToPlayersAround(World world, BlockPos pos, double radius, int potency) {
		applyEffectToPlayersAround(Potions.QUENCH, world, pos, radius, potency);
	}

	/**
	 * Apply an ambient Fed potion effect to a player.
	 * The effect duration is 5 seconds + 5 per potency level.
	 * @param player player that will receive the effect
	 * @param potency tier of effect to apply
	 */
	@SuppressWarnings("unused")
	public static void applyQuenchedToPlayer(EntityPlayer player, int potency) {
		applyEffectToPlayer(Potions.QUENCH, player, potency);
	}

	/**
	 * Apply a potion effect to players in a radius
	 * @param world world of block pos
	 * @param pos origin block
	 * @param radius radius of player search
	 * @param potency effect to apply
	 */
	@SuppressWarnings("WeakerAccess")
	public static void applyEffectToPlayersAround(Potion potion, World world, BlockPos pos, double radius,
												  int potency) {
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		AxisAlignedBB axisalignedbb = new AxisAlignedBB(x, y, z, x + 1, y + 1, z + 1).expandXyz(radius);
		List<EntityPlayer> list = world.getEntitiesWithinAABB(EntityPlayer.class, axisalignedbb);

		for (EntityPlayer entityplayer : list) {
			applyEffectToPlayer(potion, entityplayer, potency);
		}
	}

	/**
	 * Apply an ambient potion effect to a player.
	 * The effect duration is 5 seconds + 5 per potency level.
	 * @param player player that will receive the effect
	 * @param potency tier of effect to apply
	 */
	@SuppressWarnings("WeakerAccess")
	public static void applyEffectToPlayer(Potion potion, EntityPlayer player, int potency) {
		PotionEffect currentEffect = player.getActivePotionEffect(potion);
		if (currentEffect == null || currentEffect.getIsAmbient())
			player.addPotionEffect(new PotionEffect(potion, (5 + 5 * potency) * 20,
					potency, true, true));
	}
}
