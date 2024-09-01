plugins {
    `java-library`
//    id("net.ltgt.errorprone")
    id("net.kyori.indra")
    id("net.kyori.indra.git")
    id("floodgate.depsize")
}

dependencies {
    compileOnly("org.checkerframework", "checker-qual", "3.19.0")
}

indra {
    github("GeyserMC", "Floodgate") {
        ci(true)
        issues(true)
        scm(true)
    }
    mitLicense()

    javaVersions {
        target(17)
    }
}

tasks {
    processResources {
        filesMatching(listOf("plugin.yml", "bungee.yml", "velocity-plugin.json")) {
            expand(
                "id" to "floodgate",
                "name" to "floodgate",
                "version" to fullVersion(),
                "description" to project.description,
                "url" to "https://geysermc.org",
                "author" to "GeyserMC"
            )
        }
    }
}