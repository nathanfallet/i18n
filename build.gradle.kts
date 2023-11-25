plugins {
    kotlin("multiplatform") version "1.9.20"
    kotlin("plugin.serialization") version "1.9.20"
    id("convention.publication")
    id("org.jetbrains.kotlinx.kover") version "0.7.4"
    id("com.google.devtools.ksp") version "1.9.20-1.0.13"
    id("dev.petuska.npm.publish") version "3.4.1"
}

group = "me.nathanfallet.i18n"
version = "1.0.0"

repositories {
    mavenCentral()
}

kotlin {
    // Tiers are in accordance with <https://kotlinlang.org/docs/native-target-support.html>
    // Tier 1
    macosX64()
    macosArm64()
    iosSimulatorArm64()
    iosX64()

    // Tier 2
    //linuxX64()
    //linuxArm64()
    watchosSimulatorArm64()
    watchosX64()
    watchosArm32()
    watchosArm64()
    tvosSimulatorArm64()
    tvosX64()
    tvosArm64()
    iosArm64()

    // Tier 3
    //mingwX64()
    watchosDeviceArm64()

    // jvm & js
    jvm {
        jvmToolchain(19)
        withJava()
        testRuns.named("test") {
            executionTask.configure {
                useJUnitPlatform()
            }
        }
    }
    js {
        binaries.library()
        nodejs()
        browser()
        //generateTypeScriptDefinitions() // Not supported for now because of collections etc...
    }

    applyDefaultHierarchyTemplate()
    sourceSets {
        all {
            languageSettings.apply {
                optIn("kotlin.js.ExperimentalJsExport")
            }
        }
        val commonMain by getting {
            dependencies {
                api("me.nathanfallet.usecases:usecases:1.3.1")
            }
        }
        val jvmMain by getting {
            dependencies {
                implementation(kotlin("reflect"))
            }
        }
        val jsMain by getting {
            dependencies {
                api("org.jetbrains.kotlin-wrappers:kotlin-js:1.0.0-pre.648")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation("io.mockative:mockative:2.0.1")
            }
        }
    }
}

dependencies {
    configurations
        .filter { it.name.startsWith("ksp") && it.name.contains("Test") }
        .forEach {
            add(it.name, "io.mockative:mockative-processor:2.0.1")
        }
}

npmPublish {
    readme.set(file("README.md"))
    packages {
        named("js") {
            packageJson {
                name.set("i18n-kt")
            }
        }
    }
    registries {
        register("npmjs") {
            uri.set("https://registry.npmjs.org")
        }
    }
}
