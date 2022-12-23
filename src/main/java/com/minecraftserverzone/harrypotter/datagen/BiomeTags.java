package com.minecraftserverzone.harrypotter.datagen;

import com.minecraftserverzone.harrypotter.HarryPotterMod;
import com.minecraftserverzone.harrypotter.setup.Registrations;

import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

public class BiomeTags extends TagsProvider<Biome>{
	
	public BiomeTags(DataGenerator generator, ExistingFileHelper helper) {
		super(generator, BuiltinRegistries.BIOME, HarryPotterMod.MODID, helper);
	}

	@Override
	public String getName() {
		return "HarryPotter Tags";
	}
	
	@Override
    protected void addTags() {
        ForgeRegistries.BIOMES.getValues().forEach(biome -> {
        	tag(Registrations.HAS_WITCH_TOWER).add(biome);
        });
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
