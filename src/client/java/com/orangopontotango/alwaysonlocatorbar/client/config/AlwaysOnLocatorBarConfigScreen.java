package com.orangopontotango.alwaysonlocatorbar.client.config;

import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.CycleButton;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;

public final class AlwaysOnLocatorBarConfigScreen extends Screen {

    private static final int BUTTON_WIDTH = 200;
    private static final int BUTTON_HEIGHT = 20;
    private static final int SPACING = 4;

    private final Screen parent;
    private final AlwaysOnLocatorBarConfig config;

    private AlwaysOnLocatorBarConfigScreen(Screen parent) {
        super(Component.literal("Always On Locator Bar"));
        this.parent = parent;
        this.config = AlwaysOnLocatorBarConfig.get();
    }

    public static Screen create(Screen parent) {
        return new AlwaysOnLocatorBarConfigScreen(parent);
    }

    @Override
    protected void init() {
        int centerX = this.width / 2 - BUTTON_WIDTH / 2;
        int y = this.height / 2 - BUTTON_HEIGHT;

        this.addRenderableWidget(CycleButton.<Mode>builder(mode -> Component.literal(mode.label), Mode.fromConfig(this.config))
            .withValues(Mode.values())
            .withTooltip(mode -> Tooltip.create(Component.literal(mode.tooltip)))
            .create(centerX, y, BUTTON_WIDTH, BUTTON_HEIGHT,
                Component.literal("Locator bar"),
                (btn, mode) -> mode.applyTo(this.config)));

        y += BUTTON_HEIGHT + SPACING * 4;

        this.addRenderableWidget(Button.builder(CommonComponents.GUI_DONE, btn -> this.onClose())
            .bounds(centerX, y, BUTTON_WIDTH, BUTTON_HEIGHT)
            .build());
    }

    @Override
    public void onClose() {
        AlwaysOnLocatorBarConfig.HANDLER.save();
        this.minecraft.setScreen(this.parent);
    }

    private enum Mode {
        OFF("Off",
            "Vanilla behaviour. The locator bar gets hidden whenever the XP bar is shown."),
        OVERLAY("Overlay on XP",
            "The locator dots are drawn on top of the XP bar whenever it appears, " +
            "so waypoints stay visible."),
        ALWAYS_XP("Always show XP bar",
            "Keeps the XP bar on screen at all times, with the locator dots drawn on top.");

        final String label;
        final String tooltip;

        Mode(String label, String tooltip) {
            this.label = label;
            this.tooltip = tooltip;
        }

        static Mode fromConfig(AlwaysOnLocatorBarConfig config) {
            if (!config.enabled) return OFF;
            return config.xpBarAlwaysVisible ? ALWAYS_XP : OVERLAY;
        }

        void applyTo(AlwaysOnLocatorBarConfig config) {
            config.enabled = this != OFF;
            config.xpBarAlwaysVisible = this == ALWAYS_XP;
        }
    }
}
