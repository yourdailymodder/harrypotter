package com.minecraftserverzone.harrypotter.setup.network;

import java.util.UUID;
import java.util.function.Supplier;

import com.minecraftserverzone.harrypotter.setup.Registrations;

import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;

public class PacketHerbivicusToClient {
	private UUID uuid;

    public PacketHerbivicusToClient(FriendlyByteBuf buf) {
    	this.uuid = buf.readUUID();
    }

    public PacketHerbivicusToClient(UUID uuid) {
    	this.uuid = uuid;
    }

    public void toBytes(FriendlyByteBuf buf) {
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
    			Networking.sendToServer(new PacketSpells(11));
    			sp.playSound(Registrations.BUFF.get(), 1.0F, 1.0F);
			}
		}
	}

}