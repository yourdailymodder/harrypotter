package com.minecraftserverzone.harrypotter.items;

import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class MaraudersMapItem extends Item{

	public MaraudersMapItem(Properties p_41383_) {
		super(p_41383_);
	}
	
	@Override
	public InteractionResultHolder<ItemStack> use(Level p_43468_, Player player, InteractionHand p_43470_) {
	      ItemStack itemstack = player.getItemInHand(p_43470_);
	      player.openItemGui(itemstack, p_43470_);
	      player.awardStat(Stats.ITEM_USED.get(this));
	      return InteractionResultHolder.sidedSuccess(itemstack, p_43468_.isClientSide());
	   }
}
