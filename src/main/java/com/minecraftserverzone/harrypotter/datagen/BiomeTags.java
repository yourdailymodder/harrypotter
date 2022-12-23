package com.minecraftserverzone.harrypotter.datagen;

import java.util.concurrent.CompletableFuture;

import com.minecraftserverzone.harrypotter.HarryPotterMod;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.common.data.ExistingFileHelper;

public class BiomeTags extends TagsProvider<Biome>{
	
	public BiomeTags(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, ExistingFileHelper fileHelper) {
		super(output, Registries.BIOME, registries, HarryPotterMod.MODID, fileHelper);
	}

	@Override
	public String getName() {
		return "HarryPotter Tags";
	}
	
	@Override
    protected void addTags(Provider p_256380_) {
        /*ForgeRegistries.BIOMES.getValues().forEach(biome -> {
        	tag(ModTags.HAS_WITCH_TOWER).add(biome);
        	tag(ModTags.HAS_LABYRINTH).add(biome);
        	tag(ModTags.HAS_HIDDEN_BASEMENT).add(biome);
        });*/
    }
	
//	@Override
//	public void addTags() {
//		 System.out.println("add tags now");
//		 ForgeRegistries.BIOMES.getValues().forEach(biome -> {
//			 tag(Registrations.HAS_WITCH_TOWER).add(biome);
//			 System.out.println(biome.getRegistryName());
//		 });
//	}

}
