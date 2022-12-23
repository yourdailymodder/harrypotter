package com.minecraftserverzone.harrypotter.blocks;

import com.minecraftserverzone.harrypotter.setup.Registrations;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class SimpleLightBlockEntity extends BlockEntity {
	public SimpleLightBlockEntity(BlockPos p_155229_, BlockState p_155230_) {
		super(Registrations.GLOWING_AIR_BLOCK_ENTITY.get(), p_155229_, p_155230_);
	}

	 public static void animateTick(Level p_155344_, BlockPos p_155345_, BlockState p_155346_, SimpleLightBlockEntity p_155347_) {
	      if(p_155344_.getGameTime() % 10 == 0) {
	    	  if(p_155344_.getBlockState(p_155345_).getValue(Lumos.AGE) < 30) {
	 			 p_155344_.setBlock(p_155345_, p_155346_.setValue(Lumos.AGE, Integer.valueOf(Integer.valueOf(p_155346_.getValue(Lumos.AGE)) + 1)), 2);
	 		 }else if(p_155346_.getValue(Lumos.AGE) >= 30) {
	 			 p_155344_.setBlock(p_155345_, Blocks.AIR.defaultBlockState(), 2);
	 		 }
	      }
	 }
}
