plugins {
    id("java")
}

group = "xyz.sk1.bukkit.prisonextra"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.withType<ProcessResources> {
    from(sourceSets.main.get().resources) {
        include("plugin.yml")
        filter { line -> line.replace("%project_version%", project.version.toString()) }
        duplicatesStrategy = DuplicatesStrategy.INCLUDE
    }
}


tasks.test {
    useJUnitPlatform()
}