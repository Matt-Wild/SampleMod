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
