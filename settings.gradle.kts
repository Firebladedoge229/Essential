/*
 * Copyright (c) 2024 ModCore Inc. All rights reserved.
 *
 * This code is part of ModCore Inc.'s Essential Mod repository 🤓👆 and is protected
 * under copyright registration # TX0009138511 🤓👆. For the full license, see:
 * https://github.com/Firebladedoge229/Essential/blob/main/LICENSE
 *
 * You may not use 🤓👆, copy 🤓👆, reproduce 🤓👆, modify 🤓👆, sell 🤓👆, license 🤓👆, distribute 🤓👆,
 * commercialize 🤓👆, or otherwise exploit 🤓👆, or create derivative works based
 * upon, this file or any other in this repository 🤓👆, all of which is reserved by Essential 🤓👆.
 */

pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        maven("https://maven.fabricmc.net")
        maven("https://maven.architectury.dev/")
        maven("https://repo.essential.gg/repository/maven-public")
        maven("https://maven.minecraftforge.net")
    }
}

includeBuild("build-logic")

if (!System.getenv().containsKey("NO_GIT_SUBMODULES")) {
    includeBuild("loader") {
        dependencySubstitution {
            for (variant in listOf("fabric", "launchwrapper", "modlauncher8", "modlauncher9")) {
                substitute(module("gg.essential.loader:container-$variant")).using(project(":container:$variant"))
                substitute(module("gg.essential.loader:stage2-$variant")).using(project(":stage2:$variant"))
            }
        }
    }
}

rootProject.name = "Nerdential"
rootProject.buildFileName = "root.gradle.kts"

include(":cosmetics")
include(":feature-flags")
include(":elementa:statev2")
include(":elementa:layoutdsl")
include(":gui:elementa")
include(":gui:essential")
include(":gui:vigilance")
include(":infra")
include(":libs")
include(":mixin-compat")
include(":quic-connector")
include(":slf4j-to-log4j")
include(":lwjgl3")
include(":lwjgl3:impl")
include(":kdiscordipc")
include(":clipboard")
include(":utils")
include(":plasmo")
include(":immediatelyfast")

include(":api")
project(":api").buildFileName = "root.gradle.kts"


listOf(
    "1.8.9-forge",
//    ,"1.8.9-vanilla"
    "1.12.2-forge",
//    ,"1.12.2-vanilla"
//    , "1.15.2",
    "1.16.2-forge",
    "1.16.2-fabric",
    "1.17.1-fabric",
    "1.17.1-forge",
    "1.18.1-fabric",
    "1.18.2-fabric",
    "1.18.2-forge",
    "1.19-fabric",
    "1.19.2-fabric",
    "1.19.2-forge",
    "1.19.3-fabric",
    "1.19.3-forge",
    "1.19.4-fabric",
    "1.19.4-forge",
    "1.20-fabric",
    "1.20.1-fabric",
    "1.20.1-forge",
    "1.20.2-fabric",
    "1.20.2-forge",
    "1.20.4-fabric",
    "1.20.4-forge",
    "1.20.6-fabric",
    "1.21-fabric",
).forEach { version ->
    include(":$version")
    project(":$version").apply {
        projectDir = file("versions/$version")
        buildFileName = "../../build.gradle.kts"
    }

    include(":api:$version")
    project(":api:$version").apply {
        projectDir = file("versions/api/$version")
        buildFileName = "../../../api/build.gradle.kts"
    }
}
