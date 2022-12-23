package com.minecraftserverzone.harrypotter.spells.fire_storm;

import com.minecraftserverzone.harrypotter.HarryPotterMod;
import com.minecraftserverzone.harrypotter.setup.Registrations;
import com.minecraftserverzone.harrypotter.setup.capabilities.PlayerStatsProvider;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class FireStormSpawner extends ThrowableItemProjectile {
   public FireStormSpawner(EntityType<? extends FireStormSpawner> p_37473_, Level p_37474_) {
      super(p_37473_, p_37474_);
      this.noPhysics = true;
   }

   public FireStormSpawner(Level p_37481_, LivingEntity p_37482_) {
      super(Registrations.FIRE_STORM_SPAWNER.get(), p_37482_, p_37481_);
   }

   public FireStormSpawner(Level p_37476_, double p_37477_, double p_37478_, double p_37479_) {
      super(Registrations.FIRE_STORM_SPAWNER.get(), p_37477_, p_37478_, p_37479_, p_37476_);
   }

   public void handleEntityEvent(byte p_37484_) {
      if (p_37484_ == 3) {
         for(int i = 0; i < 1; ++i) {
            this.level.addParticle(ParticleTypes.SMOKE, this.getX(), this.getY() + 1, this.getZ(), ((double)this.random.nextFloat() - 0.5D) * 0.08D, ((double)this.random.nextFloat() - 0.5D) * 0.08D, ((double)this.random.nextFloat() - 0.5D) * 0.08D);
         }
      }
   }

   @Override
	public void tick() {
	   if (this.tickCount % 1200 == 0 && this.tickCount != 0) {
			if (!this.level.isClientSide ) {
				this.discard();
			}
		}
	   
		this.setPos(this.getOwner().getX(), this.getOwner().getY(), this.getOwner().getZ());
		if (!this.level.isClientSide) {

		    Entity entity1 = this.getOwner();
		    if(this.getOwner() != null) {
				if(this.getOwner() instanceof LivingEntity) {
					entity1.getCapability(PlayerStatsProvider.PLAYER_STATS_CAPABILITY).ifPresent(h -> {
						int id = 24;
						int spelllevel = h.getSpellsLevel()[id];
						float cooldown = HarryPotterMod.spellCooldownOrDamage(id, spelllevel, false);
						if (this.tickCount > (15 * 20)) {
							this.discard();
						}
					});
				}
			}
			
			 this.level.broadcastEntityEvent(this, (byte)3);
			//spawn fire storm
			 this.getOwner().getCapability(PlayerStatsProvider.PLAYER_STATS_CAPABILITY).ifPresent(h -> {
					if(h.getUsingSkill() == 1) {
						Vec3 look = this.getOwner().getLookAngle();
						
						FireStorm fire_storm = new FireStorm(this.getOwner(), this.getOwner().level);
						fire_storm.setPos((this.getOwner().getX()) + look.x * 1.5D, this.getOwner().getY() + 1 + look.y * 1.5D, this.getOwner().getZ() + look.z * 1.5D);
						fire_storm.setOwner(this.getOwner());
						this.getOwner().level.addFreshEntity(fire_storm);

			 			
					}else {
						this.discard();
					}
				}); 

			
		}
		if(this.tickCount % 30 == 0 && this.tickCount != 0) {
			this.playSound(Registrations.FIREBALL_SOUND1.get(), 1.0F, 1.0F);
		}
		super.tick();
	}

   protected Item getDefaultItem() {
      return Items.AIR;
   }
}