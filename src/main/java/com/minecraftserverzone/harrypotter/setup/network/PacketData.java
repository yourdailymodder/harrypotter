package com.minecraftserverzone.harrypotter.setup.network;

import java.util.UUID;
import java.util.function.Supplier;

import com.minecraftserverzone.harrypotter.setup.capabilities.PlayerStatsProvider;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkEvent;

public class PacketData {

	private int[] spells;
	private UUID uuid;
	private int spellSlot;
	
    public PacketData(FriendlyByteBuf buf) {
    	this.spells = buf.readVarIntArray();
    	this.uuid = buf.readUUID();
    	this.spellSlot = buf.readInt();
    }

    public PacketData(int[] spells, UUID uuid, int i) {
    	this.spells = spells;
    	this.uuid = uuid;
    	this.spellSlot = i;
    }

    public void toBytes(FriendlyByteBuf buf) {
    	buf.writeVarIntArray(spells);
    	buf.writeUUID(uuid);
    	buf.writeInt(spellSlot);
    }

	public void handle(Supplier<NetworkEvent.Context> ctx) {
	    ctx.get().enqueueWork(() -> {
	        ServerPlayer sender = ctx.get().getSender(); 
	 			if (!sender.level.isClientSide) {
	 				sender.getCapability(PlayerStatsProvider.PLAYER_STATS_CAPABILITY).ifPresent(h -> {
	 					if(spells!=null && spellSlot != -1) {
	 						int spelllevelcost =  h.getSpellsLevel() != null ?  h.getSpellsLevel()[spellSlot] == 0 ? 1 : h.getSpellsLevel()[spellSlot] : 1;
	 						if(sender.experienceLevel >= spelllevelcost) {
	 							sender.setExperienceLevels(sender.experienceLevel - spelllevelcost);
		 						//h.setSpellsLevel(spells);
		 						h.setSpellLevel(h.getSpellsLevel()[spellSlot]+1, spellSlot);

		 						/** send data to all client when client login **/
			 					for(Player sp : ctx.get().getSender().level.players()) {
			 						Networking.sendToClient(new PacketDataForAll(spellSlot, h.getSpellsLevel(), sender.getUUID()), (ServerPlayer) sp);
			 					}
	 						}
	 					}else {
	 						int[] allSpell = new int[] {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
	 						if(spells[0] == -1) {
	 							//just get the data
	 							if(h.getSpellsLevel() == null) {
	 								h.setSpellsLevel(allSpell);
	 							}
	 							if(h.getSpellsLevel().length == 25) {
			 						allSpell = h.getSpellsLevel();
			 					}else {
			 						h.setSpellsLevel(allSpell);
			 					}	
	 						}
	 					
		 					
		 					/** send data to all client when client login **/
//		 					for(Player sp : ctx.get().getSender().level.players()) {
//		 						Networking.sendToClient(new PacketDataForAll(-1, allSpell, uuid), (ServerPlayer) sp);
//		 					}
		 					
		 					/** send player data to client when player is near **/
		 					for(Player sp : ctx.get().getSender().level.players()) {
		 						if(uuid.equals(sp.getUUID())) {
		 							sp.getCapability(PlayerStatsProvider.PLAYER_STATS_CAPABILITY).ifPresent(h2 -> {
		 								int[] allSpell2 = new int[] {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
		 								if(h2.getSpellsLevel() == null) {
			 								h2.setSpellsLevel(allSpell2);
			 							}
		 								if(h2.getSpellsLevel().length == 25) {
		 			 						allSpell2 = h2.getSpellsLevel();
		 			 					}else {
		 			 						h2.setSpellsLevel(allSpell2);
		 			 					}
		 								Networking.sendToClient(new PacketDataForAll(-1, allSpell2, uuid), (ServerPlayer) sender);
		 							});
		 						}
		 					}
	 					}
	 				});
		        }
	    });
	    ctx.get().setPacketHandled(true);
	    
	}

}