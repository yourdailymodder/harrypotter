package com.minecraftserverzone.harrypotter.blocks;

import javax.annotation.Nullable;

import com.minecraftserverzone.harrypotter.setup.Registrations;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.IPlantable;

public class Lumos extends BaseEntityBlock {
   protected static final int AABB_STANDING_OFFSET = 2;
   public static final IntegerProperty AGE = IntegerProperty.create("age", 0, 60);
	
   public Lumos(BlockBehaviour.Properties p_57491_) {
      super(p_57491_);
      this.registerDefaultState(this.stateDefinition.any().setValue(AGE, Integer.valueOf(0)));
   }
   
   protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_48928_) {
	      p_48928_.add(AGE);
	   }
   
   @Override
	public boolean canDropFromExplosion(BlockState state, BlockGetter world, BlockPos pos, Explosion explosion) {
		return false;
	}
	
	@Override
	public boolean canSustainPlant(BlockState state, BlockGetter world, BlockPos pos, Direction facing,
			IPlantable plantable) {
		return true;
	}
	
	@Override
	public boolean canHarvestBlock(BlockState state, BlockGetter world, BlockPos pos, Player player) {
		return false;
	}
	
	@Override
	public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
		return false;
	}
   
   public RenderShape getRenderShape(BlockState p_48758_) {
	      return RenderShape.INVISIBLE;
	   }

   public VoxelShape getShape(BlockState p_57510_, BlockGetter p_57511_, BlockPos p_57512_, CollisionContext p_57513_) {
      return Shapes.empty();
   }

   
   @Override
   public BlockState updateShape(BlockState p_48990_, Direction p_48991_, BlockState p_48992_, LevelAccessor p_48993_, BlockPos p_48994_, BlockPos p_48995_) { 
	   return Blocks.AIR.defaultBlockState();
   }
   
   public boolean isRandomlyTicking(BlockState p_53961_) {
	   return true;
   }
   
   @SuppressWarnings("deprecation")
   @Override
	public void randomTick(BlockState p_49888_, ServerLevel p_49889_, BlockPos p_49890_, RandomSource p_49891_) {
//	   p_49889_.setBlock(p_49890_, Blocks.AIR.defaultBlockState(), 2);
		super.randomTick(p_49888_, p_49889_, p_49890_, p_49891_);
	}

   @Override
	public void animateTick(BlockState p_49888_, Level p_49889_, BlockPos p_49890_, RandomSource p_49891_) {
	   	super.animateTick(p_49888_, p_49889_, p_49890_, p_49891_);
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new SimpleLightBlockEntity(pos, state);
	}
	
	@Nullable
	   public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level p_153055_, BlockState p_153056_, BlockEntityType<T> p_153057_) {
		return !p_153055_.isClientSide ? createTickerHelper(p_153057_, Registrations.GLOWING_AIR_BLOCK_ENTITY.get(), SimpleLightBlockEntity::animateTick) : null;
	   }
}