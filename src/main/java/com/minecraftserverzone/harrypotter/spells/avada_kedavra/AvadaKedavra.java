package com.minecraftserverzone.harrypotter.spells.avada_kedavra;

import org.joml.Vector3f;

import com.minecraftserverzone.harrypotter.broomsticks.BroomStick;
import com.minecraftserverzone.harrypotter.setup.Registrations;
import com.minecraftserverzone.harrypotter.spells.DamageSpell;

import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.IndirectEntityDamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class AvadaKedavra extends DamageSpell {
	public long seed;
	
	public AvadaKedavra(EntityType<? extends AvadaKedavra> p_37364_, Level p_37365_) {
		super(p_37364_, p_37365_);
		this.seed = this.random.nextLong();
	}

	public AvadaKedavra(Level p_37375_, LivingEntity p_37376_, double p_37377_, double p_37378_, double p_37379_) {
		super(Registrations.AVADA_KEDAVRA.get(), p_37376_, p_37377_, p_37378_, p_37379_, p_37375_);
	}
	
	@Override
	public boolean isOnFire() {
		this.setRemainingFireTicks(0);
		return false;
	}
	
	@Override
	public void tick() {
		this.seed = this.random.nextLong();
		super.tick();
	}

	public AvadaKedavra(Level p_37367_, double p_37368_, double p_37369_, double p_37370_, double p_37371_,
			double p_37372_, double p_37373_) {
		super(Registrations.AVADA_KEDAVRA.get(), p_37368_, p_37369_, p_37370_, p_37371_, p_37372_, p_37373_, p_37367_);
	}
	
	@Override
	protected void onHitBlock(BlockHitResult p_37258_) {
		super.onHitBlock(p_37258_);
	}

	protected void onHitEntity(EntityHitResult p_37386_) {
		super.onHitEntity(p_37386_);
		if (!this.level.isClientSide) {
			if(this.getOwner() != null) {
				Entity entity = p_37386_.getEntity();
				Entity entity1 = this.getOwner();
				
				if(p_37386_.getEntity() instanceof BroomStick && ((LivingEntity) p_37386_.getEntity()).getFirstPassenger() != null) {
					if(((LivingEntity) p_37386_.getEntity()).getFirstPassenger() != entity1) {
//						entity.hurt(DamageSource.MAGIC, 999.0F);
					    entity.hurt(new IndirectEntityDamageSource("InstantDeath", entity1, entity1).setProjectile(), 999.0F);
					}
				}else {
//					entity.hurt(DamageSource.MAGIC, 999.0F);
				    entity.hurt(new IndirectEntityDamageSource("InstantDeath", entity1, entity1).setProjectile(), 999.0F);
				}
				if (entity1 instanceof LivingEntity) {
			    	this.doEnchantDamageEffects((LivingEntity)entity1, entity);
			    }
			}
		}
	}
	
	@Override
	public float getLightLevelDependentMagicValue() {
		return 1F;
	}

	@Override
	protected ParticleOptions getTrailParticle() {
		// Example values
		int r = 255; // 0xFF
		int g = 1;   // 0x01
		int b = 15;  // 0x0F

		// go back to original form:
		                      //    A  R  G  B
		int color = r << 16;  // 0x00.FF.00.00
		color += g << 8;      // 0x00.FF.01.00
		color += b;           // 0x00.FF.01.0F

		// just add back the alpha if it is going to be full on
		color += 255 << 24; // 0xFF.FF.01.0F
		
		Vector3f PARTICLE_COLOR = Vec3.fromRGB24(0x69ff7d).toVector3f();//FastColor.ARGB32.color(255, 252, 119, 3); 16711680
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
