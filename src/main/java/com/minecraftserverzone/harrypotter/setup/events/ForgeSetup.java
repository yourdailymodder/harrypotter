package com.minecraftserverzone.harrypotter.setup.events;

import com.minecraftserverzone.harrypotter.HarryPotterMod;
import com.minecraftserverzone.harrypotter.broomsticks.BroomStick;
import com.minecraftserverzone.harrypotter.setup.Registrations;
import com.minecraftserverzone.harrypotter.setup.capabilities.PlayerStatsProvider;
import com.minecraftserverzone.harrypotter.setup.config.HarryPotterModConfig;
import com.minecraftserverzone.harrypotter.setup.network.Networking;
import com.minecraftserverzone.harrypotter.setup.network.PacketDataCDForAll;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.biome.MobSpawnSettings.SpawnerData;
import net.minecraftforge.common.world.MobSpawnSettingsBuilder;
import net.minecraftforge.event.TickEvent.Phase;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = HarryPotterMod.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ForgeSetup {
	
//	@SubscribeEvent
//	 public static void onAttachCapabilitiesEvent(AttachCapabilitiesEvent<Entity> event) {
//	        if (event.getObject() instanceof Player) {
//	        	PlayerStatsProvider provider = new PlayerStatsProvider();
//	            event.addCapability(DB_PLAYER_DATA, provider);
//	            event.addListener(provider::invalidate);
//	        }
//	    }
	

	@SubscribeEvent
	public static void damageEvent(LivingDamageEvent event) {
		if(event.getEntityLiving().getVehicle() != null) {
			if(event.getEntityLiving().getVehicle() instanceof BroomStick) {
				if(event.getSource() == DamageSource.FALL) {
					event.setAmount(0);
				}
			}
		}
	}
		
	@SubscribeEvent
	public static void changeCapabilityOfPlayers(PlayerTickEvent event) {
		if(event.phase == Phase.END && !event.player.level.isClientSide() && event.player.tickCount % 10 == 0) {
			Player player = event.player;
			player.getCapability(PlayerStatsProvider.PLAYER_STATS_CAPABILITY).ifPresent(h -> {
				int[] allSpellCooldown = new int[] {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
					if(h.getSpellsCD() != null) {
						
						allSpellCooldown = h.getSpellsCD();
						if(h.getSpellsCD().length < 25) {
							allSpellCooldown = new int[] {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
							h.setSpellsCD(allSpellCooldown);
						}
						
						if(h.getSpellsCD().length == 25) {
							for(int id = 0; id < 25; id++) {
								if(allSpellCooldown[id] > 0) {
//									int cd = h.getSpellsCD()[id];
									h.setSpellCD(h.getSpellsCD()[id] - 1, id);
									//send package to decrease cooldown on client
									Networking.sendToClient(new PacketDataCDForAll(id, h.getSpellsCD()[id], player.getUUID()), (ServerPlayer) player);
//									System.out.println("Server: "+id+ " - "+h.getSpellsCD()[id]);
								}
							}
						}
					}else {
						h.setSpellsCD(allSpellCooldown);
					}
			});
		}
	}
		
	@SubscribeEvent
    public static void onPlayerDeath(PlayerEvent.Clone event) {
		if(event.isWasDeath()) {
			event.getOriginal().reviveCaps();
            event.getOriginal().getCapability(PlayerStatsProvider.PLAYER_STATS_CAPABILITY).ifPresent(og -> {
            	event.getEntity().getCapability(PlayerStatsProvider.PLAYER_STATS_CAPABILITY).ifPresent(h -> {
                	h.setSpellsLevel(og.getSpellsLevel());
                	int[] allSpellCooldown = new int[] {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
                	h.setSpellsCD(allSpellCooldown);
                });
            });
            event.getOriginal().invalidateCaps();
		}
            
    }
	

//	//mob spawns
	@SubscribeEvent(priority = EventPriority.HIGH)
    public static void onBiomeLoadingEvent(final BiomeLoadingEvent event) {
		if(event.getName() == null) {
            return;
		}
		String biomename = event.getName().toString();
		String[] spawnInBiomesList = HarryPotterModConfig.DEATH_EATER_BIOME.get().split(",");

		if(HarryPotterModConfig.DEATH_EATER[0].get() > 0) {
			MobSpawnSettingsBuilder spawns = event.getSpawns();
			if(HarryPotterModConfig.DEATH_EATER_BIOME.get() == "") {
				spawns.getSpawner(MobCategory.MONSTER).add(new SpawnerData(Registrations.DEATH_EATER.get(), HarryPotterModConfig.DEATH_EATER[0].get(), HarryPotterModConfig.DEATH_EATER[1].get(), HarryPotterModConfig.DEATH_EATER[2].get()));
			}

			for(String list : spawnInBiomesList) {
				if(biomename.equals(list)) {
					spawns.getSpawner(MobCategory.MONSTER).add(new SpawnerData(Registrations.DEATH_EATER.get(), HarryPotterModConfig.DEATH_EATER[0].get(), HarryPotterModConfig.DEATH_EATER[1].get(), HarryPotterModConfig.DEATH_EATER[2].get()));
				}
			}
		}

		spawnInBiomesList = HarryPotterModConfig.TROLL_BIOME.get().split(",");

		if(HarryPotterModConfig.TROLL[0].get() > 0) {
			MobSpawnSettingsBuilder spawns = event.getSpawns();
			if(HarryPotterModConfig.TROLL_BIOME.get() == "") {
				spawns.getSpawner(MobCategory.MONSTER).add(new SpawnerData(Registrations.TROLL.get(), HarryPotterModConfig.TROLL[0].get(), HarryPotterModConfig.TROLL[1].get(), HarryPotterModConfig.TROLL[2].get()));
			}

			for(String list : spawnInBiomesList) {
				if(biomename.equals(list)) {
					spawns.getSpawner(MobCategory.MONSTER).add(new SpawnerData(Registrations.TROLL.get(), HarryPotterModConfig.TROLL[0].get(), HarryPotterModConfig.TROLL[1].get(), HarryPotterModConfig.TROLL[2].get()));
				}
			}
		}
		
		//dementors
		spawnInBiomesList = HarryPotterModConfig.DEMENTOR_BIOME.get().split(",");

		if(HarryPotterModConfig.DEMENTOR[0].get() > 0) {
			MobSpawnSettingsBuilder spawns = event.getSpawns();
			if(HarryPotterModConfig.DEMENTOR_BIOME.get() == "") {
				spawns.getSpawner(MobCategory.MONSTER).add(new SpawnerData(Registrations.DEMENTOR.get(), HarryPotterModConfig.DEMENTOR[0].get(), HarryPotterModConfig.DEMENTOR[1].get(), HarryPotterModConfig.DEMENTOR[2].get()));
			}

			for(String list : spawnInBiomesList) {
				if(biomename.equals(list)) {
					spawns.getSpawner(MobCategory.MONSTER).add(new SpawnerData(Registrations.DEMENTOR.get(), HarryPotterModConfig.DEMENTOR[0].get(), HarryPotterModConfig.DEMENTOR[1].get(), HarryPotterModConfig.DEMENTOR[2].get()));
				}
			}
		}
		
		//inferius
		spawnInBiomesList = HarryPotterModConfig.INFERIUS_BIOME.get().split(",");

		if(HarryPotterModConfig.INFERIUS[0].get() > 0) {
			MobSpawnSettingsBuilder spawns = event.getSpawns();
			if(HarryPotterModConfig.INFERIUS_BIOME.get() == "") {
				spawns.getSpawner(MobCategory.MONSTER).add(new SpawnerData(Registrations.INFERIUS.get(), HarryPotterModConfig.INFERIUS[0].get(), HarryPotterModConfig.INFERIUS[1].get(), HarryPotterModConfig.INFERIUS[2].get()));
			}

			for(String list : spawnInBiomesList) {
				if(biomename.equals(list)) {
					spawns.getSpawner(MobCategory.MONSTER).add(new SpawnerData(Registrations.INFERIUS.get(), HarryPotterModConfig.INFERIUS[0].get(), HarryPotterModConfig.INFERIUS[1].get(), HarryPotterModConfig.INFERIUS[2].get()));
				}
			}
		}
		
		//acromantula
		spawnInBiomesList = HarryPotterModConfig.ACROMANTULA_BIOME.get().split(",");

		if(HarryPotterModConfig.ACROMANTULA[0].get() > 0) {
			MobSpawnSettingsBuilder spawns = event.getSpawns();
			if(HarryPotterModConfig.ACROMANTULA_BIOME.get() == "") {
				spawns.getSpawner(MobCategory.MONSTER).add(new SpawnerData(Registrations.ACROMANTULA.get(), HarryPotterModConfig.ACROMANTULA[0].get(), HarryPotterModConfig.ACROMANTULA[1].get(), HarryPotterModConfig.ACROMANTULA[2].get()));
			}

			for(String list : spawnInBiomesList) {
				if(biomename.equals(list)) {
					spawns.getSpawner(MobCategory.MONSTER).add(new SpawnerData(Registrations.ACROMANTULA.get(), HarryPotterModConfig.ACROMANTULA[0].get(), HarryPotterModConfig.ACROMANTULA[1].get(), HarryPotterModConfig.ACROMANTULA[2].get()));
				}
			}
		}

	}
	
}
