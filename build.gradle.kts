// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.0" apply false
    id("org.jetbrains.kotlin.android") version "1.8.0" apply false
    id("maven-publish")
    id("java")
}
publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "ir.fastclick.tavoosSDK"
            artifactId = "tavoosSDKLibrary"
            version = "0.3.0"

            from(components["java"])
        }
    }
}