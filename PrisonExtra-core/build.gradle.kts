import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

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
    maven("https://repo.dmulloy2.net/repository/public/")
}

dependencies {
    implementation(project(":PrisonExtra-api"))
    implementation("org.reflections:reflections:0.10.2")
    implementation("com.zaxxer:HikariCP:5.0.1")
    compileOnly("com.comphenix.protocol:ProtocolLib:4.8.0")
    implementation(kotlin("stdlib-jdk8"))
}

tasks.withType<ProcessResources> {
    from(sourceSets.main.get().resources) {
        include("plugin.yml")
        filter { line -> line.replace("%project_version%", project.version.toString()) }
        duplicatesStrategy = DuplicatesStrategy.INCLUDE
    }
}

/*tasks.withType<ShadowJar>{
    minimize()

    archiveBaseName.set(rootProject.name)
    archiveVersion.set(this.project.version.toString())

    val classifier: String
    if(this.project.name.equals("PrisonExtra-api")){
        classifier = "api"
    } else {
        classifier = ""
    }

    archiveClassifier.set(classifier)
}*/

tasks.named("assemble"){
    dependsOn("ShadowJar")
}

kotlin {
    jvmToolchain(8)
}