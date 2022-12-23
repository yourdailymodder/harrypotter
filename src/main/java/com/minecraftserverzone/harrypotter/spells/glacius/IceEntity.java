package com.minecraftserverzone.harrypotter.spells.glacius;

import com.minecraftserverzone.harrypotter.setup.Registrations;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class IceEntity extends Projectile {

	public IceEntity(EntityType<? extends IceEntity> p_37364_, Level p_37365_) {
		super(p_37364_, p_37365_);
		this.noPhysics = true;
	}
	
	public IceEntity(Entity player, Level level) {
		super(Registrations.ICE_ENTITY.get(), level);
		this.setOwner(player);
	}
	
	@Override
	public boolean isOnFire() {
		this.setRemainingFireTicks(0);
		return false;
	}
	
	@Override
	public void tick() {
		this.noPhysics = true;
		
		if(this.tickCount % 2 == 0) {
			this.level.addParticle(ParticleTypes.SNOWFLAKE, this.getX(), this.getY() + 1, this.getZ(), ((double)this.random.nextFloat() - 0.5D) * 0.08D, ((double)this.random.nextFloat() - 0.5D) * 0.08D, ((double)this.random.nextFloat() - 0.5D) * 0.08D);
		}
		
			if (!this.level.isClientSide ) {
				if(this.getOwner() != null) {
					if(this.getOwner() instanceof LivingEntity) {
						this.startRiding(this.getOwner());
					}
					
					if (this.tickCount > 100 && this.getOwner().getTicksFrozen()==0) {
						if (!this.level.isClientSide ) {
							this.discard();
						}
					}
				}else {
					if (this.tickCount > 50) {
						this.discard();
					}
				}
			}

		
		super.tick();
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

	protected ParticleOptions getTrailParticle() {
		return ParticleTypes.SNOWFLAKE;
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

	@Override
	protected void defineSynchedData() {
	}
}
