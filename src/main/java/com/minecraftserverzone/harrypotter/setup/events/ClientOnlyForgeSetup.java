package com.minecraftserverzone.harrypotter.setup.events;

import java.util.Map;
import java.util.Random;
import java.util.function.Function;

import org.joml.Vector3f;

import com.google.common.collect.Maps;
import com.minecraftserverzone.harrypotter.HarryPotterMod;
import com.minecraftserverzone.harrypotter.gui.MaraudersMap;
import com.minecraftserverzone.harrypotter.gui.SpellBook;
import com.minecraftserverzone.harrypotter.mobs.dementor.Dementor;
import com.minecraftserverzone.harrypotter.setup.KeyHandler;
import com.minecraftserverzone.harrypotter.setup.Registrations;
import com.minecraftserverzone.harrypotter.setup.capabilities.PlayerStatsProvider;
import com.minecraftserverzone.harrypotter.setup.config.HarryPotterModConfig;
import com.minecraftserverzone.harrypotter.setup.network.Networking;
import com.minecraftserverzone.harrypotter.setup.network.PacketData;
import com.minecraftserverzone.harrypotter.setup.network.PacketSpells;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;

import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.event.TickEvent.Phase;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = HarryPotterMod.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ClientOnlyForgeSetup {

	public static int channelFireStorm = 0;
	 
	@SubscribeEvent
	public static void changeCapabilityOfPlayers(PlayerTickEvent event) {
		if(event.phase == Phase.END && event.player.level.isClientSide()) {

		Player player = event.player;
		if(player instanceof LocalPlayer) {
				if(player.tickCount == 10) {
					//send client player data to all player
					player.getCapability(PlayerStatsProvider.PLAYER_STATS_CAPABILITY).ifPresent(h -> {
						int[] allSpell = new int[] {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};
	 					if(h.getSpellsLevel() != null) {
	 						if(h.getSpellsLevel().length == 25) {
	 							allSpell = h.getSpellsLevel();
	 						}
	 					}
//	 					System.out.println("1: "+allSpell.length);
						Networking.sendToServer(new PacketData(allSpell, player.getUUID(), -1));
					});
					//get all player data for client player
//					System.out.println("set new data at forge setup");
				}
			}else {
//				if(!(player instanceof LocalPlayer)) {
					if(player.getCapability(PlayerStatsProvider.PLAYER_STATS_CAPABILITY).isPresent()) {
						player.getCapability(PlayerStatsProvider.PLAYER_STATS_CAPABILITY).ifPresent(h -> {
							if(h.getSpellsLevel()==null) {
								//send data
								int[] allSpell = new int[] {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};
			 					if(h.getSpellsLevel() != null) {
			 						if(h.getSpellsLevel().length == 25) {
			 							allSpell = h.getSpellsLevel();
			 						}
			 					}
//	 							System.out.println("2: "+allSpell.length);
								Networking.sendToServer(new PacketData(allSpell, player.getUUID(), -1));
							}
						});
					}
			}
		}
	}
	
	public static Random rand = new Random();
	public static Map<String, Vector3f> modelRotationValues = Maps.newHashMap();

	public static final RenderStateShard.WriteMaskStateShard COLOR_DEPTH_WRITE = new RenderStateShard.WriteMaskStateShard(true, true);
	public static final RenderStateShard.WriteMaskStateShard COLOR_WRITE = new RenderStateShard.WriteMaskStateShard(true, false);
	public static final RenderStateShard.OverlayStateShard OVERLAY = new RenderStateShard.OverlayStateShard(true);
	public static final RenderStateShard.LightmapStateShard LIGHTMAP = new RenderStateShard.LightmapStateShard(true);
	public static final RenderStateShard.TransparencyStateShard TRANSLUCENT_TRANSPARENCY = new RenderStateShard.TransparencyStateShard("translucent_transparency2", () -> {
	      RenderSystem.enableBlend();
	      RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
	      RenderSystem.setShader(GameRenderer::getRendertypeEnergySwirlShader);
	   }, () -> {
	      RenderSystem.disableBlend();
	      RenderSystem.defaultBlendFunc();
	   });
	public static final RenderStateShard.ShaderStateShard RENDERTYPE_ENTITY_TRANSLUCENT_CULL_SHADER = new RenderStateShard.ShaderStateShard(GameRenderer::getRendertypeEntityTranslucentCullShader);
	
	public static final Function<ResourceLocation, RenderType> ENTITY_TRANSLUCENT_CULL = Util.memoize((p_173198_) -> {
	      RenderType.CompositeState rendertype$compositestate = RenderType.CompositeState.builder().setShaderState(RENDERTYPE_ENTITY_TRANSLUCENT_CULL_SHADER).setTextureState(new RenderStateShard.TextureStateShard(p_173198_, false, false)).setTransparencyState(TRANSLUCENT_TRANSPARENCY).setLightmapState(LIGHTMAP).setOverlayState(OVERLAY).createCompositeState(true); //.setWriteMaskState(COLOR_DEPTH_WRITE)
	      return RenderType.create("entity_translucent_cull2", DefaultVertexFormat.NEW_ENTITY, VertexFormat.Mode.QUADS, 256, true, true, rendertype$compositestate);
	   });
	
	@SubscribeEvent
	public static void onEntityDeath(LivingDeathEvent event) {
		if(event.getEntity() instanceof Villager || event.getEntity() instanceof IronGolem || event.getEntity() instanceof Dementor) {
			if(Math.random() < HarryPotterModConfig.DEMENTOR_SPAWN_PERCENT.get()) {
				Networking.sendToServer(new PacketSpells(92)); //spawn dementor
			}
		}
	}
	
	@SubscribeEvent
	public static void onServerJoin(PlayerLoggedInEvent event) {
//		event.getEntity().getCapability(PlayerStatsProvider.PLAYER_STATS_CAPABILITY).ifPresent(h -> {
//			h.setClickedSkill(0);
//			h.setFlying(0);
//			h.setUsingSkill(0);
//			int[] allSpell = new int[] {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};
//				if(h.getSpellsLevel() != null) {
//					if(h.getSpellsLevel().length == 25) {
//							allSpell = h.getSpellsLevel();
//						}
//				}
////				System.out.println("x: "+allSpell.length);
//			Networking.sendToServer(new PacketData(allSpell, event.getEntity().getUUID(), -1));
//		});
//		Networking.sendToServer(new PacketSpells(91));
		
	}

	@SubscribeEvent
    public static void onItemUse(PlayerInteractEvent.RightClickItem event) {
		if(event.getEntity().level.isClientSide && (event.getEntity().getMainHandItem().is(Registrations.MARAUDERS_MAP.get()) || event.getEntity().getOffhandItem().is(Registrations.MARAUDERS_MAP.get()))) {
		    Minecraft.getInstance().setScreen(new MaraudersMap());
		}
	}
	
	@SuppressWarnings("resource")
	@SubscribeEvent
    public static void detectScroll(InputEvent.MouseScrollingEvent event) {
		Player player = Minecraft.getInstance().player;
		
		if (event.getScrollDelta() > 0) {
			if(event.isCancelable()) {
				player.getCapability(PlayerStatsProvider.PLAYER_STATS_CAPABILITY).ifPresent(h -> {
					if(h.getBattleTick() == 1) {
							h.setSelectedHotbar((int) (h.getSelectedHotbar() - event.getScrollDelta())); 
							Networking.sendToServer(new PacketSpells(100 + h.getSelectedHotbar())); //save nbt data for player
						if(h.getSelectedHotbar() < 0) {
							h.setSelectedHotbar(8);
							Networking.sendToServer(new PacketSpells(108)); //save nbt data for player
						}else if(h.getSelectedHotbar() > 8) {
							h.setSelectedHotbar(0);
							Networking.sendToServer(new PacketSpells(100)); //save nbt data for player
						}
						event.setCanceled(true);
					}
				});
			}
		}else if(event.getScrollDelta() < 0) {
			if(event.isCancelable()) {
				player.getCapability(PlayerStatsProvider.PLAYER_STATS_CAPABILITY).ifPresent(h -> {
					if(h.getBattleTick() == 1) {
							h.setSelectedHotbar((int) (h.getSelectedHotbar() - event.getScrollDelta()));
							Networking.sendToServer(new PacketSpells(100 + h.getSelectedHotbar())); //save nbt data for player
						if(h.getSelectedHotbar() < 0) {
							h.setSelectedHotbar(8);
							Networking.sendToServer(new PacketSpells(108)); //save nbt data for player
						}else if(h.getSelectedHotbar() > 8) {
							h.setSelectedHotbar(0);
							Networking.sendToServer(new PacketSpells(100)); //save nbt data for player
						}
						event.setCanceled(true);
					}
				});
			}
		}
	}

	@SuppressWarnings("resource")
	@SubscribeEvent
	public static void keyPressed(InputEvent.Key event) {
		Player player = Minecraft.getInstance().player;
		Options keys = Minecraft.getInstance().options;

		//move buttons
		//W		
		if(keys.keyUp.isDown()) {

		}else {

		}
		
		//S
		if(keys.keyDown.isDown()) {

		}else {

		}
		
		//A
		if(keys.keyLeft.isDown()) {

		}else {

		} 
		
		//D
		if(keys.keyRight.isDown()) {

		}else {

		}
		
		//D
		if(keys.keyShift.isDown()) {

		}else {

		}
		
		if(keys.keyDrop.isDown()) {
			player.getCapability(PlayerStatsProvider.PLAYER_STATS_CAPABILITY).ifPresent(h -> {
				if(h.getBattleTick() == 1) {
					keys.keyDrop.consumeClick();
				}
			});
		}
		
		if(keys.keySwapOffhand.isDown()) {
			player.getCapability(PlayerStatsProvider.PLAYER_STATS_CAPABILITY).ifPresent(h -> {
				if(h.getBattleTick() == 1) {
					keys.keySwapOffhand.consumeClick();
				}
			});
		}
		
		//b
		if(KeyHandler.SPELL_BOOK.isDown()) {
			Minecraft.getInstance().setScreen(new SpellBook());
		}
		//h
		if(KeyHandler.BATTLE_STANCE.isDown()) {
			player.getCapability(PlayerStatsProvider.PLAYER_STATS_CAPABILITY).ifPresent(h -> {
	        	//jump
				if(h.getBattleTick() == 0) {
					if(player.getMainHandItem().is(Registrations.APPRENTICE_WAND.get())) {
//						save the item slot which is currently active
						h.setSelectedHotbar(player.getInventory().selected); //new hotbar uses this value
						Networking.sendToServer(new PacketSpells(100 + h.getSelectedHotbar())); //save nbt data for player
						h.setHotbarBeforeBattleStance(player.getInventory().selected);//changes only here
						h.setBattleTick(1);
					}
					
				}else {
					h.setBattleTick(0);
				}
			});
		}
		
		//hotbar buttons
		for(int i = 0; i < 9; i++) {
			if(keys.keyHotbarSlots[i].isDown()) {
				int b = i;
//				if hotbar numbers are pressed and battletick is 1, then switch item slot back to the saved slot
//				and save last pressed hotbar number, and change the new gui selector to that, not to the original selector	
				player.getCapability(PlayerStatsProvider.PLAYER_STATS_CAPABILITY).ifPresent(h -> {
					if(h.getBattleTick() == 1) {
							h.setSelectedHotbar(b); //new hotbar uses this value
							Networking.sendToServer(new PacketSpells(100 + h.getSelectedHotbar())); //save nbt data for player
							keys.keyHotbarSlots[b].consumeClick();
					}
				});
			}
		}
	}
}
