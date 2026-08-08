plugins {
    application
}

group = "com.spilledsoup.samplemod"
version = "0.0.1"

repositories {
    mavenCentral()
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

dependencies {
    implementation("com.spilledsoup.umapi:UMAPI:0.0.1")

    testImplementation(platform("org.junit:junit-bom:6.0.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

application {
    mainClass = "com.spilledsoup.samplemod.SampleMod"
}

tasks.test {
    useJUnitPlatform()
}