package com.minecraftserverzone.harrypotter.setup;

import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.ClientRegistry;

public class KeyHandler {
	public static KeyMapping BATTLE_STANCE, FLY, SPELL_BOOK;
	
	public static void register() {
		final String MOD_KEY="key.harrypotter.category";
		//h button
//		FLY = new KeyMapping("key.harrypotter.fly", 82, MOD_KEY);
		BATTLE_STANCE = new KeyMapping("key.harrypotter.battle_stance", 66, MOD_KEY); //b key
		SPELL_BOOK = new KeyMapping("key.harrypotter.spell_book", 72, MOD_KEY); //h key
		
//		ClientRegistry.registerKeyBinding(FLY);
		ClientRegistry.registerKeyBinding(BATTLE_STANCE);
		ClientRegistry.registerKeyBinding(SPELL_BOOK);
	}
}
