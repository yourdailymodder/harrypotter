package com.minecraftserverzone.harrypotter.setup.config;

import com.minecraftserverzone.harrypotter.HarryPotterMod;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;

public final class CommonConfig {
	public final ConfigValue<String>  HOTBAR;
	final ForgeConfigSpec.IntValue[] DEATH_EATER = new ForgeConfigSpec.IntValue[3];
	final ConfigValue<String>  DEATH_EATER_BIOME;
	final ForgeConfigSpec.IntValue[] TROLL = new ForgeConfigSpec.IntValue[3];
	final ConfigValue<String> TROLL_BIOME;
	final ForgeConfigSpec.IntValue[] DEMENTOR = new ForgeConfigSpec.IntValue[3];
	final ConfigValue<String> DEMENTOR_BIOME;
	
	final ForgeConfigSpec.IntValue[] INFERIUS = new ForgeConfigSpec.IntValue[3];
	final ConfigValue<String> INFERIUS_BIOME;
	final ForgeConfigSpec.IntValue[] ACROMANTULA = new ForgeConfigSpec.IntValue[3];
	final ConfigValue<String> ACROMANTULA_BIOME;
	
	final ForgeConfigSpec.DoubleValue DEMENTOR_SPAWN_PERCENT;
	
	CommonConfig(final ForgeConfigSpec.Builder builder) {
		builder.push("general");
        builder.comment("Spawn settings").push("spawnrate");
        	HOTBAR = builder.comment("Hotbar placeholder").define("hotbar","0;0;0;0;0;0;0;0;0");
		
        	DEATH_EATER_BIOME = builder.comment("\nBiome name where the Death Eaters should spawn - leave it blank if it should spawn everywhere!").define("biome_name_1","");
        	TROLL_BIOME = builder.comment("\nBiome name where the Trolls should spawn").define("biome_name_2","minecraft:swamp");
        	DEMENTOR_BIOME = builder.comment("\nBiome name where the Dementors should spawn").define("biome_name_3","");
			DEMENTOR_SPAWN_PERCENT = builder.comment("Spawn chance from killing villagers, golems or dementors.").translation(HarryPotterMod.MODID + ".config.dementor.spawn_from")
					.defineInRange("chance", 0.05d, 0, 1);
			INFERIUS_BIOME = builder.comment("\nBiome name where Inferi should spawn").define("biome_name_4","");
			ACROMANTULA_BIOME = builder.comment("\nBiome name where acromantulas should spawn").define("biome_name_5","minecraft:dark_forest");
			
        initMobSpawnRate(builder, "Death Eater", "death eater", 5, 0, 1, DEATH_EATER);
        
        initMobSpawnRate(builder, "Troll", "troll", 10, 0, 1, TROLL);
        
        initMobSpawnRate(builder, "Dementor", "dementor", 0, 0, 0, DEMENTOR);
        
        initMobSpawnRate(builder, "Inferius", "inferius", 0, 0, 0, INFERIUS);
        
        initMobSpawnRate(builder, "Acromantula", "acromantula", 10, 0, 1, ACROMANTULA);
        
		builder.pop();
	}
	
	public static void initMobSpawnRate(ForgeConfigSpec.Builder builder, String name, String pushname, int weight, int min, int max, ForgeConfigSpec.IntValue[] array) {
    	builder.comment(name).push(pushname);
    	array[0] = builder
                .comment("Spawn Chance")
                .translation(HarryPotterMod.MODID + ".config."+name+".weight")
                .defineInRange("weight", weight, 0, Integer.MAX_VALUE);
    	array[1] = builder
                .comment("Monster Spawn Minimum Number")
                .translation(HarryPotterMod.MODID + ".config."+name+".min")
                .defineInRange("min", min, 0, Integer.MAX_VALUE);
    	array[2] = builder
                .comment("Monster Spawn Maximum Number")
                .translation(HarryPotterMod.MODID + ".config."+name+".max")
                .defineInRange("max", max, 0, Integer.MAX_VALUE);
        builder.pop();
    }
}
