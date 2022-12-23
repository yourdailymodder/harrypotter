package com.minecraftserverzone.harrypotter.setup.config;

import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;
import net.minecraftforge.common.ForgeConfigSpec.DoubleValue;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;

public final class HarryPotterModConfig {

	// CLIENT
	public static ConfigValue<String> HOTBAR;
	
	// COMMON
    public static IntValue[] DEATH_EATER = new IntValue[3];
    public static ConfigValue<String> DEATH_EATER_BIOME;
    
    public static IntValue[] TROLL = new IntValue[3];
    public static ConfigValue<String>  TROLL_BIOME;

    public static IntValue[] DEMENTOR = new IntValue[3];
    public static ConfigValue<String>  DEMENTOR_BIOME;
    
    public static DoubleValue DEMENTOR_SPAWN_PERCENT;
    
    public static IntValue[] INFERIUS = new IntValue[3];
    public static ConfigValue<String>  INFERIUS_BIOME;
    
    public static IntValue[] ACROMANTULA = new IntValue[3];
    public static ConfigValue<String>  ACROMANTULA_BIOME;
    
    
}
