plugins {
    java
}

group = "me.fortibrine"
version = "1.0"

allprojects {
    repositories {
        mavenCentral()

        maven("https://repo.papermc.io/repository/maven-public/")
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

        compileOnly(libs.paper)

        compileOnly(libs.lombok)
        annotationProcessor(libs.lombok)

        compileOnly(libs.jetbrains.annotations)
        annotationProcessor(libs.jetbrains.annotations)
    }
}
