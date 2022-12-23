package com.minecraftserverzone.harrypotter.spells.expelliarmus;

import org.joml.Vector3f;

import com.minecraftserverzone.harrypotter.setup.Registrations;
import com.minecraftserverzone.harrypotter.spells.DamageSpell;

import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class Expelliarmus extends DamageSpell {

	public Expelliarmus(EntityType<? extends Expelliarmus> p_37364_, Level p_37365_) {
		super(p_37364_, p_37365_);
	}

	public Expelliarmus(Level p_37375_, LivingEntity p_37376_, double p_37377_, double p_37378_, double p_37379_) {
		super(Registrations.EXPELLIARMUS.get(), p_37376_, p_37377_, p_37378_, p_37379_, p_37375_);
	}
	
	@Override
	public boolean isOnFire() {
		this.setRemainingFireTicks(0);
		return false;
	}
	
	@Override
	public void tick() {
		Vec3 vec3 = this.getDeltaMovement();
        double d0 = this.getX() + vec3.x;
        double d1 = this.getY() + vec3.y;
        double d2 = this.getZ() + vec3.z;
        
		for(float i = -1; i < 2; ++i) {
        	for(float j = -1; j < 2; ++j) {
        		for(float k = -1; k < 2; ++k) {
        			this.level.addParticle(this.getTrailParticle(), d0 - vec3.x + i/10, 0.15f +  d1 - vec3.y + j/10, d2 - vec3.z + k/10, vec3.x * 0.0f, vec3.y * 0.0f, vec3.z * 0.0f);
        		}
        	}
        }
		super.tick();
	}

	public Expelliarmus(Level p_37367_, double p_37368_, double p_37369_, double p_37370_, double p_37371_,
			double p_37372_, double p_37373_) {
		super(Registrations.EXPELLIARMUS.get(), p_37368_, p_37369_, p_37370_, p_37371_, p_37372_, p_37373_, p_37367_);
	}
	
	@Override
	protected void onHitBlock(BlockHitResult p_37258_) {
		super.onHitBlock(p_37258_);
	}

	protected void onHitEntity(EntityHitResult p_37386_) {
		super.onHitEntity(p_37386_);
		
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
		
//		if(p_37386_.getEntity() instanceof Player) {
//			Player player = (Player) p_37386_.getEntity();
//			
//			if(!player.getMainHandItem().isEmpty()) {
//				player.drop(player.getMainHandItem(), true);
//			}
//		}
		if (!this.level.isClientSide) {
			if(p_37386_.getEntity() instanceof LivingEntity) {
				LivingEntity livingEntity = (LivingEntity) p_37386_.getEntity();
				
				if(!livingEntity.getMainHandItem().isEmpty()) {
					livingEntity.spawnAtLocation(livingEntity.getMainHandItem());
					livingEntity.setItemSlot(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
				}
			}
		}
		
//		if(p_37386_.getEntity() instanceof Mob) {
//			Mob mob = (Mob) p_37386_.getEntity();
//			
//			if(!mob.getMainHandItem().isEmpty()) {
//				mob.spawnAtLocation(mob.getMainHandItem());
//				mob.setItemSlot(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
//			}
//		}
		
	}
	
	@Override
	public float getLightLevelDependentMagicValue() {
		return 1F;
	}

	@Override
	protected ParticleOptions getTrailParticle() {
		Vector3f PARTICLE_COLOR = Vec3.fromRGB24(0xb5120c).toVector3f();
		return new DustParticleOptions(PARTICLE_COLOR, 1);
	}

	protected void onHit(HitResult p_37388_) {
		super.onHit(p_37388_);
		if (!this.level.isClientSide) {
			this.discard();
		}
	}

	public boolean isPickable() {
		return false;
	}

	public boolean hurt(DamageSource p_37381_, float p_37382_) {
		return false;
	}
}
