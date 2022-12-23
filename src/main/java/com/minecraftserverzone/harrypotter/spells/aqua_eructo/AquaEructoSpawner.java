package com.minecraftserverzone.harrypotter.spells.aqua_eructo;

import com.minecraftserverzone.harrypotter.setup.Registrations;
import com.minecraftserverzone.harrypotter.setup.capabilities.PlayerStatsProvider;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class AquaEructoSpawner extends ThrowableItemProjectile {
   public AquaEructoSpawner(EntityType<? extends AquaEructoSpawner> p_37473_, Level p_37474_) {
      super(p_37473_, p_37474_);
      this.noPhysics = true;
   }

   public AquaEructoSpawner(Level p_37481_, LivingEntity p_37482_) {
      super(Registrations.AQUA_ERUCTO_SPAWNER.get(), p_37482_, p_37481_);
   }

   public AquaEructoSpawner(Level p_37476_, double p_37477_, double p_37478_, double p_37479_) {
      super(Registrations.AQUA_ERUCTO_SPAWNER.get(), p_37477_, p_37478_, p_37479_, p_37476_);
   }

   public void handleEntityEvent(byte p_37484_) {
      if (p_37484_ == 3) {
         for(int i = 0; i < 1; ++i) {
            this.level.addParticle(ParticleTypes.BUBBLE_COLUMN_UP, this.getX(), this.getY() + 1, this.getZ(), ((double)this.random.nextFloat() - 0.5D) * 0.08D, ((double)this.random.nextFloat() - 0.5D) * 0.08D, ((double)this.random.nextFloat() - 0.5D) * 0.08D);
         }
      }
   }

   @Override
	public void tick() {
	   if (this.tickCount % 1000 == 0 ) {
			if (!this.level.isClientSide ) {
				this.discard();
			}
		}
	   
	   if(this.getOwner() != null) {
		   this.setPos(this.getOwner().getX(), this.getOwner().getY(), this.getOwner().getZ());
		   
		   if (!this.level.isClientSide) {
				 this.level.broadcastEntityEvent(this, (byte)3);
				//spawn aqua eructo
				 this.getOwner().getCapability(PlayerStatsProvider.PLAYER_STATS_CAPABILITY).ifPresent(h -> {
						if(h.getUsingSkill() == 1) {
							Vec3 look = this.getOwner().getLookAngle();
				 			AquaEructo aquaEructo3 = new AquaEructo(this.getOwner().level, this.getOwner(), look.x * 1, look.y * 1, look.z * 1);
				 			aquaEructo3.setPos((this.getOwner().getX()) + look.x * 1.5D, this.getOwner().getY() + 1 + look.y * 1.5D, this.getOwner().getZ() + look.z * 1.5D);
				 			aquaEructo3.shootFromRotation(this.getOwner(), this.getOwner().getXRot(), this.getOwner().getYRot() + 2, 0.0F, 1F, 1.0F);
				 			this.getOwner().level.addFreshEntity(aquaEructo3);
				 			if(this.tickCount % 30 == 0) {
				 				this.playSound(SoundEvents.WATER_AMBIENT, 1.0F, 1.0F);
				 			}
						}else {
							this.discard();
						}
					}); 

				
			}
	   }else {
		   if(this.tickCount > 50) {
			   if (!this.level.isClientSide) {
				   this.discard();
			   }
		   }
	   }
		
		
		super.tick();
	}

   protected Item getDefaultItem() {
      return Items.AIR; //Items.AIR
   }
}