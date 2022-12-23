package com.minecraftserverzone.harrypotter.spells.confringo;

import java.util.List;

import org.joml.Vector3f;

import com.minecraftserverzone.harrypotter.HarryPotterMod;
import com.minecraftserverzone.harrypotter.setup.Registrations;
import com.minecraftserverzone.harrypotter.setup.capabilities.PlayerStatsProvider;
import com.minecraftserverzone.harrypotter.spells.DamageSpell;

import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.IndirectEntityDamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class Confringo extends DamageSpell {

	public Confringo(EntityType<? extends Confringo> p_37364_, Level p_37365_) {
		super(p_37364_, p_37365_);
	}

	public Confringo(Level p_37375_, LivingEntity p_37376_, double p_37377_, double p_37378_, double p_37379_) {
		super(Registrations.CONFRINGO.get(), p_37376_, p_37377_, p_37378_, p_37379_, p_37375_);
	}
	
	@Override
	public boolean isOnFire() {
		this.setRemainingFireTicks(0);
		return false;
	}
	
	@Override
	public void tick() {
		Vec3 vec3 = this.getDeltaMovement();
        double d0 = this.getX() + vec3.x;
        double d1 = this.getY() + vec3.y;
        double d2 = this.getZ() + vec3.z;
        
		for(float i = -1; i < 2; ++i) {
        	for(float j = -1; j < 2; ++j) {
        		for(float k = -1; k < 2; ++k) {
        			this.level.addParticle(ParticleTypes.SMOKE, d0 - vec3.x + i/10, 0.1f + d1 - vec3.y + j/10, d2 - vec3.z + k/10, vec3.x * 0.0f, vec3.y * 0.0f, vec3.z * 0.0f);
        			this.level.addParticle(this.getTrailParticle(), d0 - vec3.x + i/10, 0.1f + d1 - vec3.y + j/10, d2 - vec3.z + k/10, vec3.x * 0.0f, vec3.y * 0.0f, vec3.z * 0.0f);
        		}
        	}
        }
		super.tick();
	}

	public Confringo(Level p_37367_, double p_37368_, double p_37369_, double p_37370_, double p_37371_,
			double p_37372_, double p_37373_) {
		super(Registrations.CONFRINGO.get(), p_37368_, p_37369_, p_37370_, p_37371_, p_37372_, p_37373_, p_37367_);
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
               this.level.addParticle(this.getTrailParticle(), d0 - vec3.x * f1, 0.15f + d1 - vec3.y * f1, d2 - vec3.z * f1, vec3.x, vec3.y, vec3.z);
            }
		}
		
		if (!this.level.isClientSide) {
			if(this.getOwner() != null) {
				if(this.getOwner() instanceof LivingEntity) {
					Entity entity1 = this.getOwner();
					entity1.getCapability(PlayerStatsProvider.PLAYER_STATS_CAPABILITY).ifPresent(h -> {
						int id = 5;
						int spelllevel = h.getSpellsLevel()[id];
						float damage = HarryPotterMod.spellCooldownOrDamage(id, spelllevel, true);
						
						List<Entity> livingEntitiesNear = this.level.getEntities(this, new AABB(this.getX() - 2.0D, this.getY() - 2.0D, this.getZ() - 2.0D, this.getX() + 2.0D, this.getY() + 2.0D, this.getZ() + 2.0D), Entity::isAlive);
						for(Entity entityNearExplosion : livingEntitiesNear) {
							if(entityNearExplosion instanceof LivingEntity) {
								entityNearExplosion.hurt(new IndirectEntityDamageSource("confringo", entity1, entity1).setProjectile(), damage);
							}
						}
					});
				}
			}
			//check if there is an entity in near, if yes then damage entity
	         this.level.explode(this, this.getX(), this.getY(), this.getZ(), (float)2, Level.ExplosionInteraction.MOB);
	         this.discard();
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
               this.level.addParticle(this.getTrailParticle(), d0 - vec3.x * f1, d1 - vec3.y * f1, d2 - vec3.z * f1, vec3.x, vec3.y, vec3.z);
            }
		}
		
		if (!this.level.isClientSide) {
			Entity entity = p_37386_.getEntity();
			Entity entity1 = this.getOwner();
			
			if(this.getOwner() != null) {
				if(this.getOwner() instanceof LivingEntity) {
					entity1.getCapability(PlayerStatsProvider.PLAYER_STATS_CAPABILITY).ifPresent(h -> {
						int id = 5;
						int spelllevel = h.getSpellsLevel()[id];
						float damage = HarryPotterMod.spellCooldownOrDamage(id, spelllevel, true);
						List<Entity> livingEntitiesNear = this.level.getEntities(entity, new AABB(this.getX() - 2.0D, this.getY() - 2.0D, this.getZ() - 2.0D, this.getX() + 2.0D, this.getY() + 2.0D, this.getZ() + 2.0D), Entity::isAlive);
						entity.hurt(new IndirectEntityDamageSource("confringo", entity1, entity1).setProjectile(), damage);
						
						for(Entity entityNearExplosion : livingEntitiesNear) {
							if(entityNearExplosion instanceof LivingEntity && entityNearExplosion!=entity) {
								entityNearExplosion.hurt(new IndirectEntityDamageSource("confringo", entity1, entity1).setProjectile(), damage);
							}
						}
					});
				}
			}
				//explode
				this.level.explode(this, this.getX(), this.getY(), this.getZ(), (float)2, Level.ExplosionInteraction.MOB);
			}
	         this.discard();
	}
	
	@Override
	public float getLightLevelDependentMagicValue() {
		return 1F;
	}

	@Override
	protected ParticleOptions getTrailParticle() {
		Vector3f PARTICLE_COLOR = Vec3.fromRGB24(0xe84d15).toVector3f();
		return new DustParticleOptions(PARTICLE_COLOR, 1);
	}

	protected void onHit(HitResult p_37388_) {
		super.onHit(p_37388_);
		if (!this.level.isClientSide) {
			this.discard();
		}else {
			this.playSound(Registrations.EXPLOSION2.get(), 1.0F, 1.0F);
		}

	}

	public boolean isPickable() {
		return false;
	}

	public boolean hurt(DamageSource p_37381_, float p_37382_) {
		return false;
	}
}
