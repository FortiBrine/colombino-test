plugins {
    java
}

group = "me.fortibrine"
version = "1.0"

allprojects {
    repositories {
        mavenCentral()

        maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
        maven("https://oss.sonatype.org/content/repositories/snapshots")
    }
}

subprojects {
    apply {
        plugin("java")
    }

    tasks {
        withType<JavaCompile>().configureEach {
            options.encoding = "UTF-8"
            targetCompatibility = "17"
            sourceCompatibility = "17"
        }
    }

    dependencies {
        val libs = rootProject.libs

        compileOnly(libs.spigot)

        compileOnly(libs.lombok)
        annotationProcessor(libs.lombok)

        compileOnly(libs.jetbrains.annotations)
        annotationProcessor(libs.jetbrains.annotations)
    }
}
