package com.minecraftserverzone.harrypotter.spells.mobilicorpus;

import com.minecraftserverzone.harrypotter.setup.Registrations;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class BodyMoverEntity extends Projectile {
	public double[] ownerViewVector = new double[3];
	public double[] direction = {0, 0, 0};

	public BodyMoverEntity(EntityType<? extends BodyMoverEntity> p_37364_, Level p_37365_) {
		super(p_37364_, p_37365_);
	}
	
	public BodyMoverEntity(Entity player, Level level) {
		super(Registrations.BODY_MOVER.get(), level);
		this.setOwner(player);
			ownerViewVector[0] = 0;
			ownerViewVector[1] = 0;
			ownerViewVector[2] = 0;
	}
	
	@Override
	public boolean isOnFire() {
		this.setRemainingFireTicks(0);
		return false;
	}
	
	@Override
	public void tick() {
		
//			if (!this.level.isClientSide ) {
				if(this.getOwner() != null) {
					if(this.getOwner() instanceof LivingEntity) {
						if(this.getFirstPassenger() != null) {
							this.getFirstPassenger().setDeltaMovement(0, 0, 0);
							
							if(ownerViewVector[0] != this.getOwner().getLookAngle().x) {
								ownerViewVector[0] = this.getOwner().getLookAngle().x;
							}
							if(ownerViewVector[1] != this.getOwner().getLookAngle().y) {
								ownerViewVector[1] = this.getOwner().getLookAngle().y;
							}
							if(ownerViewVector[2] != this.getOwner().getLookAngle().z) {
								ownerViewVector[2] = this.getOwner().getLookAngle().z;
							}

							if(this.level.getBlockState(this.blockPosition().offset(this.getOwner().getLookAngle().x, 0, 0)).getBlock() == Blocks.AIR) {
								this.setPos(this.position().add(this.getOwner().getLookAngle().x/4, 0, 0));
							}
							
							if(this.level.getBlockState(this.blockPosition().offset(this.getOwner().getLookAngle().z, 0, 0)).getBlock() == Blocks.AIR) {
								this.setPos(this.position().add(0, 0, this.getOwner().getLookAngle().z/4));
							}
							
							//y
							if(this.getOwner().getLookAngle().y < 0 && this.level.getBlockState(this.getFirstPassenger().blockPosition().below()).getBlock() == Blocks.AIR) {
								this.setPos(this.getX(), this.getY()+this.getOwner().getLookAngle().y/5, this.getZ());
							}
							
							if(this.getOwner().getLookAngle().y > 0 && this.level.getBlockState(this.getFirstPassenger().blockPosition().above()).getBlock() == Blocks.AIR) {
								this.setPos(this.getX(), this.getY()+this.getOwner().getLookAngle().y/5, this.getZ());
							}

						}else {
							if (!this.level.isClientSide && this.tickCount > 50) {
								this.discard();
							}
						}
					}
					

					
					if (this.tickCount > 200) {
						if (!this.level.isClientSide ) {
							this.discard();
						}
					}
				}else {
					if (!this.level.isClientSide && this.tickCount > 50) {
						this.discard();
					}
				}
//			}

		
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
