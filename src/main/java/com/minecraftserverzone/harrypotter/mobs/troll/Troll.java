package com.minecraftserverzone.harrypotter.mobs.troll;

import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.MoveTowardsTargetGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;

public class Troll extends Monster {
   private static final EntityDataAccessor<Byte> DATA_FLAGS_ID = SynchedEntityData.defineId(Troll.class, EntityDataSerializers.BYTE);
   private static final EntityDataAccessor<Integer> DATA_COLOR = SynchedEntityData.defineId(Troll.class, EntityDataSerializers.INT);
   private static final EntityDataAccessor<Boolean> DATA_IS_CHARGING = SynchedEntityData.defineId(Troll.class, EntityDataSerializers.BOOLEAN);
   private int attackAnimationTick;
   
   
   public Troll(EntityType<? extends Troll> p_33786_, Level p_33787_) {
      super(p_33786_, p_33787_);
      this.xpReward = 15;
   }

   @Override
   protected boolean shouldDespawnInPeaceful() {
	      return true;
	   }
   
   @Override
   public boolean shouldDropExperience() {
		return true;
	}

   @Override
   protected void registerGoals() {
	  this.goalSelector.addGoal(1, new FloatGoal(this));
	  this.goalSelector.addGoal(2, new TrollAttackGoal(this));
	  this.goalSelector.addGoal(3, new MoveTowardsTargetGoal(this, 1D, 100.0F));
      this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 0.8D));
      this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
      this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
      this.targetSelector.addGoal(1, new HurtByTargetGoal(this).setAlertOthers());
      this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
      this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Player.class, true));

   }

   @Override
   protected void defineSynchedData() {
      super.defineSynchedData();
      this.entityData.define(DATA_FLAGS_ID, (byte)0);
      this.entityData.define(DATA_COLOR, 1);
      this.entityData.define(DATA_IS_CHARGING, false);
   }
   
   public int getColor() {
	      return this.entityData.get(DATA_COLOR);
	   }

	   public void setColor(int p_30398_) {
	      this.entityData.set(DATA_COLOR, p_30398_);
	   }

   @Override
   public void tick() {
      super.tick();
      if (!this.level.isClientSide) {
         
      }
      if (this.attackAnimationTick > 0) {
          --this.attackAnimationTick;
       }
   }
   
   @Override
   public void aiStep() {
	   super.aiStep();
   }
   
   @Override
	public void addAdditionalSaveData(CompoundTag p_21484_) {
		super.addAdditionalSaveData(p_21484_);
		p_21484_.putByte("Color", (byte)this.getColor());
	}
   
   public void readAdditionalSaveData(CompoundTag p_30402_) {
	      super.readAdditionalSaveData(p_30402_);
	      if (p_30402_.contains("Color", 99)) {
	         this.setColor(p_30402_.getInt("Color"));
	      }
	   }
   
   public static AttributeSupplier.Builder createAttributes() {
      return Monster.createMonsterAttributes()
    		  .add(Attributes.MAX_HEALTH, 80.0D)
    		  .add(Attributes.FOLLOW_RANGE, 60.0D)
    		  .add(Attributes.MOVEMENT_SPEED, (double)0.3F)
    		  .add(Attributes.ATTACK_DAMAGE, 25)
    		  .add(Attributes.KNOCKBACK_RESISTANCE, 1);
   }

   //loot drops
//   @Override
//   protected void dropCustomDeathLoot(DamageSource p_33574_, int p_33575_, boolean p_33576_) {
//	      super.dropCustomDeathLoot(p_33574_, p_33575_, p_33576_);
//	      Entity entity = p_33574_.getEntity();
//	      if (entity instanceof Creeper) {
//	         Creeper creeper = (Creeper)entity;
//	         if (creeper.canDropMobsSkull()) {
//	            creeper.increaseDroppedSkulls();
//	            this.spawnAtLocation(Items.SKELETON_SKULL);
//	         }
//	      }
//
//	   }

   @Override
	protected float getSoundVolume() {
		return 1;
	}

   public MobType getMobType() {
      return MobType.UNDEFINED;
   }

@Override
	public boolean doHurtTarget(Entity p_21372_) {
		this.attackAnimationTick = 10;
	    this.level.broadcastEntityEvent(this, (byte)4);
	    p_21372_.setDeltaMovement(p_21372_.getDeltaMovement().add(0.0D, (double)0.4F, 0.0D));
		return super.doHurtTarget(p_21372_);
	}

@Override
	public void handleEntityEvent(byte p_28844_) {
	    if (p_28844_ == 4) {
	       this.attackAnimationTick = 10;
	    } else {
	       super.handleEntityEvent(p_28844_);
	    }
	
	 }
	
	 public int getAttackAnimationTick() {
	    return this.attackAnimationTick;
	 }
	 
	 public boolean isCharging() {
	      return this.entityData.get(DATA_IS_CHARGING);
	   }

	   public void setCharging(boolean p_32759_) {
	      this.entityData.set(DATA_IS_CHARGING, p_32759_);
	   }

   @Nullable
   public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_33790_, DifficultyInstance p_33791_, MobSpawnType p_33792_, @Nullable SpawnGroupData p_33793_, @Nullable CompoundTag p_33794_) {
      p_33793_ = super.finalizeSpawn(p_33790_, p_33791_, p_33792_, p_33793_, p_33794_);

      if (p_33793_ == null) {
         p_33793_ = new Troll.TwohandEffectsGroupData();
         if (p_33790_.getDifficulty() == Difficulty.HARD && p_33790_.getRandom().nextFloat() < 0.1F * p_33791_.getSpecialMultiplier()) {
            ((Troll.TwohandEffectsGroupData)p_33793_).setRandomEffect(p_33790_.getRandom());
         }
      }

      if (p_33793_ instanceof Troll.TwohandEffectsGroupData) {
         MobEffect mobeffect = ((Troll.TwohandEffectsGroupData)p_33793_).effect;
         if (mobeffect != null) {
            this.addEffect(new MobEffectInstance(mobeffect, Integer.MAX_VALUE));
         }
      }

      int rand = new Random().nextInt(2)+1;
      this.setColor(rand);
      
      this.setPersistenceRequired();
      return p_33793_;
   }

   protected float getStandingEyeHeight(Pose p_33799_, EntityDimensions p_33800_) {
      return 0.65F;
   }

   public static class TwohandEffectsGroupData implements SpawnGroupData {
      public MobEffect effect;

      public void setRandomEffect(RandomSource p_33830_) {
         int i = p_33830_.nextInt(5);
         if (i <= 1) {
            this.effect = MobEffects.MOVEMENT_SPEED;
         } else if (i <= 2) {
            this.effect = MobEffects.DAMAGE_BOOST;
         } else if (i <= 3) {
            this.effect = MobEffects.REGENERATION;
         } else if (i <= 4) {
            this.effect = MobEffects.INVISIBILITY;
         }

      }
   }
   
   static class TrollAttackGoal extends MeleeAttackGoal {
	      public TrollAttackGoal(Troll p_33822_) {
	         super(p_33822_, 1.0D, true);
	      }

	      public boolean canUse() {
	         return super.canUse() && !this.mob.isVehicle();
	      }

	      public boolean canContinueToUse() {
	         if (this.mob.getRandom().nextInt(100) == 0) {
	            this.mob.setTarget((LivingEntity)null);
	            return false;
	         } else {
	            return super.canContinueToUse();
	         }
	      }

	      protected double getAttackReachSqr(LivingEntity p_33825_) {
	         return (double)(15.0F + p_33825_.getBbWidth());
	      }
	   }
}
