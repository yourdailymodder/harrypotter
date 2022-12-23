package com.minecraftserverzone.harrypotter.gui;

import com.minecraftserverzone.harrypotter.HarryPotterMod;
import com.minecraftserverzone.harrypotter.setup.capabilities.PlayerStatsProvider;
import com.minecraftserverzone.harrypotter.setup.config.HarryPotterModConfig;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.AttackIndicatorStatus;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FastColor;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;


public class Hotbar extends Screen{
	public static String[] hotbars = {"0","0","0","0","0","0","0","0","0"};
	
	public Hotbar() {
		super(Component.translatable("screen.harrypotter.hotbar"));
	}

	protected static final ResourceLocation WIDGETS_LOCATION = new ResourceLocation(HarryPotterMod.MODID, "textures/gui/widgets.png");
	   
	@SuppressWarnings("static-access")
	public static void renderHotbar(float p_93010_, PoseStack p_93011_, Gui gui, Minecraft minecraft) {
	      Player player = (Player) minecraft.getCameraEntity();
	      if (player != null) {
	    	 
	         RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
	         RenderSystem.setShader(GameRenderer::getPositionTexShader);
	         RenderSystem.setShaderTexture(0, WIDGETS_LOCATION);
	         ItemStack itemstack = player.getOffhandItem();
	         HumanoidArm humanoidarm = player.getMainArm().getOpposite();
	         
	         int i = minecraft.getInstance().getWindow().getGuiScaledWidth() / 2;
	         int j = gui.getBlitOffset();
	         int l = 91;
	         p_93011_.pushPose();
	         gui.setBlitOffset(-90);

	         
	    	  player.getCapability(PlayerStatsProvider.PLAYER_STATS_CAPABILITY).ifPresent(h -> {
					if(h.getBattleTick() == 1) {

						gui.blit(p_93011_, i - l, minecraft.getInstance().getWindow().getGuiScaledHeight() - 22, 0, 0, 182, 22);
						gui.blit(p_93011_, i - l - 1 + h.getSelectedHotbar() * 20, minecraft.getInstance().getWindow().getGuiScaledHeight() - 22 - 1, 0, 22, 24, 22);
					}
				});

	         if (!itemstack.isEmpty()) {
	            if (humanoidarm == HumanoidArm.LEFT) {
	            	gui.blit(p_93011_, i - l - 29, minecraft.getInstance().getWindow().getGuiScaledHeight() - 23, 24, 22, 29, 24);
	            } else {
	            	gui.blit(p_93011_, i + l, minecraft.getInstance().getWindow().getGuiScaledHeight() - 23, 53, 22, 29, 24);
	            }
	         }

	         
	         player.getCapability(PlayerStatsProvider.PLAYER_STATS_CAPABILITY).ifPresent(h -> {
					if(h.getBattleTick() == 1) { // && player.level.isClientSide
						gui.blit(p_93011_, i - l, minecraft.getInstance().getWindow().getGuiScaledHeight() - 22, 0, 0, 182, 22);
						gui.blit(p_93011_, i - l - 1 + h.getSelectedHotbar() * 20, minecraft.getInstance().getWindow().getGuiScaledHeight() - 22 - 1, 0, 22, 24, 22);
				        //render slots
						for(int x = 0; x < 9; x++) {
							String[] hotbar = HarryPotterModConfig.HOTBAR.get().split(";");
							if(hotbar != null) {
									if(!hotbar[x].equals("0")) {
										ResourceLocation ICONS = new ResourceLocation(HarryPotterMod.MODID, "textures/gui/spells.png"); //kepet megvaltoztatni
										RenderSystem.setShaderTexture(0, ICONS);
										
										if((Integer.parseInt(hotbar[x]) - 1) * 16 >= 256){
											gui.blit(p_93011_, i - l + 3 + x * 20,
													minecraft.getInstance().getWindow().getGuiScaledHeight() - 18 - 1, ((Integer.parseInt(hotbar[x]) - 1) - 16)*16,
													16, 16, 16);
						                 }else {
						                	 gui.blit(p_93011_, i - l + 3 + x * 20,
						                			 minecraft.getInstance().getWindow().getGuiScaledHeight() - 18 - 1,
						                			 (Integer.parseInt(hotbar[x]) - 1) * 16, 0, 16, 16);
						                 }
									
							
									
									//render cd
									if(h.getSpellsCD() != null) {
										if(h.getSpellsCD().length == 25) {
											if(h.getSpellsCD()[Integer.parseInt(hotbar[x]) - 1] > 0) {
												int k2 = FastColor.ARGB32.color(128, 0, 0, 0);
												float baseCD = HarryPotterMod.spellCooldownOrDamage(Integer.parseInt(hotbar[x]) - 1, h.getSpellsLevel()[Integer.parseInt(hotbar[x]) - 1], false);
												float currentCD = h.getSpellsCD()[Integer.parseInt(hotbar[x]) - 1];
												float a = (16f / (baseCD*2f));
												float b = (16f - (a * currentCD));
												RenderSystem.setShaderColor(1, 1, 1, 1);
										         GuiComponent.fill(p_93011_, i - l + 3 + x * 20,
										        		 (int) ((minecraft.getInstance().getWindow().getGuiScaledHeight() - 18 - 1) + (b+1)),
										        		 (i - l + 3 + x * 20) + 16,
										        		 minecraft.getInstance().getWindow().getGuiScaledHeight() - 18 - 1 + 16,
										        		 k2);
										         RenderSystem.setShaderColor(1, 1, 1, 1);
											}
										}
									}
								}
							}
						}
						
					}
				});

	         RenderSystem.setShaderTexture(0, WIDGETS_LOCATION);

	         gui.setBlitOffset(j);
	         RenderSystem.enableBlend();
	         RenderSystem.defaultBlendFunc();
	         int i1 = 1;
	         
	         for(int j1 = 0; j1 < 9; ++j1) {
	            int k1 = i - 90 + j1 * 20 + 2;
	            int l1 = minecraft.getInstance().getWindow().getGuiScaledHeight() - 16 - 3;
	            renderSlot(k1, l1, p_93010_, player, player.getInventory().items.get(j1), i1++, gui, minecraft);
	         }

	         if (!itemstack.isEmpty()) {
	            int j2 = minecraft.getInstance().getWindow().getGuiScaledHeight() - 16 - 3;
	            if (humanoidarm == HumanoidArm.LEFT) {
	               renderSlot(i - l - 26, j2, p_93010_, player, itemstack, i1++, gui, minecraft);
	            } else {
	               renderSlot(i + l + 10, j2, p_93010_, player, itemstack, i1++, gui, minecraft);
	            }
	         }

	         if (minecraft.options.attackIndicator().get() == AttackIndicatorStatus.HOTBAR) {
	            float f = minecraft.player.getAttackStrengthScale(0.0F);
	            if (f < 1.0F) {
	               int k2 = minecraft.getInstance().getWindow().getGuiScaledHeight() - 20;
	               int l2 = i + l + 6;
	               if (humanoidarm == HumanoidArm.RIGHT) {
	                  l2 = i - l - 22;
	               }

	               RenderSystem.setShaderTexture(0, GuiComponent.GUI_ICONS_LOCATION);
	               int i2 = (int)(f * 19.0F);
	               RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
	               gui.blit(p_93011_, l2, k2, 0, 94, 18, 18);
	               gui.blit(p_93011_, l2, k2 + 18 - i2, 18, 112 - i2, 18, i2);
	            }
	         }
	         
		       
	         //hover over skills in new hotbar
	         
	         p_93011_.popPose();
	         RenderSystem.disableBlend();
	      }
	   }
	
	public static void renderSlot(int p_168678_, int p_168679_, float p_168680_, Player p_168681_, ItemStack p_168682_, int p_168683_, Gui gui, Minecraft minecraft) {
	      if (!p_168682_.isEmpty()) {
	         PoseStack posestack = RenderSystem.getModelViewStack();
	         float f = (float)p_168682_.getPopTime() - p_168680_;
	         if (f > 0.0F) {
	            float f1 = 1.0F + f / 5.0F;
	            posestack.pushPose();
	            posestack.translate((double)(p_168678_ + 8), (double)(p_168679_ + 12), 0.0D);
	            posestack.scale(1.0F / f1, (f1 + 1.0F) / 2.0F, 1.0F);
	            posestack.translate((double)(-(p_168678_ + 8)), (double)(-(p_168679_ + 12)), 0.0D);
	            RenderSystem.applyModelViewMatrix();
	         }

//	         minecraft.getItemRenderer().renderAndDecorateItem(p_168681_, p_168682_, p_168678_, p_168679_, p_168683_);
	         RenderSystem.setShader(GameRenderer::getPositionColorShader);
	         if (f > 0.0F) {
	            posestack.popPose();
	            RenderSystem.applyModelViewMatrix();
	         }

//	         minecraft.getItemRenderer().renderGuiItemDecorations(minecraft.font, p_168682_, p_168678_, p_168679_);
	      }
	   }
}

