package com.orangopontotango.alwaysonlocatorbar.client.config;

import com.orangopontotango.alwaysonlocatorbar.client.AlwaysOnLocatorBarClient;
import dev.isxander.yacl3.config.v2.api.ConfigClassHandler;
import dev.isxander.yacl3.config.v2.api.SerialEntry;
import dev.isxander.yacl3.config.v2.api.serializer.GsonConfigSerializerBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.resources.Identifier;

public class AlwaysOnLocatorBarConfig {
    public static final ConfigClassHandler<AlwaysOnLocatorBarConfig> HANDLER =
        ConfigClassHandler.createBuilder(AlwaysOnLocatorBarConfig.class)
            .id(Identifier.fromNamespaceAndPath(AlwaysOnLocatorBarClient.MOD_ID, "config"))
            .serializer(config -> GsonConfigSerializerBuilder.create(config)
                .setPath(FabricLoader.getInstance().getConfigDir()
                    .resolve(AlwaysOnLocatorBarClient.MOD_ID + ".json"))
                .setJson5(false)
                .build())
            .build();

    @SerialEntry(comment = "Master Toggle. When false, the mod does nothing, vannial behaviour.")
    public boolean enabled = true;

    @SerialEntry(comment = "If true, force the XP bar to always be visible (with locator dots overlayed).")
    public boolean xpBarAlwaysVisible = false;

    public static AlwaysOnLocatorBarConfig get() {
        return HANDLER.instance();
    }

    public static void load() {
        HANDLER.load();
    }
}