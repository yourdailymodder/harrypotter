package com.minecraftserverzone.harrypotter.items;

import com.minecraftserverzone.harrypotter.broomsticks.BroomStick;
import com.minecraftserverzone.harrypotter.setup.Registrations;

import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class BroomStickItem extends Item{

	public BroomStickItem(Properties p_41383_) {
		super(p_41383_);
	}
	
	

	@Override
	public InteractionResultHolder<ItemStack> use(Level p_41432_, Player p_41433_, InteractionHand p_41434_) {
		if(!p_41432_.isClientSide) {
			Vec3 look = p_41433_.getLookAngle();
			
			BroomStick cs = Registrations.BROOMSTICK.get().create(p_41432_);
			cs.moveTo(p_41433_.getX(), p_41433_.getY() + 1D, p_41433_.getZ(), (float)look.y() * 360.0F, 0.0F);
			p_41432_.addFreshEntity(cs);
			
			if(p_41433_.getMainHandItem().getItem() == Registrations.BROOMSTICK_ITEM.get()) {
				if(p_41433_.getMainHandItem().getTagElement("customnames") != null) {
					TextComponent customname = new TextComponent(p_41433_.getMainHandItem().getTagElement("customnames").getString("name"));
					cs.setCustomName(customname);
				}
				p_41433_.getMainHandItem().shrink(1);
			}else if(p_41433_.getOffhandItem().getItem() == Registrations.BROOMSTICK_ITEM.get()) {
				p_41433_.getOffhandItem().shrink(1);
			}
			
		}
		return super.use(p_41432_, p_41433_, p_41434_);
	}
}
