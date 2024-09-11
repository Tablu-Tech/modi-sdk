plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {

    packagingOptions {
        exclude ("META-INF/androidx.compose.material3_material3.version")
    }

    androidResources {
        noCompress += "Regula/faceSdkResource.dat"
    }

    namespace = "com.tablutech.modisdklibray"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.tablutech.modisdklibray"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation ("com.google.dagger:hilt-android:2.51")
   // implementation(libs.androidx.runtime.livedata)
    implementation(project(":modisdk"))

    //kapt ("com.google.dagger:hilt-android-compiler:2.51")
    //kapt ("androidx.hilt:hilt-compiler:1.0.0")
    implementation ("androidx.camera:camera-core:1.2.1")
    implementation ("androidx.camera:camera-camera2:1.2.1")
    implementation ("androidx.camera:camera-lifecycle:1.2.1")
    implementation ("androidx.camera:camera-view:1.2.1")
    implementation ("androidx.camera:camera-extensions:1.2.1")
    implementation ("com.squareup.retrofit2:retrofit:2.11.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.11.0")
    implementation ("com.squareup.okhttp3:logging-interceptor:4.9.0")
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.5.1")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.5.1")
    implementation ("com.google.accompanist:accompanist-permissions:0.35.1-alpha")
    implementation ("com.prabhatpandey:otp-compose:1.0.1")

    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")

    implementation ("com.regula.face:api:6.2.+@aar"){
        isTransitive = true
    }

    implementation("androidx.datastore:datastore-preferences:1.1.1")

    //  implementation ('com.regula.face.core:basic:6.2.+@aar')
    implementation("com.regula.face.core:match:+@aar")

    implementation("com.regula.documentreader.core:fullrfid:+@aar")
    implementation("com.regula.documentreader:api:+@aar") {
        isTransitive = true
    }


    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.navigation.runtime.ktx)
    implementation(libs.androidx.navigation.compose)
    //  implementation(libs.firebase.analytics)
    //implementation(libs.androidx.navigation.compose)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}