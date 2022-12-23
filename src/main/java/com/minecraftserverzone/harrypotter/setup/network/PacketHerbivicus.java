package com.minecraftserverzone.harrypotter.setup.network;

import java.util.function.Supplier;

import com.minecraftserverzone.harrypotter.items.WandItem;
import com.minecraftserverzone.harrypotter.setup.Registrations;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.network.NetworkEvent;


public class PacketHerbivicus {

	private BlockPos blockpos, blockpos1;

    public PacketHerbivicus(FriendlyByteBuf buf) {
    	this.blockpos = buf.readBlockPos();
    	this.blockpos1 = buf.readBlockPos();
    }

    public PacketHerbivicus(BlockPos blockpos, BlockPos blockpos1) {
    	this.blockpos = blockpos;
    	this.blockpos1 = blockpos1;
    }

    public void toBytes(FriendlyByteBuf buf) {
    	buf.writeBlockPos(blockpos);
    	buf.writeBlockPos(blockpos1);
    }
    
	public void handle(Supplier<NetworkEvent.Context> ctx) {
	    ctx.get().enqueueWork(() -> {
	    	// the client that sent this packet
	        ServerPlayer player = ctx.get().getSender(); 
	        Level level = player.level;
	        if (WandItem.applyBonemeal(player.getItemInHand(InteractionHand.MAIN_HAND), level, blockpos, player)) {
					player.playSound(Registrations.BUFF.get(), 1.0F, 1.0F);
					Networking.sendToClient(new PacketHerbivicusToClient(player.getUUID()), (ServerPlayer) player);
		      } else {
		         BlockState blockstate = level.getBlockState(blockpos);
		         boolean flag = blockstate.isFaceSturdy(level, blockpos, Direction.UP);
		         if (flag && WandItem.growWaterPlant(player.getItemInHand(InteractionHand.MAIN_HAND), level, blockpos1, Direction.UP)) {
					player.playSound(Registrations.BUFF.get(), 1.0F, 1.0F);
					Networking.sendToClient(new PacketHerbivicusToClient(player.getUUID()), (ServerPlayer) player);
		         } else {
		        	 return;
		         }
		      }
	    });
	    ctx.get().setPacketHandled(true);
	    
	}

}