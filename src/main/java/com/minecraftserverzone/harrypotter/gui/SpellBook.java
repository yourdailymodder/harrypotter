package com.minecraftserverzone.harrypotter.gui;

import com.minecraftserverzone.harrypotter.HarryPotterMod;
import com.minecraftserverzone.harrypotter.gui.buttons.Button;
import com.minecraftserverzone.harrypotter.setup.KeyHandler;
import com.minecraftserverzone.harrypotter.setup.Registrations;
import com.minecraftserverzone.harrypotter.setup.capabilities.PlayerStatsProvider;
import com.minecraftserverzone.harrypotter.setup.config.ConfigHolder;
import com.minecraftserverzone.harrypotter.setup.config.HarryPotterModConfig;
import com.minecraftserverzone.harrypotter.setup.network.Networking;
import com.minecraftserverzone.harrypotter.setup.network.PacketData;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.math.Matrix4f;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.locale.Language;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FastColor;
import net.minecraft.world.entity.player.Player;

public class SpellBook extends Screen{
	public static final ResourceLocation SPELL_BOOK = new ResourceLocation(HarryPotterMod.MODID, "textures/gui/spell_book.png");
	public static final ResourceLocation EXP = new ResourceLocation(HarryPotterMod.MODID, "textures/gui/exp.png");
	public static int page = 0;
	private int[] spellLevel = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
	private Button[] increaseButton = new Button[25];
	public int exp = 0;
	
	
	public SpellBook() {
		super(new TranslatableComponent("screen.harrypotter.spellbook"));
	}
	
	@SuppressWarnings("resource")
	@Override
	protected void init() {
		super.init();
		Player player = Minecraft.getInstance().player;
		page = -1;
		
		int i = (this.width) / 2;
	    int j = (this.height) / 2;
	    
	    int x = 0;
	    int y = 0;
	    for(int num = 0; num < 25; num++, y++) {
	    	int numx = num;
	    	if(num % 8 == 0 && num != 0) {
	    		x+=1;
	    		y-=4;
	    	}else if(num % 4 == 0 && num != 0) {
	    		x-=1;
	    		y-=4;
	    	}
	    	increaseButton[num] = this.addRenderableWidget(new Button(i - 65 - (x * 117), j - 68 + (y * 36), 10, 10, new TranslatableComponent("screen.harrypotter.plus"), (p_86679_) -> {
	        	levelup(numx);
	        }, spellLevel[num] <= player.experienceLevel ? true : false));
			
	    }
		
		player.getCapability(PlayerStatsProvider.PLAYER_STATS_CAPABILITY).ifPresent(h -> {
			if(h.getSpellsLevel()!=null) {
				if(h.getSpellsLevel().length > 0) {
					spellLevel = h.getSpellsLevel();
				}
			}
		});
	}

	@SuppressWarnings("resource")
	private void levelup(int spellNum) {
		Player player = Minecraft.getInstance().player;
			player.getCapability(PlayerStatsProvider.PLAYER_STATS_CAPABILITY).ifPresent(h -> {
				if(player.experienceLevel >= (h.getSpellsLevel()[spellNum] == 0 ? 1 : h.getSpellsLevel()[spellNum])) {
//					System.out.println("gui "+spellLevel[spellNum]);
					spellLevel[spellNum] = h.getSpellsLevel()[spellNum]+1;
//					System.out.println("gui "+spellLevel[spellNum]);
					Networking.sendToServer(new PacketData(h.getSpellsLevel(), player.getUUID(), spellNum));
				}
			});
	}

	@Override
	public void render(PoseStack p_96562_, int mouseX, int mouseY, float partialTicks) {
		try {
			Minecraft minecraft = Minecraft.getInstance();
			int screenHeight = this.height - 63;
			int screenWidth =  this.width;
			Font fontrenderer = this.font;

			if(minecraft.player.experienceLevel != exp) {
				exp = minecraft.player.experienceLevel;
				setButtonVisibility();
			}

			//render function like in ForgeInGameGui
			minecraft.getProfiler().push("spell_book");
			p_96562_.pushPose();
		      RenderSystem.setShaderTexture(0, SPELL_BOOK);
		         int l = (this.height/2) - 105;
		         this.blit(p_96562_, screenWidth/2 - 120, l, 0, 0, 240, 181);
		         
		         //page plus
		         if(isMouseOverArea(mouseX, mouseY, screenWidth/2 + 85, l + 163, 18, 10)) {
		        	 this.blit(p_96562_, screenWidth/2 + 85, l + 163, 18, 181, 18, 10);
		         }else {
		        	 this.blit(p_96562_, screenWidth/2 + 85, l + 163, 0, 181, 18, 10);
		         }
		         
		         //page minus
		         if(isMouseOverArea(mouseX, mouseY, screenWidth/2 - 100, l + 163, 18, 10)) {
		        	 this.blit(p_96562_, screenWidth/2 - 100, l + 163, 18, 194, 18, 10);
		         }else {
		        	 this.blit(p_96562_, screenWidth/2 - 100, l + 163, 0, 194, 18, 10);
		         }
		         
		         float exp = minecraft.player.experienceLevel;
		         RenderSystem.setShaderTexture(0, EXP);
		         drawString(p_96562_, fontrenderer, new TranslatableComponent("screen.harrypotter.expleft").getString() + " " + String.valueOf((int)exp), (int) ((screenWidth/2)+ 8), ((screenHeight/2)- 65), Integer.parseInt("b8aa8a", 16));

		         int xx = 0;
		         int yy = 0;
		         for(int i = page*8; i < ((page + 1) * 8 < 25 ? (page + 1) * 8 : 25); i++, yy++) {
		 	    	if(i % 8 == 0 && i != 0) {
		 	    		xx = 0;
		 	    		yy = 0;
		 	    	}else if(i % 4 == 0 && i != 0) {
		 	    		xx = 1;
		 	    		yy = 0;
		 	    	}
//		 	    	System.out.println(xx*117);
		        	 drawString(p_96562_, fontrenderer, new TranslatableComponent("screen.harrypotter.lvl").getString() +" "+String.valueOf(spellLevel[i]), (int) ((screenWidth/2) - 107) + (xx*117), ((screenHeight/2) - 36 +(36*yy)), Integer.parseInt("b8aa8a", 16));
		         }


		         RenderSystem.setShaderTexture(0, SPELL_BOOK);
			   	 
//title color: b8aa8a //text color: 857759
			   	drawString(p_96562_, fontrenderer, new TranslatableComponent("screen.harrypotter.spellbook").getString(), (int) ((screenWidth/2)- 55) -fontrenderer.width(new TranslatableComponent("screen.harrypotter.spellbook").getString()) /2, ((screenHeight/2)- 65), Integer.parseInt("b8aa8a", 16));

//skills 1 - 20
			   	//draw itemframe
		         int i = minecraft.getWindow().getGuiScaledWidth() / 2;
		         int m = 91;
		         
		         if(page==0) {
			         drawSkillInBook(0, i - m - 20, screenHeight/2 - 55, new TranslatableComponent("screen.harrypotter.accio").getString(), new TranslatableComponent("screen.harrypotter.accio.description").getString(),fontrenderer, p_96562_, mouseX, mouseY);
			         drawSkillInBook(1, i - m - 20, screenHeight/2 - 19, new TranslatableComponent("screen.harrypotter.aqua_eructo").getString(), new TranslatableComponent("screen.harrypotter.aqua_eructo.description").getString(),fontrenderer, p_96562_, mouseX, mouseY);
			         drawSkillInBook(2, i - m - 20, screenHeight/2 + 17, new TranslatableComponent("screen.harrypotter.ascendo").getString(), new TranslatableComponent("screen.harrypotter.ascendo.description").getString(),fontrenderer, p_96562_, mouseX, mouseY);
			         drawSkillInBook(3, i - m - 20, screenHeight/2 + 53, new TranslatableComponent("screen.harrypotter.avada_kedavra").getString(), new TranslatableComponent("screen.harrypotter.avada_kedavra.description").getString(),fontrenderer, p_96562_, mouseX, mouseY);

			         drawSkillInBook(4, i - m + 97, screenHeight/2 - 55, new TranslatableComponent("screen.harrypotter.avis").getString(), new TranslatableComponent("screen.harrypotter.avis.description").getString(),fontrenderer, p_96562_, mouseX, mouseY);
			         drawSkillInBook(5, i - m + 97, screenHeight/2 - 19, new TranslatableComponent("screen.harrypotter.confringo").getString(), new TranslatableComponent("screen.harrypotter.confringo.description").getString(),fontrenderer, p_96562_, mouseX, mouseY);
			         drawSkillInBook(6, i - m + 97, screenHeight/2 + 17, new TranslatableComponent("screen.harrypotter.depulso").getString(), new TranslatableComponent("screen.harrypotter.depulso.description").getString(),fontrenderer, p_96562_, mouseX, mouseY);
			         drawSkillInBook(7, i - m + 97, screenHeight/2 + 53, new TranslatableComponent("screen.harrypotter.expecto_patronum").getString(), new TranslatableComponent("screen.harrypotter.expecto_patronum.description").getString(),fontrenderer, p_96562_, mouseX, mouseY);

			     //page 2
		         }else if(page==1) {
			         drawSkillInBook(8, i - m - 20, screenHeight/2 - 55, new TranslatableComponent("screen.harrypotter.expelliarmus").getString(), new TranslatableComponent("screen.harrypotter.expelliarmus.description").getString(),fontrenderer, p_96562_, mouseX, mouseY);
			         drawSkillInBook(9, i - m - 20, screenHeight/2 - 19, new TranslatableComponent("screen.harrypotter.fumos").getString(), new TranslatableComponent("screen.harrypotter.fumos.description").getString(),fontrenderer, p_96562_, mouseX, mouseY);
			         drawSkillInBook(10, i - m - 20, screenHeight/2 + 17, new TranslatableComponent("screen.harrypotter.glacius").getString(), new TranslatableComponent("screen.harrypotter.glacius.description").getString(),fontrenderer, p_96562_, mouseX, mouseY);
			         drawSkillInBook(11, i - m - 20, screenHeight/2 + 53, new TranslatableComponent("screen.harrypotter.herbivicus").getString(), new TranslatableComponent("screen.harrypotter.herbivicus.description").getString(),fontrenderer, p_96562_, mouseX, mouseY);

			         drawSkillInBook(12, i - m + 97, screenHeight/2 - 55, new TranslatableComponent("screen.harrypotter.incendio").getString(), new TranslatableComponent("screen.harrypotter.incendio.description").getString(),fontrenderer, p_96562_, mouseX, mouseY);
			         drawSkillInBook(13, i - m + 97, screenHeight/2 - 19, new TranslatableComponent("screen.harrypotter.lumos").getString(), new TranslatableComponent("screen.harrypotter.lumos.description").getString(),fontrenderer, p_96562_, mouseX, mouseY);
			         drawSkillInBook(14, i - m + 97, screenHeight/2 + 17, new TranslatableComponent("screen.harrypotter.melofors").getString(), new TranslatableComponent("screen.harrypotter.melofors.description").getString(),fontrenderer, p_96562_, mouseX, mouseY);
			         drawSkillInBook(15, i - m + 97, screenHeight/2 + 53, new TranslatableComponent("screen.harrypotter.mobilicorpus").getString(), new TranslatableComponent("screen.harrypotter.mobilicorpus.description").getString(),fontrenderer, p_96562_, mouseX, mouseY);

		         //page 3
				}else if(page==2) {
			         drawSkillInBook(16, i - m - 20, screenHeight/2 - 55, new TranslatableComponent("screen.harrypotter.reparo").getString(), new TranslatableComponent("screen.harrypotter.reparo.description").getString(),fontrenderer, p_96562_, mouseX, mouseY);
			         drawSkillInBook(17, i - m - 20, screenHeight/2 - 19, new TranslatableComponent("screen.harrypotter.sectumsempra").getString(), new TranslatableComponent("screen.harrypotter.sectumsempra.description").getString(),fontrenderer, p_96562_, mouseX, mouseY);
			         drawSkillInBook(18, i - m - 20, screenHeight/2 + 17, new TranslatableComponent("screen.harrypotter.vulnera_sanentur").getString(), new TranslatableComponent("screen.harrypotter.vulnera_sanentur.description").getString(),fontrenderer, p_96562_, mouseX, mouseY);
			         drawSkillInBook(19, i - m - 20, screenHeight/2 + 53, new TranslatableComponent("screen.harrypotter.wingardium_leviosa").getString(), new TranslatableComponent("screen.harrypotter.wingardium_leviosa.description").getString(),fontrenderer, p_96562_, mouseX, mouseY);

			         drawSkillInBook(20, i - m + 97, screenHeight/2 - 55, new TranslatableComponent("screen.harrypotter.episkey").getString(), new TranslatableComponent("screen.harrypotter.episkey.description").getString(),fontrenderer, p_96562_, mouseX, mouseY);
			         drawSkillInBook(21, i - m + 97, screenHeight/2 - 19, new TranslatableComponent("screen.harrypotter.alarte_ascandare").getString(), new TranslatableComponent("screen.harrypotter.alarte_ascandare.description").getString(),fontrenderer, p_96562_, mouseX, mouseY);
			         drawSkillInBook(22, i - m + 97, screenHeight/2 + 17, new TranslatableComponent("screen.harrypotter.finite").getString(), new TranslatableComponent("screen.harrypotter.finite.description").getString(),fontrenderer, p_96562_, mouseX, mouseY);
			         drawSkillInBook(23, i - m + 97, screenHeight/2 + 53, new TranslatableComponent("screen.harrypotter.evanesco").getString(), new TranslatableComponent("screen.harrypotter.evanesco.description").getString(),fontrenderer, p_96562_, mouseX, mouseY);
				}else if(page==3) {
					 drawSkillInBook(24, i - m - 20, screenHeight/2 - 55, new TranslatableComponent("screen.harrypotter.fire_storm").getString(), new TranslatableComponent("screen.harrypotter.fire_storm.description").getString(),fontrenderer, p_96562_, mouseX, mouseY);
			         
		        }
        		 /** TODO szoveg tores*/
		         
		         //hotbar 1
		         if(isMouseOverArea(mouseX, mouseY, i - m, this.height - 22, 182, 20)) {
						for(int x = 0; x < 9; x++) {
							if(isMouseOverArea(mouseX, mouseY, i - m + (20 * x), this.height - 22, 20, 20)) {
								String[] hotbar = HarryPotterModConfig.HOTBAR.get().split(";");
								String SkillName = skillInfoFromLevel(1, Integer.parseInt(hotbar[x]), 1, false);
								
								String[] SkillDescriptionx = skillInfo(2, Integer.parseInt(hotbar[x])).split(" ");
								int skillDescLength = SkillDescriptionx.length;
								String[] newSkillDescription = new String[skillDescLength];
								
								int num = 0;
								for(int desc = 0; desc < skillDescLength; desc++) {
									if(newSkillDescription[num] != null) {
										if(newSkillDescription[num].length() + SkillDescriptionx[desc].length() <= 20) {
											newSkillDescription[num] += " " + SkillDescriptionx[desc];
										}else {
											num++;
											newSkillDescription[num] = SkillDescriptionx[desc];
											
										}
									}else {
										newSkillDescription[num] = SkillDescriptionx[desc];
									}
								}
								num++;

								String[] SkillDescription = newSkillDescription;
								final int descLength = num;
								String longestTextLine = SkillDescription[0];

								for(int boxSizeX = 0; boxSizeX < descLength+1; boxSizeX++) {
									if(boxSizeX == descLength) {
										if(SkillName.length() > longestTextLine.length()) {
											longestTextLine = SkillName;
										}
									}else {
										if(SkillDescription[boxSizeX] != null) {
											if(SkillDescription[boxSizeX].length() > longestTextLine.length()) {
												longestTextLine = SkillDescription[boxSizeX];
											}
										}
									}
								}
								final String longestTextL = longestTextLine;

								if(SkillName.length() > 2) {
									minecraft.player.getCapability(PlayerStatsProvider.PLAYER_STATS_CAPABILITY).ifPresent(h -> {
										if(h.getBattleTick() > 0) {
											int k = FastColor.ARGB32.color(235, 46, 10, 101);
											int k2 = FastColor.ARGB32.color(235, 10, 4, 10);
											GuiComponent.fill(p_96562_, mouseX + 11, mouseY - 20 - (descLength * 10), mouseX + 19 + fontrenderer.width(longestTextL) , mouseY , k2);
											GuiComponent.fill(p_96562_, mouseX + 19 + fontrenderer.width(longestTextL) , mouseY - 19 - (descLength * 10), mouseX + 20 + fontrenderer.width(longestTextL) , mouseY - 1, k2);
											GuiComponent.fill(p_96562_, mouseX + 10, mouseY - 19 - (descLength * 10), mouseX + 11, mouseY - 1, k2);
											
											GuiComponent.fill(p_96562_, mouseX + 11, mouseY - 19 - (descLength * 10), mouseX + 19 + fontrenderer.width(longestTextL) , mouseY - 18 - (descLength * 10), k);
											GuiComponent.fill(p_96562_, mouseX + 11, mouseY - 1, mouseX + 19 + fontrenderer.width(longestTextL) , mouseY - 2, k);
											GuiComponent.fill(p_96562_, mouseX + 18 + fontrenderer.width(longestTextL) , mouseY - 18 - (descLength * 10), mouseX + 19 + fontrenderer.width(longestTextL) , mouseY - 2, k);
											GuiComponent.fill(p_96562_, mouseX + 11, mouseY - 18 - (descLength * 10), mouseX + 12, mouseY - 2, k);
											
											drawString(p_96562_, fontrenderer, SkillName, mouseX + 15, mouseY - 15 - (descLength * 10), Integer.parseInt("ffffff", 16));

										    for(int y = 0; y < descLength; y++){
										    	drawString(p_96562_, fontrenderer, SkillDescription[y], mouseX + 15, (mouseY - 3) + ((y) * 10) - (descLength * 10), Integer.parseInt("5858f6", 16));
										    }
										}
									});
								}
							}
						}
		         }
		         
		        //render buttons
		 		super.render(p_96562_, mouseX, mouseY, partialTicks);
		         
		         /** TODO skill description*/
		         if(isMouseOverArea(mouseX, mouseY, (screenWidth / 2) - 110, (screenHeight / 2) - 56, 220, 124)) {
		        	 xx = 0;
			         yy = 0;
			         for(int ix = page*8; ix < ((page + 1) * 8 < 25 ? (page + 1) * 8 : 25); ix++, yy++) {
			 	    	if(ix % 8 == 0 && ix != 0) {
			 	    		xx = 0;
			 	    		yy = 0;
			 	    	}else if(ix % 4 == 0 && ix != 0) {
			 	    		xx = 1;
			 	    		yy = 0;
			 	    	}

							if(isMouseOverArea(mouseX, mouseY,(screenWidth / 2) - 110 + (117 * xx), (screenHeight / 2) - 56 + (36 * yy), fontrenderer.width(skillInfoFromLevel(1, ix+1, 1, false))+20, 15)) {					        	
								String SkillName = skillInfoFromLevel(1, ix+1, 1, false);
								/** TODO description sor tores */
//								String[] SkillDescription = skillInfo(2, ix+1).split("(?<=\\G.{" + 20 + "})");
								String[] SkillDescriptionx = skillInfo(2, ix+1).split(" ");
								int skillDescLength = SkillDescriptionx.length;
								String[] newSkillDescription = new String[skillDescLength];
								
								int num = 0;
								for(int desc = 0; desc < skillDescLength; desc++) {
									if(newSkillDescription[num] != null) {
										if(newSkillDescription[num].length() + SkillDescriptionx[desc].length() <= 20) {
											newSkillDescription[num] += " " + SkillDescriptionx[desc];
										}else {
											num++;
											newSkillDescription[num] = SkillDescriptionx[desc];
											
										}
									}else {
										newSkillDescription[num] = SkillDescriptionx[desc];
									}
								}
								num++;

								String[] SkillDescription = newSkillDescription;
								final int descLength = num;
								
								String longestTextLine = SkillDescription[0];

								for(int boxSizeX = 0; boxSizeX < descLength+1; boxSizeX++) {
									if(boxSizeX == descLength) {
										if(SkillName.length() > longestTextLine.length()) {
											longestTextLine = SkillName;
										}
									}else {
										if(SkillDescription[boxSizeX] != null) {
											if(SkillDescription[boxSizeX].length() > longestTextLine.length()) {
												longestTextLine = SkillDescription[boxSizeX];
											}
										}
									}
								}
								final String longestTextL = longestTextLine;
								
								
								if(SkillName.length()>2) {
									int k = FastColor.ARGB32.color(235, 46, 10, 101);
									int k2 = FastColor.ARGB32.color(235, 10, 4, 10);
									GuiComponent.fill(p_96562_, mouseX + 11, mouseY - 20 - (descLength * 10), mouseX + 19 + fontrenderer.width(longestTextL) , mouseY , k2);
									GuiComponent.fill(p_96562_, mouseX + 19 + fontrenderer.width(longestTextL) , mouseY - 19 - (descLength * 10), mouseX + 20 + fontrenderer.width(longestTextL) , mouseY - 1, k2);
									GuiComponent.fill(p_96562_, mouseX + 10, mouseY - 19 - (descLength * 10), mouseX + 11, mouseY - 1, k2);
											
									GuiComponent.fill(p_96562_, mouseX + 11, mouseY - 19 - (descLength * 10), mouseX + 19 + fontrenderer.width(longestTextL) , mouseY - 18 - (descLength * 10), k);
									GuiComponent.fill(p_96562_, mouseX + 11, mouseY - 1, mouseX + 19 + fontrenderer.width(longestTextL) , mouseY - 2, k);
									GuiComponent.fill(p_96562_, mouseX + 18 + fontrenderer.width(longestTextL) , mouseY - 18 - (descLength * 10), mouseX + 19 + fontrenderer.width(longestTextL) , mouseY - 2, k);
									GuiComponent.fill(p_96562_, mouseX + 11, mouseY - 18 - (descLength * 10), mouseX + 12, mouseY - 2, k);
											
									drawString(p_96562_, fontrenderer, SkillName, mouseX + 15, mouseY - 15 - (descLength * 10), Integer.parseInt("ffffff", 16));

									for(int y = 0; y < descLength; y++){
										drawString(p_96562_, fontrenderer, SkillDescription[y], mouseX + 15, (mouseY - 3) + ((y) * 10) - (descLength * 10), Integer.parseInt("5858f6", 16));
									}
								}
							}
						}
		         }
		         
		         /** TODO level attributes */
		         if(isMouseOverArea(mouseX, mouseY, (screenWidth / 2) - 110, (screenHeight / 2) - 36, 220, 124)) {
		        	 xx = 0;
			         yy = 0;
			         for(int ix = page*8; ix < ((page + 1) * 8 < 25 ? (page + 1) * 8 : 25); ix++, yy++) {
			 	    	if(ix % 8 == 0 && ix != 0) {
			 	    		xx = 0;
			 	    		yy = 0;
			 	    	}else if(ix % 4 == 0 && ix != 0) {
			 	    		xx = 1;
			 	    		yy = 0;
			 	    	}

							if(isMouseOverArea(mouseX, mouseY,(screenWidth / 2) - 110 + (117 * xx), (screenHeight / 2) - 36 + (36 * yy), 35 + 20, 15)) {
								boolean canLevelUp = minecraft.player.experienceLevel >= spellLevel[ix] && minecraft.player.experienceLevel != 0;
								String SkillName = skillInfoFromLevel(1, ix+1, spellLevel[ix], false);
								String SkillInfo2 = skillInfoFromLevel(2, ix+1, spellLevel[ix], false);
								String SkillInfo3 = skillInfoFromLevel(3, ix+1, spellLevel[ix], false);
								String SkillInfo4 = skillInfoFromLevel(4, ix+1, spellLevel[ix], false);
								String SkillInfo2plus = skillInfoFromLevel(2, ix+1, spellLevel[ix], true);
								String SkillInfo3plus = skillInfoFromLevel(3, ix+1, spellLevel[ix], true);
								String SkillInfo4plus = skillInfoFromLevel(4, ix+1, spellLevel[ix], true);
								
								int skillInfoNum = SkillInfo2.length() > 1 ? SkillInfo3.length() > 1 ? SkillInfo4.length() > 1 ? 4 : 3 : 2 : 1;

//								String[] SkillDescription = "000000000000000000000000000000000000000000000000000000000000".split("(?<=\\G.{" + 20 + "})");
								String[] SkillDescription = {
										SkillName,
										SkillInfo2,
										SkillInfo3.length() > 2 ? SkillInfo3 : SkillInfo4.length() > 2 ? SkillInfo4 : "",
										SkillInfo4.length() > 2 ? SkillInfo4 : ""
									};
								String[] LevelUpSkillDescription = SkillDescription;
								
								if(canLevelUp && (isMouseOverArea(mouseX, mouseY,(screenWidth / 2) - 110 + (117 * xx) + 45, (screenHeight / 2) - 36 + (36 * yy), 10, 10))) {
									LevelUpSkillDescription[1] += SkillInfo2plus;
									LevelUpSkillDescription[2] += SkillInfo3plus;
									LevelUpSkillDescription[3] += SkillInfo4plus;
								}
								
								String longestTextL = LevelUpSkillDescription[0];
								for(int boxSizeX = 0; boxSizeX < LevelUpSkillDescription.length; boxSizeX++) {
									if(boxSizeX == LevelUpSkillDescription.length - 1) {
										if(SkillName.length() > longestTextL.length()) {
											longestTextL = SkillName;
										}
									}else {
										if(LevelUpSkillDescription[boxSizeX] != null) {
											if(LevelUpSkillDescription[boxSizeX].length() > longestTextL.length()) {
												longestTextL = LevelUpSkillDescription[boxSizeX];
											}
										}
									}
								}
								
								
								if(SkillName.length()>2) {
									int k = FastColor.ARGB32.color(235, 46, 10, 101);
									int k2 = FastColor.ARGB32.color(235, 10, 4, 10);
									GuiComponent.fill(p_96562_, mouseX + 11, mouseY - 20 - (skillInfoNum * 10), mouseX + 19 + fontrenderer.width(longestTextL) , mouseY , k2);
									GuiComponent.fill(p_96562_, mouseX + 19 + fontrenderer.width(longestTextL) , mouseY - 19 - (skillInfoNum * 10), mouseX + 20 + fontrenderer.width(longestTextL) , mouseY - 1, k2);
									GuiComponent.fill(p_96562_, mouseX + 10, mouseY - 19 - (skillInfoNum * 10), mouseX + 11, mouseY - 1, k2);
											
									GuiComponent.fill(p_96562_, mouseX + 11, mouseY - 19 - (skillInfoNum * 10), mouseX + 19 + fontrenderer.width(longestTextL) , mouseY - 18 - (skillInfoNum * 10), k);
									GuiComponent.fill(p_96562_, mouseX + 11, mouseY - 1, mouseX + 19 + fontrenderer.width(longestTextL) , mouseY - 2, k);
									GuiComponent.fill(p_96562_, mouseX + 18 + fontrenderer.width(longestTextL) , mouseY - 18 - (skillInfoNum * 10), mouseX + 19 + fontrenderer.width(longestTextL) , mouseY - 2, k);
									GuiComponent.fill(p_96562_, mouseX + 11, mouseY - 18 - (skillInfoNum * 10), mouseX + 12, mouseY - 2, k);
											
									drawString(p_96562_, fontrenderer, SkillName, mouseX + 15, mouseY - 15 - (skillInfoNum * 10), Integer.parseInt("ffffff", 16));

									drawString(p_96562_, fontrenderer, new TranslatableComponent("screen.harrypotter.lvl").getString()+" "+spellLevel[ix], mouseX + 15, (mouseY - 3) + ((0) * 10) - (skillInfoNum * 10), Integer.parseInt("a8a8a8", 16));
									
									if(SkillInfo2!=null) {
										drawString(p_96562_, fontrenderer, SkillInfo2, mouseX + 15, (mouseY - 3) + ((1) * 10) - (skillInfoNum * 10), Integer.parseInt("5858f6", 16));
									}
									if(SkillInfo3!=null) {
										drawString(p_96562_, fontrenderer, SkillInfo3, mouseX + 15, (mouseY - 3) + ((2) * 10) - (skillInfoNum * 10), Integer.parseInt("5858f6", 16));
									}
									if(SkillInfo4!=null) {
										drawString(p_96562_, fontrenderer, SkillInfo4, mouseX + 15, (mouseY - 3) + ((3) * 10) - (skillInfoNum * 10), Integer.parseInt("5858f6", 16));
									}
									
//									drawString(p_96562_, fontrenderer, new TranslatableComponent("screen.harrypotter.cooldown").getString() + cooldown, mouseX + 15, (mouseY - 3) + ((2) * 10) - (descLength * 10), Integer.parseInt("5858f6", 16));
									
									//level up text
									if(canLevelUp && (isMouseOverArea(mouseX, mouseY,(screenWidth / 2) - 110 + (117 * xx) + 45, (screenHeight / 2) - 36 + (36 * yy), 10, 10))) {
										drawString(p_96562_, fontrenderer, new TranslatableComponent("screen.harrypotter.side_arrow").getString() + (spellLevel[ix]+1), mouseX + 15 + fontrenderer.width(new TranslatableComponent("screen.harrypotter.lvl").getString()+" "+spellLevel[ix]), (mouseY - 3) + ((0) * 10) - (skillInfoNum * 10), Integer.parseInt("00aa00", 16));
										drawString(p_96562_, fontrenderer, SkillInfo2plus, mouseX + 15 + fontrenderer.width(SkillInfo2), (mouseY - 3) + ((1) * 10) - (skillInfoNum * 10), Integer.parseInt("00aa00", 16));
										drawString(p_96562_, fontrenderer, SkillInfo3plus, mouseX + 15 + fontrenderer.width(SkillInfo3), (mouseY - 3) + ((2) * 10) - (skillInfoNum * 10), Integer.parseInt("00aa00", 16));
										drawString(p_96562_, fontrenderer, SkillInfo4plus, mouseX + 15 + fontrenderer.width(SkillInfo4), (mouseY - 3) + ((3) * 10) - (skillInfoNum * 10), Integer.parseInt("00aa00", 16));
									}
								}
							}
						}
		         }
		         
		         RenderSystem.disableBlend();
			  p_96562_.popPose();
		      minecraft.getProfiler().pop();	
		}catch(Exception e) {}
		
		if(page == -1) {
			page = 0;
			setButtonVisibility();
		}
	}
	
	/** TODO Skill info from level */
	public String skillInfoFromLevel(int nameOrDescription, int number, int level, boolean canLevelUp) {
		String info = "";
			if(number==1) {
				if(nameOrDescription == 1) {
					info = new TranslatableComponent("screen.harrypotter.accio").getString();
				}else if(nameOrDescription == 2) {
					info = new TranslatableComponent("screen.harrypotter.cooldown").getString() + Math.max(10f - ((level - 1) / 2f), 1);
					if(canLevelUp) {
						info = new TranslatableComponent("screen.harrypotter.side_arrow").getString() + Math.max(10f - ((level) / 2f), 1); // +" sec"
					}else {
//						info += " sec";
					}
				}else if(nameOrDescription == 3) {
					info = "";
				}else if(nameOrDescription == 4) {
					info = "";
				}
				
			}else if(number == 2) {
				if(nameOrDescription == 1) {
					info = new TranslatableComponent("screen.harrypotter.aqua_eructo").getString();
				}else if(nameOrDescription == 2) {
					if(!canLevelUp) {
						info = new TranslatableComponent("screen.harrypotter.chance_to_spawn").getString();
					}
				}else if(nameOrDescription == 3) {
					if(canLevelUp) {
						info = new TranslatableComponent("screen.harrypotter.side_arrow").getString() + Math.min(1f + ((level - 1) / 2f), 100) + new TranslatableComponent("screen.harrypotter.percent").getString();
					}else {
						info = new TranslatableComponent("screen.harrypotter.water").getString() + Math.min(1f + ((level) / 2f), 100) + new TranslatableComponent("screen.harrypotter.percent").getString();
					}
				}else if(nameOrDescription == 4) {
					info = "";
				}
				
			}else if(number == 3) {
				if(nameOrDescription == 1) {
					info = new TranslatableComponent("screen.harrypotter.ascendo").getString();
				}else if(nameOrDescription == 2) {
					info = new TranslatableComponent("screen.harrypotter.cooldown").getString() + Math.max(10f - ((level - 1) / 2f), 1);
					if(canLevelUp) {
						info = new TranslatableComponent("screen.harrypotter.side_arrow").getString() + Math.max(10f - ((level) / 2f), 1);
					}else {
//						info += " sec";
					}
				}else if(nameOrDescription == 3) {
					info = "";
				}else if(nameOrDescription == 4) {
					info = "";
				}

			}else if(number == 4) {
				if(nameOrDescription == 1) {
					info = new TranslatableComponent("screen.harrypotter.avada_kedavra").getString();
				}else if(nameOrDescription == 2) {
					info = new TranslatableComponent("screen.harrypotter.cooldown").getString() + Math.max(60f - ((level - 1) / 2f), 1);
					if(canLevelUp) {
						info = new TranslatableComponent("screen.harrypotter.side_arrow").getString() + Math.max(60f - ((level) / 2f), 1);
					}else {
//						info += " sec";
					}
				}else if(nameOrDescription == 3) {
					info = "";
				}else if(nameOrDescription == 4) {
					info = "";
				}

			}else if(number == 5) {
				if(nameOrDescription == 1) {
					info = new TranslatableComponent("screen.harrypotter.avis").getString();
				}else if(nameOrDescription == 2) {
					info = new TranslatableComponent("screen.harrypotter.dot").getString() + Math.max(7f + ((level - 1) / 2f), 1);
					if(canLevelUp) {
						info = new TranslatableComponent("screen.harrypotter.side_arrow").getString() + Math.max(7f + ((level) / 2f), 1);
					}
				}else if(nameOrDescription == 3) {
					info = new TranslatableComponent("screen.harrypotter.cooldown").getString() + Math.max(10f - ((level - 1) / 2f), 1);
					if(canLevelUp) {
						info = new TranslatableComponent("screen.harrypotter.side_arrow").getString() + Math.max(10f - ((level) / 2f), 1);
					}else {
//						info += " sec";
					}
				}else if(nameOrDescription == 4) {
					info = "";
				}

			}else if(number == 6) {
				if(nameOrDescription == 1) {
					info = new TranslatableComponent("screen.harrypotter.confringo").getString();
				}else if(nameOrDescription == 2) {
					info = new TranslatableComponent("screen.harrypotter.aoe_damage").getString() + Math.max(4f + ((level - 1) / 2f), 1);
					if(canLevelUp) {
						info = new TranslatableComponent("screen.harrypotter.side_arrow").getString() + Math.max(4f + ((level) / 2f), 1);
					}
				}else if(nameOrDescription == 3) {
					info = new TranslatableComponent("screen.harrypotter.cooldown").getString() + Math.max(15f - ((level - 1) / 2f), 1);
					if(canLevelUp) {
						info = new TranslatableComponent("screen.harrypotter.side_arrow").getString() + Math.max(15f - ((level) / 2f), 1);
					}else {
//						info += " sec";
					}
				}else if(nameOrDescription == 4) {
					info = "";
				}

			}else if(number == 7) {
				if(nameOrDescription == 1) {
					info = new TranslatableComponent("screen.harrypotter.depulso").getString();
				}else if(nameOrDescription == 2) {
					info = new TranslatableComponent("screen.harrypotter.cooldown").getString() + Math.max(10f - ((level - 1) / 2f), 1);
					if(canLevelUp) {
						info = new TranslatableComponent("screen.harrypotter.side_arrow").getString() + Math.max(10f - ((level) / 2f), 1);
					}else {
//						info += " sec";
					}
				}else if(nameOrDescription == 3) {
					info = "";
				}else if(nameOrDescription == 4) {
					info = "";
				}

			}else if(number == 8) {
				if(nameOrDescription == 1) {
					info = new TranslatableComponent("screen.harrypotter.expecto_patronum").getString();
				}else if(nameOrDescription == 2) {
					info = new TranslatableComponent("screen.harrypotter.cooldown").getString() + Math.max(15f - ((level - 1) / 2f), 1);
					if(canLevelUp) {
						info = new TranslatableComponent("screen.harrypotter.side_arrow").getString() + Math.max(15f - ((level) / 2f), 1);
					}else {
//						info += " sec";
					}
				}else if(nameOrDescription == 3) {
					info = "";
				}else if(nameOrDescription == 4) {
					info = "";
				}
				
			}else if(number == 9) {
				if(nameOrDescription == 1) {
					info = new TranslatableComponent("screen.harrypotter.expelliarmus").getString();
				}else if(nameOrDescription == 2) {
					info = new TranslatableComponent("screen.harrypotter.cooldown").getString() + Math.max(30f - ((level - 1) / 2f), 1);
					if(canLevelUp) {
						info = new TranslatableComponent("screen.harrypotter.side_arrow").getString() + Math.max(30f - ((level) / 2f), 1);
					}else {
//						info += " sec";
					}
				}else if(nameOrDescription == 3) {
					info = "";
				}else if(nameOrDescription == 4) {
					info = "";
				}
				
			}else if(number == 10) {
				if(nameOrDescription == 1) {
					info = new TranslatableComponent("screen.harrypotter.fumos").getString();
				}else if(nameOrDescription == 2) {
					info = new TranslatableComponent("screen.harrypotter.cooldown").getString() + Math.max(30f - ((level - 1) / 2f), 1);
					if(canLevelUp) {
						info = new TranslatableComponent("screen.harrypotter.side_arrow").getString() + Math.max(30f - ((level) / 2f), 1);
					}else {
//						info += " sec";
					}
				}else if(nameOrDescription == 3) {
					info = "";
				}else if(nameOrDescription == 4) {
					info = "";
				}
				
			}else if(number == 11) {
				if(nameOrDescription == 1) {
					info = new TranslatableComponent("screen.harrypotter.glacius").getString();
				}else if(nameOrDescription == 2) {
					info = new TranslatableComponent("screen.harrypotter.damage").getString() + Math.max(4f + ((level - 1) / 2f), 1);
					if(canLevelUp) {
						info = new TranslatableComponent("screen.harrypotter.side_arrow").getString() + Math.max(4f + ((level) / 2f), 1);
					}
				}else if(nameOrDescription == 3) {
					info = new TranslatableComponent("screen.harrypotter.cooldown").getString() + Math.max(10f - ((level - 1) / 2f), 1);
					if(canLevelUp) {
						info = new TranslatableComponent("screen.harrypotter.side_arrow").getString() + Math.max(10f - ((level) / 2f), 1);
					}else {
//						info += " sec";
					}
				}else if(nameOrDescription == 4) {
					if(!canLevelUp) {
						info = new TranslatableComponent("screen.harrypotter.frozen").getString();
					}
				}

			}else if(number == 12) {
				if(nameOrDescription == 1) {
					info = new TranslatableComponent("screen.harrypotter.herbivicus").getString();
				}else if(nameOrDescription == 2) {
					info = new TranslatableComponent("screen.harrypotter.cooldown").getString() + Math.max(10f - ((level - 1) / 2f), 1);
					if(canLevelUp) {
						info = new TranslatableComponent("screen.harrypotter.side_arrow").getString() + Math.max(10f - ((level) / 2f), 1);
					}else {
//						info += " sec";
					}
				}else if(nameOrDescription == 3) {
					info = "";
				}else if(nameOrDescription == 4) {
					info = "";
				}
				
			}else if(number == 13) {
				if(nameOrDescription == 1) {
					info = new TranslatableComponent("screen.harrypotter.incendio").getString();
				}else if(nameOrDescription == 2) {
					info = new TranslatableComponent("screen.harrypotter.damage").getString() + Math.max(5f + ((level - 1) / 2f), 1);
					if(canLevelUp) {
						info = new TranslatableComponent("screen.harrypotter.side_arrow").getString() + Math.max(5f + ((level) / 2f), 1);
					}
				}else if(nameOrDescription == 3) {
					info = new TranslatableComponent("screen.harrypotter.cooldown").getString() + Math.max(10f - ((level - 1) / 2f), 1);
					if(canLevelUp) {
						info = new TranslatableComponent("screen.harrypotter.side_arrow").getString() + Math.max(10f - ((level) / 2f), 1);
					}else {
//						info += " sec";
					}
				}else if(nameOrDescription == 4) {
					if(!canLevelUp) {
						info = new TranslatableComponent("screen.harrypotter.burn").getString();
					}
				}
				
			}else if(number == 14) {
				if(nameOrDescription == 1) {
					info = new TranslatableComponent("screen.harrypotter.lumos").getString();
				}else if(nameOrDescription == 2) {
					info = new TranslatableComponent("screen.harrypotter.cooldown").getString() + Math.max(5f - ((level - 1) / 2f), 1);
					if(canLevelUp) {
						info = new TranslatableComponent("screen.harrypotter.side_arrow").getString() + Math.max(5f - ((level) / 2f), 1);
					}else {
//						info += " sec";
					}
				}else if(nameOrDescription == 3) {
					info = "";
				}else if(nameOrDescription == 4) {
					info = "";
				}
				
			}else if(number == 15) {
				if(nameOrDescription == 1) {
					info = new TranslatableComponent("screen.harrypotter.melofors").getString();
				}else if(nameOrDescription == 2) {
					info = new TranslatableComponent("screen.harrypotter.cooldown").getString() + Math.max(30f - ((level - 1) / 2f), 1);
					if(canLevelUp) {
						info = new TranslatableComponent("screen.harrypotter.side_arrow").getString() + Math.max(30f - ((level) / 2f), 1);
					}else {
//						info += " sec";
					}
				}else if(nameOrDescription == 3) {
					info = "";
				}else if(nameOrDescription == 4) {
					info = "";
				}
				
			}else if(number == 16) {
				if(nameOrDescription == 1) {
					info = new TranslatableComponent("screen.harrypotter.mobilicorpus").getString();
				}else if(nameOrDescription == 2) {
					info = new TranslatableComponent("screen.harrypotter.cooldown").getString() + Math.max(30f - ((level - 1) / 2f), 1);
					if(canLevelUp) {
						info = new TranslatableComponent("screen.harrypotter.side_arrow").getString() + Math.max(30f - ((level) / 2f), 1);
					}else {
//						info += " sec";
					}
				}else if(nameOrDescription == 3) {
					info = "";
				}else if(nameOrDescription == 4) {
					info = "";
				}
				
			}else if(number == 17) {
				if(nameOrDescription == 1) {
					info = new TranslatableComponent("screen.harrypotter.reparo").getString();
				}else if(nameOrDescription == 2) {
					info = new TranslatableComponent("screen.harrypotter.cooldown").getString() + Math.max(30f - ((level - 1) / 2f), 1);
					if(canLevelUp) {
						info = new TranslatableComponent("screen.harrypotter.side_arrow").getString() + Math.max(30f - ((level) / 2f), 1);
					}else {
//						info += " sec";
					}
				}else if(nameOrDescription == 3) {
					info = "";
				}else if(nameOrDescription == 4) {
					info = "";
				}
				
			}else if(number == 18) {
				if(nameOrDescription == 1) {
					info = new TranslatableComponent("screen.harrypotter.sectumsempra").getString();
				}else if(nameOrDescription == 2) {
					info = new TranslatableComponent("screen.harrypotter.damage").getString() + Math.max(15f + ((level - 1) / 2f), 1);
					if(canLevelUp) {
						info = new TranslatableComponent("screen.harrypotter.side_arrow").getString() + Math.max(15f + ((level) / 2f), 1);
					}
				}else if(nameOrDescription == 3) {
					info = new TranslatableComponent("screen.harrypotter.cooldown").getString() + Math.max(30f - ((level - 1) / 2f), 1);
					if(canLevelUp) {
						info = new TranslatableComponent("screen.harrypotter.side_arrow").getString() + Math.max(30f - ((level) / 2f), 1);
					}else {
//						info += " sec";
					}
				}else if(nameOrDescription == 4) {
					info = "";
				}

			}else if(number == 19) {
				if(nameOrDescription == 1) {
					info = new TranslatableComponent("screen.harrypotter.vulnera_sanentur").getString();
				}else if(nameOrDescription == 2) {
					info = new TranslatableComponent("screen.harrypotter.cooldown").getString() + Math.max(30f - ((level - 1) / 2f), 1);
					if(canLevelUp) {
						info = new TranslatableComponent("screen.harrypotter.side_arrow").getString() + Math.max(30f - ((level) / 2f), 1);
					}else {
//						info += " sec";
					}
				}else if(nameOrDescription == 3) {
					info = "";
				}else if(nameOrDescription == 4) {
					info = "";
				}
				
			}else if(number == 20) {
				if(nameOrDescription == 1) {
					info = new TranslatableComponent("screen.harrypotter.wingardium_leviosa").getString();
				}else if(nameOrDescription == 2) {
					info = new TranslatableComponent("screen.harrypotter.cooldown").getString() + Math.max(30f - ((level - 1) / 2f), 1);
					if(canLevelUp) {
						info = new TranslatableComponent("screen.harrypotter.side_arrow").getString() + Math.max(30f - ((level) / 2f), 1);
					}else {
//						info += " sec";
					}
				}else if(nameOrDescription == 3) {
					info = "";
				}else if(nameOrDescription == 4) {
					info = "";
				}
		}else if(number == 21) {
			if(nameOrDescription == 1) {
				info = new TranslatableComponent("screen.harrypotter.episkey").getString();
			}else if(nameOrDescription == 2) {
				info = new TranslatableComponent("screen.harrypotter.cooldown").getString() + Math.max(15f - ((level - 1) / 2f), 1);
				if(canLevelUp) {
					info = new TranslatableComponent("screen.harrypotter.side_arrow").getString() + Math.max(15f - (level / 2f), 1);
				}else {
				}
			}else if(nameOrDescription == 3) {
				info = "";
			}else if(nameOrDescription == 4) {
				info = "";
			}
		}else if(number == 22) {
			if(nameOrDescription == 1) {
				info = new TranslatableComponent("screen.harrypotter.alarte_ascandare").getString();
			}else if(nameOrDescription == 2) {
				info = new TranslatableComponent("screen.harrypotter.cooldown").getString() + Math.max(10f - ((level - 1) / 2f), 1);
				if(canLevelUp) {
					info = new TranslatableComponent("screen.harrypotter.side_arrow").getString() + Math.max(10f - (level / 2f), 1);
				}else {
				}
			}else if(nameOrDescription == 3) {
				info = "";
			}else if(nameOrDescription == 4) {
				info = "";
			}
		}else if(number == 23) {
			if(nameOrDescription == 1) {
				info = new TranslatableComponent("screen.harrypotter.finite").getString();
			}else if(nameOrDescription == 2) {
				info = new TranslatableComponent("screen.harrypotter.cooldown").getString() + Math.max(15f - ((level - 1) / 2f), 1);
				if(canLevelUp) {
					info = new TranslatableComponent("screen.harrypotter.side_arrow").getString() + Math.max(15f - (level / 2f), 1);
				}else {
				}
			}else if(nameOrDescription == 3) {
				info = "";
			}else if(nameOrDescription == 4) {
				info = "";
			}
		}else if(number == 24) {
			if(nameOrDescription == 1) {
				info = new TranslatableComponent("screen.harrypotter.evanesco").getString();
			}else if(nameOrDescription == 2) {
				info = new TranslatableComponent("screen.harrypotter.cooldown").getString() + Math.max(60f - ((level - 1) / 2f), 1);
				if(canLevelUp) {
					info = new TranslatableComponent("screen.harrypotter.side_arrow").getString() + Math.max(60f - (level / 2f), 1);
				}else {
				}
			}else if(nameOrDescription == 3) {
				info = "";
			}else if(nameOrDescription == 4) {
				info = "";
			}
		}else if(number == 25) {
			if(nameOrDescription == 1) {
				info = new TranslatableComponent("screen.harrypotter.fire_storm").getString();
			}else if(nameOrDescription == 2) {
				info = new TranslatableComponent("screen.harrypotter.damage").getString() + Math.max(4f + ((level - 1) / 2f), 1);
				if(canLevelUp) {
					info = new TranslatableComponent("screen.harrypotter.side_arrow").getString() + Math.max(4f + ((level) / 2f), 1);
				}
			}else if(nameOrDescription == 3) {
				info = new TranslatableComponent("screen.harrypotter.cooldown").getString() + Math.max(30f - ((level - 1) / 2f), 1);
				if(canLevelUp) {
					info = new TranslatableComponent("screen.harrypotter.side_arrow").getString() + Math.max(30f - ((level) / 2f), 1);
				}else {
//					info += " sec";
				}
			}else if(nameOrDescription == 4) {
				info = "";
			}
		}

		return info;
	}
	
	public String skillInfo(int nameOrDescription, int number) {
		String info = "";
		
		if(nameOrDescription == 1) {
		}else {
			if(number==1) {
				info = new TranslatableComponent("screen.harrypotter.accio.description").getString();
			}else if(number == 2) {
				info = new TranslatableComponent("screen.harrypotter.aqua_eructo.description").getString();
			}else if(number == 3) {
				info = new TranslatableComponent("screen.harrypotter.ascendo.description").getString();
			}else if(number == 4) {
				info = new TranslatableComponent("screen.harrypotter.avada_kedavra.description").getString();
			}else if(number == 5) {
				info = new TranslatableComponent("screen.harrypotter.avis.description").getString();
			}else if(number == 6) {
				info = new TranslatableComponent("screen.harrypotter.confringo.description").getString();
			}else if(number == 7) {
				info = new TranslatableComponent("screen.harrypotter.depulso.description").getString();
			}else if(number == 8) {
				info = new TranslatableComponent("screen.harrypotter.expecto_patronum.description").getString();
			}else if(number == 9) {
				info = new TranslatableComponent("screen.harrypotter.expelliarmus.description").getString();
			}else if(number == 10) {
				info = new TranslatableComponent("screen.harrypotter.fumos.description").getString();
			}else if(number == 11) {
				info = new TranslatableComponent("screen.harrypotter.glacius.description").getString();
			}else if(number == 12) {
				info = new TranslatableComponent("screen.harrypotter.herbivicus.description").getString();
			}else if(number == 13) {
				info = new TranslatableComponent("screen.harrypotter.incendio.description").getString();
			}else if(number == 14) {
				info = new TranslatableComponent("screen.harrypotter.lumos.description").getString();
			}else if(number == 15) {
				info = new TranslatableComponent("screen.harrypotter.melofors.description").getString();
			}else if(number == 16) {
				info = new TranslatableComponent("screen.harrypotter.mobilicorpus.description").getString();
			}else if(number == 17) {
				info = new TranslatableComponent("screen.harrypotter.reparo.description").getString();
			}else if(number == 18) {
				info = new TranslatableComponent("screen.harrypotter.sectumsempra.description").getString();
			}else if(number == 19) {
				info = new TranslatableComponent("screen.harrypotter.vulnera_sanentur.description").getString();
			}else if(number == 20) {
				info = new TranslatableComponent("screen.harrypotter.wingardium_leviosa.description").getString();	
			}else if(number == 21) {
				info = new TranslatableComponent("screen.harrypotter.episkey.description").getString();
			}else if(number == 22) {
				info = new TranslatableComponent("screen.harrypotter.alarte_ascandare.description").getString();	
			}else if(number == 23) {
				info = new TranslatableComponent("screen.harrypotter.finite.description").getString();	
			}else if(number == 24) {
				info = new TranslatableComponent("screen.harrypotter.evanesco.description").getString();	
			}else if(number == 25) {
				info = new TranslatableComponent("screen.harrypotter.fire_storm.description").getString();	
			}
			
		}

		return info;
	}

	public void drawSkillInBook(int skillnum, int posx, int posy, String skillname, String description, Font fontrenderer, PoseStack p_96562_, int mouseX , int mouseY) {
		Minecraft minecraft = Minecraft.getInstance();
		RenderSystem.setShaderTexture(0, SPELL_BOOK);

		//draw itemframe
	   	RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
         RenderSystem.setShader(GameRenderer::getPositionTexShader);
         ResourceLocation ICONS = new ResourceLocation(HarryPotterMod.MODID, "textures/gui/spells.png"); //kepet megvaltoztatni
         RenderSystem.setShaderTexture(0, ICONS);

         if(skillnum*16 >= 256){
        	 this.blit(p_96562_, posx + 1, posy - 1, (skillnum-16)*16, 16, 16, 16);
         }else {
        	 this.blit(p_96562_, posx + 1, posy - 1, skillnum *16, 0, 16, 16); 
         }

         RenderSystem.setShaderTexture(0, Hotbar.WIDGETS_LOCATION);
 	   	//draw skill - that can be dragged
 	   	//draw string name
          drawString(p_96562_, fontrenderer, skillname, (int) ((posx)+18), ((posy) + 4), Integer.parseInt("786848", 16));
 		 //draw string description
          String[] descriptionarray = null;
          /** TODO description*/

          RenderSystem.setShaderTexture(0, ICONS);
         minecraft.player.getCapability(PlayerStatsProvider.PLAYER_STATS_CAPABILITY).ifPresent(h -> {
        	 if(h.getClickedSkill() != 0 ) {
        		 //render selected skill where the mouse is
        		 RenderSystem.setShaderTexture(0, ICONS);

        		 if((h.getClickedSkill() - 1) * 16 >= 256){
                	 this.blit(p_96562_, mouseX - 11, mouseY - 11, ((h.getClickedSkill() - 1) - 16)*16, 16, 16, 16);
                 }else {
                	 this.blit(p_96562_, mouseX - 11, mouseY - 11, (h.getClickedSkill()-1) *16, 0, 16, 16);
                 }
        	 }
		 });
	}

	public static void drawString(PoseStack p_93237_, Font p_93238_, String p_93239_, int p_93240_, int p_93241_, int p_93242_) {
		drawShadow(p_93238_, p_93237_, p_93239_, (float)p_93240_, (float)p_93241_, p_93242_);
	}

	public static int drawShadow(Font font, PoseStack p_92751_, String p_92752_, float p_92753_, float p_92754_, int p_92755_) {
		return drawInternal(font, p_92752_, p_92753_, p_92754_, p_92755_, p_92751_.last().pose(), false, isBidirectional());
	}

	private static int drawInternal(Font font, String p_92804_, float p_92805_, float p_92806_, int p_92807_, Matrix4f p_92808_, boolean p_92809_, boolean p_92810_) {
	      if (p_92804_ == null) {
	         return 0;
	      } else {
	         MultiBufferSource.BufferSource multibuffersource$buffersource = MultiBufferSource.immediate(Tesselator.getInstance().getBuilder());
	         int i = font.drawInBatch(p_92804_, p_92805_, p_92806_, p_92807_, p_92809_, p_92808_, multibuffersource$buffersource, true, 0, 15728880, p_92810_);
	         multibuffersource$buffersource.endBatch();
	         return i;
	      }
	   }
	
	public static boolean isBidirectional() {
	      return Language.getInstance().isDefaultRightToLeft();
	   }
	
	private boolean isMouseOverArea(double mouseX, double mouseY, int posX, int posY, int sizeX, int sizeY) {
		return (mouseX >= posX && mouseX < posX + sizeX && mouseY >= posY && mouseY < posY + sizeY);
	}
	
	@SuppressWarnings("resource")
	@Override
	public boolean mouseClicked(double p_94695_, double p_94696_, int p_94697_) {
		Player player = Minecraft.getInstance().player;
		int screenHeight = (this.height - 63) / 2;
		int screenWidth =  (this.width / 2);
//		(screenWidth - 92), (screenHeight - 36 +(36*i))
		int i = this.width / 2;
		int w = (this.height - 63) / 2;
        int m = 91;
		
        //meg kell 1 olyan hogy droppolni a skills a new hotbar-rol
        if(!isMouseOverArea(p_94695_, p_94696_, i - m, this.height - 22, 182, 22)) {
			//remove selected skill if the click is not on the hotbar
			player.getCapability(PlayerStatsProvider.PLAYER_STATS_CAPABILITY).ifPresent(h -> {
				if(h.getClickedSkill() > 0) {
//					release clicked skill
					h.setClickedSkill(0);
				}
			});
		}
        
        
      //save new hotbar if skill is released on the hotbar while a skill is selected
//		gui.blit(p_93011_, i - l, minecraft.getInstance().getWindow().getGuiScaledHeight() - 22, 0, 0, 182, 22);
		if(isMouseOverArea(p_94695_, p_94696_, i - m, this.height - 22, 182, 20)) {
			if(player.getMainHandItem().is(Registrations.APPRENTICE_WAND.get())) {
				for(int num = 0; num < 9; num++) {
					int x = num;
					//save selected item
					player.getCapability(PlayerStatsProvider.PLAYER_STATS_CAPABILITY).ifPresent(h -> {
						if(h.getClickedSkill() > 0 && h.getClickedSkill() < 26) {
							if(isMouseOverArea(p_94695_, p_94696_, (screenWidth - 92) + x * 20 , this.height - 22, 22, 22)) {
								//save clicked skill to hotbar
								String[] hotbar = HarryPotterModConfig.HOTBAR.get().split(";");
								String newhotbar = "";
								hotbar[x] = String.valueOf(h.getClickedSkill());
								
								for(int n = 0; n < 8; n++) {
									newhotbar += hotbar[n]+";";
								}
									newhotbar += hotbar[8];

								//release clicked skill
								h.setClickedSkill(0);
								//save client side config hotbar
								ConfigHolder.CLIENT.HOTBAR.set(newhotbar);
							}
						}else {
							if(isMouseOverArea(p_94695_, p_94696_, (screenWidth - 92) + x * 20 , this.height - 22, 22, 22)) {
								String[] hotbar = HarryPotterModConfig.HOTBAR.get().split(";");
								String newhotbar = "";
								
								h.setClickedSkill(Integer.parseInt(hotbar[x]));
	
								hotbar[x] = String.valueOf(0);
								
								for(int n = 0; n < 8; n++) {
									newhotbar += hotbar[n]+";";
								}
									newhotbar += hotbar[8];
									
	//							h.setSkills(newhotbar);
								//save client side config hotbar
								ConfigHolder.CLIENT.HOTBAR.set(newhotbar);
							}
						}
					});
				}
			}
		}
		
//skills clicked in spell book	
		
		//page 1
        //skill 1 clicked
		if(isMouseOverArea(p_94695_, p_94696_, (screenWidth - 107), screenHeight - 56 + (36 * 0), 20, 20)) {
			if(player.getMainHandItem().is(Registrations.APPRENTICE_WAND.get())) {
				//save selected item
				player.getCapability(PlayerStatsProvider.PLAYER_STATS_CAPABILITY).ifPresent(h -> {
					if(h.getSpellsLevel()[0+(page*8)] > 0) {
						h.setClickedSkill(1 + (Math.min(page, 3) * 8)); 
					}
				});
			}
		}
		
		//skill 2 clicked
		if(isMouseOverArea(p_94695_, p_94696_, (screenWidth - 107), screenHeight - 56 + (36 * 1), 20, 20)) {
			if(player.getMainHandItem().is(Registrations.APPRENTICE_WAND.get())) {
				player.getCapability(PlayerStatsProvider.PLAYER_STATS_CAPABILITY).ifPresent(h -> {
					if(page<3) {
						if(h.getSpellsLevel()[1+(page*8)] > 0) {
							h.setClickedSkill(2 + (Math.min(page, 2) * 8)); 
						}
					}
				});
			}
		}
		
		//skill 3 clicked
		if(isMouseOverArea(p_94695_, p_94696_, (screenWidth - 107), screenHeight - 56 + (36 * 2), 20, 20)) {
			if(player.getMainHandItem().is(Registrations.APPRENTICE_WAND.get())) {
				player.getCapability(PlayerStatsProvider.PLAYER_STATS_CAPABILITY).ifPresent(h -> {
					if(page<3) {
						if(h.getSpellsLevel()[2+(page*8)] > 0) {
							h.setClickedSkill(3 + (Math.min(page, 2) * 8)); 
						}
					}
				});
			}
		}

		//skill 4 clicked
		if(isMouseOverArea(p_94695_, p_94696_, (screenWidth - 107), screenHeight - 56 + (36 * 3), 20, 20)) {
			if(player.getMainHandItem().is(Registrations.APPRENTICE_WAND.get())) {
				player.getCapability(PlayerStatsProvider.PLAYER_STATS_CAPABILITY).ifPresent(h -> {
					if(page<3) {
						if(h.getSpellsLevel()[3+(page*8)] > 0) {
							h.setClickedSkill(4 + (Math.min(page, 2) * 8)); 
						}
					}
				});
			}
		}

		//page 2
		//skill 5 clicked
				if(isMouseOverArea(p_94695_, p_94696_, (screenWidth - 107) + 112, screenHeight - 56 + (36 * 0), 20, 20)) {
					if(player.getMainHandItem().is(Registrations.APPRENTICE_WAND.get())) {
						player.getCapability(PlayerStatsProvider.PLAYER_STATS_CAPABILITY).ifPresent(h -> {
							if(page<3) {
								if(h.getSpellsLevel()[4+(page*8)] > 0) {
									h.setClickedSkill(5 + (Math.min(page, 2) * 8)); 
								}
							}
						});
					}
				}
				
		//skill 6 clicked
		if(isMouseOverArea(p_94695_, p_94696_, (screenWidth - 107) + 112, screenHeight - 56 + (36 * 1), 20, 20)) {
			if(player.getMainHandItem().is(Registrations.APPRENTICE_WAND.get())) {
				player.getCapability(PlayerStatsProvider.PLAYER_STATS_CAPABILITY).ifPresent(h -> {
					if(page<3) {
						if(h.getSpellsLevel()[5+(page*8)] > 0) {
							h.setClickedSkill(6 + (Math.min(page, 2) * 8));
						}
					}
				});
			}
		}
		
		//skill 7 clicked
		if(isMouseOverArea(p_94695_, p_94696_, (screenWidth - 107) + 112, screenHeight - 56 + (36 * 2), 20, 20)) {
			if(player.getMainHandItem().is(Registrations.APPRENTICE_WAND.get())) {
				player.getCapability(PlayerStatsProvider.PLAYER_STATS_CAPABILITY).ifPresent(h -> {
					if(page<3) {
						if(h.getSpellsLevel()[6+(page*8)] > 0) {
							h.setClickedSkill(7 + (Math.min(page, 2) * 8));
						}
					}
				});
			}
		}
		
		//skill 8 clicked
		if(isMouseOverArea(p_94695_, p_94696_, (screenWidth - 107) + 112, screenHeight - 56 + (36 * 3), 20, 20)) {
			if(player.getMainHandItem().is(Registrations.APPRENTICE_WAND.get())) {
				player.getCapability(PlayerStatsProvider.PLAYER_STATS_CAPABILITY).ifPresent(h -> {
					if(page<3) {
						if(h.getSpellsLevel()[7+(page*8)] > 0) {
							h.setClickedSkill(8 + (Math.min(page, 2) * 8));
						}
					}
				});
			}
		}
		
		int l = (this.height/2) - 105;
		//page plus
        if(isMouseOverArea(p_94695_, p_94696_, i + 85, l + 163, 18, 10)) {
        	if(page < 3) {
        		page++;
        		setButtonVisibility();
			}
        }
        
        //page minus
        if(isMouseOverArea(p_94695_, p_94696_, i - 100, l + 163, 18, 10)) {
        	if(page > 0) {
        		page--;
        		setButtonVisibility();
			}
        }
		
		return super.mouseClicked(p_94695_, p_94696_, p_94697_);
	}
	
	public void setButtonVisibility() {
		int exp = Minecraft.getInstance().player.experienceLevel;
		for(int ix = 0; ix < 25; ix++) {
			if(page == 0) {
				if(ix < 8 && exp >= spellLevel[ix] && exp != 0) {
					increaseButton[ix].visible = true;
				}else {
					increaseButton[ix].visible = false;
				}
			}else if(page == 1) {
				if(ix > 7 && ix < 16 && exp >= spellLevel[ix] && exp != 0) {
					increaseButton[ix].visible = true;
				}else {
					increaseButton[ix].visible = false;
				}
			}else if(page == 2) {
				if(ix > 15 && ix < 24 && exp >= spellLevel[ix] && exp != 0) {
					increaseButton[ix].visible = true;
				}else {
					increaseButton[ix].visible = false;
				}
			}else if(page == 3) {
				if(ix > 23 && ix < 32 && exp >= spellLevel[ix] && exp != 0) {
					increaseButton[ix].visible = true;
				}else {
					increaseButton[ix].visible = false;
				}
			}
		}
	}
	
	@Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if ((keyCode == KeyHandler.SPELL_BOOK.getKey().getValue()) || (keyCode == 256 && this.shouldCloseOnEsc())) {
        	this.onClose();
        }
        return true;
    }

	@Override
	public boolean isPauseScreen() {
		return false;
	}
}