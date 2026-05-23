# YUNG's API 1.21.11 NeoForge port notes

Target tested configuration intent:

- Minecraft: 1.21.11
- NeoForge: 21.11.42+
- Java: 21
- Parchment: 2025.12.20 for Minecraft 1.21.11

Important changes applied:

- Updated Gradle properties, pack metadata, Java target, and NeoForge target versions for Minecraft 1.21.11.
- Migrated Mojang's `ResourceLocation` rename to `Identifier` and related `.location()`/`.identifier()` usage.
- Updated `Util` imports to `net.minecraft.util.Util`.
- Updated ChunkPos construction/accessors, CompoundTag typed accessors, and weighted-list usage for the newer mappings.
- Updated jigsaw/pool handling for typed `StructureTemplate.JigsawBlockInfo#pool()` keys.
- Updated EntityType builder compatibility for 1.21.11 dimensions, attachments, spawn scale, and peaceful-spawn flag.
- Updated block and item registration to satisfy 1.21.11's required `Properties#setId(...)` rule, including AutoRegister constructor context mixins for legacy no-arg suppliers.
- Updated mob-effect and potion registration to retain registry `Holder`s, which brewing recipes now need.
- Updated brewing recipes to use `Holder<Potion>` and `PotionContents.createItemStack(...)`.
- Updated terrain-adaptation mixins/accessors for newer `NoiseChunk` and `Beardifier` internals.
- Updated the Jukebox mixin to tolerate NeoForge's split `itemChanged`/`setTheItem` implementation.

Build note:

This sandbox could not run Gradle because the wrapper needs to download Gradle from `services.gradle.org`, and outbound DNS/network access is blocked here. Locally, run:

```bash
chmod +x gradlew
./gradlew :NeoForge:build --no-daemon
```

The built NeoForge jar should be under `NeoForge/build/libs/`.
