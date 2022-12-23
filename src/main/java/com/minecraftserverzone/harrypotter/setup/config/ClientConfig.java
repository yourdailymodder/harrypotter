package com.minecraftserverzone.harrypotter.setup.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;

public final class ClientConfig {
	public final ConfigValue<String>  HOTBAR;

	ClientConfig(final ForgeConfigSpec.Builder builder) {
		builder.push("general");
        	HOTBAR = builder.comment("Hotbar placeholder").define("hotbar","0;0;0;0;0;0;0;0;0");
		builder.pop();
	}
}
