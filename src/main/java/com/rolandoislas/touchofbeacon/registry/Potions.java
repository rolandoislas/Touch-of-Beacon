package com.rolandoislas.touchofbeacon.registry;

import com.rolandoislas.touchofbeacon.TouchOfBacon;
import com.rolandoislas.touchofbeacon.potion.PotionFood;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionType;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Rolando on 10/31/2016.
 */
public class Potions {
	public static final Potion FOOD = new PotionFood();
	public static final PotionType TYPE_FOOD;
	public static final  PotionType TYPE_FOOD_1;
	public static final PotionType TYPE_FOOD_2;
	public static final PotionType TYPE_FOOD_3;
	public static final PotionType TYPE_LEVITATION;
	public static final PotionType TYPE_LEVITATION_LONG;
	public static final PotionType TYPE_LEVITATION_STRONG;
	public static final PotionType TYPE_LEVITATION_EXTRA_STRONG;

	static {
		TYPE_FOOD = new PotionType(new PotionEffect(FOOD, 12000)).setRegistryName(TouchOfBacon.MODID + ".food");
		TYPE_FOOD_1 = new PotionType(TouchOfBacon.MODID + ".food", new PotionEffect(FOOD, 6000, 1))
				.setRegistryName(TouchOfBacon.MODID + ".food.1");
		TYPE_FOOD_2 = new PotionType(TouchOfBacon.MODID + ".food", new PotionEffect(FOOD, 3600, 2))
				.setRegistryName(TouchOfBacon.MODID + ".food.2");
		TYPE_FOOD_3 = new PotionType(TouchOfBacon.MODID + ".food", new PotionEffect(FOOD, 1200, 3))
				.setRegistryName(TouchOfBacon.MODID + ".food.3");
		Potion levitation = Potion.getPotionFromResourceLocation("levitation");
		TYPE_LEVITATION = new PotionType(new PotionEffect(levitation, 900))
				.setRegistryName("levitation");
		TYPE_LEVITATION_LONG = new PotionType("levitation", new PotionEffect(levitation, 1800))
				.setRegistryName("long_levitation");
		TYPE_LEVITATION_STRONG = new PotionType("levitation", new PotionEffect(levitation, 420, 1))
				.setRegistryName("strong_levitation");
		TYPE_LEVITATION_EXTRA_STRONG = new PotionType("levitation", new PotionEffect(levitation, 40, 99))
				.setRegistryName("extra_strong_levitation");
	}

	public static void register() {
		// Potions
		ForgeRegistries.POTIONS.register(FOOD);

		// Potion Types (Causes Minecraft to register potion, splash, lingering, and arrow)
		ForgeRegistries.POTION_TYPES.register(TYPE_FOOD);
		ForgeRegistries.POTION_TYPES.register(TYPE_FOOD_1);
		ForgeRegistries.POTION_TYPES.register(TYPE_FOOD_2);
		ForgeRegistries.POTION_TYPES.register(TYPE_FOOD_3);

		// Levitation has a lang entry. Register it!
		try {
			ForgeRegistries.POTION_TYPES.register(TYPE_LEVITATION);
			ForgeRegistries.POTION_TYPES.register(TYPE_LEVITATION_LONG);
			ForgeRegistries.POTION_TYPES.register(TYPE_LEVITATION_STRONG);
			ForgeRegistries.POTION_TYPES.register(TYPE_LEVITATION_EXTRA_STRONG);
		} catch (Exception ignore) {}
	}
}
