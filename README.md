# Always On Locator Bar

A small client-side Fabric mod for Minecraft **26.1.2** that stops the locator bar (waypoint dots) from disappearing whenever the XP bar pops up to animate absorbed experience.

In vanilla, picking up XP orbs hides your locator bar for a few seconds: annoying when you're trying to follow a waypoint. This mod overlays the locator dots on top of the XP bar so your waypoints stay visible at all times.

## Video DEMO

https://www.youtube.com/watch?v=_JZ3edkz2wk

## Screenshots

| Locator bar (vanilla state) | Locator dots overlayed on XP bar |
| --- | --- |
| ![Locator bar](noxpabsorbtion.png) | ![Overlay on XP](xpabsorption.png) |

## Features

- **Locator dots overlay**: when the XP bar takes over the screen space, the waypoint markers are drawn on top of it instead of being hidden.
- **Optional always-on XP bar**: force the XP bar to stay on screen permanently, with locator dots overlayed.
- **Master toggle**: flip the whole mod off to instantly fall back to vanilla behaviour.
- **In-game config screen**: accessible through Mod Menu, three simple modes:
  - `Off`: vanilla behaviour
  - `Overlay on XP`: locator dots drawn on top of the XP bar (default)
  - `Always show XP bar`: XP bar permanently visible, with locator dots overlayed

## Requirements

- Minecraft **26.1.2**
- Fabric Loader **>= 0.19.2**
- Fabric API
- Java **25**

## Optional

- [Mod Menu](https://modrinth.com/mod/modmenu): needed to open the in-game config screen.

## Install

1. Install [Fabric Loader](https://fabricmc.net/use/) for 26.1.2.
2. Drop [Fabric API](https://modrinth.com/mod/fabric-api) into your `mods/` folder.
3. Drop the `always-on-locator-bar` jar into `mods/`.
4. (Optional) Add Mod Menu for the in-game config UI.

## Build from source

```sh
./gradlew build
```

Built jar lands in `build/libs/`.
