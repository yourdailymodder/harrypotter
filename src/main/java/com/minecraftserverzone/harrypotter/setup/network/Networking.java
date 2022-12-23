package com.minecraftserverzone.harrypotter.setup.network;

import com.minecraftserverzone.harrypotter.HarryPotterMod;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class Networking {

    private static SimpleChannel INSTANCE;
    private static int ID = 0;

    private static int nextID() {
        return ID++;
    }

    public static void registerMessages() {
        INSTANCE = NetworkRegistry.newSimpleChannel(new ResourceLocation(HarryPotterMod.MODID, "harrypotter"),
                () -> "1.0",
                s -> true,
                s -> true);

        INSTANCE.messageBuilder(PacketSpells.class, nextID())
                .encoder(PacketSpells::toBytes)
                .decoder(PacketSpells::new)
                .consumerMainThread(PacketSpells::handle)
                .add();
        INSTANCE.messageBuilder(PacketDataForAll.class, nextID())
		        .encoder(PacketDataForAll::toBytes)
		        .decoder(PacketDataForAll::new)
		        .consumerMainThread(PacketDataForAll::handle)
		        .add();
        INSTANCE.messageBuilder(PacketData.class, nextID())
		        .encoder(PacketData::toBytes)
		        .decoder(PacketData::new)
		        .consumerMainThread(PacketData::handle)
		        .add();
        INSTANCE.messageBuilder(PacketDataCDForAll.class, nextID())
		        .encoder(PacketDataCDForAll::toBytes)
		        .decoder(PacketDataCDForAll::new)
		        .consumerMainThread(PacketDataCDForAll::handle)
		        .add();
        INSTANCE.messageBuilder(PacketHerbivicus.class, nextID())
		        .encoder(PacketHerbivicus::toBytes)
		        .decoder(PacketHerbivicus::new)
		        .consumerMainThread(PacketHerbivicus::handle)
		        .add();
        INSTANCE.messageBuilder(PacketHerbivicusToClient.class, nextID())
        		.encoder(PacketHerbivicusToClient::toBytes)
		        .decoder(PacketHerbivicusToClient::new)
		        .consumerMainThread(PacketHerbivicusToClient::handle)
		        .add();
    }

    public static void sendToClient(Object packet, ServerPlayer player) {
        INSTANCE.sendTo(packet, player.connection.connection, NetworkDirection.PLAY_TO_CLIENT);
    }

    public static void sendToServer(Object packet) {
        INSTANCE.sendToServer(packet);
    }
}
