package com.minecraftserverzone.harrypotter.spells.glacius;

import java.util.List;

import org.joml.Vector3f;

import com.minecraftserverzone.harrypotter.HarryPotterMod;
import com.minecraftserverzone.harrypotter.broomsticks.BroomStick;
import com.minecraftserverzone.harrypotter.setup.Registrations;
import com.minecraftserverzone.harrypotter.setup.capabilities.PlayerStatsProvider;
import com.minecraftserverzone.harrypotter.spells.DamageSpell;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.IndirectEntityDamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class Glacius extends DamageSpell {

	private boolean foundTarget = false;

	public Glacius(EntityType<? extends Glacius> p_37364_, Level p_37365_) {
		super(p_37364_, p_37365_);
	}

	public Glacius(Level p_37375_, LivingEntity p_37376_, double p_37377_, double p_37378_, double p_37379_) {
		super(Registrations.GLACIUS.get(), p_37376_, p_37377_, p_37378_, p_37379_, p_37375_);
	}
	
	@Override
	public boolean isOnFire() {
		this.setRemainingFireTicks(0);
		return false;
	}
	
	@Override
	public void tick() {
			if(this.isInWater()) {
				BlockPos blockpos = new BlockPos(this.blockPosition());
				level.setBlock(blockpos, Blocks.ICE.defaultBlockState(), 3);
				level.gameEvent(this, GameEvent.BLOCK_PLACE, blockpos);
				this.discard();
			}
		
		if (this.tickCount % 1000 == 0 ) {
			if (!this.level.isClientSide) {
				this.discard();
			}
		}
		
		if(!this.isPassenger()) {
			if (!this.level.isClientSide && foundTarget) {
				this.discard();
			}
			Vec3 vec3 = this.getDeltaMovement();
	        double d0 = this.getX() + vec3.x;
	        double d1 = this.getY() + vec3.y;
	        double d2 = this.getZ() + vec3.z;
	        
			for(float i = -1; i < 2; ++i) {
	        	for(float j = -1; j < 2; ++j) {
	        		for(float k = -1; k < 2; ++k) {
	        			this.level.addParticle(this.getTrailParticle(), d0 - vec3.x + i/10, 0.2f + d1 - vec3.y + j/10, d2 - vec3.z + k/10, vec3.x * 0.0f, vec3.y * 0.0f, vec3.z * 0.0f);
	        		}
	        	}
	        }
			
			List<Entity> livingEntitiesNear = this.level.getEntities(this, new AABB(this.getX() - 1.0D, this.getY() - 1.0D, this.getZ() - 1.0D, this.getX() + 1.0D, this.getY() + 1.0D, this.getZ() + 1.0D), Entity::isAlive);
			for(Entity entity : livingEntitiesNear) {
				if(entity instanceof LivingEntity) {
					if(entity != this.getOwner()) {
						Entity entity1 = this.getOwner();
						if(this.getOwner() != null) {
							if(this.getOwner() instanceof LivingEntity) {
								if(entity instanceof BroomStick && ((LivingEntity) entity).getFirstPassenger() != null) {
									if(((LivingEntity) entity).getFirstPassenger() != this.getOwner()) {
										entity1.getCapability(PlayerStatsProvider.PLAYER_STATS_CAPABILITY).ifPresent(h -> {
											int id = 10;
											int spelllevel = h.getSpellsLevel()[id];
											float damage = HarryPotterMod.spellCooldownOrDamage(id, spelllevel, true);
											entity.hurt(new IndirectEntityDamageSource("frozen", entity1, entity1).setProjectile(), damage);
										});
									}
								}else {
									entity1.getCapability(PlayerStatsProvider.PLAYER_STATS_CAPABILITY).ifPresent(h -> {
										int id = 10;
										int spelllevel = h.getSpellsLevel()[id];
										float damage = HarryPotterMod.spellCooldownOrDamage(id, spelllevel, true);
										entity.hurt(new IndirectEntityDamageSource("frozen", entity1, entity1).setProjectile(), damage);
									});
								}
							}
						}
			 			entity.setTicksFrozen(600);
						
					    this.startRiding(entity);
					    this.tickCount = 0;
					}
				}
			}
		}else {
			foundTarget  = true;
			if (this.tickCount % 200 == 0 ) {
				if (!this.level.isClientSide ) {
					this.discard();
				}
			}
		}
		
		super.tick();
	}

	public Glacius(Level p_37367_, double p_37368_, double p_37369_, double p_37370_, double p_37371_,
			double p_37372_, double p_37373_) {
		super(Registrations.GLACIUS.get(), p_37368_, p_37369_, p_37370_, p_37371_, p_37372_, p_37373_, p_37367_);
	}
	
	@Override
	protected void onHitBlock(BlockHitResult p_37258_) {
		if (this.level.isClientSide) {
		Vec3 vec3 = this.getDeltaMovement();
         double d0 = this.getX() + vec3.x;
         double d1 = this.getY() + vec3.y;
         double d2 = this.getZ() + vec3.z;
         
            for(int i = 0; i < 10; ++i) {
               float f1 =  i* 0.05F;
               this.level.addParticle(this.getTrailParticle(), d0 - vec3.x * f1, d1 - vec3.y * f1, d2 - vec3.z * f1, vec3.x, vec3.y, vec3.z);
            }
		}
		
		super.onHitBlock(p_37258_);
	}

	protected void onHitEntity(EntityHitResult p_37386_) {
		super.onHitEntity(p_37386_);

		if (this.level.isClientSide) {
		Vec3 vec3 = this.getDeltaMovement();
         double d0 = this.getX() + vec3.x;
         double d1 = this.getY() + vec3.y;
         double d2 = this.getZ() + vec3.z;

            for(int i = 0; i < 10; ++i) {
               float f1 =  i* 0.05F;
               this.level.addParticle(ParticleTypes.SNOWFLAKE, d0 - vec3.x * f1, d1 - vec3.y * f1, d2 - vec3.z * f1, vec3.x, vec3.y, vec3.z);
               this.level.addParticle(this.getTrailParticle(), d0 - vec3.x * f1, d1 - vec3.y * f1, d2 - vec3.z * f1, vec3.x, vec3.y, vec3.z);
            }
		}
	}

	@Override
	public float getLightLevelDependentMagicValue() {
		return 1F;
	}

	@Override
	protected ParticleOptions getTrailParticle() {
		Vector3f PARTICLE_COLOR = Vec3.fromRGB24(0x03ecfc).toVector3f();
		return new DustParticleOptions(PARTICLE_COLOR, 1);
	}

	protected void onHit(HitResult p_37388_) {
		super.onHit(p_37388_);
	}

	public boolean isPickable() {
		return false;
	}

	public boolean hurt(DamageSource p_37381_, float p_37382_) {
		return false;
	}
}
