package com.minecraftserverzone.harrypotter.spells.sectumsempra;

import com.minecraftserverzone.harrypotter.HarryPotterMod;
import com.minecraftserverzone.harrypotter.setup.Registrations;
import com.minecraftserverzone.harrypotter.setup.capabilities.PlayerStatsProvider;
import com.minecraftserverzone.harrypotter.spells.DamageSpell;
import com.mojang.math.Vector3f;

import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.IndirectEntityDamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class Sectumsempra extends DamageSpell {
	public Sectumsempra(EntityType<? extends Sectumsempra> p_37364_, Level p_37365_) {
		super(p_37364_, p_37365_);
	}

	public Sectumsempra(Level p_37375_, LivingEntity p_37376_, double p_37377_, double p_37378_, double p_37379_) {
		super(Registrations.SECTUMSEMPRA.get(), p_37376_, p_37377_, p_37378_, p_37379_, p_37375_);
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

	public Sectumsempra(Level p_37367_, double p_37368_, double p_37369_, double p_37370_, double p_37371_,
			double p_37372_, double p_37373_) {
		super(Registrations.SECTUMSEMPRA.get(), p_37368_, p_37369_, p_37370_, p_37371_, p_37372_, p_37373_, p_37367_);
	}
	
	@Override
	protected void onHitBlock(BlockHitResult p_37258_) {
		super.onHitBlock(p_37258_);
	}

	protected void onHitEntity(EntityHitResult p_37386_) {
		super.onHitEntity(p_37386_);
		if (!this.level.isClientSide) {
			Entity entity = p_37386_.getEntity();
				Entity entity1 = this.getOwner();
				if(this.getOwner() != null) {
					if(this.getOwner() instanceof LivingEntity) {
						entity1.getCapability(PlayerStatsProvider.PLAYER_STATS_CAPABILITY).ifPresent(h -> {
							int id = 17;
							int spelllevel = h.getSpellsLevel()[id];
							float damage = HarryPotterMod.spellCooldownOrDamage(id, spelllevel, true);
							entity.hurt(new IndirectEntityDamageSource("sectumSempra", entity1, entity1).setProjectile(), damage);
						});
					}
				}
			}
	}
	
	@Override
	public float getBrightness() {
		return 1F;
	}

	@Override
	protected ParticleOptions getTrailParticle() {
		Vector3f PARTICLE_COLOR = new Vector3f(Vec3.fromRGB24(0xffffff));//FastColor.ARGB32.color(255, 252, 119, 3); 16711680
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
