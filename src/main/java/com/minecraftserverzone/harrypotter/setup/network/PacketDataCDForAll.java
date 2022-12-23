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

public class PacketDataCDForAll {
	private int spellNum;
    private int spellCD;
	private UUID uuid;

    public PacketDataCDForAll(FriendlyByteBuf buf) {
    	this.spellNum = buf.readInt();
    	this.spellCD = buf.readInt();
    	this.uuid = buf.readUUID();
    }

    public PacketDataCDForAll(int spellNum, int spellCD, UUID uuid) {
    	this.spellNum = spellNum;
    	this.spellCD = spellCD;
    	this.uuid = uuid;
    }

    public void toBytes(FriendlyByteBuf buf) {
    	buf.writeInt(spellNum);
    	buf.writeInt(spellCD);
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
				sp.getCapability(PlayerStatsProvider.PLAYER_STATS_CAPABILITY).ifPresent(h -> {
					int[] allSpellCooldown = new int[] {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
					if(h.getSpellsCD() != null) {
						if(h.getSpellsCD().length < 25) {
							h.setSpellsCD(allSpellCooldown);
						}else {
							allSpellCooldown = h.getSpellsCD();
						}
					}else {
						h.setSpellsCD(allSpellCooldown);
					}
//					System.out.println("Client: "+h.getSpellsCD().length);
					h.setSpellCD(spellCD, spellNum);				
 				});
			}
		}
	}

}