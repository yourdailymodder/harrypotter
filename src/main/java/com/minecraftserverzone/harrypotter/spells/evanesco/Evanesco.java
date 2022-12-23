package com.minecraftserverzone.harrypotter.spells.evanesco;

import com.minecraftserverzone.harrypotter.setup.Registrations;
import com.minecraftserverzone.harrypotter.spells.accio.PullSpell;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class Evanesco extends PullSpell {
	public Evanesco(EntityType<? extends Evanesco> p_37364_, Level p_37365_) {
		super(p_37364_, p_37365_);
	}

	public Evanesco(Level p_37375_, LivingEntity p_37376_, double p_37377_, double p_37378_, double p_37379_) {
		super(Registrations.EVANESCO.get(), p_37376_, p_37377_, p_37378_, p_37379_, p_37375_);
	}
	
	@Override
	public boolean isOnFire() {
		this.setRemainingFireTicks(0);
		return false;
	}
	
	@Override
	public void tick() {
		super.tick();
	}

	public Evanesco(Level p_37367_, double p_37368_, double p_37369_, double p_37370_, double p_37371_,
			double p_37372_, double p_37373_) {
		super(Registrations.EVANESCO.get(), p_37368_, p_37369_, p_37370_, p_37371_, p_37372_, p_37373_, p_37367_);
	}
	
	@Override
	protected void onHitBlock(BlockHitResult p_37258_) {
		super.onHitBlock(p_37258_);
	}

	protected void onHitEntity(EntityHitResult p_37386_) {
		super.onHitEntity(p_37386_);
		if (!this.level.isClientSide) {
			Entity entity = p_37386_.getEntity();
			if(entity instanceof LivingEntity) {
				((LivingEntity)entity).addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, 300));

			}
//				entity.setDeltaMovement(-owner.getLookAngle().x * 2, -owner.getLookAngle().y * 2, -owner.getLookAngle().z * 2);
			}
	}
	
	@Override
	public float getLightLevelDependentMagicValue() {
		return 1F;
	}

	@Override
	protected ParticleOptions getTrailParticle() {
		int i = Mth.floor(this.getX());
        int j = Mth.floor(this.getY() - (double)0.2F);
        int k = Mth.floor(this.getZ());
        BlockPos pos = new BlockPos(i, j, k);
        BlockState blockstate = this.level.getBlockState(pos);

		return new BlockParticleOption(ParticleTypes.BLOCK, blockstate);
	}

	protected void onHit(HitResult p_37388_) {
		super.onHit(p_37388_);
		if (!this.level.isClientSide) {
			this.discard();
		}
		

		for(ItemEntity itementity : this.level.getEntitiesOfClass(ItemEntity.class, this.getBoundingBox().inflate(2D, 2D, 2D))) {
            if (!itementity.isRemoved() && !itementity.getItem().isEmpty() && !itementity.hasPickUpDelay()) {
               itementity.setDeltaMovement(0, 0.1f, 0);
               itementity.setNoGravity(true);

            }
         }
		
	}

	public boolean isPickable() {
		return false;
	}

	public boolean hurt(DamageSource p_37381_, float p_37382_) {
		return false;
	}
}
