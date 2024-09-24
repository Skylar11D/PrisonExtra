import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    id("java")
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

tasks.compileJava {
    options.encoding = "UTF-8"
    sourceCompatibility = "8"
    targetCompatibility = "8"
}

group = "xyz.sk1.bukkit.prisonextra"
version = "1.0-SNAPSHOT"

apply {
    plugin("java")
    plugin("com.github.johnrengelman.shadow")
}


subprojects {

    apply {
        plugin("java")
        plugin("com.github.johnrengelman.shadow")
    }

    repositories {
        mavenLocal()
        mavenCentral()
        maven("https://repo.papermc.io/repository/maven-public/")
        maven("https://repo.codemc.io/repository/maven-releases/")
        maven("https://oss.sonatype.org/content/groups/public/")
        maven("https://jitpack.io")
    }

    dependencies {
        compileOnly("com.google.code.gson:gson:2.11.0")
        compileOnly("org.reflections:reflections:0.9.12")

        implementation("org.spigotmc:spigot:1.8.8-R0.1-SNAPSHOT")
        implementation("org.spigotmc:minecraft-server:1.8.8-SNAPSHOT")
        implementation("org.slf4j:slf4j-simple:1.7.5")

        implementation("mysql:mysql-connector-java:8.0.33")
        implementation("com.fasterxml.jackson.core:jackson-databind:2.15.0")
        implementation("com.github.patheloper.pathetic:pathetic-mapping:3.1")

        annotationProcessor("org.projectlombok:lombok:1.18.32")

    }

    tasks.withType<ShadowJar>{
        minimize()

        archiveBaseName.set(rootProject.name)
        archiveVersion.set(this.project.version.toString())

        var classifier: String
        if(project.name == "PrisonExtra-api"){
            classifier = "api"
        } else {
            classifier = "core"
        }

        archiveClassifier.set(classifier)

        destinationDirectory.set(file("/home/skylar/Desktop/minecraft-net/1/plugins"))
    }

}

/*tasks.withType<ShadowJar>{
    minimize()

    archiveBaseName.set(rootProject.name)
    archiveVersion.set(version.toString())
    destinationDirectory.set(file("/home/skylar/Desktop/minecraft-net/1/plugins/"))
}*/

tasks.test {
    useJUnitPlatform()
}

apply(plugin = "java")
apply(plugin = "com.github.johnrengelman.shadow")
