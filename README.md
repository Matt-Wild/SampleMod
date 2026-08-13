# SampleMod

SampleMod is an example Minecraft mod that uses UMAPI as a proof of concept.

Its job is to prove that a SpilledSoup mod can be written against the Universal Modding API instead of directly against a specific Minecraft loader or version.

## Goal

The long-term ideal is for SampleMod to be completely loader- and version-neutral. In that state, SampleMod should not need loader-specific Java code, loader-specific Gradle configuration, or direct references to Minecraft versions and loaders.

During development, SampleMod declares UMAPI targets while UMAPI learns to own each loader/version path safely. The current 1.20.1 target shape is:

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
        neoforge("1.20.1")
        forge("1.20.1")
        quilt("1.20.1")
    }
}
```

Eventually, even target selection may move out of SampleMod if SpilledSoup build conventions can provide it elsewhere.

## Current State

SampleMod currently declares Fabric, NeoForge, Forge, and Quilt targets for Minecraft 1.20.1. Fabric was the first known-good launch path, NeoForge has been tested successfully, and Forge and Quilt are being introduced through the same UMAPI target model.

SampleMod declares neutral mod metadata and its UMAPI targets. UMAPI owns the loader tooling, Minecraft dependency, loader/platform dependency, UMAPI platform dependency, generated loader metadata, generated Forge-family entrypoint bridges, runtime tasks, and exported jar location for each declared target.

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

The UMAPI plugin also exports the finished target jar to:

```text
build/umapi/exports/
```

UMAPI also provides runtime tasks for launching the declared targets:

```powershell
.\gradlew.bat runUMAPIFabric1201Client
.\gradlew.bat runUMAPIFabric1201Server
.\gradlew.bat runUMAPINeoForge1201Client
.\gradlew.bat runUMAPINeoForge1201Server
.\gradlew.bat runUMAPIForge1201Client
.\gradlew.bat runUMAPIForge1201Server
.\gradlew.bat runUMAPIQuilt1201Client
.\gradlew.bat runUMAPIQuilt1201Server
.\gradlew.bat runUMAPIClient
.\gradlew.bat runUMAPIServer
```

With multiple declared targets, the neutral `runUMAPIClient` and `runUMAPIServer` shortcuts use UMAPI's default runtime selection unless SampleMod declares its own runtime default.

UMAPI keeps loader runtime data in target-specific directories under `runs/`, so testing one loader does not reuse another loader's config, logs, or saves.
