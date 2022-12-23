package com.minecraftserverzone.harrypotter.mobs.patronus_deer;

import java.util.List;

import com.minecraftserverzone.harrypotter.setup.Registrations;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.FollowParentGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

public class PatronusDeer extends Animal {
   public PatronusDeer(EntityType<? extends PatronusDeer> p_29462_, Level p_29463_) {
      super(p_29462_, p_29463_);
   }

   protected void registerGoals() {
      this.goalSelector.addGoal(0, new FloatGoal(this));
      this.goalSelector.addGoal(1, new DeerPanicGoal(this, 1.25D));
      this.goalSelector.addGoal(3, new BreedGoal(this, 1.0D));
      this.goalSelector.addGoal(5, new FollowParentGoal(this, 1.1D));
      this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 1.0D));
      this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 6.0F));
      this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
   }

   public static AttributeSupplier.Builder createAttributes() {
      return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 100.0D).add(Attributes.MOVEMENT_SPEED, 0.5D);
   }
   
   @Override
	public void tick() {
		super.tick();
		   List<Entity> livingEntitiesNear = this.level.getEntities(this, new AABB(this.getX() - 10.0D, this.getY() - 10.0D, this.getZ() - 10.0D, this.getX() + 10.0D, this.getY() + 10.0D, this.getZ() + 10.0D), Entity::isAlive);
			for(Entity entity : livingEntitiesNear) {
				if(entity instanceof LivingEntity) {
					if(((LivingEntity)entity).getMobType() == MobType.UNDEAD) {
						entity.setDeltaMovement(-((LivingEntity)entity).getLookAngle().x * 2, 1.5f, -((LivingEntity)entity).getLookAngle().z * 2);
					}
				}
			}
			
			if(!this.level.isClientSide && tickCount > 200) {
				this.level.addParticle(ParticleTypes.POOF, this.getX() + ((double)this.random.nextFloat() - 0.5D) * (double)this.getBbWidth(), this.getY() + 0.1D, this.getZ() + ((double)this.random.nextFloat() - 0.5D) * (double)this.getBbWidth(), 4.0D * ((double)this.random.nextFloat() - 0.5D), 0.5D, ((double)this.random.nextFloat() - 0.5D) * 4.0D);
				this.discard();
			}
	}

   public void onSyncedDataUpdated(EntityDataAccessor<?> p_29480_) {
      super.onSyncedDataUpdated(p_29480_);
   }

   protected void defineSynchedData() {
      super.defineSynchedData();
   }

   public void addAdditionalSaveData(CompoundTag p_29495_) {
      super.addAdditionalSaveData(p_29495_);
   }

   public void readAdditionalSaveData(CompoundTag p_29478_) {
      super.readAdditionalSaveData(p_29478_);
   }

   protected void playStepSound(BlockPos p_29492_, BlockState p_29493_) {
      this.playSound(SoundEvents.HORSE_STEP, 0.15F, 1.0F);
   }

   @Override
	public boolean canBeLeashed(Player p_21418_) {
		return false;
	}

   public PatronusDeer getBreedOffspring(ServerLevel p_149001_, AgeableMob p_149002_) {
      return Registrations.PATRONUS_DEER.get().create(p_149001_);
   }
}
