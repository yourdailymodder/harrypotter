package com.minecraftserverzone.harrypotter.spells.aqua_eructo;

import java.util.Random;

import com.minecraftserverzone.harrypotter.HarryPotterMod;
import com.minecraftserverzone.harrypotter.setup.Registrations;
import com.minecraftserverzone.harrypotter.setup.capabilities.PlayerStatsProvider;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class AquaEructo extends WaterSpell {

	public AquaEructo(EntityType<? extends AquaEructo> p_37364_, Level p_37365_) {
		super(p_37364_, p_37365_);
	}

	public AquaEructo(Level p_37375_, LivingEntity p_37376_, double p_37377_, double p_37378_, double p_37379_) {
		super(Registrations.AQUA_ERUCTO.get(), p_37376_, p_37377_, p_37378_, p_37379_, p_37375_);
	}
	
	public AquaEructo(Level p_37375_, Entity player, double p_37377_, double p_37378_, double p_37379_) {
		super(Registrations.AQUA_ERUCTO.get(), player, p_37377_, p_37378_, p_37379_, p_37375_);
	}
	
	@Override
	public boolean isOnFire() {
		this.setRemainingFireTicks(0);
		return false;
	}
	
	@Override
	public void tick() { 
		super.tick();
	}

	public AquaEructo(Level p_37367_, double p_37368_, double p_37369_, double p_37370_, double p_37371_,
			double p_37372_, double p_37373_) {
		super(Registrations.AQUA_ERUCTO.get(), p_37368_, p_37369_, p_37370_, p_37371_, p_37372_, p_37373_, p_37367_);
	}
	
	@Override
	protected void onHitBlock(BlockHitResult p_37258_) {
		if (!this.level.isClientSide) {
			
			BlockPos blockpos = p_37258_.getBlockPos().relative(p_37258_.getDirection());

			if (this.level.getBlockState(blockpos).is(Blocks.FIRE)) {
				// Remove fire on server
			    level.removeBlock(blockpos, false);
	        }
			
			if(this.getOwner() != null) {
				if(this.getOwner() instanceof LivingEntity) {
					this.getOwner().getCapability(PlayerStatsProvider.PLAYER_STATS_CAPABILITY).ifPresent(h -> {
						float random = new Random().nextFloat(100);
						int spelllevel = h.getSpellsLevel()[1];
						
						if(random <= HarryPotterMod.spellCooldownOrDamage(1, spelllevel, true)) {
							level.setBlock(p_37258_.getBlockPos().above(1), Blocks.WATER.defaultBlockState(), 3);
						    level.gameEvent(this, GameEvent.BLOCK_PLACE, p_37258_.getBlockPos().above(1));
						}
					});
				}
			}
				
		}
		super.onHitBlock(p_37258_);
	}

	protected void onHitEntity(EntityHitResult p_37386_) {
		super.onHitEntity(p_37386_);
		if (!this.level.isClientSide) {
			Entity entity = p_37386_.getEntity();
  			BlockPos blockpos = new BlockPos(p_37386_.getEntity().blockPosition());

  			if(p_37386_.getEntity().getRemainingFireTicks() > 0) {
  				p_37386_.getEntity().setRemainingFireTicks(0);
  			}
  			
  			
  			if(this.getOwner() != null) {
				if(this.getOwner() instanceof LivingEntity) {
					this.getOwner().getCapability(PlayerStatsProvider.PLAYER_STATS_CAPABILITY).ifPresent(h -> {
						float random = new Random().nextFloat(100);
						int spelllevel = h.getSpellsLevel()[1];
						if(random <= HarryPotterMod.spellCooldownOrDamage(1, spelllevel, true)) {
							if((level.getBlockState(blockpos).getBlock()) == Blocks.AIR) {
				  				level.setBlock(blockpos, Blocks.WATER.defaultBlockState(), 3);
				  				level.gameEvent(p_37386_.getEntity(), GameEvent.BLOCK_PLACE, blockpos);
				  			}
						}
					});
				}
				entity.setDeltaMovement(this.getOwner().getLookAngle().x * 0.15f, 0.2f, this.getOwner().getLookAngle().z * 0.15f);
			}
		}
	}
	
	@Override
	public float getBrightness() {
		return 1F;
	}

	@Override
	protected ParticleOptions getTrailParticle() {
		return ParticleTypes.BUBBLE;
	}

	protected void onHit(HitResult p_37388_) {
		super.onHit(p_37388_);
		if (!this.level.isClientSide) {
//			if(this.tickCount % 10 == 0) {
//				this.playSound(SoundEvents.GENERIC_SPLASH, 1.0F, 1.0F);
//			}
//			if((level.getBlockState(this.blockPosition().above(1)).getBlock()) == Blocks.AIR) {
//  				level.setBlock(this.blockPosition().above(1), Blocks.WATER.defaultBlockState(), 3);
//  				level.gameEvent(this, GameEvent.BLOCK_PLACE, this.blockPosition().above(1));
//  			}
				this.discard();
		}
	}

	public boolean isPickable() {
		return false;
	}

	public boolean hurt(DamageSource p_37381_, float p_37382_) {
		return false;
	}
}
