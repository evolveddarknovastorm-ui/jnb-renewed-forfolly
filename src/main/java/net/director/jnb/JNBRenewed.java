package net.director.jnb;

import net.director.jnb.item.ModItemGroups;
import net.director.jnb.item.ModItems;
import net.fabricmc.api.ModInitializer;

import net.minecraft.util.Identifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JNBRenewed implements ModInitializer {
	public static final String MOD_ID = "jnb-renewed";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Hello Fabric world!");

		ModItemGroups.registerItemGroups();
		ModItems.registerModItems();

	}

	public static Identifier id(String path) {
		return new Identifier(MOD_ID, path);
	}
}
