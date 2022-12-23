package com.minecraftserverzone.harrypotter.setup.events;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import com.minecraftserverzone.harrypotter.HarryPotterMod;
import com.minecraftserverzone.harrypotter.setup.capabilities.PlayerStatsProvider;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;

public class PlayerEventHandler {

    public static void onAttachCapabilitiesEvent(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player) {
            PlayerStatsProvider provider = new PlayerStatsProvider();
            event.addCapability(new ResourceLocation(HarryPotterMod.MODID, "stats"), provider);
            //event.addListener(provider::invalidate);
        }
    }
}
