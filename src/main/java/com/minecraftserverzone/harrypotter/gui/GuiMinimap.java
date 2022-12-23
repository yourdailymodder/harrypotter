package com.minecraftserverzone.harrypotter.gui;

import java.util.List;

import com.minecraftserverzone.harrypotter.HarryPotterMod;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FastColor;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;

public class GuiMinimap extends Screen{
	public static final ResourceLocation MAP = new ResourceLocation(HarryPotterMod.MODID, "textures/gui/marauders_map.png");

	protected GuiMinimap() {
		super(Component.translatable("screen.harrypotter.minimap"));
	}

	public static void renderMinimap(float p_93010_, PoseStack p_96562_, Gui gui, Minecraft minecraft) {
		try {
			minecraft = Minecraft.getInstance();
			Player player = minecraft.player;
			int screenWidth =  minecraft.getWindow().getGuiScaledWidth();
			int l = (minecraft.getWindow().getGuiScaledHeight()/2) - 66;

			//render function like in ForgeInGameGui
			minecraft.getProfiler().push("marauders_map");
			p_96562_.pushPose();
			RenderSystem.setShaderTexture(0, MAP);

			p_96562_.pushPose();
			//abc from window size
			//base if rotation is 0
			double playerYrotation = player.getRotationVector().y;
			double b = minecraft.getWindow().getGuiScaledHeight()/2; //y center of the map
			double c = minecraft.getWindow().getGuiScaledWidth()/2; //x center of the map
			double a = Math.sqrt(Math.pow(b, 2) + Math.pow(c, 2));
			double alpha = 90; //always 90
			double alphaInRad = 90/57.2957795d; //pi / 2
			double betaInRad = Math.acos((Math.pow(a, 2) + Math.pow(c, 2) - Math.pow(b, 2))/(2*a*c));
			double betaInDeg = betaInRad * 57.2957795d;				//1 radian = 57.2957795 deg
			
			double gammaInRad = Math.acos((Math.pow(a, 2) + Math.pow(b, 2) - Math.pow(c, 2))/(2*a*b));
			double gammaInDeg = gammaInRad * 57.2957795d;				//1 radian = 57.2957795 deg
			
			//current rotation
			double gamma2InDeg = gammaInDeg - playerYrotation;
			double beta2InRad = Math.PI - (alphaInRad) - (gamma2InDeg/57.2957795d);
			double b2 = a * Math.sin(beta2InRad) / Math.sin(Math.PI/2);
			double c2 = a * Math.sin(gamma2InDeg/57.2957795d) / Math.sin(Math.PI/2);
			
			int translatex = (int) (c - c2);
			int translatey = (int) (b - b2);
			int translatez = 0;
			
			p_96562_.translate(translatex, translatey, translatez);
			p_96562_.mulPose(Axis.ZP.rotationDegrees((float) playerYrotation));
			
			gui.blit(p_96562_, screenWidth/2 - 25, (minecraft.getWindow().getGuiScaledHeight()/2) - 26, 50, 0, 48, 49);
				int k2 = FastColor.ARGB32.color(255, 10, 4, 10);
				int p2 = FastColor.ARGB32.color(255, 252, 119, 3);
				
				
				int enemyDot = FastColor.ARGB32.color(255, 125, 19, 19);
				int playerx = screenWidth/2;
				int playerz = l + 66;
				
				List<Entity> livingEntitiesNear = player.level.getEntities(player, new AABB(player.getX() - 43.0D, player.getY() - 20.0D, player.getZ() - 43.0D, player.getX() + 43.0D, player.getY() + 20.0D, player.getZ() + 43.0D), Entity::isAlive);
				for(Entity entity : livingEntitiesNear) {
					int posx = (int) (player.getX() - entity.getX());
					int posz = (int) (player.getZ() - entity.getZ());
					String name = entity.getDisplayName().getString();
					
					if(entity instanceof Animal) {
						enemyDot = FastColor.ARGB32.color(255, 201, 201, 28);
					}else if(entity instanceof WaterAnimal) {
						enemyDot = FastColor.ARGB32.color(255, 20, 108, 145);
					}else if(entity instanceof Mob) {
						enemyDot = FastColor.ARGB32.color(255, 92, 92, 92);
					}else if(entity instanceof Player) {
						enemyDot = FastColor.ARGB32.color(255, 92, 92, 92);
					}

					gui.blit(p_96562_, playerx + posx - 4, playerz + posz - 3, 0, 187, 4, 7);
//						GuiComponent.fill(p_96562_, playerx + posx - 2, playerz + posz - 2, playerx + posx + 2, playerz + posz + 2, enemyDot);

				}

				p_96562_.popPose();


				gui.blit(p_96562_, playerx - 4, playerz - 3, 0, 180, 9, 7);
//			GuiComponent.fill(p_96562_, playerx - 2, playerz - 2, playerx + 2, playerz + 2, k2);
//			GuiComponent.fill(p_96562_, playerx - 2, playerz - 5, playerx + 2, playerz - 2, p2);
			p_96562_.popPose();
			RenderSystem.disableBlend();
			minecraft.getProfiler().pop();
		}catch (Exception e) {}
	}
}
