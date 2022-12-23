package com.minecraftserverzone.harrypotter.mobs.dementor;

import java.util.EnumSet;

import javax.annotation.Nullable;

import com.minecraftserverzone.harrypotter.mobs.death_eater.DeathEater;
import com.minecraftserverzone.harrypotter.mobs.inferius.Inferius;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.raid.Raider;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class Dementor extends Monster {
   
   protected static final EntityDataAccessor<Byte> DATA_FLAGS_ID = SynchedEntityData.defineId(Dementor.class, EntityDataSerializers.BYTE);
   @Nullable
   private BlockPos boundOrigin;

   public Dementor(EntityType<? extends Dementor> p_33984_, Level p_33985_) {
      super(p_33984_, p_33985_);
      this.moveControl = new Dementor.VexMoveControl(this);
      this.xpReward = 10;
   }

   public void move(MoverType p_33997_, Vec3 p_33998_) {
      super.move(p_33997_, p_33998_);
      this.checkInsideBlocks();
   }
   
   @Override
   public boolean shouldDropExperience() {
		return true;
	}
   
   @Override
   protected boolean shouldDespawnInPeaceful() {
	      return true;
	   }
   
   @Override
	public boolean isOnFire() {
		return false;
	}
   
   @Override
	public boolean fireImmune() {
		return true;
	}

   @Override
	public MobType getMobType() {
		return MobType.UNDEAD;
	}
   
   public void tick() {
      this.noPhysics = true;
      super.tick();
      this.noPhysics = false;
      this.setNoGravity(true);
   }

   protected void registerGoals() {
      super.registerGoals();
      this.goalSelector.addGoal(0, new FloatGoal(this));
      this.goalSelector.addGoal(4, new Dementor.VexChargeAttackGoal());
      this.goalSelector.addGoal(8, new Dementor.VexRandomMoveGoal());
      this.goalSelector.addGoal(9, new LookAtPlayerGoal(this, Player.class, 3.0F, 1.0F));
      this.goalSelector.addGoal(10, new LookAtPlayerGoal(this, Mob.class, 8.0F));
      this.targetSelector.addGoal(1, (new HurtByTargetGoal(this, Dementor.class)).setAlertOthers());
      this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Player.class, true));
      this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Raider.class, true));
      this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, DeathEater.class, true));
      this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Inferius.class, true));
//      this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, RoxfortStudent.class, true)); 
   }

   public static AttributeSupplier.Builder createAttributes() {
      return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH, 30.0D).add(Attributes.ATTACK_DAMAGE, 4.0D)
    		  .add(Attributes.FOLLOW_RANGE, 100.0D);
   }

   protected void defineSynchedData() {
      super.defineSynchedData();
      this.entityData.define(DATA_FLAGS_ID, (byte)0);
   }

   public void readAdditionalSaveData(CompoundTag p_34008_) {
      super.readAdditionalSaveData(p_34008_);
      if (p_34008_.contains("BoundX")) {
         this.boundOrigin = new BlockPos(p_34008_.getInt("BoundX"), p_34008_.getInt("BoundY"), p_34008_.getInt("BoundZ"));
      }
   }

   public void addAdditionalSaveData(CompoundTag p_34015_) {
      super.addAdditionalSaveData(p_34015_);
      if (this.boundOrigin != null) {
         p_34015_.putInt("BoundX", this.boundOrigin.getX());
         p_34015_.putInt("BoundY", this.boundOrigin.getY());
         p_34015_.putInt("BoundZ", this.boundOrigin.getZ());
      }
   }

   @Nullable
   public BlockPos getBoundOrigin() {
      return this.boundOrigin;
   }

   public void setBoundOrigin(@Nullable BlockPos p_34034_) {
      this.boundOrigin = p_34034_;
   }

   private boolean getVexFlag(int p_34011_) {
      int i = this.entityData.get(DATA_FLAGS_ID);
      return (i & p_34011_) != 0;
   }

   private void setVexFlag(int p_33990_, boolean p_33991_) {
      int i = this.entityData.get(DATA_FLAGS_ID);
      if (p_33991_) {
         i |= p_33990_;
      } else {
         i &= ~p_33990_;
      }

      this.entityData.set(DATA_FLAGS_ID, (byte)(i & 255));
   }

   public boolean isCharging() {
      return this.getVexFlag(1);
   }

   public void setIsCharging(boolean p_34043_) {
      this.setVexFlag(1, p_34043_);
   }

   protected SoundEvent getDeathSound() {
      return SoundEvents.VEX_DEATH;
   }

   public float getBrightness() {
      return 1.0F;
   }

   class VexChargeAttackGoal extends Goal {
      public VexChargeAttackGoal() {
         this.setFlags(EnumSet.of(Goal.Flag.MOVE));
      }

      public boolean canUse() {
         if (Dementor.this.getTarget() != null && !Dementor.this.getMoveControl().hasWanted() && Dementor.this.random.nextInt(reducedTickDelay(7)) == 0) {
            return Dementor.this.distanceToSqr(Dementor.this.getTarget()) > 4.0D;
         } else {
            return false;
         }
      }

      public boolean canContinueToUse() {
         return Dementor.this.getMoveControl().hasWanted() && Dementor.this.isCharging() && Dementor.this.getTarget() != null && Dementor.this.getTarget().isAlive();
      }

      public void start() {
         LivingEntity livingentity = Dementor.this.getTarget();
         if (livingentity != null) {
            Vec3 vec3 = livingentity.getEyePosition();
            Dementor.this.moveControl.setWantedPosition(vec3.x, vec3.y, vec3.z, 1.0D);
         }

         Dementor.this.setIsCharging(true);
         Dementor.this.playSound(SoundEvents.VEX_CHARGE, 1.0F, 1.0F);
      }

      public void stop() {
         Dementor.this.setIsCharging(false);
      }

      public boolean requiresUpdateEveryTick() {
         return true;
      }

      public void tick() {
         LivingEntity livingentity = Dementor.this.getTarget();
         if (livingentity != null) {
            if (Dementor.this.getBoundingBox().intersects(livingentity.getBoundingBox())) {
               Dementor.this.doHurtTarget(livingentity);
               Dementor.this.setIsCharging(false);
            } else {
               double d0 = Dementor.this.distanceToSqr(livingentity);
               if (d0 < 9.0D) {
                  Vec3 vec3 = livingentity.getEyePosition();
                  Dementor.this.moveControl.setWantedPosition(vec3.x, vec3.y, vec3.z, 1.0D);
               }
            }

         }
      }
   }

   class VexMoveControl extends MoveControl {
      public VexMoveControl(Dementor p_34062_) {
         super(p_34062_);
      }

      public void tick() {
         if (this.operation == MoveControl.Operation.MOVE_TO) {
            Vec3 vec3 = new Vec3(this.wantedX - Dementor.this.getX(), this.wantedY - Dementor.this.getY(), this.wantedZ - Dementor.this.getZ());
            double d0 = vec3.length();
            if (d0 < Dementor.this.getBoundingBox().getSize()) {
               this.operation = MoveControl.Operation.WAIT;
               Dementor.this.setDeltaMovement(Dementor.this.getDeltaMovement().scale(0.5D));
            } else {
               Dementor.this.setDeltaMovement(Dementor.this.getDeltaMovement().add(vec3.scale(this.speedModifier * 0.05D / d0)));
               if (Dementor.this.getTarget() == null) {
                  Vec3 vec31 = Dementor.this.getDeltaMovement();
                  Dementor.this.setYRot(-((float)Mth.atan2(vec31.x, vec31.z)) * (180F / (float)Math.PI));
                  Dementor.this.yBodyRot = Dementor.this.getYRot();
               } else {
                  double d2 = Dementor.this.getTarget().getX() - Dementor.this.getX();
                  double d1 = Dementor.this.getTarget().getZ() - Dementor.this.getZ();
                  Dementor.this.setYRot(-((float)Mth.atan2(d2, d1)) * (180F / (float)Math.PI));
                  Dementor.this.yBodyRot = Dementor.this.getYRot();
               }
            }

         }
      }
   }

   class VexRandomMoveGoal extends Goal {
      public VexRandomMoveGoal() {
         this.setFlags(EnumSet.of(Goal.Flag.MOVE));
      }

      public boolean canUse() {
         return !Dementor.this.getMoveControl().hasWanted() && Dementor.this.random.nextInt(reducedTickDelay(7)) == 0;
      }

      public boolean canContinueToUse() {
         return false;
      }

      public void tick() {
         BlockPos blockpos = Dementor.this.getBoundOrigin();
         if (blockpos == null) {
            blockpos = Dementor.this.blockPosition();
         }

         for(int i = 0; i < 3; ++i) {
            BlockPos blockpos1 = blockpos.offset(Dementor.this.random.nextInt(15) - 7, Dementor.this.random.nextInt(11) - 5, Dementor.this.random.nextInt(15) - 7);
            if (Dementor.this.level.isEmptyBlock(blockpos1)) {
               Dementor.this.moveControl.setWantedPosition((double)blockpos1.getX() + 0.5D, (double)blockpos1.getY() + 0.5D, (double)blockpos1.getZ() + 0.5D, 0.25D);
               if (Dementor.this.getTarget() == null) {
                  Dementor.this.getLookControl().setLookAt((double)blockpos1.getX() + 0.5D, (double)blockpos1.getY() + 0.5D, (double)blockpos1.getZ() + 0.5D, 180.0F, 20.0F);
               }
               break;
            }
         }

      }
   }
}