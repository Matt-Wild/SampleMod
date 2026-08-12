plugins {
    id("com.spilledsoup.umapi")
}

group = "com.spilledsoup.samplemod"
version = "0.0.1"

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
    }
}
