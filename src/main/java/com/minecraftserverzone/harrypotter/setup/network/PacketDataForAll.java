package com.minecraftserverzone.harrypotter.setup.network;

import java.util.UUID;
import java.util.function.Supplier;

import com.minecraftserverzone.harrypotter.setup.capabilities.PlayerStatsProvider;

import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;

public class PacketDataForAll {
	private int spellNum;
	private int[] spells;
	private UUID uuid;

    public PacketDataForAll(FriendlyByteBuf buf) {
    	this.spellNum = buf.readInt();
    	this.spells = buf.readVarIntArray();
    	this.uuid = buf.readUUID();
    }

    public PacketDataForAll(int spellNum, int[] spells, UUID uuid) {
    	this.spellNum = spellNum;
    	this.spells = spells;
    	this.uuid = uuid;
    }

    public void toBytes(FriendlyByteBuf buf) {
    	buf.writeInt(spellNum);
    	buf.writeVarIntArray(spells);
    	buf.writeUUID(uuid);
    }

	public void handle(Supplier<NetworkEvent.Context> ctx) {
	    ctx.get().enqueueWork(() -> {
	    	DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> ()-> playerChanged());
	    });
	    ctx.get().setPacketHandled(true);
	}

	@SuppressWarnings("resource")
	public  void playerChanged() {
    	for(Player sp : Minecraft.getInstance().level.players()) {
    		if(sp.getUUID().equals(uuid)) {
//    			System.out.println("set new data for client");
				sp.getCapability(PlayerStatsProvider.PLAYER_STATS_CAPABILITY).ifPresent(h -> {
					h.setSpellsLevel(spells);
//					System.out.println("y: "+spells.length);
 				});
			}else {
//				System.out.println("dont set new data for client");
			}
		}
	}

}