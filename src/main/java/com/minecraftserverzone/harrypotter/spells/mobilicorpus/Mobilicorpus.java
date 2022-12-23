package com.minecraftserverzone.harrypotter.spells.mobilicorpus;

import com.minecraftserverzone.harrypotter.broomsticks.BroomStick;
import com.minecraftserverzone.harrypotter.setup.Registrations;
import com.minecraftserverzone.harrypotter.spells.DamageSpell;
import com.mojang.math.Vector3f;

import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class Mobilicorpus extends DamageSpell {

	public Mobilicorpus(EntityType<? extends Mobilicorpus> p_37364_, Level p_37365_) {
		super(p_37364_, p_37365_);
	}

	public Mobilicorpus(Level p_37375_, LivingEntity p_37376_, double p_37377_, double p_37378_, double p_37379_) {
		super(Registrations.MOBILICORPUS.get(), p_37376_, p_37377_, p_37378_, p_37379_, p_37375_);
	}
	
	@Override
	public boolean isOnFire() {
		this.setRemainingFireTicks(0);
		return false;
	}
	
	@Override
	public void tick() {
		if (this.tickCount % 600 == 0 ) {
			if (!this.level.isClientSide ) {
				this.discard();
			}
		}
		
		super.tick();
	}

	public Mobilicorpus(Level p_37367_, double p_37368_, double p_37369_, double p_37370_, double p_37371_,
			double p_37372_, double p_37373_) {
		super(Registrations.MOBILICORPUS.get(), p_37368_, p_37369_, p_37370_, p_37371_, p_37372_, p_37373_, p_37367_);
	}
	
	@Override
	protected void onHitBlock(BlockHitResult p_37258_) {
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
				//spawn ice entity around the target
				//the target of the ice entity = entity
				if(entity != this.getOwner() && entity instanceof LivingEntity) {		
					
					if(entity instanceof BroomStick && ((LivingEntity) entity).getFirstPassenger() != null) {
						if(((LivingEntity) entity).getFirstPassenger() != this.getOwner()) {
							BodyMoverEntity bodyMoverEntity = new BodyMoverEntity(this.getOwner(), this.getOwner().level);
				 			bodyMoverEntity.setPos(entity.getX(), entity.getY(), entity.getZ());
				 			entity.startRiding(bodyMoverEntity);
				 			entity.level.addFreshEntity(bodyMoverEntity);
						}
					}else {
						BodyMoverEntity bodyMoverEntity = new BodyMoverEntity(this.getOwner(), this.getOwner().level);
			 			bodyMoverEntity.setPos(entity.getX(), entity.getY(), entity.getZ());
			 			entity.startRiding(bodyMoverEntity);
			 			entity.level.addFreshEntity(bodyMoverEntity);
					}
				}
			}
	}

	@Override
	public float getBrightness() {
		return 1F;
	}

	@Override
	protected ParticleOptions getTrailParticle() {
		Vector3f PARTICLE_COLOR = new Vector3f(Vec3.fromRGB24(0xffffff));
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
