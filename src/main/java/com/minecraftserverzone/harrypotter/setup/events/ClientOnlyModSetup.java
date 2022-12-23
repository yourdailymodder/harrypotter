package com.minecraftserverzone.harrypotter.setup.events;

import com.minecraftserverzone.harrypotter.HarryPotterMod;
import com.minecraftserverzone.harrypotter.broomsticks.BroomStickRenderer;
import com.minecraftserverzone.harrypotter.gui.Hotbar;
import com.minecraftserverzone.harrypotter.mobs.acromantula.AcromantulaRenderer;
import com.minecraftserverzone.harrypotter.mobs.death_eater.DeathEaterRenderer;
import com.minecraftserverzone.harrypotter.mobs.dementor.DementorRenderer;
import com.minecraftserverzone.harrypotter.mobs.inferius.InferiusRenderer;
import com.minecraftserverzone.harrypotter.mobs.patronus_deer.PatronusDeerRenderer;
import com.minecraftserverzone.harrypotter.mobs.troll.TrollRenderer;
import com.minecraftserverzone.harrypotter.setup.HelperFunctions;
import com.minecraftserverzone.harrypotter.setup.KeyHandler;
import com.minecraftserverzone.harrypotter.setup.Registrations;
import com.minecraftserverzone.harrypotter.setup.capabilities.PlayerStatsProvider;
import com.minecraftserverzone.harrypotter.spells.accio.AccioRenderer;
import com.minecraftserverzone.harrypotter.spells.alarte_ascandare.AlarteAscandareRenderer;
import com.minecraftserverzone.harrypotter.spells.aqua_eructo.AquaEructoRenderer;
import com.minecraftserverzone.harrypotter.spells.avada_kedavra.AvadaKedavraRenderer;
import com.minecraftserverzone.harrypotter.spells.avis.AvisRenderer;
import com.minecraftserverzone.harrypotter.spells.avis.BirdsRenderer;
import com.minecraftserverzone.harrypotter.spells.confringo.ConfringoRenderer;
import com.minecraftserverzone.harrypotter.spells.depulso.DepulsoRenderer;
import com.minecraftserverzone.harrypotter.spells.episkey.EpiskeyRenderer;
import com.minecraftserverzone.harrypotter.spells.evanesco.EvanescoRenderer;
import com.minecraftserverzone.harrypotter.spells.expecto_patronum.ExpectoPatronumRenderer;
import com.minecraftserverzone.harrypotter.spells.expelliarmus.ExpelliarmusRenderer;
import com.minecraftserverzone.harrypotter.spells.finite.FiniteRenderer;
import com.minecraftserverzone.harrypotter.spells.fire_storm.FireStormRenderer;
import com.minecraftserverzone.harrypotter.spells.fumos.FumosRenderer;
import com.minecraftserverzone.harrypotter.spells.glacius.GlaciusRenderer;
import com.minecraftserverzone.harrypotter.spells.glacius.IceEntityRenderer;
import com.minecraftserverzone.harrypotter.spells.incendio.IncendioRenderer;
import com.minecraftserverzone.harrypotter.spells.melofors.MeloforsRenderer;
import com.minecraftserverzone.harrypotter.spells.mobilicorpus.BodyMoverRenderer;
import com.minecraftserverzone.harrypotter.spells.mobilicorpus.MobilicorpusRenderer;
import com.minecraftserverzone.harrypotter.spells.reparo.ReparoRenderer;
import com.minecraftserverzone.harrypotter.spells.sectumsempra.SectumsempraRenderer;
import com.minecraftserverzone.harrypotter.spells.wingardium_leviosa.WingardiumLeviosaRenderer;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = HarryPotterMod.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientOnlyModSetup {

	public static final ResourceLocation HOTBAR = new ResourceLocation(HarryPotterMod.MODID, "textures/gui/widgets.png");
	
	@SubscribeEvent
	public static void registerKeybinds(RegisterKeyMappingsEvent event) {
		event.register(KeyHandler.BATTLE_STANCE);
		event.register(KeyHandler.SPELL_BOOK);
	}
	
	
    
	/*@SuppressWarnings("deprecation")
    @SubscribeEvent
    public static void onTextureStitch(TextureStitchEvent event) {
    	if(!event.getAtlas().location().equals(TextureAtlas.LOCATION_BLOCKS)) {
    		return;
    	}
    	event.addSprite(ApprenticeWandRenderer.TEXTURE);
    }*/
	
	
    
    @SubscribeEvent
	public static void onRegisterGuiOverlays(RegisterGuiOverlaysEvent event) {
        event.registerAboveAll("harry-potter-hotbar", (gui, mStack, partialTicks, screenWidth, screenHeight) -> {
        	gui.setupOverlayRenderState(true, false);
            HelperFunctions.bind(ClientOnlyModSetup.HOTBAR);

			Player player = Minecraft.getInstance().player;
			player.getCapability(PlayerStatsProvider.PLAYER_STATS_CAPABILITY).ifPresent(h -> {
				if(h.getBattleTick() == 1) {
					 RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
				        RenderSystem.disableBlend();

				        //render screen
				        renderHotbar(mStack, gui);

				        RenderSystem.enableBlend();
				        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
				}
			});
        });
    }

	 public static void renderHotbar(PoseStack mStack, ForgeGui gui) {
			Minecraft minecraft = Minecraft.getInstance();
			Hotbar.renderHotbar(0, mStack, gui, minecraft);
		}

	@SubscribeEvent
	public static void registerEntityRenderer(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(Registrations.ACCIO.get(), m -> new AccioRenderer(m));
		event.registerEntityRenderer(Registrations.AQUA_ERUCTO.get(), m -> new AquaEructoRenderer(m));
		event.registerEntityRenderer(Registrations.AQUA_ERUCTO_SPAWNER.get(), ThrownItemRenderer::new);
		event.registerEntityRenderer(Registrations.AVADA_KEDAVRA.get(), m -> new AvadaKedavraRenderer(m));
		event.registerEntityRenderer(Registrations.AVIS.get(), m -> new AvisRenderer(m));
		event.registerEntityRenderer(Registrations.CONFRINGO.get(), m -> new ConfringoRenderer(m));
		event.registerEntityRenderer(Registrations.DEPULSO.get(), m -> new DepulsoRenderer(m));
		event.registerEntityRenderer(Registrations.FUMOS.get(), m -> new FumosRenderer(m));
		event.registerEntityRenderer(Registrations.EXPELLIARMUS.get(), m -> new ExpelliarmusRenderer(m));
		event.registerEntityRenderer(Registrations.INCENDIO.get(), m -> new IncendioRenderer(m));
		event.registerEntityRenderer(Registrations.GLACIUS.get(), m -> new GlaciusRenderer(m));
		event.registerEntityRenderer(Registrations.ICE_ENTITY.get(), m -> new IceEntityRenderer(m));
		event.registerEntityRenderer(Registrations.BIRDS.get(), m -> new BirdsRenderer(m));
		event.registerEntityRenderer(Registrations.MELOFORS.get(), m -> new MeloforsRenderer(m));
		event.registerEntityRenderer(Registrations.SECTUMSEMPRA.get(), m -> new SectumsempraRenderer(m));
		event.registerEntityRenderer(Registrations.MOBILICORPUS.get(), m -> new MobilicorpusRenderer(m));
		event.registerEntityRenderer(Registrations.BODY_MOVER.get(), m -> new BodyMoverRenderer(m));
		event.registerEntityRenderer(Registrations.REPARO.get(), m -> new ReparoRenderer(m));
		event.registerEntityRenderer(Registrations.WINGARDIUM_LEVIOSA.get(), m -> new WingardiumLeviosaRenderer(m));
		event.registerEntityRenderer(Registrations.EPISKEY.get(), m -> new EpiskeyRenderer(m));
		event.registerEntityRenderer(Registrations.ALARTE_ASCANDARE.get(), m -> new AlarteAscandareRenderer(m));
		event.registerEntityRenderer(Registrations.FINITE.get(), m -> new FiniteRenderer(m));
		event.registerEntityRenderer(Registrations.EVANESCO.get(), m -> new EvanescoRenderer(m));
		event.registerEntityRenderer(Registrations.FIRE_STORM.get(), m -> new FireStormRenderer(m));
		event.registerEntityRenderer(Registrations.FIRE_STORM_SPAWNER.get(), ThrownItemRenderer::new);

		//mobs
		event.registerEntityRenderer(Registrations.EXPECTO_PATRONUM.get(), m -> new ExpectoPatronumRenderer(m));
		event.registerEntityRenderer(Registrations.PATRONUS_DEER.get(), m -> new PatronusDeerRenderer(m));
		event.registerEntityRenderer(Registrations.DEMENTOR.get(), m -> new DementorRenderer(m));
		event.registerEntityRenderer(Registrations.DEATH_EATER.get(), m -> new DeathEaterRenderer(m));
		event.registerEntityRenderer(Registrations.TROLL.get(), m -> new TrollRenderer(m));
		event.registerEntityRenderer(Registrations.INFERIUS.get(), m -> new InferiusRenderer(m));
		event.registerEntityRenderer(Registrations.ACROMANTULA.get(), m -> new AcromantulaRenderer<>(m));

		//models
		event.registerEntityRenderer(Registrations.BROOMSTICK.get(), m -> new BroomStickRenderer(m));
	}
}
