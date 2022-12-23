package com.minecraftserverzone.harrypotter.mobs.death_eater;

import java.util.Random;

import javax.annotation.Nullable;

import com.minecraftserverzone.harrypotter.setup.Registrations;
import com.minecraftserverzone.harrypotter.spells.confringo.Confringo;
import com.minecraftserverzone.harrypotter.spells.depulso.Depulso;
import com.minecraftserverzone.harrypotter.spells.glacius.Glacius;
import com.minecraftserverzone.harrypotter.spells.incendio.Incendio;
import com.minecraftserverzone.harrypotter.spells.sectumsempra.Sectumsempra;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.Mth;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.Vec3;

public class DeathEater extends Monster implements RangedAttackMob{
   private static final EntityDataAccessor<Byte> DATA_FLAGS_ID = SynchedEntityData.defineId(DeathEater.class, EntityDataSerializers.BYTE);
   private static final EntityDataAccessor<Integer> DATA_COLOR = SynchedEntityData.defineId(DeathEater.class, EntityDataSerializers.INT);
   private static final EntityDataAccessor<Boolean> DATA_IS_CHARGING = SynchedEntityData.defineId(DeathEater.class, EntityDataSerializers.BOOLEAN);
   private int attackAnimationTick;
   
   
   public DeathEater(EntityType<? extends DeathEater> p_33786_, Level p_33787_) {
      super(p_33786_, p_33787_);
      this.xpReward = 10;
   }
   
	@Override
	protected boolean shouldDropExperience() {
		return true;
	}

   @Override
   protected boolean shouldDespawnInPeaceful() {
	      return true;
	   }

   @Override
   protected void registerGoals() {
//	  this.goalSelector.addGoal(1, new DeathEaterAttackGoal(this));
	  this.goalSelector.addGoal(1, new FloatGoal(this));
	  this.goalSelector.addGoal(2, new DeathEaterRangedAttackGoal(this, 2D, 50, 70.0F));
//      this.goalSelector.addGoal(2, new DeathEater.ShootSpellsGoal(this));
//	  this.goalSelector.addGoal(3, new MoveTowardsTargetGoal(this, 1D, 100.0F));
      this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 0.8D));
      this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
      this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
      this.targetSelector.addGoal(1, new HurtByTargetGoal(this).setAlertOthers());
      this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Player.class, true));
      this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
      this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Villager.class, true));
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
    	  if(this.getMainHandItem().isEmpty() && tickCount % 100 == 0) {
        	  this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Registrations.APPRENTICE_WAND.get()));
          }
      }
      if (this.attackAnimationTick > 0) {
          --this.attackAnimationTick;
       }
   }

   
   @Override
	protected void populateDefaultEquipmentSlots(DifficultyInstance p_21383_) {
		super.populateDefaultEquipmentSlots(p_21383_);
		
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
    		  .add(Attributes.MAX_HEALTH, 50.0D)
    		  .add(Attributes.FOLLOW_RANGE, 100.0D)
    		  .add(Attributes.MOVEMENT_SPEED, (double)0.3F)
    		  .add(Attributes.ATTACK_DAMAGE, 5);
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
      return MobType.ILLAGER;
   }

@Override
	public boolean doHurtTarget(Entity p_21372_) {
		this.attackAnimationTick = 10;
	    this.level.broadcastEntityEvent(this, (byte)4);
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
         p_33793_ = new DeathEater.TwohandEffectsGroupData();
         if (p_33790_.getDifficulty() == Difficulty.HARD && p_33790_.getRandom().nextFloat() < 0.1F * p_33791_.getSpecialMultiplier()) {
            ((DeathEater.TwohandEffectsGroupData)p_33793_).setRandomEffect(p_33790_.getRandom());
         }
      }

      if (p_33793_ instanceof DeathEater.TwohandEffectsGroupData) {
         MobEffect mobeffect = ((DeathEater.TwohandEffectsGroupData)p_33793_).effect;
         if (mobeffect != null) {
            this.addEffect(new MobEffectInstance(mobeffect, Integer.MAX_VALUE));
         }
      }

      int rand = new Random().nextInt(2)+1;
      this.setColor(rand);
      this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Registrations.APPRENTICE_WAND.get()));
      
      this.setPersistenceRequired();
      return p_33793_;
   }

   protected float getStandingEyeHeight(Pose p_33799_, EntityDimensions p_33800_) {
      return 0.65F;
   }

   public static class TwohandEffectsGroupData implements SpawnGroupData {
      public MobEffect effect;

      public void setRandomEffect(Random p_33830_) {
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
   
   
   static class DeathEaterAttackGoal extends MeleeAttackGoal {
	      public DeathEaterAttackGoal(DeathEater p_33822_) {
	         super(p_33822_, 1D, true);
	      }

	      public boolean canUse() {
	    	  LivingEntity livingentity = this.mob.getTarget();
	    	  if(livingentity != null) {
	    		  if (livingentity instanceof DeathEater) {
		        	  return false;
		          }
	    	  }
	    	  
	         return super.canUse();
	      }

	      public boolean canContinueToUse() {
	            return super.canContinueToUse();
	      }

	      protected double getAttackReachSqr(LivingEntity p_33825_) {
	         return (double)(4.0F + p_33825_.getBbWidth());
	      }
	   }
   
   @Override
	protected int calculateFallDamage(float p_21237_, float p_21238_) {
	   	  MobEffectInstance mobeffectinstance = this.getEffect(MobEffects.JUMP);
	      float f = mobeffectinstance == null ? 0.0F : (float)(mobeffectinstance.getAmplifier() + 1);
	      return Mth.ceil((p_21237_ - 5.0F - f) * p_21238_);
	}
   
   static class ShootSpellsGoal extends Goal {
	      private final DeathEater DeathEater;
	      public int chargeTime;

	      public ShootSpellsGoal(DeathEater p_32776_) {
	         this.DeathEater = p_32776_;
	      }

	      public boolean canUse() {
	         return this.DeathEater.getTarget() != null;
	      }

	      public void start() {
	         this.chargeTime = 0;
	      }

	      public void stop() {
	         this.DeathEater.setCharging(false);
	      }

	      public boolean requiresUpdateEveryTick() {
	         return true;
	      }

	      public void tick() {
	         LivingEntity livingentity = this.DeathEater.getTarget();
	         if (livingentity != null) {
	            double d0 = 64.0D;
	            if (livingentity.distanceToSqr(this.DeathEater) < 4096.0D && this.DeathEater.hasLineOfSight(livingentity)) {
	               Level level = this.DeathEater.level;
	               Vec3 look = this.DeathEater.getLookAngle();
	               ++this.chargeTime;
	               if (this.chargeTime == 10 && !this.DeathEater.isSilent()) {
	                  level.levelEvent((Player)null, 1015, this.DeathEater.blockPosition(), 0);
	               }

	               if (this.chargeTime == 20) {
	                  double d1 = 4.0D;
	                  Vec3 vec3 = this.DeathEater.getViewVector(1.0F);
	                  double d2 = livingentity.getX() - (this.DeathEater.getX() + vec3.x * 4.0D);
	                  double d3 = livingentity.getY(0.5D) - (0.5D + this.DeathEater.getY(0.5D));
	                  double d4 = livingentity.getZ() - (this.DeathEater.getZ() + vec3.z * 4.0D);
	                  if (!this.DeathEater.isSilent()) {
	                     level.levelEvent((Player)null, 1016, this.DeathEater.blockPosition(), 0);
	                  }
	                  
	                  int rand = new Random().nextInt(5);
	                  if(rand == 0) {
	                	  Sectumsempra sectumsempra = new Sectumsempra(level, this.DeathEater, d2, d3, d4);
	                	  sectumsempra.setPos(this.DeathEater.getX() + vec3.x * 4.0D, this.DeathEater.getY(0.5D) + 0.5D, sectumsempra.getZ() + vec3.z * 4.0D);
	  		              level.addFreshEntity(sectumsempra);
	                  }else if(rand == 1) {
	                	  Depulso depulso = new Depulso(level, this.DeathEater, d2, d3, d4);
	                	  depulso.setPos(this.DeathEater.getX() + vec3.x * 4.0D, this.DeathEater.getY(0.5D) + 0.5D, depulso.getZ() + vec3.z * 4.0D);
	  		              level.addFreshEntity(depulso);
	                  }else if(rand == 2) {
	                	  Incendio incendio = new Incendio(level, this.DeathEater, d2, d3, d4);
	                	  incendio.setPos(this.DeathEater.getX() + vec3.x * 4.0D, this.DeathEater.getY(0.5D) + 0.5D, incendio.getZ() + vec3.z * 4.0D);
	  		              level.addFreshEntity(incendio);
	                  }else if(rand == 3) {
	                	  Confringo confringo = new Confringo(level, this.DeathEater, d2, d3, d4);
	                	  confringo.setPos(this.DeathEater.getX() + vec3.x * 4.0D, this.DeathEater.getY(0.5D) + 0.5D, confringo.getZ() + vec3.z * 4.0D);
	  		              level.addFreshEntity(confringo);
	                  }else {
	                	  Glacius glacius = new Glacius(level, this.DeathEater, d2, d3, d4);
	                	  glacius.setPos(this.DeathEater.getX() + vec3.x * 4.0D, this.DeathEater.getY(0.5D) + 0.5D, glacius.getZ() + vec3.z * 4.0D);
	  		              level.addFreshEntity(glacius);
	                  }

	                  
	                  this.chargeTime = -40;
	               }
	            } else if (this.chargeTime > 0) {
	               --this.chargeTime;
	            }

	            this.DeathEater.setCharging(this.chargeTime > 10);
	         }
	      }
	   }

   public void performRangedAttack(LivingEntity p_29912_, float p_29913_) {
	   LivingEntity livingentity = this.getTarget();
       if (livingentity != null) {
    	   if(!this.getMainHandItem().isEmpty()) {
    		   this.attackAnimationTick = 10;
    		   this.level.broadcastEntityEvent(this, (byte)4);
    		   
    		   Vec3 vec3 = this.getViewVector(1.0F);
               double d2 = livingentity.getX() - (this.getX() + vec3.x * 4.0D);
               double d3 = livingentity.getY(0.5D) - (0.5D + this.getY(0.5D));
               double d4 = livingentity.getZ() - (this.getZ() + vec3.z * 4.0D);
               
        	   int rand = new Random().nextInt(5);
               if(rand == 0) {
             	  Sectumsempra sectumsempra = new Sectumsempra(level, this, d2, d3, d4);
             	  sectumsempra.setPos(this.getX() + vec3.x * 1.0D, this.getY(0.5D) + 0.5D, sectumsempra.getZ() + vec3.z * 1.0D);
                     level.addFreshEntity(sectumsempra);
               }else if(rand == 1) {
             	  Depulso depulso = new Depulso(level, this, d2, d3, d4);
             	  depulso.setPos(this.getX() + vec3.x * 1.0D, this.getY(0.5D) + 0.5D, depulso.getZ() + vec3.z * 1.0D);
                     level.addFreshEntity(depulso);
               }else if(rand == 2) {
             	  Incendio incendio = new Incendio(level, this, d2, d3, d4);
             	  incendio.setPos(this.getX() + vec3.x * 1.0D, this.getY(0.5D) + 0.5D, incendio.getZ() + vec3.z * 1.0D);
                     level.addFreshEntity(incendio);
               }else if(rand == 3) {
             	  Confringo confringo = new Confringo(level, this, d2, d3, d4);
             	  confringo.setPos(this.getX() + vec3.x * 1.0D, this.getY(0.5D) + 0.5D, confringo.getZ() + vec3.z * 1.0D);
                     level.addFreshEntity(confringo);
               }else {
             	  Glacius glacius = new Glacius(level, this, d2, d3, d4);
             	  glacius.setPos(this.getX() + vec3.x * 1.0D, this.getY(0.5D) + 0.5D, glacius.getZ() + vec3.z * 1.0D);
                     level.addFreshEntity(glacius);
               }
    	   }
       }
   }
}
