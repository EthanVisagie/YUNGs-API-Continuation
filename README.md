# YUNG's API Continuation

YUNG's API Continuation is an unofficial continuation of YUNG's API for newer Minecraft versions. It provides shared utilities and common code used by YUNG's mods and compatible ports.

This branch currently targets Minecraft `1.21.11` and is configured for Fabric and NeoForge.

## Purpose

YUNG's API is primarily a library mod. By itself, it usually does not add visible gameplay features. Install it when another mod lists it as a required dependency.

This continuation is intended to keep dependent mods working on newer Minecraft versions while preserving the original API behavior where possible.

## Installation

1. Install the correct mod loader for your Minecraft version.
2. Install the YUNG's API Continuation jar for that loader in your `mods` folder.
3. Install the mod that depends on YUNG's API.
4. Launch the game.

## Supported Loaders

This branch is configured for:

- Fabric
- NeoForge

Current branch details:

- Minecraft target: `1.21.11`
- Fabric API: `0.141.3`
- Fabric Loader: `0.18.6`
- NeoForge: `21.11.42`
- Version: `5.4.0-neoforge-1.21.11-port`

## Downloads

Use the approved Modrinth or CurseForge project page when available. Development builds may also be attached to GitHub releases for this repository.

The original YUNG's API project pages remain useful for older releases and general dependency information.

## Reporting Issues

Open issues on this continuation repository for bugs in this fork.

Include:

- Minecraft version
- loader and loader version
- YUNG's API Continuation version
- the dependent mod that required the API
- `latest.log` or the crash report

## Building

```bash
./gradlew build
```

Built jars are written to the loader-specific `build/libs` folders.

## Credits

YUNG's API was originally created by YUNGNICKYOUNG. This continuation keeps the library available for newer Minecraft versions while preserving the original project credits and license terms.
