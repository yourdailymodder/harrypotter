package com.minecraftserverzone.harrypotter.spells.finite;

import java.util.List;

import com.minecraftserverzone.harrypotter.setup.Registrations;
import com.minecraftserverzone.harrypotter.spells.DamageSpell;
import com.minecraftserverzone.harrypotter.spells.NormalBallTypeSpell;
import com.minecraftserverzone.harrypotter.spells.accio.PullSpell;
import com.minecraftserverzone.harrypotter.spells.aqua_eructo.WaterSpell;
import com.minecraftserverzone.harrypotter.spells.glacius.IceEntity;
import com.minecraftserverzone.harrypotter.spells.mobilicorpus.BodyMoverEntity;
import com.mojang.math.Vector3f;

import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class Finite extends DamageSpell {

	public Finite(EntityType<? extends Finite> p_37364_, Level p_37365_) {
		super(p_37364_, p_37365_);
	}

	public Finite(Level p_37375_, LivingEntity p_37376_, double p_37377_, double p_37378_, double p_37379_) {
		super(Registrations.FINITE.get(), p_37376_, p_37377_, p_37378_, p_37379_, p_37375_);
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
        			this.level.addParticle(this.getTrailParticle(), d0 - vec3.x + i/10, 0.15f +  d1 - vec3.y + j/10, d2 - vec3.z + k/10, vec3.x * 0.0f, vec3.y * 0.0f, vec3.z * 0.0f);
        		}
        	}
        }
		List<Entity> entitiesNear = this.level.getEntities(this, new AABB(this.getX() - 2.0D, this.getY() - 2.0D, this.getZ() - 2.0D, this.getX() + 2.0D, this.getY() + 2.0D, this.getZ() + 2.0D), Entity::isAlive);
		for(Entity entity : entitiesNear) {
			if(entity instanceof PullSpell || entity instanceof DamageSpell ||
					entity instanceof WaterSpell || entity instanceof NormalBallTypeSpell ||
					entity instanceof IceEntity || entity instanceof BodyMoverEntity) {

		        this.level.addParticle(ParticleTypes.EXPLOSION, d0 - vec3.x, 0.15f +  d1 - vec3.y, d2 - vec3.z, vec3.x * 0.0f, vec3.y * 0.0f, vec3.z * 0.0f);
				entity.discard();
				this.discard();
			}
		}
		
		super.tick();
	}

	public Finite(Level p_37367_, double p_37368_, double p_37369_, double p_37370_, double p_37371_,
			double p_37372_, double p_37373_) {
		super(Registrations.FINITE.get(), p_37368_, p_37369_, p_37370_, p_37371_, p_37372_, p_37373_, p_37367_);
	}
	
	@Override
	protected void onHitBlock(BlockHitResult p_37258_) {
		super.onHitBlock(p_37258_);
	}

	protected void onHitEntity(EntityHitResult p_37386_) {
		super.onHitEntity(p_37386_);
		
		if (!this.level.isClientSide) {
			if(p_37386_.getEntity() instanceof PullSpell || p_37386_.getEntity() instanceof DamageSpell ||
					p_37386_.getEntity() instanceof WaterSpell || p_37386_.getEntity() instanceof NormalBallTypeSpell ||
					p_37386_.getEntity() instanceof IceEntity || p_37386_.getEntity() instanceof BodyMoverEntity) {
				p_37386_.getEntity().discard();
				
			}
		}
		Vec3 vec3 = this.getDeltaMovement();
        double d0 = this.getX() + vec3.x;
        double d1 = this.getY() + vec3.y;
        double d2 = this.getZ() + vec3.z;
        this.level.addParticle(ParticleTypes.EXPLOSION, d0 - vec3.x, 0.15f +  d1 - vec3.y, d2 - vec3.z, vec3.x * 0.0f, vec3.y * 0.0f, vec3.z * 0.0f);
	}
	
	@Override
	public float getBrightness() {
		return 1F;
	}

	@Override
	protected ParticleOptions getTrailParticle() {
		Vector3f PARTICLE_COLOR = new Vector3f(Vec3.fromRGB24(0xb5120c));
		return new DustParticleOptions(PARTICLE_COLOR, 1);
	}

	protected void onHit(HitResult p_37388_) {
		super.onHit(p_37388_);
		if (!this.level.isClientSide) {
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
