package com.minecraftserverzone.harrypotter.datagen;

import com.minecraftserverzone.harrypotter.HarryPotterMod;

import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = HarryPotterMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {

    /*@SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        if (event.includeServer()) {
        	generator.addProvider(true, new BiomeTags(generator, event.getExistingFileHelper()));
        }
        if (event.includeClient()) {
        }
    }*/
}