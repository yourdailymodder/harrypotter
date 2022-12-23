package com.minecraftserverzone.harrypotter.datagen;

import java.util.concurrent.CompletableFuture;

import com.minecraftserverzone.harrypotter.HarryPotterMod;
import com.minecraftserverzone.harrypotter.setup.ModTags;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBiomeTags extends TagsProvider<Biome>{
	
	public ModBiomeTags(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, ExistingFileHelper fileHelper) {
		super(output, Registries.BIOME, registries, HarryPotterMod.MODID, fileHelper);
	}

	@Override
	public String getName() {
		return "Harry Potter Spawns";
	}
	
	@Override
    protected void addTags(Provider p_256380_) {
		tag(ModTags.HAS_ACROMANTULA).add(Biomes.DARK_FOREST);
		tag(ModTags.HAS_DEATH_EATER).add(Biomes.DARK_FOREST);
		tag(ModTags.HAS_DEMENTOR);
		tag(ModTags.HAS_INFERIUS);
		tag(ModTags.HAS_TROLL).add(Biomes.SWAMP).add(Biomes.MANGROVE_SWAMP);
    }
}
