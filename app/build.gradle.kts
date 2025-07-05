import com.android.build.gradle.internal.generators.BuildConfigData

plugins {
    alias(libs.plugins.android.application)
    id("com.google.gms.google-services")
}

val mapsApiKey = rootProject.file("local.properties")
                    .readLines()
                    .first { it.startsWith("MAPS_API_KEY=") }
                    .split("=")[1]

val webClientId = rootProject.file("local.properties")
                    .readLines()
                    .first { it.startsWith("WEB_CLIENT_ID=") }
                    .split("=")[1]

android {
    namespace = "com.example.eatstreet2"
    compileSdk = 35

    buildFeatures {
        buildConfig = true
    }

    defaultConfig {
        applicationId = "com.example.eatstreet2"
        minSdk = 25
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "MAPS_API_KEY", "\"$mapsApiKey\"")
        resValue("string", "maps_api_key", mapsApiKey)
        buildConfigField("String", "WEB_CLIENT_ID", "\"$webClientId\"")
        resValue("string", "web_client_id", webClientId)
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation("com.google.firebase:firebase-auth:22.3.1")
    implementation("com.google.android.gms:play-services-auth:21.0.0")

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.google.android.gms:play-services-location:21.3.0")
    implementation("com.google.android.gms:play-services-maps:19.2.0")
    implementation("com.google.android.libraries.places:places:4.3.1")
    implementation("androidx.cardview:cardview:1.0.0")
    implementation("com.github.bumptech.glide:glide:4.15.1")
    implementation("com.google.android.material:material:1.12.0")
    implementation(libs.playservices.maps)
    implementation(libs.places)
    implementation(libs.playserviceslocation)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    annotationProcessor("com.github.bumptech.glide:compiler:4.15.1")
}
