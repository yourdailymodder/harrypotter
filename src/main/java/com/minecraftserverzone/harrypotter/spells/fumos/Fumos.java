package com.minecraftserverzone.harrypotter.spells.fumos;

import com.minecraftserverzone.harrypotter.setup.Registrations;
import com.minecraftserverzone.harrypotter.spells.NormalBallTypeSpell;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class Fumos extends NormalBallTypeSpell {

	public Fumos(EntityType<? extends Fumos> p_37364_, Level p_37365_) {
		super(p_37364_, p_37365_);
	}

	public Fumos(Level p_37375_, LivingEntity p_37376_, double p_37377_, double p_37378_, double p_37379_) {
		super(Registrations.FUMOS.get(), p_37376_, p_37377_, p_37378_, p_37379_, p_37375_);
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
	 
	         if(this.tickCount % 20 == 0 && !this.level.isClientSide) {
	        	 this.discard();
	         } 
	            for(int i = -2; i < 3; ++i) {
	            	for(int j = -2; j < 3; ++j) {
	            		for(int k = -2; k < 3; ++k) {
	            			
	            			this.level.addParticle(this.getTrailParticle(), d0 - vec3.x + i, 0.15f +  d1 - vec3.y + j, d2 - vec3.z + k, vec3.x * 0.0f, vec3.y * 0.0f, vec3.z * 0.0f);
	            		}
	            	}
	            }

		super.tick();
	}

	public Fumos(Level p_37367_, double p_37368_, double p_37369_, double p_37370_, double p_37371_,
			double p_37372_, double p_37373_) {
		super(Registrations.FUMOS.get(), p_37368_, p_37369_, p_37370_, p_37371_, p_37372_, p_37373_, p_37367_);
	}
	
	@Override
	protected void onHitBlock(BlockHitResult p_37258_) {
		super.onHitBlock(p_37258_);
	}

	protected void onHitEntity(EntityHitResult p_37386_) {
		super.onHitEntity(p_37386_);
	}
	
	@Override
	public float getLightLevelDependentMagicValue() {
		return 1F;
	}

	@Override
	protected ParticleOptions getTrailParticle() {
		return ParticleTypes.CAMPFIRE_SIGNAL_SMOKE;
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
