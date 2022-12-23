package com.minecraftserverzone.harrypotter.spells.fire_storm;

import org.joml.Vector3f;

import com.minecraftserverzone.harrypotter.HarryPotterMod;
import com.minecraftserverzone.harrypotter.broomsticks.BroomStick;
import com.minecraftserverzone.harrypotter.setup.Registrations;
import com.minecraftserverzone.harrypotter.setup.capabilities.PlayerStatsProvider;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.IndirectEntityDamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class FireStorm extends Projectile {
	private float angle;
    private float distance;
    private float height;
    private float clockwise;
    BlockPos anchorPoint = BlockPos.ZERO;
	Vec3 moveTargetPoint = Vec3.ZERO;
     
	public FireStorm(EntityType<? extends FireStorm> p_37364_, Level p_37365_) {
		super(p_37364_, p_37365_);
		this.distance = 6.0F;
        this.height = 0.1F + this.random.nextFloat() * 2.5F;
        this.clockwise = -1.0F;
	}
	
	public FireStorm(Entity player, Level level) {
		super(Registrations.FIRE_STORM.get(), level);
		this.setOwner(player);
		this.distance = 6.0F;
        this.height = 0.1F + this.random.nextFloat() * 2.5F;
        this.clockwise = -1.0F;
//        this.clockwise = this.random.nextBoolean() ? 1.0F : -1.0F;
	}
	
//	public FireStorm(EntityType<? extends FireStorm> p_37364_, Level p_37365_) {
//		super(p_37364_, p_37365_);
//	}
//
//	public FireStorm(Level p_37375_, LivingEntity p_37376_, double p_37377_, double p_37378_, double p_37379_) {
//		super(Registrations.FIRE_STORM.get(), p_37376_, p_37377_, p_37378_, p_37379_, p_37375_);
//	}
	
	@Override
	public boolean isOnFire() {
		this.setRemainingFireTicks(8);
		return true;
	}
	
	protected float getInertia() {
	      return 0.95F;
	   }
	
	@SuppressWarnings("deprecation")
	@Override
	public void tick() {
		if(this.isInWaterRainOrBubble()) {
			this.discard();
		}
		if(this.getOwner()!=null) {
			anchorPoint = this.getOwner().blockPosition();
		}

        Entity entity = this.getOwner();
        if (this.level.isClientSide || (entity == null || !entity.isRemoved()) && this.level.hasChunkAt(this.blockPosition())) {
			 if (this.shouldBurn()) {
		            this.setSecondsOnFire(1);
		         }
			 HitResult hitresult = ProjectileUtil.getHitResult(this, this::canHitEntity);
		        if (hitresult.getType() != HitResult.Type.MISS && !net.minecraftforge.event.ForgeEventFactory.onProjectileImpact(this, hitresult)) {
		           this.onHit(hitresult);
		        }
		        
		        this.checkInsideBlocks();
		         Vec3 vec3 = this.getDeltaMovement();
		         double d0 = this.getX() + vec3.x;
		         double d1 = this.getY() + vec3.y;
		         double d2 = this.getZ() + vec3.z;
		         ProjectileUtil.rotateTowardsMovement(this, 0.2F);
		         float f = this.getInertia();
		         if (this.isInWater()) {
		            for(int i = 0; i < 4; ++i) {
		               float f1 = 0.25F;
		               this.level.addParticle(ParticleTypes.BUBBLE, d0 - vec3.x * 0.25D, d1 - vec3.y * 0.25D, d2 - vec3.z * 0.25D, vec3.x, vec3.y, vec3.z);
		            }

		            f = 0.8F;
		         }
	         
//		        this.level.addParticle(ParticleTypes.SMOKE, d0 - vec3.x, d1 - vec3.y, d2 - vec3.z, vec3.x * 0.0f, vec3.y * 0.0f, vec3.z * 0.0f);
//      			this.level.addParticle(this.getTrailParticle(), d0 - vec3.x, d1 - vec3.y, d2 - vec3.z, vec3.x * 0.0f, vec3.y * 0.0f, vec3.z * 0.0f);
      		
	    }

         if (this.random.nextInt((200)) == 0) {
            ++this.distance;
            if (this.distance > 3.0F) {
               this.distance = 0.1F;
            }
         }
         
         if(this.angle % 360 == 0) {
        	 this.height = 0.1F + this.random.nextFloat() * 2.5F;
         }

         if(this.getOwner()!=null) {
        	 moveGoal();
         }else {
        	 this.discard();
         }

         if(!this.level.isClientSide && this.tickCount > 600) {
        	 this.discard();
         }
		
		super.tick();
	}

	public void moveGoal(){
		float speed = 0.1F;
		double d0 = this.moveTargetPoint.x - this.getX();
        double d1 = this.moveTargetPoint.y - this.getY();
        double d2 = this.moveTargetPoint.z - this.getZ();
        double d3 = Math.sqrt(d0 * d0 + d2 * d2);
        if (Math.abs(d3) > (double)1.0E-5F) {
            double d4 = 1.0D - Math.abs(d1 * (double)0.7F) / d3;
            d0 *= d4;
            d2 *= d4;
            d3 = Math.sqrt(d0 * d0 + d2 * d2);
            double d5 = Math.sqrt(d0 * d0 + d2 * d2 + d1 * d1);
            float f = this.getYRot();
            float f1 = (float)Mth.atan2(d2, d0);
            float f2 = Mth.wrapDegrees(this.getYRot() + 90.0F);
            float f3 = Mth.wrapDegrees(f1 * (180F / (float)Math.PI));
            this.setYRot(Mth.approachDegrees(f2, f3, 4.0F) - 90.0F);
            if (Mth.degreesDifferenceAbs(f, this.getYRot()) < 3.0F) {
               speed = Mth.approach(speed, 1.8F, 0.005F * (1.8F / speed));
            } else {
               speed = Mth.approach(speed, 0.2F, 0.025F);
            }

            float f4 = (float)(-(Mth.atan2(-d1, d3) * (double)(180F / (float)Math.PI)));
            this.setXRot(f4);
            float f5 = this.getYRot() + 90.0F;
            double d6 = (double)(speed * Mth.cos(f5 * ((float)Math.PI / 180F))) * Math.abs(d0 / d5);
            double d7 = (double)(speed * Mth.sin(f5 * ((float)Math.PI / 180F))) * Math.abs(d2 / d5);
            double d8 = (double)(speed * Mth.sin(f4 * ((float)Math.PI / 180F))) * Math.abs(d1 / d5);
            Vec3 vec3 = this.getDeltaMovement();
            this.setDeltaMovement(vec3.add((new Vec3(d6, d8, d7)).subtract(vec3).scale(0.2D)));
           
            selectNext();
            Vec3 vec3b = new Vec3(moveTargetPoint.x, 0.2f, moveTargetPoint.z);
            
            this.moveRelative(0.4f, vec3b);
           	this.move(MoverType.SELF, this.getDeltaMovement());
           	
         }
	}

	private void selectNext() {
        anchorPoint = this.getOwner().blockPosition();
        this.angle += this.clockwise * 15.0F * ((float)Math.PI / 180F);
        this.moveTargetPoint = Vec3.atLowerCornerOf(this.anchorPoint).add((double)(this.distance * Mth.cos(this.angle) * 0.05f), (double)(0.05F + this.height), (double)(this.distance * Mth.sin(this.angle) * 0.05f));
	}
	
	@Override
	protected void onHitBlock(BlockHitResult p_37258_) {
		if (this.level.isClientSide) {
		Vec3 vec3 = this.getDeltaMovement();
         double d0 = this.getX() + vec3.x;
         double d1 = this.getY() + vec3.y;
         double d2 = this.getZ() + vec3.z;
         
            for(int i = 0; i < 10; ++i) {
               float f1 =  i* 0.05F;
               this.level.addParticle(this.getTrailParticle(), d0 - vec3.x * f1, d1 - vec3.y * f1, d2 - vec3.z * f1, vec3.x, vec3.y, vec3.z);
            }
		}

		if (!this.level.isClientSide) {
			//put fire on block
		      BlockPos blockpos = p_37258_.getBlockPos().relative(p_37258_.getDirection());
	            if (this.level.isEmptyBlock(blockpos)) {
	               this.level.setBlockAndUpdate(blockpos, BaseFireBlock.getState(this.level, blockpos));
	            }
		}
		super.onHitBlock(p_37258_);
		
		if (!this.level.isClientSide) {
			this.discard();
		}
	}
	
	protected boolean shouldBurn() {
	      return true;
	   }

	protected void onHitEntity(EntityHitResult p_37386_) {
		if(p_37386_.getEntity() != this.getOwner()) {
			if(p_37386_.getEntity() instanceof BroomStick && ((LivingEntity) p_37386_.getEntity()).getFirstPassenger() != null) {
				if(((LivingEntity) p_37386_.getEntity()).getFirstPassenger() != this.getOwner()) {
					super.onHitEntity(p_37386_);
					if (!this.level.isClientSide) {
							//fire on entity				
							Entity entity = p_37386_.getEntity();
						    Entity entity1 = this.getOwner();
						    if(this.getOwner() != null) {
								if(this.getOwner() instanceof LivingEntity) {
									entity1.getCapability(PlayerStatsProvider.PLAYER_STATS_CAPABILITY).ifPresent(h -> {
										int id = 24;
										int spelllevel = h.getSpellsLevel()[id];
										float damage = HarryPotterMod.spellCooldownOrDamage(id, spelllevel, true);
										entity.hurt(new IndirectEntityDamageSource("onFire", entity1, entity1).setProjectile(), damage);
									});
								}
							}
							entity.setSecondsOnFire(8);
						    if (entity1 instanceof LivingEntity) {
						    	this.doEnchantDamageEffects((LivingEntity)entity1, entity);
						    }
						}
						if (!this.level.isClientSide) {
							this.discard();
						}
				}
			}else {
				super.onHitEntity(p_37386_);
				if (!this.level.isClientSide) {
						//fire on entity				
						Entity entity = p_37386_.getEntity();
					    Entity entity1 = this.getOwner();
					    if(this.getOwner() != null) {
							if(this.getOwner() instanceof LivingEntity) {
								entity1.getCapability(PlayerStatsProvider.PLAYER_STATS_CAPABILITY).ifPresent(h -> {
									int id = 24;
									int spelllevel = h.getSpellsLevel()[id];
									float damage = HarryPotterMod.spellCooldownOrDamage(id, spelllevel, true);
									entity.hurt(new IndirectEntityDamageSource("onFire", entity1, entity1).setProjectile(), damage);
								});
							}
						}
						entity.setSecondsOnFire(8);
					    if (entity1 instanceof LivingEntity) {
					    	this.doEnchantDamageEffects((LivingEntity)entity1, entity);
					    }
					}
					if (!this.level.isClientSide) {
						this.discard();
					}
			}
		}
	}
	
	@Override
	public float getLightLevelDependentMagicValue() {
		return 1F;
	}

	protected ParticleOptions getTrailParticle() {
		Vector3f PARTICLE_COLOR = Vec3.fromRGB24(0xf55d42).toVector3f();
		return new DustParticleOptions(PARTICLE_COLOR, 1);
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
	
	public void moveAroundOwner(Entity p_37252_, float p_37253_, float p_37254_, float p_37255_, float p_37256_, float p_37257_) {
	    if(this.getOwner() != null) {
	    	 this.distance = 6.0F;
	         this.height = 0.5F + this.random.nextFloat() * 5.0F;
	         this.clockwise = -1.0F;
	         this.selectNext();
	    }
	}
	
	@Override
	protected void defineSynchedData() {}
}
