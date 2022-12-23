package com.minecraftserverzone.harrypotter.spells.avis;

import com.minecraftserverzone.harrypotter.setup.Registrations;

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
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class Birds extends Projectile {

	public Birds(EntityType<? extends Birds> p_37364_, Level p_37365_) {
		super(p_37364_, p_37365_);
	}

	public Birds(Entity entity, Level level) {
		super(Registrations.BIRDS.get(), level);
		this.setOwner(entity);
	}

	@Override
	public boolean isOnFire() {
		this.setRemainingFireTicks(0);
		return false;
	}
	
	@Override
	public void tick() {
		this.noPhysics = true;
		if (!this.level.isClientSide ) {
			if(this.getOwner() != null) {
				if(this.getOwner() instanceof LivingEntity) {
					this.startRiding(this.getOwner());
					this.setPos(this.getOwner().getX(), this.getOwner().getEyeY(), this.getOwner().getZ());
					if (this.tickCount % 15 == 0 ) {
						Entity entity1 = this.getOwner();
						this.getOwner().hurt(new IndirectEntityDamageSource("birds", entity1, entity1).setProjectile(), 1.0F);
					}
				}
			}
		}

		if (this.tickCount % 150 == 0 ) {
			if (!this.level.isClientSide ) {
				this.discard();
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
		int i = Mth.floor(this.getX());
        int j = Mth.floor(this.getY() - (double)0.1F);
        int k = Mth.floor(this.getZ());
        BlockPos pos = new BlockPos(i, j, k);
        BlockState blockstate = this.level.getBlockState(pos);

		return new BlockParticleOption(ParticleTypes.BLOCK, blockstate);
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
