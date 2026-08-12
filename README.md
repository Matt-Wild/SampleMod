# SampleMod

SampleMod is an example Minecraft mod that uses UMAPI as a proof of concept.

Its job is to prove that a SpilledSoup mod can be written against the Universal Modding API instead of directly against a specific Minecraft loader or version.

## Goal

The long-term ideal is for SampleMod to be completely loader- and version-neutral. In that state, SampleMod should not need Fabric-specific Java code, Fabric-specific Gradle configuration, or direct references to Minecraft versions and loaders.

During development, SampleMod may temporarily keep some explicit Fabric setup while UMAPI learns to own that responsibility safely. The current transition shape is:

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
    }
}
```

Eventually, even target selection may move out of SampleMod if SpilledSoup build conventions can provide it elsewhere.

## Current State

SampleMod currently uses the Fabric 1.20.1 path. This path is known to work: the game has launched successfully and the SampleMod welcome message appeared in chat.

SampleMod declares neutral mod metadata and its UMAPI target. UMAPI owns the Fabric Loom setup, Minecraft dependency, mappings, Fabric Loader dependency, UMAPI platform dependency, and generated Fabric metadata for the current target.

SampleMod still uses the UMAPI settings plugin so Gradle can resolve UMAPI's loader tooling before the main project plugin is loaded.

## Development Rule

Preserve the welcome-message path. Changes should be small, verified, and reversible in spirit:

- move one build responsibility into UMAPI at a time
- build UMAPI first when changing the plugin
- build SampleMod after UMAPI changes
- avoid removing Fabric-specific SampleMod setup until UMAPI has replaced it
- keep SampleMod source code focused on UMAPI concepts rather than loader APIs

## Building

From the repository root:

```powershell
.\gradlew.bat clean build
```

When testing UMAPI plugin changes, build UMAPI first, then build SampleMod.
