/*
 * Copyright (c) Forge Development LLC and contributors
 * SPDX-License-Identifier: LGPL-2.1-only
 */

package com.minecraftserverzone.harrypotter.setup;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.minecraftserverzone.harrypotter.HarryPotterMod;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings.SpawnerData;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ModifiableBiomeInfo.BiomeInfo.Builder;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = HarryPotterMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BiomeModifierTest
{
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = HarryPotterMod.MODID;
    public static final ResourceLocation ADD_FEATURES_TO_BIOMES_RL = new ResourceLocation(MODID, "harry_potter_spawns");

    /*
    @SuppressWarnings("unchecked")
	@SubscribeEvent
    public static void onGatherData(GatherDataEvent event) {
    	Object[][] SPAWNS = 
    		{
    			{"spawn_acromantula", ModTags.HAS_ACROMANTULA, Registrations.ACROMANTULA.get()},
    			{"spawn_death_eater", ModTags.HAS_DEATH_EATER, Registrations.DEATH_EATER.get()},
    			{"spawn_dementor", ModTags.HAS_DEMENTOR, Registrations.DEMENTOR.get()},
    			{"spawn_inferius", ModTags.HAS_INFERIUS, Registrations.INFERIUS.get()},
    			{"spawn_troll", ModTags.HAS_TROLL, Registrations.TROLL.get()}
    		};

        DataGenerator generator = event.getGenerator();
        final Path outputFolder = generator.getOutputFolder();
        final RegistryOps<JsonElement> ops = RegistryOps.create(JsonOps.INSTANCE, RegistryAccess.BUILTIN.get());
        final String directory = PackType.SERVER_DATA.getDirectory();

        // prepare to datagenerate our biome modifier
        final ResourceLocation biomeModifiersRegistryID = ForgeRegistries.Keys.BIOME_MODIFIERS.location();
        
        for(int i = 0; i < SPAWNS.length; i++) {
        	final String biomeModifierPathString = String.join("/", directory, MODID, biomeModifiersRegistryID.getNamespace(), biomeModifiersRegistryID.getPath(), SPAWNS[i][0] + ".json");
        	final Path biomeModifierPath = outputFolder.resolve(biomeModifierPathString);
            final BiomeModifier biomeModifiersBrownScorpion = new TestModifier(
            	new HolderSet.Named<>(ops.registry(Registry.BIOME_REGISTRY).get(), (TagKey<Biome>) SPAWNS[i][1]),
                new SpawnerData((EntityType<?>) SPAWNS[i][2], 5, 0, 2));

        if (event.includeServer()) {
        	generator.addProvider(true, new ModBiomeTags(generator, event.getExistingFileHelper()));
        }
        
        generator.addProvider(event.includeServer(), new DataProvider() {
            @Override
            public void run(final CachedOutput cache) throws IOException {
                BiomeModifier.DIRECT_CODEC.encodeStart(ops, biomeModifiersBrownScorpion)
                .resultOrPartial(msg -> LOGGER.error("Failed to encode {}: {}", biomeModifierPathString, msg)) // Log error on encode failure.
                .ifPresent(json -> { try { DataProvider.saveStable(cache, json, biomeModifierPath); } catch (IOException e) {}});
            }
            
            @Override
            public String getName() {
                return MODID + " data provider";
            }
        });
        }
    }*/

    public static record TestModifier(HolderSet<Biome> biomes, SpawnerData spawn) implements BiomeModifier {
        private static final RegistryObject<Codec<? extends BiomeModifier>> SERIALIZER = RegistryObject.create(ADD_FEATURES_TO_BIOMES_RL, ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, MODID);

        @Override
        public void modify(Holder<Biome> biome, Phase phase, Builder builder) {
            if (phase == Phase.ADD && this.biomes.contains(biome)) {
            	builder.getMobSpawnSettings().addSpawn(this.spawn.type.getCategory(), this.spawn);
            }
        }

        @Override
        public Codec<? extends BiomeModifier> codec() {
            return SERIALIZER.get();
        }

        public static Codec<TestModifier> makeCodec() {
            return RecordCodecBuilder.create(builder -> builder.group(
                    Biome.LIST_CODEC.fieldOf("biomes").forGetter(TestModifier::biomes),
                    SpawnerData.CODEC.fieldOf("spawn").forGetter(TestModifier::spawn)
                    ).apply(builder, TestModifier::new));
        }
    }
}