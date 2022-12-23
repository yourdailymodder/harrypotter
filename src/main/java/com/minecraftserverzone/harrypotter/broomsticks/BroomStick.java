package com.minecraftserverzone.harrypotter.broomsticks;

import javax.annotation.Nullable;

import com.minecraftserverzone.harrypotter.setup.Registrations;

import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class BroomStick extends LivingEntity{

	private final NonNullList<ItemStack> handItems = NonNullList.withSize(2, ItemStack.EMPTY);
	private final NonNullList<ItemStack> armorItems = NonNullList.withSize(4, ItemStack.EMPTY);
	public int speedx = 0;
	
	public BroomStick(EntityType<? extends BroomStick> p_20966_, Level p_20967_) {
		super(p_20966_, p_20967_);

	}
	
	@Override
	protected void dropCustomDeathLoot(DamageSource p_21018_, int p_21019_, boolean p_21020_) {
		if(!this.level.isClientSide) {
			ItemStack stack = new ItemStack(Registrations.BROOMSTICK_ITEM.get());
			if(this.hasCustomName()) {
				stack.getOrCreateTagElement("customnames").putString("name", this.getCustomName().getString());
			}
			this.spawnAtLocation(stack);
		}
		super.dropCustomDeathLoot(p_21018_, p_21019_, p_21020_);
	}

	@Override
	public Iterable<ItemStack> getHandSlots() {
	      return this.handItems;
	   }
	@Override
	   public Iterable<ItemStack> getArmorSlots() {
	      return this.armorItems;
	   }
	   @Override
	   public ItemStack getItemBySlot(EquipmentSlot p_31612_) {
	      switch(p_31612_.getType()) {
	      case HAND:
	         return this.handItems.get(p_31612_.getIndex());
	      case ARMOR:
	         return this.armorItems.get(p_31612_.getIndex());
	      default:
	         return ItemStack.EMPTY;
	      }
	   }
	   @Override
	   public void setItemSlot(EquipmentSlot p_31584_, ItemStack p_31585_) {
	      this.verifyEquippedItem(p_31585_);
	      switch(p_31584_.getType()) {
	      case HAND:
	         this.handItems.set(p_31584_.getIndex(), p_31585_);
	         break;
	      case ARMOR:
	         this.armorItems.set(p_31584_.getIndex(), p_31585_);
	      }

	   }
	   
	   @Override
	public boolean causeFallDamage(float p_147187_, float p_147188_, DamageSource p_147189_) {
		return false;
	}
	   
	@Override
	public int getFallFlyingTicks() {
		return 0;
	}
	
	@Override
	public boolean isFallFlying() {
		return false;
	}
	@Override
	protected void checkFallDamage(double p_20990_, boolean p_20991_, BlockState p_20992_, BlockPos p_20993_) {
	}
	
	@Override
	public void tick() {
		resetFallDistance();
		super.tick();
	}

	@Override
	public HumanoidArm getMainArm() {
		return HumanoidArm.RIGHT;
	}
	
	public void addAdditionalSaveData(CompoundTag p_31619_) {
    super.addAdditionalSaveData(p_31619_);
    ListTag listtag = new ListTag();

    for(ItemStack itemstack : this.armorItems) {
       CompoundTag compoundtag = new CompoundTag();
       if (!itemstack.isEmpty()) {
          itemstack.save(compoundtag);
       }

       listtag.add(compoundtag);
    }

    p_31619_.put("ArmorItems", listtag);
    ListTag listtag1 = new ListTag();

    for(ItemStack itemstack1 : this.handItems) {
       CompoundTag compoundtag1 = new CompoundTag();
       if (!itemstack1.isEmpty()) {
          itemstack1.save(compoundtag1);
       }

       listtag1.add(compoundtag1);
    }

    p_31619_.put("HandItems", listtag1);
 }

	 public boolean canTakeItem(ItemStack p_31638_) {
	     return false;
	   }
	 
 public void readAdditionalSaveData(CompoundTag p_31600_) {
    super.readAdditionalSaveData(p_31600_);
    if (p_31600_.contains("ArmorItems", 9)) {
       ListTag listtag = p_31600_.getList("ArmorItems", 10);

       for(int i = 0; i < this.armorItems.size(); ++i) {
          this.armorItems.set(i, ItemStack.of(listtag.getCompound(i)));
       }
    }
    if (p_31600_.contains("HandItems", 9)) {
       ListTag listtag1 = p_31600_.getList("HandItems", 10);

       for(int j = 0; j < this.handItems.size(); ++j) {
          this.handItems.set(j, ItemStack.of(listtag1.getCompound(j)));
       }
    }
 }

 public InteractionResult interactAt(Player p_31594_, Vec3 p_31595_, InteractionHand p_31596_) {
	 if (p_31594_.isSpectator()) {
         return InteractionResult.FAIL;
      } else {
    	  if (this.isVehicle()) {
              return super.interactAt(p_31594_, p_31595_, p_31596_);
    	  } else {
    		  if(!p_31594_.getMainHandItem().is(Items.NAME_TAG)) {
    			  this.doPlayerRide(p_31594_);
    			  return InteractionResult.sidedSuccess(this.level.isClientSide);
    		  }else {
    			  return super.interactAt(p_31594_, p_31595_, p_31596_);
    		  }
    	  }
      }
 }
 
 protected void doPlayerRide(Player p_30634_) {
     if (!this.level.isClientSide) {
        p_30634_.setYRot(this.getYRot());
        p_30634_.setXRot(this.getXRot());
        p_30634_.startRiding(this);
     }
  }
 
 @Override
	public void handleEntityEvent(byte p_21375_) {
		if (p_21375_ == 4) {
				speedx++;				
	    }else if (p_21375_ == 5) {
	    		speedx--;
		}else
		super.handleEntityEvent(p_21375_);
	}
 
 @Nullable
 public Entity getControllingPassenger() {
    return this.getFirstPassenger();
 }

 @Override
public double getPassengersRidingOffset() {

	return 0.25d;
}
 
 @Override
public double getMyRidingOffset() {
	return super.getMyRidingOffset();
}
 
 @Override
protected int calculateFallDamage(float p_21237_, float p_21238_) {
	return 0;
}
 
 public boolean canBeControlledByRider() {
		return this.getControllingPassenger() instanceof LivingEntity;
	}
 
@Override
public void travel(Vec3 p_21280_) {
	
	if (this.isAlive()) {
         if (this.isVehicle() && this.canBeControlledByRider()) {
        	 LivingEntity livingentity = (LivingEntity)this.getControllingPassenger();
             this.setYRot(livingentity.getYRot());
             this.yRotO = this.getYRot();
             this.setXRot(livingentity.getXRot());
             this.setRot(this.getYRot(), this.getXRot());
             this.yBodyRot = this.getYRot();
             this.yHeadRot = this.yBodyRot;
             
            float f = livingentity.xxa * 0.5f;
            float f1 = livingentity.zza;

            if (f1 <= 0.0F) {
            	f1 *= 0.25F; 
            }

            setSpeed(f1 * 0.1f);

            if (this.isControlledByLocalInstance()) {
            	if(this.getSpeed() != 0) {
    				if(speedx < 100) {
    					speedx++;
    					this.level.broadcastEntityEvent(this, (byte)4);
    				}
    			}else {
    				if(speedx > 0) {
    					speedx--;
    					this.level.broadcastEntityEvent(this, (byte)5);
    				}
    			}
            	
            	this.setNoGravity(true);
                    double d3 = Math.min(0.3f, livingentity.getLookAngle().y * ((float)flyingSpeed * 20f));
                    double d5 = 0;
                       if (this.getSpeed() != 0) {
                    	   d5 = + (d3 + 0.3f - p_21280_.y);
                    	   this.flyingSpeed = 0.1f;
          	               setSpeed(0.1f);
                        }else {
                        	f1 += flyingSpeed;
                        	flyingSpeed = 0.01f;
                        }

           	 super.travel(new Vec3((double)f, p_21280_.y + d5, (double)f1));
            } else if (livingentity instanceof Player) {
            	this.setNoGravity(false);
               this.setDeltaMovement(Vec3.ZERO);
            }

            this.calculateEntityAnimation(this, false);
            this.tryCheckInsideBlocks();
         } else {
            this.flyingSpeed = 0.1F;
            super.travel(p_21280_);
         }
      }
	this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.0D);
}

public static AttributeSupplier.Builder createAttributes() {
    return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 1.0D).add(Attributes.MOVEMENT_SPEED, (double)0.0F);
 }
}
