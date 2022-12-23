package com.minecraftserverzone.harrypotter.setup.config;

import net.minecraftforge.fml.config.ModConfig;

public final class ConfigHelper {
	
	public static void bakeClient(final ModConfig config) {
		HarryPotterModConfig.HOTBAR = ConfigHolder.CLIENT.HOTBAR;
		HarryPotterModConfig.DEATH_EATER_MODEL = ConfigHolder.CLIENT.DEATH_EATER_MODEL;
	}
	
	public static void bakeCommon(final ModConfig config) {
		HarryPotterModConfig.DEMENTOR_SPAWN_PERCENT = ConfigHolder.COMMON.DEMENTOR_SPAWN_PERCENT;
	}
}
