
plugins {
    alias(libs.plugins.shadow)
}

dependencies {
    implementation(project(":api"))
}

tasks {
    shadowJar {
        archiveClassifier.set("")
    }
}
