
plugins {
    alias(libs.plugins.shadow)
}

dependencies {
    implementation(project(":api"))
}

tasks {
    shadowJar {
        archiveBaseName.set("Regions")
        archiveClassifier.set("")
    }
}
