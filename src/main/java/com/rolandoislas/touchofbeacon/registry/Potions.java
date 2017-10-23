package com.rolandoislas.touchofbeacon.registry;

import com.rolandoislas.touchofbeacon.TouchOfBacon;
import com.rolandoislas.touchofbeacon.potion.PotionFood;
import com.rolandoislas.touchofbeacon.potion.PotionQuench;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionType;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Rolando on 10/31/2016.
 */
public class Potions {
	public static final Potion FOOD = new PotionFood();
	public static final Potion QUENCH = new PotionQuench();
	public static final PotionType TYPE_FOOD;
	public static final PotionType TYPE_FOOD_1;
	public static final PotionType TYPE_FOOD_2;
	public static final PotionType TYPE_FOOD_3;
	public static final PotionType TYPE_QUENCH;
	public static final PotionType TYPE_QUENCH_1;
	public static final PotionType TYPE_QUENCH_2;
	public static final PotionType TYPE_QUENCH_3;
	public static PotionType TYPE_LEVITATION;
	public static PotionType TYPE_LEVITATION_LONG;
	public static PotionType TYPE_LEVITATION_STRONG;
	public static PotionType TYPE_LEVITATION_EXTRA_STRONG;



	static {
		TYPE_FOOD = new PotionType(new PotionEffect(FOOD, 12000)).setRegistryName(TouchOfBacon.MODID + ".food");
		TYPE_FOOD_1 = new PotionType(TouchOfBacon.MODID + ".food", new PotionEffect(FOOD, 6000, 1))
				.setRegistryName(TouchOfBacon.MODID + ".food.1");
		TYPE_FOOD_2 = new PotionType(TouchOfBacon.MODID + ".food", new PotionEffect(FOOD, 3600, 2))
				.setRegistryName(TouchOfBacon.MODID + ".food.2");
		TYPE_FOOD_3 = new PotionType(TouchOfBacon.MODID + ".food", new PotionEffect(FOOD, 1200, 3))
				.setRegistryName(TouchOfBacon.MODID + ".food.3");
		TYPE_QUENCH = new PotionType(new PotionEffect(QUENCH, 12000)).setRegistryName(TouchOfBacon.MODID + ".quench");
		TYPE_QUENCH_1 = new PotionType(TouchOfBacon.MODID + ".quench", new PotionEffect(QUENCH, 6000, 1))
				.setRegistryName(TouchOfBacon.MODID + ".quench.1");
		TYPE_QUENCH_2 = new PotionType(TouchOfBacon.MODID + ".quench", new PotionEffect(QUENCH, 3600, 2))
				.setRegistryName(TouchOfBacon.MODID + ".quench.2");
		TYPE_QUENCH_3 = new PotionType(TouchOfBacon.MODID + ".quench", new PotionEffect(QUENCH, 1200, 3))
				.setRegistryName(TouchOfBacon.MODID + ".quench.3");
		Potion levitation = Potion.getPotionFromResourceLocation("levitation");
		if (levitation != null) {
			TYPE_LEVITATION = new PotionType(new PotionEffect(levitation, 900))
					.setRegistryName("levitation");
			TYPE_LEVITATION_LONG = new PotionType("levitation", new PotionEffect(levitation, 1800))
					.setRegistryName("long_levitation");
			TYPE_LEVITATION_STRONG = new PotionType("levitation", new PotionEffect(levitation, 420, 1))
					.setRegistryName("strong_levitation");
			TYPE_LEVITATION_EXTRA_STRONG = new PotionType("levitation", new PotionEffect(levitation, 40, 99))
					.setRegistryName("extra_strong_levitation");
		}
	}

	public static void register() {
		// Potions
		GameRegistry.register(FOOD);
		GameRegistry.register(QUENCH);

		// Potion Types (Causes Minecraft to register potion, splash, lingering, and arrow)
		GameRegistry.register(TYPE_FOOD);
		GameRegistry.register(TYPE_FOOD_1);
		GameRegistry.register(TYPE_FOOD_2);
		GameRegistry.register(TYPE_FOOD_3);
		GameRegistry.register(TYPE_QUENCH);
		GameRegistry.register(TYPE_QUENCH_1);
		GameRegistry.register(TYPE_QUENCH_2);
		GameRegistry.register(TYPE_QUENCH_3);

		// Levitation has a lang entry. Register it!
		try {
			GameRegistry.register(TYPE_LEVITATION);
			GameRegistry.register(TYPE_LEVITATION_LONG);
			GameRegistry.register(TYPE_LEVITATION_STRONG);
			GameRegistry.register(TYPE_LEVITATION_EXTRA_STRONG);
		} catch (Exception ignore) {}
	}
}
