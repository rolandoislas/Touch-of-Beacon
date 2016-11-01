package com.rolandoislas.touchofbeacon.registry;

import com.rolandoislas.touchofbeacon.potion.PotionFood;
import net.minecraft.potion.Potion;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Rolando on 10/31/2016.
 */
public class Potions {
	public static final Potion FOOD = new PotionFood();

	public static void register() {
		GameRegistry.register(FOOD);
	}
}
