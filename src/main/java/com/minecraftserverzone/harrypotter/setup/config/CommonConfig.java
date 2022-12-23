package com.minecraftserverzone.harrypotter.setup.config;

import com.minecraftserverzone.harrypotter.HarryPotterMod;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;

public final class CommonConfig {
	public final ConfigValue<String>  HOTBAR;
	final ForgeConfigSpec.DoubleValue DEMENTOR_SPAWN_PERCENT;
	
	CommonConfig(final ForgeConfigSpec.Builder builder) {
		builder.push("general");
        builder.comment("Spawn settings").push("spawnrate");
        	HOTBAR = builder.comment("Hotbar placeholder").define("hotbar","0;0;0;0;0;0;0;0;0");
		DEMENTOR_SPAWN_PERCENT = builder.comment("Spawn chance from killing villagers, golems or dementors.").translation(HarryPotterMod.MODID + ".config.dementor.spawn_from")
					.defineInRange("chance", 0.05d, 0, 1);

		builder.pop();
	}
}
