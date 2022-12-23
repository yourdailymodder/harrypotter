package com.minecraftserverzone.harrypotter.mobs.inferius;

import java.util.Random;
import java.util.UUID;
import java.util.function.Predicate;

import javax.annotation.Nullable;

import com.minecraftserverzone.harrypotter.setup.Registrations;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.BreakDoorGoal;
import net.minecraft.world.entity.ai.goal.FleeSunGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MoveThroughVillageGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RestrictSunGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.ai.util.GoalUtils;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;

public class Inferius extends Monster {
   private static final UUID SPEED_MODIFIER_BABY_UUID = UUID.fromString("B9766B59-9566-4402-BC1F-2EE2A276D836");
   private static final AttributeModifier SPEED_MODIFIER_BABY = new AttributeModifier(SPEED_MODIFIER_BABY_UUID, "Baby speed boost", 0.25D, AttributeModifier.Operation.MULTIPLY_BASE);
   private static final EntityDataAccessor<Boolean> DATA_BABY_ID = SynchedEntityData.defineId(Inferius.class, EntityDataSerializers.BOOLEAN);
   private static final EntityDataAccessor<Integer> DATA_SPECIAL_TYPE_ID = SynchedEntityData.defineId(Inferius.class, EntityDataSerializers.INT);
   public static final float ZOMBIE_LEADER_CHANCE = 0.05F;
   public static final int REINFORCEMENT_ATTEMPTS = 50;
   public static final int REINFORCEMENT_RANGE_MAX = 40;
   public static final int REINFORCEMENT_RANGE_MIN = 7;
   private static final Predicate<Difficulty> DOOR_BREAKING_PREDICATE = (p_34284_) -> {
      return p_34284_ == Difficulty.HARD;
   };
   public static final Predicate<LivingEntity> PREY_SELECTOR = (p_30437_) -> {
	      EntityType<?> entitytype = p_30437_.getType();
	      return entitytype != Registrations.INFERIUS.get() && entitytype != Registrations.DEATH_EATER.get() ;
	   };
	   
   private final BreakDoorGoal breakDoorGoal = new BreakDoorGoal(this, DOOR_BREAKING_PREDICATE);
   private boolean canBreakDoors;

   public Inferius(EntityType<? extends Inferius> p_34271_, Level p_34272_) {
      super(p_34271_, p_34272_);
      this.xpReward = 4;
   }
   
   @Override
	protected boolean shouldDropExperience() {
		return true;
	}
   
   @Override
   protected boolean shouldDespawnInPeaceful() {
	      return true;
	   }

   protected void registerGoals() {
      this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
      this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
      this.addBehaviourGoals();
   }

   protected void addBehaviourGoals() {
	  this.goalSelector.addGoal(2, new RestrictSunGoal(this));
	  this.goalSelector.addGoal(1, new FleeSunGoal(this, 1.0D));
      this.goalSelector.addGoal(2, new InferiusAttackGoal(this, 1.0D, false));
      this.goalSelector.addGoal(6, new MoveThroughVillageGoal(this, 1.0D, true, 4, this::canBreakDoors));
      this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1.0D));
      this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setAlertOthers());
      this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
      this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillager.class, false));
      this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, LivingEntity.class, true, PREY_SELECTOR));
   }

   public static AttributeSupplier.Builder createAttributes() {
      return Monster.createMonsterAttributes().add(Attributes.FOLLOW_RANGE, 55.0D).add(Attributes.MAX_HEALTH, 20.0D).add(Attributes.MOVEMENT_SPEED, (double)0.2F).add(Attributes.ATTACK_DAMAGE, 2.0D).add(Attributes.ARMOR, 2.0D).add(Attributes.SPAWN_REINFORCEMENTS_CHANCE);
   }

   protected void defineSynchedData() {
      super.defineSynchedData();
      this.getEntityData().define(DATA_BABY_ID, false);
      this.getEntityData().define(DATA_SPECIAL_TYPE_ID, 0);
   }

   public boolean canBreakDoors() {
      return this.canBreakDoors;
   }

   public void setCanBreakDoors(boolean p_34337_) {
      if (this.supportsBreakDoorGoal() && GoalUtils.hasGroundPathNavigation(this)) {
         if (this.canBreakDoors != p_34337_) {
            this.canBreakDoors = p_34337_;
            ((GroundPathNavigation)this.getNavigation()).setCanOpenDoors(p_34337_);
            if (p_34337_) {
               this.goalSelector.addGoal(1, this.breakDoorGoal);
            } else {
               this.goalSelector.removeGoal(this.breakDoorGoal);
            }
         }
      } else if (this.canBreakDoors) {
         this.goalSelector.removeGoal(this.breakDoorGoal);
         this.canBreakDoors = false;
      }

   }

   protected boolean supportsBreakDoorGoal() {
      return true;
   }

   public boolean isBaby() {
      return this.getEntityData().get(DATA_BABY_ID);
   }

   protected int getExperienceReward(Player p_34322_) {
      if (this.isBaby()) {
         this.xpReward = (int)((double)this.xpReward * 2.5D);
      }

      return super.getExperienceReward(p_34322_);
   }

   public void setBaby(boolean p_34309_) {
      this.getEntityData().set(DATA_BABY_ID, p_34309_);
      if (this.level != null && !this.level.isClientSide) {
         AttributeInstance attributeinstance = this.getAttribute(Attributes.MOVEMENT_SPEED);
         attributeinstance.removeModifier(SPEED_MODIFIER_BABY);
         if (p_34309_) {
            attributeinstance.addTransientModifier(SPEED_MODIFIER_BABY);
         }
      }
   }

   public void onSyncedDataUpdated(EntityDataAccessor<?> p_34307_) {
      if (DATA_BABY_ID.equals(p_34307_)) {
         this.refreshDimensions();
      }
      super.onSyncedDataUpdated(p_34307_);
   }

   public void tick() {
      super.tick();
   }

   public void aiStep() {
      super.aiStep();
   }

   protected boolean isSunSensitive() {
      return false;
   }

   public boolean doHurtTarget(Entity p_34276_) {
      boolean flag = super.doHurtTarget(p_34276_);
      if (flag) {
         float f = this.level.getCurrentDifficultyAt(this.blockPosition()).getEffectiveDifficulty();
         if (this.getMainHandItem().isEmpty() && this.isOnFire() && this.random.nextFloat() < f * 0.3F) {
            p_34276_.setSecondsOnFire(2 * (int)f);
         }
      }

      return flag;
   }

   protected SoundEvent getAmbientSound() {
      return SoundEvents.ZOMBIE_AMBIENT;
   }

   protected SoundEvent getHurtSound(DamageSource p_34327_) {
      return SoundEvents.ZOMBIE_HURT;
   }

   protected SoundEvent getDeathSound() {
      return SoundEvents.ZOMBIE_DEATH;
   }

   protected SoundEvent getStepSound() {
      return SoundEvents.ZOMBIE_STEP;
   }

   protected void playStepSound(BlockPos p_34316_, BlockState p_34317_) {
      this.playSound(this.getStepSound(), 0.15F, 1.0F);
   }

   public MobType getMobType() {
      return MobType.UNDEAD;
   }

   public void addAdditionalSaveData(CompoundTag p_34319_) {
      super.addAdditionalSaveData(p_34319_);
      p_34319_.putBoolean("IsBaby", this.isBaby());
      p_34319_.putBoolean("CanBreakDoors", this.canBreakDoors());
   }

   public void readAdditionalSaveData(CompoundTag p_34305_) {
      super.readAdditionalSaveData(p_34305_);
      this.setBaby(p_34305_.getBoolean("IsBaby"));
      this.setCanBreakDoors(p_34305_.getBoolean("CanBreakDoors"));
   }

//   public void killed(ServerLevel p_34281_, LivingEntity p_34282_) {
//      super.killed(p_34281_, p_34282_);
//      if ((p_34281_.getDifficulty() == Difficulty.NORMAL || p_34281_.getDifficulty() == Difficulty.HARD) && p_34282_ instanceof Villager && net.minecraftforge.event.ForgeEventFactory.canLivingConvert(p_34282_, EntityType.ZOMBIE_VILLAGER, (timer) -> {})) {
//         if (p_34281_.getDifficulty() != Difficulty.HARD && this.random.nextBoolean()) {
//            return;
//         }
//
//         Villager villager = (Villager)p_34282_;
//         ZombieVillager zombievillager = villager.convertTo(EntityType.ZOMBIE_VILLAGER, false);
//         zombievillager.finalizeSpawn(p_34281_, p_34281_.getCurrentDifficultyAt(zombievillager.blockPosition()), MobSpawnType.CONVERSION, new Inferius.ZombieGroupData(false, true), (CompoundTag)null);
//         zombievillager.setVillagerData(villager.getVillagerData());
//         zombievillager.setGossips(villager.getGossips().store(NbtOps.INSTANCE).getValue());
//         zombievillager.setTradeOffers(villager.getOffers().createTag());
//         zombievillager.setVillagerXp(villager.getVillagerXp());
//         net.minecraftforge.event.ForgeEventFactory.onLivingConvert(p_34282_, zombievillager);
//         if (!this.isSilent()) {
//            p_34281_.levelEvent((Player)null, 1026, this.blockPosition(), 0);
//         }
//      }
//
//   }

   protected float getStandingEyeHeight(Pose p_34313_, EntityDimensions p_34314_) {
      return this.isBaby() ? 0.93F : 1.74F;
   }

   @Nullable
   public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_34297_, DifficultyInstance p_34298_, MobSpawnType p_34299_, @Nullable SpawnGroupData p_34300_, @Nullable CompoundTag p_34301_) {
      p_34300_ = super.finalizeSpawn(p_34297_, p_34298_, p_34299_, p_34300_, p_34301_);
      float f = p_34298_.getSpecialMultiplier();

      if (p_34300_ == null) {
         p_34300_ = new Inferius.InferiusGroupData(getSpawnAsBabyOdds(p_34297_.getRandom()));
      }

      if (p_34300_ instanceof Inferius.InferiusGroupData) {
         Inferius.InferiusGroupData zombie$zombiegroupdata = (Inferius.InferiusGroupData)p_34300_;
         if (zombie$zombiegroupdata.isBaby) {
            this.setBaby(true);
         }

         this.setCanBreakDoors(this.supportsBreakDoorGoal() && this.random.nextFloat() < f * 0.1F);
      }

      this.setPersistenceRequired();
      this.handleAttributes(f);
      return p_34300_;
   }

   public static boolean getSpawnAsBabyOdds(Random p_34303_) {
      return p_34303_.nextFloat() < 0.05f;
   }

   protected void handleAttributes(float p_34340_) {
      this.randomizeReinforcementsChance();
      this.getAttribute(Attributes.KNOCKBACK_RESISTANCE).addPermanentModifier(new AttributeModifier("Random spawn bonus", this.random.nextDouble() * (double)0.05F, AttributeModifier.Operation.ADDITION));
      double d0 = this.random.nextDouble() * 1.5D * (double)p_34340_;
      if (d0 > 1.0D) {
         this.getAttribute(Attributes.FOLLOW_RANGE).addPermanentModifier(new AttributeModifier("Random zombie-spawn bonus", d0, AttributeModifier.Operation.MULTIPLY_TOTAL));
      }

      if (this.random.nextFloat() < p_34340_ * 0.05F) {
         this.getAttribute(Attributes.SPAWN_REINFORCEMENTS_CHANCE).addPermanentModifier(new AttributeModifier("Leader zombie bonus", this.random.nextDouble() * 0.25D + 0.5D, AttributeModifier.Operation.ADDITION));
         this.getAttribute(Attributes.MAX_HEALTH).addPermanentModifier(new AttributeModifier("Leader zombie bonus", this.random.nextDouble() * 3.0D + 1.0D, AttributeModifier.Operation.MULTIPLY_TOTAL));
         this.setCanBreakDoors(this.supportsBreakDoorGoal());
      }

   }

   protected void randomizeReinforcementsChance() {
      this.getAttribute(Attributes.SPAWN_REINFORCEMENTS_CHANCE).setBaseValue(this.random.nextDouble() * net.minecraftforge.common.ForgeConfig.SERVER.zombieBaseSummonChance.get());
   }

   public double getMyRidingOffset() {
      return this.isBaby() ? 0.0D : -0.45D;
   }

   public static class InferiusGroupData implements SpawnGroupData {
      public final boolean isBaby;

      public InferiusGroupData(boolean p_34357_) {
         this.isBaby = p_34357_;
      }
   }
}
