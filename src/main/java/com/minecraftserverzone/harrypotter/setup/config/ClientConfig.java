package com.minecraftserverzone.harrypotter.setup.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;

public final class ClientConfig {
	public final ConfigValue<String>  HOTBAR;
	public final IntValue DEATH_EATER_MODEL;

	ClientConfig(final ForgeConfigSpec.Builder builder) {
		builder.push("general");
		this.HOTBAR = builder.comment("Hotbar placeholder").define("hotbar","0;0;0;0;0;0;0;0;0");
		this.DEATH_EATER_MODEL = builder.comment("Which model to use. 0 is default, 1 is the old model").defineInRange("death_eater_model", 0, 0, 1);
		builder.pop();
	}
}
