package com.minecraftserverzone.harrypotter.spells.avis;

import java.util.List;

import com.minecraftserverzone.harrypotter.HarryPotterMod;
import com.minecraftserverzone.harrypotter.broomsticks.BroomStick;
import com.minecraftserverzone.harrypotter.setup.Registrations;
import com.minecraftserverzone.harrypotter.setup.capabilities.PlayerStatsProvider;
import com.minecraftserverzone.harrypotter.spells.NormalBallTypeSpell;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.IndirectEntityDamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class Avis extends NormalBallTypeSpell {
	public int lifeTick;
	
	public Avis(EntityType<? extends Avis> p_37364_, Level p_37365_) {
		super(p_37364_, p_37365_);
	}

	public Avis(Level p_37375_, LivingEntity p_37376_, double p_37377_, double p_37378_, double p_37379_) {
		super(Registrations.AVIS.get(), p_37376_, p_37377_, p_37378_, p_37379_, p_37375_);
	}
	
	@Override
	public boolean isOnFire() {
		this.setRemainingFireTicks(0);
		return false;
	}
	
	@Override
	public void tick() {
		if(this.isInWater() || this.isInLava()) {
			if (!this.level.isClientSide) {
				this.discard();
			}
		}
		
		if(this.tickCount % 1000 == 0 ) {
			if (!this.level.isClientSide ) {
				this.discard();
			}
		}
		
		if(!this.isPassenger()) {
			List<Entity> livingEntitiesNear = this.level.getEntities(this, new AABB(this.getX() - 1.0D, this.getY() - 1.0D, this.getZ() - 1.0D, this.getX() + 1.0D, this.getY() + 1.0D, this.getZ() + 1.0D), Entity::isAlive);
			for(Entity entity : livingEntitiesNear) {
				if(entity instanceof LivingEntity) {
					if(entity != this.getOwner()) {
						Entity entity1 = this.getOwner();
						
						if(this.getOwner() != null) {
							if(this.getOwner() instanceof LivingEntity) {
								if(entity instanceof BroomStick && ((LivingEntity) entity).getFirstPassenger() != null) {
									if(((LivingEntity) entity).getFirstPassenger() != entity1) {
										entity1.getCapability(PlayerStatsProvider.PLAYER_STATS_CAPABILITY).ifPresent(h -> {
											int spelllevel = h.getSpellsLevel()[4];
											float damage = HarryPotterMod.spellCooldownOrDamage(4, spelllevel, true);
											entity.hurt(new IndirectEntityDamageSource("Avis", entity1, entity1).setProjectile(), damage/7f);
										});
									}
								}else {
									entity1.getCapability(PlayerStatsProvider.PLAYER_STATS_CAPABILITY).ifPresent(h -> {
										int spelllevel = h.getSpellsLevel()[4];
										float damage = HarryPotterMod.spellCooldownOrDamage(4, spelllevel, true);
										entity.hurt(new IndirectEntityDamageSource("Avis", entity1, entity1).setProjectile(), damage/7f);
									});
								}
								
								
							}
						}

					    if (entity1 instanceof LivingEntity) {
					    	this.doEnchantDamageEffects((LivingEntity)entity1, entity);
					    }
						
					    this.startRiding(entity);
					    this.tickCount = 0;
					}
				}
			}
		}else {
			if (this.tickCount % 15 == 0 ) {
				Entity entity1 = this.getOwner();
				if(this.getVehicle()!= null && this.getVehicle() instanceof LivingEntity) {
					
					if(this.getOwner() != null) {
						if(this.getOwner() instanceof LivingEntity) {
							entity1.getCapability(PlayerStatsProvider.PLAYER_STATS_CAPABILITY).ifPresent(h -> {
								int spelllevel = h.getSpellsLevel()[4];
								float damage = HarryPotterMod.spellCooldownOrDamage(4, spelllevel, true);
								this.getVehicle().hurt(new IndirectEntityDamageSource("Avis", this.getVehicle(), entity1).setProjectile(), damage/7f);
							});
						}
					}
					
//					this.getVehicle().hurt(new IndirectEntityDamageSource("birds", this.getOwner(), entity1).setProjectile(), 1.0F);
				}else {
					if (!this.level.isClientSide ) {
						this.discard();
					}
				}
				
			}
			
			if (this.tickCount % 100 == 0 ) {
				if (!this.level.isClientSide ) {
					this.discard();
				}
			}
		}
		
		
		

		super.tick();
	}

	public Avis(Level p_37367_, double p_37368_, double p_37369_, double p_37370_, double p_37371_,
			double p_37372_, double p_37373_) {
		super(Registrations.AVIS.get(), p_37368_, p_37369_, p_37370_, p_37371_, p_37372_, p_37373_, p_37367_);
	}
	
	@Override
	protected void onHitBlock(BlockHitResult p_37258_) {
		super.onHitBlock(p_37258_);
	}

	protected void onHitEntity(EntityHitResult p_37386_) {
		super.onHitEntity(p_37386_);
	}
	
	@Override
	public float getBrightness() {
		return 1F;
	}

	@Override
	protected ParticleOptions getTrailParticle() {
		int i = Mth.floor(this.getX());
        int j = Mth.floor(this.getY() - (double)0.1F);
        int k = Mth.floor(this.getZ());
        BlockPos pos = new BlockPos(i, j, k);
        BlockState blockstate = this.level.getBlockState(pos);

		return new BlockParticleOption(ParticleTypes.BLOCK, blockstate);
	}

	protected void onHit(HitResult p_37388_) {
		super.onHit(p_37388_);
		if (!this.level.isClientSide) {
//	         this.discard();
		}
	}

	public boolean isPickable() {
		return false;
	}

	public boolean hurt(DamageSource p_37381_, float p_37382_) {
		return false;
	}
}
