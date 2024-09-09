plugins {
    id("java")
    kotlin("jvm")
}

group = "xyz.sk1.bukkit.prisonextra"
version = "1.0-SNAPSHOT"

repositories {
    mavenLocal()
    mavenCentral()

    maven("https://jitpack.io")
}

dependencies {
    implementation(project(":PrisonExtra-api"))
    implementation("org.reflections:reflections:0.10.2")
    implementation("com.github.Cobeine:SQLava:1.5.5-SNAPSHOT")
    implementation("com.zaxxer:HikariCP:5.0.1")
    implementation(kotlin("stdlib-jdk8"))
}

tasks.withType<ProcessResources> {
    from(sourceSets.main.get().resources) {
        include("plugin.yml")
        filter { line -> line.replace("%project_version%", project.version.toString()) }
        duplicatesStrategy = DuplicatesStrategy.INCLUDE
    }
}
kotlin {
    jvmToolchain(8)
}