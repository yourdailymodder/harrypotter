package com.minecraftserverzone.harrypotter.items;

import java.util.Random;
import java.util.function.Consumer;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.minecraftserverzone.harrypotter.items.wand.ApprenticeWandRenderer;
import com.minecraftserverzone.harrypotter.setup.Registrations;
import com.minecraftserverzone.harrypotter.setup.capabilities.PlayerStatsProvider;
import com.minecraftserverzone.harrypotter.setup.config.HarryPotterModConfig;
import com.minecraftserverzone.harrypotter.setup.network.Networking;
import com.minecraftserverzone.harrypotter.setup.network.PacketHerbivicus;
import com.minecraftserverzone.harrypotter.setup.network.PacketSpells;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.BaseCoralWallFanBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.IItemRenderProperties;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.NonNullLazy;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.eventbus.api.Event.Result;

public class WandItem extends Item{
	
	public WandItem(Properties p_41383_) {
		super(p_41383_);
	}

	@Override
	public int getUseDuration(ItemStack p_41454_) {
		/** TODO fire storm duration? */
		return 100;
	}
	
	@Override
	public UseAnim getUseAnimation(ItemStack p_41452_) {
		return UseAnim.BLOCK;
	}
	
	@Override
	public void releaseUsing(ItemStack p_41412_, Level p_41413_, LivingEntity p_41414_, int p_41415_) {
		if(p_41414_ instanceof Player && p_41413_.isClientSide()) {
			Player player = (Player) p_41414_;
			player.getCapability(PlayerStatsProvider.PLAYER_STATS_CAPABILITY).ifPresent(h -> {
				Networking.sendToServer(new PacketSpells(91));
			});
			super.releaseUsing(p_41412_, p_41413_, p_41414_, p_41415_);
		}
	}

	@Override
	public ItemStack finishUsingItem(ItemStack p_41409_, Level p_41410_, LivingEntity p_41411_) {
		if(p_41411_ instanceof Player && p_41410_.isClientSide()) {
		Player player = (Player) p_41411_;
		player.getCapability(PlayerStatsProvider.PLAYER_STATS_CAPABILITY).ifPresent(h -> {
			Networking.sendToServer(new PacketSpells(91));
		});
		}
		return super.finishUsingItem(p_41409_, p_41410_, p_41411_);
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level p_41432_, Player p_41433_, InteractionHand p_41434_) {
		ItemStack itemstack = p_41433_.getItemInHand(p_41434_);
           p_41433_.startUsingItem(p_41434_);

           
		Player player = p_41433_;
		if(p_41432_.isClientSide) {
			player.getCapability(PlayerStatsProvider.PLAYER_STATS_CAPABILITY).ifPresent(h -> {
	        	//jump
				if(h.getBattleTick() == 1) {
					if(player.getMainHandItem().is(Registrations.APPRENTICE_WAND.get())) {
						String[] hotbar = HarryPotterModConfig.HOTBAR.get().split(";");
						
						int selectedSkillInHotbar = Integer.parseInt(hotbar[h.getSelectedHotbar()]);
	
						if(selectedSkillInHotbar>0) {
							selectedSkillInHotbar-=1;
						}
						
						if(h.getSpellsCD() != null) {
							if(h.getSpellsCD().length == 25) {
								if(h.getSpellsCD()[selectedSkillInHotbar] > 0) {
	//								System.out.println("cant use it now: "+selectedSkillInHotbar);
									return;
								}
							}
						}
						
						if(h.getSpellsLevel() != null) {
			        		if(h.getSpellsLevel()[selectedSkillInHotbar] > 0 || player.isCreative()) {
	
								if(selectedSkillInHotbar == 0) {//accio
									player.playSound(Registrations.SPELL3.get(), 1.0F, 1.0F);
									Networking.sendToServer(new PacketSpells(0));
								}else if(selectedSkillInHotbar == 1) {//aqua eructo
									player.playSound(SoundEvents.WATER_AMBIENT, 1.0F, 1.0F);
									Networking.sendToServer(new PacketSpells(1));
								}else if(selectedSkillInHotbar == 2) {//ascendio
									player.playSound(Registrations.SPELL3.get(), 1.0F, 1.0F);
									p_41433_.setDeltaMovement(0, 2, 0);
									Networking.sendToServer(new PacketSpells(2));
								}else if(selectedSkillInHotbar == 3) {//avada kedavra
									player.playSound(Registrations.DEFAULT_SPELL.get(), 1.0F, 1.0F);
									Networking.sendToServer(new PacketSpells(3));
								}else if(selectedSkillInHotbar == 4) {//avis
									player.playSound(Registrations.SPELL3.get(), 1.0F, 1.0F);
									Networking.sendToServer(new PacketSpells(4));
								}else if(selectedSkillInHotbar == 5) {//confringo
									player.playSound(Registrations.EXPLOSION.get(), 1.0F, 1.0F);
									Networking.sendToServer(new PacketSpells(5));
								}else if(selectedSkillInHotbar == 6) {//depulso
									player.playSound(Registrations.MAGIC_SPELL7.get(), 1.0F, 1.0F);
									Networking.sendToServer(new PacketSpells(6));
								}else if(selectedSkillInHotbar == 7) {//expecto_patronum
									player.playSound(Registrations.MAGIC_SPELL6.get(), 1.0F, 1.0F);
									Networking.sendToServer(new PacketSpells(7));
								}else if(selectedSkillInHotbar == 8) {//expelliarmus
									player.playSound(Registrations.MAGIC_SPELL7.get(), 1.0F, 1.0F);
									Networking.sendToServer(new PacketSpells(8));		      
								}else if(selectedSkillInHotbar == 9) {//fumos
									player.playSound(Registrations.SMOKE.get(), 1.0F, 1.0F);
									Networking.sendToServer(new PacketSpells(9));	
								}else if(selectedSkillInHotbar == 10) {//glacius
									player.playSound(Registrations.ICE.get(), 1.0F, 1.0F);
									Networking.sendToServer(new PacketSpells(10));	
								}else if(selectedSkillInHotbar == 12) { //incendio
									player.playSound(Registrations.FIREBALL_SOUND1.get(), 1.0F, 1.0F);
									Networking.sendToServer(new PacketSpells(12));	
									
								}else if(selectedSkillInHotbar == 13) { //lumos
									player.playSound(Registrations.SPELL3.get(), 1.0F, 1.0F);
									if(p_41432_.getBlockState(p_41433_.blockPosition().above(1)).getBlock() == Blocks.AIR) {
										Networking.sendToServer(new PacketSpells(13));
									}
								}else if(selectedSkillInHotbar == 14) { //Melofors
									player.playSound(Registrations.SPELL3.get(), 1.0F, 1.0F);
									Networking.sendToServer(new PacketSpells(14));
								}else if(selectedSkillInHotbar == 15) { //mobilicorpus
									player.playSound(Registrations.SPELL3.get(), 1.0F, 1.0F);
									Networking.sendToServer(new PacketSpells(15));	
								}else if(selectedSkillInHotbar == 16) { //reparo
									player.playSound(Registrations.SPELL3.get(), 1.0F, 1.0F);
									Networking.sendToServer(new PacketSpells(16));	
								}else if(selectedSkillInHotbar == 17) { //sectumsempra
									player.playSound(Registrations.DEFAULT_SPELL.get(), 1.0F, 1.0F);
									Networking.sendToServer(new PacketSpells(17));
								}else if(selectedSkillInHotbar == 18) { //vulnera_sanentur
									player.playSound(Registrations.SMALL_HEAL.get(), 1.0F, 1.0F);
			//						player.addEffect(new MobEffectInstance(MobEffects.HEAL, 1, 1));
			//						player.heal(10);
									Networking.sendToServer(new PacketSpells(18));
								}else if(selectedSkillInHotbar == 19) { //wingardium_leviosa
									player.playSound(Registrations.SPELL3.get(), 1.0F, 1.0F);
									Networking.sendToServer(new PacketSpells(19));
										
								}else if(selectedSkillInHotbar == 20) { //episkey
									player.playSound(Registrations.SMALL_HEAL.get(), 1.0F, 1.0F);
									Networking.sendToServer(new PacketSpells(20));
										
								}else if(selectedSkillInHotbar == 21) { //alarte ascandare
									player.playSound(Registrations.MAGIC_SPELL6.get(), 1.0F, 1.0F);
									Networking.sendToServer(new PacketSpells(21));
									
								}else if(selectedSkillInHotbar == 22) { //finite
									player.playSound(Registrations.MAGIC_SPELL7.get(), 1.0F, 1.0F);
									Networking.sendToServer(new PacketSpells(22));	
								}else if(selectedSkillInHotbar == 23) { //evanesco
									player.playSound(Registrations.SPELL3.get(), 1.0F, 1.0F);
									Networking.sendToServer(new PacketSpells(23));	
								}else if(selectedSkillInHotbar == 24) { //fire storm
									player.playSound(Registrations.FIREBALL_SOUND1.get(), 1.0F, 1.0F);
									Networking.sendToServer(new PacketSpells(24));	
								}
								/**TODO NEW SPELLS */
								
			        		}
						}
					}
				}
			});
		}
        return InteractionResultHolder.consume(itemstack);
	}
	
	@Override
	public InteractionResult interactLivingEntity(ItemStack p_41398_, Player p_41399_, LivingEntity p_41400_,
			InteractionHand p_41401_) {
		Player player = p_41399_;
		LivingEntity target = p_41400_;
		
		/** TODO test if it works right: heal only player if no target */
		if(player.level.isClientSide) {
			player.getCapability(PlayerStatsProvider.PLAYER_STATS_CAPABILITY).ifPresent(h -> {
				String[] hotbar = HarryPotterModConfig.HOTBAR.get().split(";");
				int selectedSkillInHotbar = Integer.parseInt(hotbar[h.getSelectedHotbar()]);
				if(selectedSkillInHotbar>0) {
					selectedSkillInHotbar-=1;
				}
						if(selectedSkillInHotbar == 18) {	//vulnera sanentur
							if(h.getSpellsCD() != null) {
								if(h.getSpellsLevel() != null) {
					        		if(h.getSpellsLevel()[selectedSkillInHotbar] > 0 || player.isCreative()) {
						        		
										if(h.getSpellsCD().length == 25) {
											if(h.getSpellsCD()[selectedSkillInHotbar] > 0) {
											}else {
												target.heal(10);
		//										System.out.println("wand: use on target");
												h.setUsingSkill(1);
											}
										}
									}
					        	}
							}
						}
			});
		}
		
		return super.interactLivingEntity(p_41398_, p_41399_, p_41400_, p_41401_);
	}
	
	public static int onApplyBonemeal(@Nonnull Player player, @Nonnull Level world, @Nonnull BlockPos pos, @Nonnull BlockState state, @Nonnull ItemStack stack)
    {
        BonemealEvent event = new BonemealEvent(player, world, pos, state, stack);
        if (MinecraftForge.EVENT_BUS.post(event)) return -1;
        if (event.getResult() == Result.ALLOW)
        {
            if (!world.isClientSide)
            return 1;
        }
        return 0;
    }
	
	@Override
	public InteractionResult useOn(UseOnContext p_40637_) {
					Player player = p_40637_.getPlayer();
					Level level = p_40637_.getLevel();
					if(level.isClientSide) {

						player.getCapability(PlayerStatsProvider.PLAYER_STATS_CAPABILITY).ifPresent(h -> {
							String[] hotbar = HarryPotterModConfig.HOTBAR.get().split(";");
							if(hotbar == null) {
								return;
							}
							int selectedSkillInHotbar = Integer.parseInt(hotbar[h.getSelectedHotbar()]);
							
							if(selectedSkillInHotbar>0) {
								selectedSkillInHotbar-=1;
							}

							if(h.getSpellsLevel() != null) {
				        		if(h.getSpellsLevel()[selectedSkillInHotbar] > 0 || player.isCreative()) {
									herbivicus: {
									if(selectedSkillInHotbar == 11) {
										if(h.getSpellsCD() != null) {
											if(h.getSpellsCD().length == 25) {
												if(h.getSpellsCD()[selectedSkillInHotbar] > 0) {
													break herbivicus;
												}
											}
										}
									}

										if(selectedSkillInHotbar == 11) {	//herbivicus
										      BlockPos blockpos = p_40637_.getClickedPos();
										      BlockPos blockpos1 = blockpos.relative(p_40637_.getClickedFace());
										      
//										      if (applyBonemeal(p_40637_.getItemInHand(), level, blockpos, p_40637_.getPlayer())) {
//										         if (level.isClientSide) {
//														player.playSound(Registrations.BUFF.get(), 1.0F, 1.0F);
//														Networking.sendToServer(new PacketSpells(11));
//										         }
//										      } else {
//										         BlockState blockstate = level.getBlockState(blockpos);
//										         boolean flag = blockstate.isFaceSturdy(level, blockpos, p_40637_.getClickedFace());
//										         if (flag && growWaterPlant(p_40637_.getItemInHand(), level, blockpos1, p_40637_.getClickedFace())) {
//										            if (level.isClientSide) {
//														player.playSound(Registrations.BUFF.get(), 1.0F, 1.0F);
//														Networking.sendToServer(new PacketSpells(11));
//										            }
//										         } else {
//										        	 return;
//										         }
//										      }
										      
										      Networking.sendToServer(new PacketHerbivicus(blockpos, blockpos1));
										      
										}
									}
								
				        		}
				        	}
						});
						
					}
						
						return InteractionResult.sidedSuccess(p_40637_.getPlayer().level.isClientSide);
						
		   }
	
	@Deprecated //Forge: Use Player/Hand version
	   public static boolean growCrop(ItemStack p_40628_, Level p_40629_, BlockPos p_40630_) {
	      if (p_40629_ instanceof net.minecraft.server.level.ServerLevel)
	         return applyBonemeal(p_40628_, p_40629_, p_40630_, net.minecraftforge.common.util.FakePlayerFactory.getMinecraft((net.minecraft.server.level.ServerLevel)p_40629_));
	      return false;
	   }

	   public static boolean applyBonemeal(ItemStack p_40628_, Level p_40629_, BlockPos p_40630_, net.minecraft.world.entity.player.Player player) {
		   BlockState blockstate = p_40629_.getBlockState(p_40630_);
		      int hook = net.minecraftforge.event.ForgeEventFactory.onApplyBonemeal(player, p_40629_, p_40630_, blockstate, p_40628_);
		      if (hook != 0) return hook > 0;
		      if (blockstate.getBlock() instanceof BonemealableBlock) {
		         BonemealableBlock bonemealableblock = (BonemealableBlock)blockstate.getBlock();
		         if (bonemealableblock.isValidBonemealTarget(p_40629_, p_40630_, blockstate, p_40629_.isClientSide)) {
		            if (p_40629_ instanceof ServerLevel) {
		               if (bonemealableblock.isBonemealSuccess(p_40629_, p_40629_.random, p_40630_, blockstate)) {
		                  bonemealableblock.performBonemeal((ServerLevel)p_40629_, p_40629_.random, p_40630_, blockstate);
		               }
		            }

		            return true;
		         }
		      }

		      return false;
	   }

	   @SuppressWarnings("deprecation")
	public static boolean growWaterPlant(ItemStack p_40632_, Level p_40633_, BlockPos p_40634_, @Nullable Direction p_40635_) {
		   if (p_40633_.getBlockState(p_40634_).is(Blocks.WATER) && p_40633_.getFluidState(p_40634_).getAmount() == 8) {
		         if (!(p_40633_ instanceof ServerLevel)) {
		            return true;
		         } else {
		            Random random = p_40633_.getRandom();

		            label78:
		            for(int i = 0; i < 128; ++i) {
		               BlockPos blockpos = p_40634_;
		               BlockState blockstate = Blocks.SEAGRASS.defaultBlockState();

		               for(int j = 0; j < i / 16; ++j) {
		                  blockpos = blockpos.offset(random.nextInt(3) - 1, (random.nextInt(3) - 1) * random.nextInt(3) / 2, random.nextInt(3) - 1);
		                  if (p_40633_.getBlockState(blockpos).isCollisionShapeFullBlock(p_40633_, blockpos)) {
		                     continue label78;
		                  }
		               }

		               Holder<Biome> holder = p_40633_.getBiome(blockpos);
		               if (holder.is(Biomes.WARM_OCEAN)) {
		                  if (i == 0 && p_40635_ != null && p_40635_.getAxis().isHorizontal()) {
		                     blockstate = Registry.BLOCK.getTag(BlockTags.WALL_CORALS).flatMap((p_204098_) -> {
		                        return p_204098_.getRandomElement(p_40633_.random);
		                     }).map((p_204100_) -> {
		                        return p_204100_.value().defaultBlockState();
		                     }).orElse(blockstate);
		                     if (blockstate.hasProperty(BaseCoralWallFanBlock.FACING)) {
		                        blockstate = blockstate.setValue(BaseCoralWallFanBlock.FACING, p_40635_);
		                     }
		                  } else if (random.nextInt(4) == 0) {
		                     blockstate = Registry.BLOCK.getTag(BlockTags.UNDERWATER_BONEMEALS).flatMap((p_204091_) -> {
		                        return p_204091_.getRandomElement(p_40633_.random);
		                     }).map((p_204095_) -> {
		                        return p_204095_.value().defaultBlockState();
		                     }).orElse(blockstate);
		                  }
		               }

		               if (blockstate.is(BlockTags.WALL_CORALS, (p_204093_) -> {
		                  return p_204093_.hasProperty(BaseCoralWallFanBlock.FACING);
		               })) {
		                  for(int k = 0; !blockstate.canSurvive(p_40633_, blockpos) && k < 4; ++k) {
		                     blockstate = blockstate.setValue(BaseCoralWallFanBlock.FACING, Direction.Plane.HORIZONTAL.getRandomDirection(random));
		                  }
		               }

		               if (blockstate.canSurvive(p_40633_, blockpos)) {
		                  BlockState blockstate1 = p_40633_.getBlockState(blockpos);
		                  if (blockstate1.is(Blocks.WATER) && p_40633_.getFluidState(blockpos).getAmount() == 8) {
		                     p_40633_.setBlock(blockpos, blockstate, 3);
		                  } else if (blockstate1.is(Blocks.SEAGRASS) && random.nextInt(10) == 0) {
		                     ((BonemealableBlock)Blocks.SEAGRASS).performBonemeal((ServerLevel)p_40633_, random, blockpos, blockstate1);
		                  }
		               }
		            }
		            return true;
		         }
		      } else {
		         return false;
		      }
		   }

	   public static void addGrowthParticles(LevelAccessor p_40639_, BlockPos p_40640_, int p_40641_) {
	      if (p_40641_ == 0) {
	         p_40641_ = 15;
	      }

	      BlockState blockstate = p_40639_.getBlockState(p_40640_);
	      if (!blockstate.isAir()) {
	         double d0 = 0.5D;
	         double d1;
	         if (blockstate.is(Blocks.WATER)) {
	            p_40641_ *= 3;
	            d1 = 1.0D;
	            d0 = 3.0D;
	         } else if (blockstate.isSolidRender(p_40639_, p_40640_)) {
	            p_40640_ = p_40640_.above();
	            p_40641_ *= 3;
	            d0 = 3.0D;
	            d1 = 1.0D;
	         } else {
	            d1 = blockstate.getShape(p_40639_, p_40640_).max(Direction.Axis.Y);
	         }

	         p_40639_.addParticle(ParticleTypes.HAPPY_VILLAGER, (double)p_40640_.getX() + 0.5D, (double)p_40640_.getY() + 0.5D, (double)p_40640_.getZ() + 0.5D, 0.0D, 0.0D, 0.0D);
	         Random random = p_40639_.getRandom();

	         for(int i = 0; i < p_40641_; ++i) {
	            double d2 = random.nextGaussian() * 0.02D;
	            double d3 = random.nextGaussian() * 0.02D;
	            double d4 = random.nextGaussian() * 0.02D;
	            double d5 = 0.5D - d0;
	            double d6 = (double)p_40640_.getX() + d5 + random.nextDouble() * d0 * 2.0D;
	            double d7 = (double)p_40640_.getY() + random.nextDouble() * d1;
	            double d8 = (double)p_40640_.getZ() + d5 + random.nextDouble() * d0 * 2.0D;
	            if (!p_40639_.getBlockState((new BlockPos(d6, d7, d8)).below()).isAir()) {
	               p_40639_.addParticle(ParticleTypes.HAPPY_VILLAGER, d6, d7, d8, d2, d3, d4);
	            }
	         }

	      }
	   }
	   
	   
	   @Override
	    public void initializeClient(Consumer<IItemRenderProperties> consumer)
	    {
	        consumer.accept(new IItemRenderProperties()
	        {
	            private final NonNullLazy<BlockEntityWithoutLevelRenderer> ister = 
	            		NonNullLazy.of(() -> new ApprenticeWandRenderer(
	            				Minecraft.getInstance().getBlockEntityRenderDispatcher(),
	            				Minecraft.getInstance().getEntityModels()));

	            @Override
	            public BlockEntityWithoutLevelRenderer getItemStackRenderer()
	            {
	                return ister.get();
	            }
	        });
	    }

}
