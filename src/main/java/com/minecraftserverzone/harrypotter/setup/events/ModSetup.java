package com.minecraftserverzone.harrypotter.setup.events;

import com.minecraftserverzone.harrypotter.HarryPotterMod;
import com.minecraftserverzone.harrypotter.broomsticks.BroomStick;
import com.minecraftserverzone.harrypotter.broomsticks.BroomStickModel;
import com.minecraftserverzone.harrypotter.items.wand.ApprenticeWandModel;
import com.minecraftserverzone.harrypotter.mobs.acromantula.Acromantula;
import com.minecraftserverzone.harrypotter.mobs.acromantula.AcromantulaModel;
import com.minecraftserverzone.harrypotter.mobs.death_eater.DeathEater;
import com.minecraftserverzone.harrypotter.mobs.death_eater.DeathEaterModel;
import com.minecraftserverzone.harrypotter.mobs.dementor.Dementor;
import com.minecraftserverzone.harrypotter.mobs.dementor.DementorModel;
import com.minecraftserverzone.harrypotter.mobs.inferius.Inferius;
import com.minecraftserverzone.harrypotter.mobs.inferius.InferiusModel;
import com.minecraftserverzone.harrypotter.mobs.patronus_deer.PatronusDeer;
import com.minecraftserverzone.harrypotter.mobs.patronus_deer.PatronusDeerModel;
import com.minecraftserverzone.harrypotter.mobs.troll.Troll;
import com.minecraftserverzone.harrypotter.mobs.troll.TrollModel;
import com.minecraftserverzone.harrypotter.setup.Registrations;
import com.minecraftserverzone.harrypotter.setup.capabilities.IPlayerStats;
import com.minecraftserverzone.harrypotter.setup.config.ConfigHelper;
import com.minecraftserverzone.harrypotter.setup.config.ConfigHolder;
import com.minecraftserverzone.harrypotter.setup.network.Networking;
import com.minecraftserverzone.harrypotter.spells.aqua_eructo.AquaEructoModel;
import com.minecraftserverzone.harrypotter.spells.avada_kedavra.AvadaKedavraModel;
import com.minecraftserverzone.harrypotter.spells.avis.AvisModel;
import com.minecraftserverzone.harrypotter.spells.avis.BirdsModel;
import com.minecraftserverzone.harrypotter.spells.confringo.ConfringoModel;
import com.minecraftserverzone.harrypotter.spells.depulso.DepulsoModel;
import com.minecraftserverzone.harrypotter.spells.expecto_patronum.ExpectoPatronumModel;
import com.minecraftserverzone.harrypotter.spells.expelliarmus.ExpelliarmusModel;
import com.minecraftserverzone.harrypotter.spells.glacius.GlaciusModel;
import com.minecraftserverzone.harrypotter.spells.glacius.IceEntityModel;
import com.minecraftserverzone.harrypotter.spells.incendio.IncendioModel;
import com.minecraftserverzone.harrypotter.spells.melofors.MeloforsModel;
import com.minecraftserverzone.harrypotter.spells.sectumsempra.SectumsempraModel;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = HarryPotterMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModSetup {
	
	@SubscribeEvent
	public static void registerEntities(RegistryEvent.Register<EntityType<?>> event) {
		SpawnPlacements.register(Registrations.DEATH_EATER.get(), SpawnPlacements.Type.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, DeathEater::checkMobSpawnRules);
		SpawnPlacements.register(Registrations.TROLL.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Troll::checkMobSpawnRules);
	}

	@SubscribeEvent
	public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(AquaEructoModel.LAYER_LOCATION, AquaEructoModel::createBodyLayer);
		event.registerLayerDefinition(AvadaKedavraModel.LAYER_LOCATION, AvadaKedavraModel::createBodyLayer);
		event.registerLayerDefinition(AvisModel.LAYER_LOCATION, AvisModel::createBodyLayer);
		event.registerLayerDefinition(BirdsModel.LAYER_LOCATION, BirdsModel::createBodyLayer);
		event.registerLayerDefinition(ConfringoModel.LAYER_LOCATION, ConfringoModel::createBodyLayer);
		event.registerLayerDefinition(DepulsoModel.LAYER_LOCATION, DepulsoModel::createBodyLayer);
		event.registerLayerDefinition(ExpelliarmusModel.LAYER_LOCATION, ExpelliarmusModel::createBodyLayer);
		event.registerLayerDefinition(IncendioModel.LAYER_LOCATION, IncendioModel::createBodyLayer);
		event.registerLayerDefinition(GlaciusModel.LAYER_LOCATION, GlaciusModel::createBodyLayer);
		event.registerLayerDefinition(IceEntityModel.LAYER_LOCATION, IceEntityModel::createBodyLayer);
		event.registerLayerDefinition(MeloforsModel.LAYER_LOCATION, MeloforsModel::createBodyLayer);
		event.registerLayerDefinition(SectumsempraModel.LAYER_LOCATION, SectumsempraModel::createBodyLayer);
		event.registerLayerDefinition(ExpectoPatronumModel.LAYER_LOCATION, ExpectoPatronumModel::createBodyLayer);
		event.registerLayerDefinition(PatronusDeerModel.LAYER_LOCATION, PatronusDeerModel::createBodyLayer);
		event.registerLayerDefinition(DementorModel.LAYER_LOCATION, DementorModel::createBodyLayer);
		event.registerLayerDefinition(DeathEaterModel.LAYER_LOCATION, DeathEaterModel::createBodyLayer);
		event.registerLayerDefinition(ApprenticeWandModel.LAYER_LOCATION, ApprenticeWandModel::createBodyLayer);
		event.registerLayerDefinition(TrollModel.LAYER_LOCATION, TrollModel::createBodyLayer);
		event.registerLayerDefinition(InferiusModel.LAYER_LOCATION, InferiusModel::createBodyLayer);
		event.registerLayerDefinition(AcromantulaModel.LAYER_LOCATION, AcromantulaModel::createBodyLayer);
		event.registerLayerDefinition(BroomStickModel.LAYER_LOCATION, BroomStickModel::createBodyLayer);
		
	}
	
	@SubscribeEvent
    public static void entityAttributeCreationEvent(EntityAttributeCreationEvent event) {
		event.put(Registrations.PATRONUS_DEER.get(), PatronusDeer.createAttributes().build());
		event.put(Registrations.DEMENTOR.get(), Dementor.createAttributes().build());
		event.put(Registrations.DEATH_EATER.get(), DeathEater.createAttributes().build());
		event.put(Registrations.TROLL.get(), Troll.createAttributes().build());
		event.put(Registrations.INFERIUS.get(), Inferius.createAttributes().build());
		event.put(Registrations.ACROMANTULA.get(), Acromantula.createAttributes().build());
		event.put(Registrations.BROOMSTICK.get(), BroomStick.createAttributes().build());
		
	}
	
	@SubscribeEvent
	public static void registerCapabilities(RegisterCapabilitiesEvent event) {
		event.register(IPlayerStats.class);
	}
	
	@SubscribeEvent
	public static void onModConfigEvent(ModConfigEvent event) {
		// Rebake the configs when they change
		if (event.getConfig().getSpec() == ConfigHolder.COMMON_SPEC) {
			ConfigHelper.bakeCommon(event.getConfig());
		}
		if (event.getConfig().getSpec() == ConfigHolder.CLIENT_SPEC) {
			ConfigHelper.bakeClient(event.getConfig());
		}
	}
	
	
	@SubscribeEvent
	public static void init(final FMLCommonSetupEvent event) {
    	Networking.registerMessages();    	
	}
	
}
