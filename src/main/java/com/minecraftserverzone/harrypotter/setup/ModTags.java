package com.minecraftserverzone.harrypotter.setup;

import com.minecraftserverzone.harrypotter.HarryPotterMod;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;

public class ModTags {

	//structure tags
	public static final TagKey<Biome> HAS_WITCH_TOWER = TagKey.create(Registries.BIOME, new ResourceLocation(HarryPotterMod.MODID, "has_structure/witch_tower"));
	public static final TagKey<Biome> HAS_LABYRINTH = TagKey.create(Registries.BIOME, new ResourceLocation(HarryPotterMod.MODID, "has_structure/labyrinth"));
	public static final TagKey<Biome> HAS_HIDDEN_BASEMENT = TagKey.create(Registries.BIOME, new ResourceLocation(HarryPotterMod.MODID, "has_structure/hidden_basement"));
	
	//spawn tags
	public static final TagKey<Biome> HAS_ACROMANTULA = TagKey.create(Registries.BIOME, new ResourceLocation(HarryPotterMod.MODID, "has_acromantula"));
	public static final TagKey<Biome> HAS_DEATH_EATER = TagKey.create(Registries.BIOME, new ResourceLocation(HarryPotterMod.MODID, "has_death_eater"));
	public static final TagKey<Biome> HAS_DEMENTOR = TagKey.create(Registries.BIOME, new ResourceLocation(HarryPotterMod.MODID, "has_dementor"));
	public static final TagKey<Biome> HAS_INFERIUS = TagKey.create(Registries.BIOME, new ResourceLocation(HarryPotterMod.MODID, "has_inferius"));
	public static final TagKey<Biome> HAS_TROLL = TagKey.create(Registries.BIOME, new ResourceLocation(HarryPotterMod.MODID, "has_troll"));
	
}
