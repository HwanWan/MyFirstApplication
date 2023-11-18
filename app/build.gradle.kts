plugins {
    id("com.android.application")
}

android {

    testOptions {
        unitTests.isReturnDefaultValues = true
    }

    namespace = "com.jnu.student"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.jnu.student"
        minSdk = 14
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
    }


    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

}

dependencies {
    implementation ("com.google.code.gson:gson:2.8.6")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.tracing:tracing:1.2.0-beta02")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.android.support:recyclerview-v7:28.0.0")
    implementation("com.google.android.material:material:1.9.0")
    implementation("com.tencent.map:tencent-map-vector-sdk:4.3.4")
    testImplementation("junit:junit:4.13.2")
    testImplementation("org.json:json:20231013")
    testImplementation("androidx.test:monitor:1.7.0-alpha03")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1") 
    androidTestImplementation("androidx.test.espresso:espresso-contrib:3.5.1")
}