package com.minecraftserverzone.harrypotter.setup.capabilities;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

public class PlayerStatsProvider implements ICapabilitySerializable<CompoundTag> {

	private final DefaultPlayerStats stats = new DefaultPlayerStats();
    private final LazyOptional<IPlayerStats> statsOptional = LazyOptional.of(() -> stats);

	public static Capability<IPlayerStats> PLAYER_STATS_CAPABILITY = CapabilityManager.get(new CapabilityToken<>() {});

    public void invalidate() {
    	statsOptional.invalidate();
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        return PLAYER_STATS_CAPABILITY.orEmpty(cap, statsOptional);
    }

    @Override
    public CompoundTag serializeNBT() {
        if (PLAYER_STATS_CAPABILITY == null) {
            return new CompoundTag();
        } else {
            CompoundTag tag = new CompoundTag();
            tag.putInt("battletick", stats.getBattleTick());
            tag.putInt("flying", stats.getFlying());
            tag.putInt("clickedskill", stats.getClickedSkill());
            tag.putInt("selectedhotbar", stats.getSelectedHotbar());
            tag.putInt("hotbarbeforebattlestance", stats.getHotbarBeforeBattleStance());
            tag.putInt("usingskill", stats.getUsingSkill());
            
            if(stats.getSpellsLevel() != null) {
            	tag.putIntArray("spellslevel", stats.getSpellsLevel());
            }else {
            	tag.putIntArray("spellslevel", new int[] {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0});
            }
            
            if(stats.getSpellsCD() != null) {
            	tag.putIntArray("spellscd", stats.getSpellsCD());
            }else {
            	tag.putIntArray("spellscd", new int[] {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0});
            }

            return tag;
        }
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        if (PLAYER_STATS_CAPABILITY != null) {
        	stats.setBattleTick(nbt.getInt("battletick"));
        	stats.setFlying(nbt.getInt("flying"));
        	stats.setClickedSkill(nbt.getInt("clickedskill"));
        	stats.setSelectedHotbar(nbt.getInt("selectedhotbar"));
        	stats.setHotbarBeforeBattleStance(nbt.getInt("hotbarbeforebattlestance"));
        	stats.setUsingSkill(nbt.getInt("usingskill"));
        	
        	if(nbt.getIntArray("spellslevel") != null) {
        		stats.setSpellsLevel(nbt.getIntArray("spellslevel"));
            } else {
            	stats.setSpellsLevel(new int[] {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0});
            }
        	
        	if(nbt.getIntArray("spellscd") != null) {
        		stats.setSpellsCD(nbt.getIntArray("spellscd"));
            } else {
            	stats.setSpellsCD(new int[] {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0});
            }
        }
    }
}