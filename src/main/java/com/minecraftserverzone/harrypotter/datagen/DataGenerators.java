package com.minecraftserverzone.harrypotter.datagen;

import com.minecraftserverzone.harrypotter.HarryPotterMod;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(modid = HarryPotterMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        if (event.includeServer()) {
        	generator.addProvider(new BiomeTags(generator, event.getExistingFileHelper()));
        }
        if (event.includeClient()) {
        }
    }
}