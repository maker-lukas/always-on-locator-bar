package com.orangopontotango.alwaysonlocatorbar.client;

import net.fabricmc.api.ClientModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AlwaysOnLocatorBarClient implements ClientModInitializer {
	public static final String MOD_ID = "always-on-locator-bar";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitializeClient() {
		LOGGER.info("Always On Locator Bar initialized");
	}
}