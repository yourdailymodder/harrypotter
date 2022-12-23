package com.minecraftserverzone.harrypotter.mobs.acromantula;

import javax.annotation.Nullable;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.Mth;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LeapAtTargetGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;

public class Acromantula extends Spider {
	private static final EntityDataAccessor<Integer> ID_SIZE = SynchedEntityData.defineId(Acromantula.class, EntityDataSerializers.INT);
	public static final int MIN_SIZE = 1;
	public static final int MAX_SIZE = 10;
	
   public Acromantula(EntityType<? extends Acromantula> p_32254_, Level p_32255_) {
      super(p_32254_, p_32255_);
      this.xpReward = 10;
   }
	@Override
	public boolean shouldDropExperience() {
		return true;
	}
	
   protected void defineSynchedData() {
	      super.defineSynchedData();
	      this.entityData.define(ID_SIZE, 1);
	   }
   
   public void refreshDimensions() {
	      double d0 = this.getX();
	      double d1 = this.getY();
	      double d2 = this.getZ();
	      super.refreshDimensions();
	      this.setPos(d0, d1, d2);
	   }
   
   public EntityDimensions getDimensions(Pose p_33597_) {
	      return super.getDimensions(p_33597_).scale(0.05f * (float)this.getSize() + 0.25f);
	   }

	   protected void setSize(int p_33594_, boolean p_33595_) {
	      int i = Mth.clamp(p_33594_, 1, 127);
	      this.entityData.set(ID_SIZE, i);
	      this.reapplyPosition();
	      this.refreshDimensions();
	      this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(5 + (double)(i));
	      this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue((double)1 + (i/5));
	      if (p_33595_) {
	         this.setHealth(this.getMaxHealth());
	      }
	      this.setPersistenceRequired();

	      this.xpReward = i;
	   }

	   public int getSize() {
	      return this.entityData.get(ID_SIZE);
	   }

	   public void addAdditionalSaveData(CompoundTag p_33619_) {
	      super.addAdditionalSaveData(p_33619_);
	      p_33619_.putInt("Size", this.getSize() - 1);
	   }

	   public void readAdditionalSaveData(CompoundTag p_33607_) {
	      this.setSize(p_33607_.getInt("Size") + 1, false);
	      super.readAdditionalSaveData(p_33607_);
	   }
   
	   
	   protected boolean shouldDespawnInPeaceful() {
		   return true;
	   }
	   
	   public void onSyncedDataUpdated(EntityDataAccessor<?> p_33609_) {
		      if (ID_SIZE.equals(p_33609_)) {
		         this.refreshDimensions();
		         this.setYRot(this.yHeadRot);
		         this.yBodyRot = this.yHeadRot;
		      }

		      super.onSyncedDataUpdated(p_33609_);
		   }
	   
	   @Nullable
	   public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_33601_, DifficultyInstance p_33602_, MobSpawnType p_33603_, @Nullable SpawnGroupData p_33604_, @Nullable CompoundTag p_33605_) {
	      int i = this.random.nextInt(40);
	      int j = i;
	      this.setSize(j, true);
//	      p_33604_ = super.finalizeSpawn(p_33601_, p_33602_, p_33603_, p_33604_, p_33605_);
//	      if (p_33601_.getRandom().nextInt(100) == 0) {
//	         DeathEater deatheater = Registrations.DEATH_EATER.get().create(this.level);
//	         deatheater.moveTo(this.getX(), this.getY(), this.getZ(), this.getYRot(), 0.0F);
//	         deatheater.finalizeSpawn(p_33601_, p_33602_, p_33603_, (SpawnGroupData)null, (CompoundTag)null);
//	         deatheater.startRiding(this);
//	      }
	      
	      return p_33604_;
	   }

   public static AttributeSupplier.Builder createAttributes() {
	   return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH, 15.0D).add(Attributes.MOVEMENT_SPEED, (double)0.3F);
   }

   public boolean doHurtTarget(Entity p_32257_) {
      if (super.doHurtTarget(p_32257_)) {
         if (p_32257_ instanceof LivingEntity) {
            int i = 0;
            if (this.level.getDifficulty() == Difficulty.NORMAL) {
               i = 7;
            } else if (this.level.getDifficulty() == Difficulty.HARD) {
               i = 15;
            }

            if (i > 0) {
               ((LivingEntity)p_32257_).addEffect(new MobEffectInstance(MobEffects.POISON, i * 20, 0), this);
            }
         }

         return true;
      } else {
         return false;
      }
   }

   protected float getStandingEyeHeight(Pose p_32265_, EntityDimensions p_32266_) {
      return 0.45F;
   }
   
   protected void registerGoals() {
	      this.goalSelector.addGoal(1, new FloatGoal(this));
	      this.goalSelector.addGoal(3, new LeapAtTargetGoal(this, 0.4F));
	      this.goalSelector.addGoal(4, new SpiderAttackGoal(this));
	      this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 0.8D));
	      this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
	      this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
	      this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setAlertOthers());
	      this.targetSelector.addGoal(2, new SpiderTargetGoal<>(this, Player.class));
	      this.targetSelector.addGoal(3, new SpiderTargetGoal<>(this, IronGolem.class));
	   }
   static class SpiderTargetGoal<T extends LivingEntity> extends NearestAttackableTargetGoal<T> {
	      public SpiderTargetGoal(Spider p_33832_, Class<T> p_33833_) {
	         super(p_33832_, p_33833_, true);
	      }

	      public boolean canUse() {
	         return super.canUse();
	      }
	   }
   
   static class SpiderAttackGoal extends MeleeAttackGoal {
	      public SpiderAttackGoal(Spider p_33822_) {
	         super(p_33822_, 1.0D, true);
	      }

	      public boolean canUse() {
	         return super.canUse() && !this.mob.isVehicle();
	      }

	      public boolean canContinueToUse() {
	    	  return super.canContinueToUse();
	      }

	      protected double getAttackReachSqr(LivingEntity p_33825_) {
	         return (double)(6.0F + p_33825_.getBbWidth());
	      }
	   }
}