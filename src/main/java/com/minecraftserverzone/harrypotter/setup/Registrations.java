package com.minecraftserverzone.harrypotter.setup;

import com.minecraftserverzone.harrypotter.HarryPotterMod;
import com.minecraftserverzone.harrypotter.blocks.Lumos;
import com.minecraftserverzone.harrypotter.blocks.SimpleLightBlockEntity;
import com.minecraftserverzone.harrypotter.broomsticks.BroomStick;
import com.minecraftserverzone.harrypotter.items.BroomStickItem;
import com.minecraftserverzone.harrypotter.items.MaraudersMapItem;
import com.minecraftserverzone.harrypotter.items.WandItem;
import com.minecraftserverzone.harrypotter.mobs.acromantula.Acromantula;
import com.minecraftserverzone.harrypotter.mobs.death_eater.DeathEater;
import com.minecraftserverzone.harrypotter.mobs.dementor.Dementor;
import com.minecraftserverzone.harrypotter.mobs.inferius.Inferius;
import com.minecraftserverzone.harrypotter.mobs.patronus_deer.PatronusDeer;
import com.minecraftserverzone.harrypotter.mobs.troll.Troll;
import com.minecraftserverzone.harrypotter.spells.accio.Accio;
import com.minecraftserverzone.harrypotter.spells.alarte_ascandare.AlarteAscandare;
import com.minecraftserverzone.harrypotter.spells.aqua_eructo.AquaEructo;
import com.minecraftserverzone.harrypotter.spells.aqua_eructo.AquaEructoSpawner;
import com.minecraftserverzone.harrypotter.spells.avada_kedavra.AvadaKedavra;
import com.minecraftserverzone.harrypotter.spells.avis.Avis;
import com.minecraftserverzone.harrypotter.spells.avis.Birds;
import com.minecraftserverzone.harrypotter.spells.confringo.Confringo;
import com.minecraftserverzone.harrypotter.spells.depulso.Depulso;
import com.minecraftserverzone.harrypotter.spells.episkey.Episkey;
import com.minecraftserverzone.harrypotter.spells.evanesco.Evanesco;
import com.minecraftserverzone.harrypotter.spells.expecto_patronum.ExpectoPatronum;
import com.minecraftserverzone.harrypotter.spells.expelliarmus.Expelliarmus;
import com.minecraftserverzone.harrypotter.spells.finite.Finite;
import com.minecraftserverzone.harrypotter.spells.fire_storm.FireStorm;
import com.minecraftserverzone.harrypotter.spells.fire_storm.FireStormSpawner;
import com.minecraftserverzone.harrypotter.spells.fumos.Fumos;
import com.minecraftserverzone.harrypotter.spells.glacius.Glacius;
import com.minecraftserverzone.harrypotter.spells.glacius.IceEntity;
import com.minecraftserverzone.harrypotter.spells.incendio.Incendio;
import com.minecraftserverzone.harrypotter.spells.melofors.Melofors;
import com.minecraftserverzone.harrypotter.spells.mobilicorpus.BodyMoverEntity;
import com.minecraftserverzone.harrypotter.spells.mobilicorpus.Mobilicorpus;
import com.minecraftserverzone.harrypotter.spells.reparo.Reparo;
import com.minecraftserverzone.harrypotter.spells.sectumsempra.Sectumsempra;
import com.minecraftserverzone.harrypotter.spells.wingardium_leviosa.WingardiumLeviosa;
import com.minecraftserverzone.harrypotter.worldgen.structures.WitchTowerStructure;

import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class Registrations {

	public static final CreativeModeTab CUSTOM_TAB = new CreativeModeTab("harrypotter") {
		@Override
		public ItemStack makeIcon() {
			return new ItemStack(Registrations.APPRENTICE_WAND.get());
		}
    };
    
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, HarryPotterMod.MODID);
	public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, HarryPotterMod.MODID);
	public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, HarryPotterMod.MODID);
	public static final DeferredRegister<SoundEvent> SOUNDS  = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, HarryPotterMod.MODID);
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, HarryPotterMod.MODID);
	private static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, HarryPotterMod.MODID);
	private static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, HarryPotterMod.MODID);
	private static final DeferredRegister<StructureFeature<?>> STRUCTURES = DeferredRegister.create(ForgeRegistries.STRUCTURE_FEATURES, HarryPotterMod.MODID);


	
	public static void init() {
		BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
		ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());	
		PARTICLE_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());
		SOUNDS.register(FMLJavaModLoadingContext.get().getModEventBus());	
		ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
		EFFECTS.register(FMLJavaModLoadingContext.get().getModEventBus());
		BLOCK_ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
		STRUCTURES.register(FMLJavaModLoadingContext.get().getModEventBus());
	}

	//tags
	public static final TagKey<Biome> HAS_WITCH_TOWER = TagKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(HarryPotterMod.MODID, "has_structure/witch_tower"));
	
	//structures
	public static final RegistryObject<StructureFeature<JigsawConfiguration>> WITCH_TOWER = 
			STRUCTURES.register("witch_tower", WitchTowerStructure::new);
	public static final RegistryObject<StructureFeature<JigsawConfiguration>> LABYRINTH = 
			STRUCTURES.register("labyrinth", WitchTowerStructure::new);
	public static final RegistryObject<StructureFeature<JigsawConfiguration>> HIDDEN_BASEMENT = 
			STRUCTURES.register("hidden_basement", WitchTowerStructure::new);
	
	//sound effects
	public static final RegistryObject<SoundEvent> DEFAULT_SPELL = SOUNDS.register(
			"spell.harrypotter.default", () -> new SoundEvent(new ResourceLocation(HarryPotterMod.MODID, "spell.harrypotter.default")));
	
	public static final RegistryObject<SoundEvent> SMALL_HEAL = SOUNDS.register(
			"spell.harrypotter.sheal", () -> new SoundEvent(new ResourceLocation(HarryPotterMod.MODID, "spell.harrypotter.sheal")));
	public static final RegistryObject<SoundEvent> MEDIUM_HEAL = SOUNDS.register(
			"spell.harrypotter.mheal", () -> new SoundEvent(new ResourceLocation(HarryPotterMod.MODID, "spell.harrypotter.mheal")));
	
	public static final RegistryObject<SoundEvent> SMOKE = SOUNDS.register(
			"spell.harrypotter.smoke", () -> new SoundEvent(new ResourceLocation(HarryPotterMod.MODID, "spell.harrypotter.smoke")));
	public static final RegistryObject<SoundEvent> ICE = SOUNDS.register(
			"spell.harrypotter.ice", () -> new SoundEvent(new ResourceLocation(HarryPotterMod.MODID, "spell.harrypotter.ice")));
	public static final RegistryObject<SoundEvent> BUFF = SOUNDS.register(
			"spell.harrypotter.magic3", () -> new SoundEvent(new ResourceLocation(HarryPotterMod.MODID, "spell.harrypotter.magic3")));
	public static final RegistryObject<SoundEvent> MAGIC_SPELL6 = SOUNDS.register(
			"spell.harrypotter.magic6", () -> new SoundEvent(new ResourceLocation(HarryPotterMod.MODID, "spell.harrypotter.magic6")));
	public static final RegistryObject<SoundEvent> MAGIC_SPELL7 = SOUNDS.register(
			"spell.harrypotter.magic7", () -> new SoundEvent(new ResourceLocation(HarryPotterMod.MODID, "spell.harrypotter.magic7")));
	
	public static final RegistryObject<SoundEvent> SPELL3 = SOUNDS.register(
			"spell.harrypotter.spell3", () -> new SoundEvent(new ResourceLocation(HarryPotterMod.MODID, "spell.harrypotter.spell3")));
	public static final RegistryObject<SoundEvent> FIREBALL_SOUND1 = SOUNDS.register(
			"spell.harrypotter.fireball", () -> new SoundEvent(new ResourceLocation(HarryPotterMod.MODID, "spell.harrypotter.fireball")));
	public static final RegistryObject<SoundEvent> SPARKS = SOUNDS.register(
			"spell.harrypotter.sparks", () -> new SoundEvent(new ResourceLocation(HarryPotterMod.MODID, "spell.harrypotter.sparks")));
	public static final RegistryObject<SoundEvent> EXPLOSION = SOUNDS.register(
			"spell.harrypotter.explosion", () -> new SoundEvent(new ResourceLocation(HarryPotterMod.MODID, "spell.harrypotter.explosion")));
	public static final RegistryObject<SoundEvent> EXPLOSION2 = SOUNDS.register(
			"spell.harrypotter.explosion2", () -> new SoundEvent(new ResourceLocation(HarryPotterMod.MODID, "spell.harrypotter.explosion2")));
	
	public static final RegistryObject<Item> APPRENTICE_WAND = ITEMS.register("apprentice_wand", ()-> new WandItem(new Item.Properties().stacksTo(1).tab(CUSTOM_TAB)));
	public static final RegistryObject<Item> MARAUDERS_MAP = ITEMS.register("marauders_map", ()-> new MaraudersMapItem(new Item.Properties().tab(CUSTOM_TAB)));
	
	//effects
	public static final RegistryObject<MobEffect> WINGARDIUM_LEVIOSA_EFFECT =
			EFFECTS.register("wingardium_leviosa_effect", ()-> new ModMobEffect(MobEffectCategory.HARMFUL, 0xffff54));
	
	
	//blocks
	public static final RegistryObject<Block> GLOWING_AIR = BLOCKS.register("lumos", ()-> 
	new Lumos(BlockBehaviour.Properties.of(Material.AIR)
			.noCollission()
			.randomTicks()
			.noDrops()
			.air()
			.dynamicShape()
			.lightLevel((p_152605_) -> {
			      return 15;
			   })
			));
	
	public static final RegistryObject<BlockEntityType<SimpleLightBlockEntity>> GLOWING_AIR_BLOCK_ENTITY =
			BLOCK_ENTITIES.register("lumos", ()-> BlockEntityType.Builder.of(SimpleLightBlockEntity::new, GLOWING_AIR.get()).build(null));
	
	
	public static final RegistryObject<Item> GLOWING_AIR_ITEM = ITEMS.register("lumos", () -> new BlockItem(GLOWING_AIR.get(), new Item.Properties().tab(CUSTOM_TAB)));

	
	//spells
	//accio
	public static final RegistryObject<EntityType<Accio>> ACCIO = ENTITIES.register("accio", () -> EntityType.Builder.<Accio>of(Accio::new, MobCategory.MISC)
			.sized(0.3f, 0.3f)
			.clientTrackingRange(4)
			.updateInterval(10)
			.build("accio")); 

	//aqua eructo
	public static final RegistryObject<EntityType<AquaEructo>> AQUA_ERUCTO = ENTITIES.register("aqua_eructo", () -> EntityType.Builder.<AquaEructo>of(AquaEructo::new, MobCategory.MISC)
			.sized(0.3f, 0.3f)
			.clientTrackingRange(4)
			.updateInterval(10)
			.build("aqua_eructo")); 
	

	//aqua eructo
	public static final RegistryObject<EntityType<AquaEructoSpawner>> AQUA_ERUCTO_SPAWNER = ENTITIES.register("aqua_eructo_spawner", () -> EntityType.Builder.<AquaEructoSpawner>of(AquaEructoSpawner::new, MobCategory.MISC)
			.sized(0.01f, 0.01f)
			.clientTrackingRange(4)
			.updateInterval(10)
			.build("aqua_eructo_spawner"));
 
	//avada_kedavra
	public static final RegistryObject<EntityType<AvadaKedavra>> AVADA_KEDAVRA = ENTITIES.register("avada_kedavra", () -> EntityType.Builder.<AvadaKedavra>of(AvadaKedavra::new, MobCategory.MISC)
			.sized(0.1f, 0.1f)
			.clientTrackingRange(4)
			.updateInterval(10)
			.build("avada_kedavra")); 
	
	//avis
	public static final RegistryObject<EntityType<Avis>> AVIS = ENTITIES.register("avis", () -> EntityType.Builder.<Avis>of(Avis::new, MobCategory.MISC)
			.sized(0.4f, 0.4f)
			.clientTrackingRange(4)
			.updateInterval(10)
			.build("avis")); 
	
	//birds
	public static final RegistryObject<EntityType<Birds>> BIRDS = ENTITIES.register("birds", () -> EntityType.Builder.<Birds>of(Birds::new, MobCategory.MISC)
			.sized(0.01f, 0.01f)
			.clientTrackingRange(4)
			.updateInterval(10)
			.build("birds")); 
	
	//confringo
	public static final RegistryObject<EntityType<Confringo>> CONFRINGO = ENTITIES.register("confringo", () -> EntityType.Builder.<Confringo>of(Confringo::new, MobCategory.MISC)
			.sized(0.2f, 0.2f)
			.clientTrackingRange(4)
			.updateInterval(10)
			.build("confringo"));
	//depulso
	public static final RegistryObject<EntityType<Depulso>> DEPULSO = ENTITIES.register("depulso", () -> EntityType.Builder.<Depulso>of(Depulso::new, MobCategory.MISC)
			.sized(0.3f, 0.3f)
			.clientTrackingRange(4)
			.updateInterval(10)
			.build("depulso"));

	//fumos
	public static final RegistryObject<EntityType<Fumos>> FUMOS = ENTITIES.register("fumos", () -> EntityType.Builder.<Fumos>of(Fumos::new, MobCategory.MISC)
			.sized(0.01f, 0.01f)
			.clientTrackingRange(4)
			.updateInterval(10)
			.build("fumos"));

	//expelliarmus
	public static final RegistryObject<EntityType<Expelliarmus>> EXPELLIARMUS = ENTITIES.register("expelliarmus", () -> EntityType.Builder.<Expelliarmus>of(Expelliarmus::new, MobCategory.MISC)
			.sized(0.2f, 0.2f)
			.clientTrackingRange(4)
			.updateInterval(10)
			.build("expelliarmus"));
	
	//incendio
	public static final RegistryObject<EntityType<Incendio>> INCENDIO = ENTITIES.register("incendio", () -> EntityType.Builder.<Incendio>of(Incendio::new, MobCategory.MISC)
			.sized(0.25f, 0.25f)
			.clientTrackingRange(4)
			.updateInterval(10)
			.build("incendio"));
	
	//glacius
	public static final RegistryObject<EntityType<Glacius>> GLACIUS = ENTITIES.register("glacius", () -> EntityType.Builder.<Glacius>of(Glacius::new, MobCategory.MISC)
			.sized(0.25f, 0.25f)
			.clientTrackingRange(4)
			.updateInterval(10)
			.build("glacius"));

	//ice_entity
	public static final RegistryObject<EntityType<IceEntity>> ICE_ENTITY = ENTITIES.register("ice_entity", () -> EntityType.Builder.<IceEntity>of(IceEntity::new, MobCategory.MISC)
			.sized(0.01f, 0.01f)
			.clientTrackingRange(4)
			.updateInterval(10)
			.build("ice_entity"));
	
	//melofors
	public static final RegistryObject<EntityType<Melofors>> MELOFORS = ENTITIES.register("melofors", () -> EntityType.Builder.<Melofors>of(Melofors::new, MobCategory.MISC)
			.sized(0.25f, 0.25f)
			.clientTrackingRange(4)
			.updateInterval(10)
			.build("melofors"));

	//Sectumsempra
	public static final RegistryObject<EntityType<Sectumsempra>> SECTUMSEMPRA = ENTITIES.register("sectumsempra", () -> EntityType.Builder.<Sectumsempra>of(Sectumsempra::new, MobCategory.MISC)
			.sized(0.3f, 0.3f)
			.clientTrackingRange(4)
			.updateInterval(10)
			.build("sectumsempra"));

	public static final RegistryObject<EntityType<Mobilicorpus>> MOBILICORPUS = ENTITIES.register("mobilicorpus", () -> EntityType.Builder.<Mobilicorpus>of(Mobilicorpus::new, MobCategory.MISC)
			.sized(0.2f, 0.2f)
			.clientTrackingRange(4)
			.updateInterval(10)
			.build("mobilicorpus"));
	
	public static final RegistryObject<EntityType<BodyMoverEntity>> BODY_MOVER = ENTITIES.register("bodymoverentity", () -> EntityType.Builder.<BodyMoverEntity>of(BodyMoverEntity::new, MobCategory.MISC)
			.sized(0.01f, 0.01f)
			.clientTrackingRange(4)
			.updateInterval(10)
			.build("bodymoverentity"));

	public static final RegistryObject<EntityType<Reparo>> REPARO = ENTITIES.register("reparo", () -> EntityType.Builder.<Reparo>of(Reparo::new, MobCategory.MISC)
			.sized(0.2f, 0.2f)
			.clientTrackingRange(4)
			.updateInterval(10)
			.build("reparo"));
	
	public static final RegistryObject<EntityType<WingardiumLeviosa>> WINGARDIUM_LEVIOSA = ENTITIES.register("wingardium_leviosa", () -> EntityType.Builder.<WingardiumLeviosa>of(WingardiumLeviosa::new, MobCategory.MISC)
			.sized(0.2f, 0.2f)
			.clientTrackingRange(4)
			.updateInterval(10)
			.build("wingardium_leviosa"));

	public static final RegistryObject<EntityType<ExpectoPatronum>> EXPECTO_PATRONUM = ENTITIES.register("expecto_patronum", () -> EntityType.Builder.<ExpectoPatronum>of(ExpectoPatronum::new, MobCategory.MISC)
			.sized(0.25f, 0.25f)
			.clientTrackingRange(4)
			.updateInterval(10)
			.build("expecto_patronum"));

	public static final RegistryObject<EntityType<Episkey>> EPISKEY = ENTITIES.register("episkey", () -> EntityType.Builder.<Episkey>of(Episkey::new, MobCategory.MISC)
			.sized(0.2f, 0.2f)
			.clientTrackingRange(4)
			.updateInterval(10)
			.build("episkey"));

	public static final RegistryObject<EntityType<AlarteAscandare>> ALARTE_ASCANDARE = ENTITIES.register("alarte_ascandare", () -> EntityType.Builder.<AlarteAscandare>of(AlarteAscandare::new, MobCategory.MISC)
			.sized(0.2f, 0.2f)
			.clientTrackingRange(4)
			.updateInterval(10)
			.build("alarte_ascandare"));
	
	public static final RegistryObject<EntityType<Finite>> FINITE = ENTITIES.register("finite", () -> EntityType.Builder.<Finite>of(Finite::new, MobCategory.MISC)
			.sized(0.5f, 0.5f)
			.clientTrackingRange(4)
			.updateInterval(10)
			.build("finite"));
	
	public static final RegistryObject<EntityType<Evanesco>> EVANESCO = ENTITIES.register("evanesco", () -> EntityType.Builder.<Evanesco>of(Evanesco::new, MobCategory.MISC)
			.sized(0.25f, 0.25f)
			.clientTrackingRange(4)
			.updateInterval(10)
			.build("evanesco"));
	
	public static final RegistryObject<EntityType<FireStorm>> FIRE_STORM = ENTITIES.register("fire_storm", () -> EntityType.Builder.<FireStorm>of(FireStorm::new, MobCategory.MISC)
			.sized(0.25f, 0.25f)
			.clientTrackingRange(4)
			.updateInterval(10)
			.build("fire_storm"));
	
	public static final RegistryObject<EntityType<FireStormSpawner>> FIRE_STORM_SPAWNER = ENTITIES.register("fire_storm_spawner", () -> EntityType.Builder.<FireStormSpawner>of(FireStormSpawner::new, MobCategory.MISC)
			.sized(0.01f, 0.01f)
			.clientTrackingRange(4)
			.updateInterval(10)
			.build("fire_storm_spawner"));
	
	/**TODO NEW SPELLS*/
	
	//mobs
	public static final RegistryObject<EntityType<PatronusDeer>> PATRONUS_DEER = ENTITIES.register("patronus_deer", () -> EntityType.Builder.of(PatronusDeer::new, MobCategory.MISC)
            .sized(0.5f, 0.5f)
            .fireImmune()
            .clientTrackingRange(8)
            .build("patronus_deer"));

	//dementor
	public static final RegistryObject<EntityType<Dementor>> DEMENTOR = ENTITIES.register("dementor", () -> EntityType.Builder.of(Dementor::new, MobCategory.MONSTER)
	        .sized(1f, 2f)
	        .fireImmune()
	        .clientTrackingRange(8)
	        .build("dementor"));
	public static final RegistryObject<Item> DEMENTOR_EGG = ITEMS.register("dementor_spawn_egg",
			() -> new ForgeSpawnEggItem(DEMENTOR, 0x5b6669, 0x152326,
			new Item.Properties().tab(CUSTOM_TAB)));
	
	//death eaters
	public static final RegistryObject<EntityType<DeathEater>> DEATH_EATER = ENTITIES.register("death_eater", () -> EntityType.Builder.of(DeathEater::new, MobCategory.MONSTER)
	        .sized(0.8f, 1.75f)
	        .clientTrackingRange(8)
	        .build("death_eater"));
	public static final RegistryObject<Item> DEATH_EATER_EGG = ITEMS.register("death_eater_spawn_egg",
			() -> new ForgeSpawnEggItem(DEATH_EATER, 0x440059, 0x212121,
			new Item.Properties().tab(CUSTOM_TAB)));

	//troll
	public static final RegistryObject<EntityType<Troll>> TROLL = ENTITIES.register("troll", () -> EntityType.Builder.of(Troll::new, MobCategory.MONSTER)
	        .sized(1.4f, 2.8f)
	        .clientTrackingRange(8)
	        .build("troll"));
	public static final RegistryObject<Item> TROLL_EGG = ITEMS.register("troll_spawn_egg",
			() -> new ForgeSpawnEggItem(TROLL, 0x8c824d, 0xb0b58d,
			new Item.Properties().tab(CUSTOM_TAB)));

//inferius
	public static final RegistryObject<EntityType<Inferius>> INFERIUS = ENTITIES.register("inferius", () -> EntityType.Builder.of(Inferius::new, MobCategory.MONSTER)
	        .sized(0.6F, 1.99F)
	        .clientTrackingRange(8)
	        .build("inferius"));
	public static final RegistryObject<Item> INFERIUS_EGG = ITEMS.register("inferius_spawn_egg",
			() -> new ForgeSpawnEggItem(INFERIUS, 0xb8b8b8, 0x696969,
			new Item.Properties().tab(CUSTOM_TAB)));

//acromantula
	public static final RegistryObject<EntityType<Acromantula>> ACROMANTULA = ENTITIES.register("acromantula", () -> EntityType.Builder.of(Acromantula::new, MobCategory.MONSTER)
	        .sized(1.1F, 0.8F)
	        .clientTrackingRange(8)
	        .build("acromantula"));
	public static final RegistryObject<Item> ACROMANTULA_EGG = ITEMS.register("acromantula_spawn_egg",
			() -> new ForgeSpawnEggItem(ACROMANTULA, 0x717b80, 0x46565e,
			new Item.Properties().tab(CUSTOM_TAB)));

	//default broomstick
	public static final RegistryObject<EntityType<BroomStick>> BROOMSTICK = ENTITIES.register("broomstick", () -> EntityType.Builder.of(BroomStick::new, MobCategory.MISC)
	        .sized(0.9F, 0.5F)
	        .clientTrackingRange(12)
	        .build("broomstick"));
	
	public static final RegistryObject<Item> BROOMSTICK_ITEM = ITEMS.register("broomstick", ()-> new BroomStickItem(new Item.Properties().tab(CUSTOM_TAB).stacksTo(1)));
	
}
