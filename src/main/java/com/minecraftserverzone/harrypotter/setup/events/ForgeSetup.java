package com.minecraftserverzone.harrypotter.setup.events;

import com.minecraftserverzone.harrypotter.HarryPotterMod;
import com.minecraftserverzone.harrypotter.broomsticks.BroomStick;
import com.minecraftserverzone.harrypotter.setup.capabilities.PlayerStatsProvider;
import com.minecraftserverzone.harrypotter.setup.network.Networking;
import com.minecraftserverzone.harrypotter.setup.network.PacketDataCDForAll;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent.Phase;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
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
		if(event.getEntity().getVehicle() != null) {
			if(event.getEntity().getVehicle() instanceof BroomStick) {
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
}
