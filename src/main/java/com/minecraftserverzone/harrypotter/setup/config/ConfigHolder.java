package com.minecraftserverzone.harrypotter.setup.config;

import org.apache.commons.lang3.tuple.Pair;

import net.minecraftforge.common.ForgeConfigSpec;

public final class ConfigHolder {
	
	public static final ForgeConfigSpec COMMON_SPEC;
	public static final CommonConfig COMMON;
	public static final ForgeConfigSpec CLIENT_SPEC;
	public static final ClientConfig CLIENT;
	
	static {
		{
			final Pair<CommonConfig, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(CommonConfig::new);
			COMMON = specPair.getLeft();
			COMMON_SPEC = specPair.getRight();
			
			final Pair<ClientConfig, ForgeConfigSpec> specPairClient = new ForgeConfigSpec.Builder().configure(ClientConfig::new);
			CLIENT = specPairClient.getLeft();
			CLIENT_SPEC = specPairClient.getRight();
		}
	}
}
