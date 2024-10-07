package net.diemond_player.simplemud;

import net.fabricmc.api.ModInitializer;

public class SimpleMud implements ModInitializer {
	@Override
	public void onInitialize() {
		SimpleMudCauldronBehavior.registerCauldronBehaviors();
	}
}