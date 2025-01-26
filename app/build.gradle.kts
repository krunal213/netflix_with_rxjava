plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.kapt)
    //alias(libs.plugins.navigationSafeargsKotlin)
}

android {
    namespace = "com.app.netflixwithrxjava"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.app.netflixwithrxjava"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        kapt {
            arguments {
                arg("room.schemaLocation", "$projectDir/schemas")
            }
        }
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(libs.dagger)
    implementation(libs.room.runtime)
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.glide)

    kapt(libs.dagger.compiler)
    kapt(libs.room.compiler)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(project(":feature:ui:splash"))
    implementation(project(":feature:ui:getstarted"))
    implementation(project(":feature:ui:help"))
    implementation(project(":feature:ui:main"))
    implementation(project(":feature:ui:search"))
    implementation(project(":feature:ui:settings"))

    implementation(project(":android_resource"))

}