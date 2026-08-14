# SampleMod

SampleMod is an example Minecraft mod that uses UMAPI as a proof of concept.

Its job is to prove that a SpilledSoup mod can be written against the Universal Modding API instead of directly against a specific Minecraft loader or version.

## Goal

The long-term ideal is for SampleMod to be completely loader- and version-neutral. In that state, SampleMod should not need loader-specific Java code, loader-specific Gradle configuration, or direct references to Minecraft versions and loaders.

During development, SampleMod declares UMAPI targets while UMAPI learns to own each loader/version path safely. The current target shape is:

```kotlin
plugins {
    id("com.spilledsoup.umapi")
}

umapi {
    mod {
        id = "samplemod"
        name = "Sample Mod"
        description = "UMAPI proof-of-concept sample mod"
        authors.add("SpilledSoup")
        entrypoint = "com.spilledsoup.samplemod.SampleMod"
    }

    targets {
        fabric("1.20.1")
        fabric("1.20.4")
        fabric("1.20.6")
        fabric("1.21.1")
        fabric("1.21.3")
        fabric("1.21.5")
        fabric("1.21.8")
        fabric("1.21.10")
        fabric("1.21.11")
        neoforge("1.20.1")
        neoforge("1.20.4")
        neoforge("1.20.6")
        neoforge("1.21.1")
        neoforge("1.21.3")
        neoforge("1.21.5")
        neoforge("1.21.8")
        neoforge("1.21.10")
        neoforge("1.21.11")
        forge("1.20.1")
        forge("1.20.4")
        forge("1.20.6")
        quilt("1.20.1")
        quilt("1.20.4")
    }
}
```

Eventually, even target selection may move out of SampleMod if SpilledSoup build conventions can provide it elsewhere.

## Current State

SampleMod currently declares Fabric, NeoForge, Forge, and Quilt targets for Minecraft 1.20.1 and 1.20.4, Fabric, NeoForge, and Forge targets for Minecraft 1.20.6, and Fabric and NeoForge targets for Minecraft 1.21.1, 1.21.3, 1.21.5, 1.21.8, 1.21.10, and 1.21.11. Fabric 1.20.1 was the first known-good launch path, and the other targets are being introduced through the same UMAPI target model.

SampleMod declares neutral mod metadata and its UMAPI targets. UMAPI owns the loader tooling, Minecraft dependency, loader/platform dependency, compile-only UMAPI API dependency, UMAPI platform dependency, generated loader metadata, generated Forge-family entrypoint bridges, runtime tasks, and exported jar location for each declared target.

SampleMod still uses the UMAPI settings plugin so Gradle can resolve UMAPI's loader tooling before the main project plugin is loaded.

SampleMod source code uses UMAPI for lifecycle, events, players, chat messages, and logging.

## Development Rule

Preserve the welcome-message path. Changes should be small, verified, and reversible in spirit:

- move one build responsibility into UMAPI at a time
- build UMAPI first when changing the plugin
- build SampleMod after UMAPI changes
- avoid removing loader-specific SampleMod setup until UMAPI has replaced it
- keep SampleMod source code focused on UMAPI concepts rather than loader APIs

## Building

From the repository root:

```powershell
.\gradlew.bat clean build
```

When testing UMAPI plugin changes, build UMAPI first, then build SampleMod.

`build` is the quick verification command for the neutral SampleMod project. It does not export every declared loader/version jar.

Use `exportUMAPI` when every declared target jar is needed:

```powershell
.\gradlew.bat exportUMAPI
```

Use a target-specific export task when only one jar is needed:

```powershell
.\gradlew.bat exportUMAPIFabric1201
.\gradlew.bat exportUMAPIFabric1204
.\gradlew.bat exportUMAPIFabric1206
.\gradlew.bat exportUMAPIFabric1211
.\gradlew.bat exportUMAPIFabric1213
.\gradlew.bat exportUMAPIFabric1215
.\gradlew.bat exportUMAPIFabric1218
.\gradlew.bat exportUMAPIFabric12110
.\gradlew.bat exportUMAPIFabric12111
.\gradlew.bat exportUMAPINeoForge1201
.\gradlew.bat exportUMAPINeoForge1204
.\gradlew.bat exportUMAPINeoForge1206
.\gradlew.bat exportUMAPINeoForge1211
.\gradlew.bat exportUMAPINeoForge1213
.\gradlew.bat exportUMAPINeoForge1215
.\gradlew.bat exportUMAPINeoForge1218
.\gradlew.bat exportUMAPINeoForge12110
.\gradlew.bat exportUMAPINeoForge12111
.\gradlew.bat exportUMAPIForge1201
.\gradlew.bat exportUMAPIForge1204
.\gradlew.bat exportUMAPIForge1206
.\gradlew.bat exportUMAPIQuilt1201
.\gradlew.bat exportUMAPIQuilt1204
```

The UMAPI plugin exports finished target jars to:

```text
build/umapi/exports/
```

UMAPI also provides runtime tasks for launching the declared targets:

```powershell
.\gradlew.bat runUMAPIFabric1201Client
.\gradlew.bat runUMAPIFabric1201Server
.\gradlew.bat runUMAPIFabric1204Client
.\gradlew.bat runUMAPIFabric1204Server
.\gradlew.bat runUMAPIFabric1206Client
.\gradlew.bat runUMAPIFabric1206Server
.\gradlew.bat runUMAPIFabric1211Client
.\gradlew.bat runUMAPIFabric1211Server
.\gradlew.bat runUMAPIFabric1213Client
.\gradlew.bat runUMAPIFabric1213Server
.\gradlew.bat runUMAPIFabric1215Client
.\gradlew.bat runUMAPIFabric1215Server
.\gradlew.bat runUMAPIFabric1218Client
.\gradlew.bat runUMAPIFabric1218Server
.\gradlew.bat runUMAPIFabric12110Client
.\gradlew.bat runUMAPIFabric12110Server
.\gradlew.bat runUMAPIFabric12111Client
.\gradlew.bat runUMAPIFabric12111Server
.\gradlew.bat runUMAPINeoForge1201Client
.\gradlew.bat runUMAPINeoForge1201Server
.\gradlew.bat runUMAPINeoForge1204Client
.\gradlew.bat runUMAPINeoForge1204Server
.\gradlew.bat runUMAPINeoForge1206Client
.\gradlew.bat runUMAPINeoForge1206Server
.\gradlew.bat runUMAPINeoForge1211Client
.\gradlew.bat runUMAPINeoForge1211Server
.\gradlew.bat runUMAPINeoForge1213Client
.\gradlew.bat runUMAPINeoForge1213Server
.\gradlew.bat runUMAPINeoForge1215Client
.\gradlew.bat runUMAPINeoForge1215Server
.\gradlew.bat runUMAPINeoForge1218Client
.\gradlew.bat runUMAPINeoForge1218Server
.\gradlew.bat runUMAPINeoForge12110Client
.\gradlew.bat runUMAPINeoForge12110Server
.\gradlew.bat runUMAPINeoForge12111Client
.\gradlew.bat runUMAPINeoForge12111Server
.\gradlew.bat runUMAPIForge1201Client
.\gradlew.bat runUMAPIForge1201Server
.\gradlew.bat runUMAPIForge1204Client
.\gradlew.bat runUMAPIForge1204Server
.\gradlew.bat runUMAPIForge1206Client
.\gradlew.bat runUMAPIForge1206Server
.\gradlew.bat runUMAPIQuilt1201Client
.\gradlew.bat runUMAPIQuilt1201Server
.\gradlew.bat runUMAPIQuilt1204Client
.\gradlew.bat runUMAPIQuilt1204Server
.\gradlew.bat runUMAPIClient
.\gradlew.bat runUMAPIServer
```

With multiple declared targets, the neutral `runUMAPIClient` and `runUMAPIServer` shortcuts use UMAPI's default runtime selection unless SampleMod declares its own runtime default.

UMAPI keeps loader runtime data in target-specific directories under `runs/`, so testing one loader does not reuse another loader's config, logs, or saves.
