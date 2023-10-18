plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.umair.facebookads.example"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.umair.facebookads.example"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    viewBinding {
        enable = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.lifecycle:lifecycle-process:2.6.2")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    /*Applovin ads
    for current skd version you can get it from here
     https://dash.applovin.com/documentation/mediation/android/getting-started/integration
     */
    implementation("com.applovin:applovin-sdk:11.11.3")
    implementation ("com.applovin.mediation:facebook-adapter:6.16.0.0")
    implementation ("com.facebook.android:audience-network-sdk:6.16.0")

    //Navigation
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.4")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.4")

    //SDP
    implementation("com.intuit.sdp:sdp-android:1.1.0")
    //SSP
    implementation("com.intuit.ssp:ssp-android:1.1.0")

    implementation ("androidx.recyclerview:recyclerview:1.2.0")



}