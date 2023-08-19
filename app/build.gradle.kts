plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("maven-publish")
}

android {
    signingConfigs {
        getByName("debug") {
            keyAlias = "Amir"
            keyPassword = "12345678"
            storeFile = file("C:\\Users\\Amir\\AndroidStudioProjects\\TavoosSDKsample\\key.jks")
            storePassword = "12345678"
        }
        create("release") {
            keyAlias = "Amir"
            keyPassword = "12345678"
            storeFile = file("C:\\Users\\Amir\\AndroidStudioProjects\\TavoosSDKsample\\key.jks")
            storePassword = "12345678"
        }
    }
    namespace = "ir.fastclick.tavoossdk"
    compileSdk = 33

    defaultConfig {
        applicationId = "ir.fastclick.tavoossdk"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("release")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    implementation(project(mapOf("path" to ":core")))
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}