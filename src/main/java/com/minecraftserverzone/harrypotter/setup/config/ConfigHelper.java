package com.minecraftserverzone.harrypotter.setup.config;

import net.minecraftforge.fml.config.ModConfig;

public final class ConfigHelper {
	
	public static void bakeClient(final ModConfig config) {
		HarryPotterModConfig.HOTBAR = ConfigHolder.CLIENT.HOTBAR;
	}
	
	public static void bakeCommon(final ModConfig config) {
		
		HarryPotterModConfig.DEATH_EATER = ConfigHolder.COMMON.DEATH_EATER;
		HarryPotterModConfig.DEATH_EATER_BIOME = ConfigHolder.COMMON.DEATH_EATER_BIOME;
		
		HarryPotterModConfig.TROLL = ConfigHolder.COMMON.TROLL;
		HarryPotterModConfig.TROLL_BIOME = ConfigHolder.COMMON.TROLL_BIOME;
		
		HarryPotterModConfig.DEMENTOR = ConfigHolder.COMMON.DEMENTOR;
		HarryPotterModConfig.DEMENTOR_BIOME = ConfigHolder.COMMON.DEMENTOR_BIOME;
		
		HarryPotterModConfig.DEMENTOR_SPAWN_PERCENT = ConfigHolder.COMMON.DEMENTOR_SPAWN_PERCENT;
		
		HarryPotterModConfig.INFERIUS = ConfigHolder.COMMON.INFERIUS;
		HarryPotterModConfig.INFERIUS_BIOME = ConfigHolder.COMMON.INFERIUS_BIOME;
		
		HarryPotterModConfig.ACROMANTULA = ConfigHolder.COMMON.ACROMANTULA;
		HarryPotterModConfig.ACROMANTULA_BIOME = ConfigHolder.COMMON.ACROMANTULA_BIOME;
		
		
	}
}
