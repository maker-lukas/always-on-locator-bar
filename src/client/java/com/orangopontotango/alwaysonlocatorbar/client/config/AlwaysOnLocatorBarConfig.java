package com.orangopontotango.alwaysonlocatorbar.client.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.orangopontotango.alwaysonlocatorbar.client.AlwaysOnLocatorBarClient;
import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class AlwaysOnLocatorBarConfig {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path CONFIG_PATH = FabricLoader.getInstance().getConfigDir()
        .resolve(AlwaysOnLocatorBarClient.MOD_ID + ".json");

    private static AlwaysOnLocatorBarConfig instance = new AlwaysOnLocatorBarConfig();

    /** Master toggle. When false, the mod does nothing (vanilla behaviour). */
    public boolean enabled = true;

    /** If true, force the XP bar to always be visible (with locator dots overlayed). */
    public boolean xpBarAlwaysVisible = false;

    public static AlwaysOnLocatorBarConfig get() {
        return instance;
    }

    public static void load() {
        if (!Files.exists(CONFIG_PATH)) {
            save();
            return;
        }
        try (var reader = Files.newBufferedReader(CONFIG_PATH)) {
            AlwaysOnLocatorBarConfig loaded = GSON.fromJson(reader, AlwaysOnLocatorBarConfig.class);
            if (loaded != null) {
                instance = loaded;
            }
        } catch (IOException | JsonParseException e) {
            AlwaysOnLocatorBarClient.LOGGER.warn("Failed to load config, using defaults", e);
        }
    }

    public static void save() {
        try {
            Files.createDirectories(CONFIG_PATH.getParent());
            try (var writer = Files.newBufferedWriter(CONFIG_PATH)) {
                GSON.toJson(instance, writer);
            }
        } catch (IOException e) {
            AlwaysOnLocatorBarClient.LOGGER.warn("Failed to save config", e);
        }
    }
}
