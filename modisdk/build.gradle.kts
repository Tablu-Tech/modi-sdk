import org.gradle.api.publish.maven.MavenPublication
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("maven-publish")
    id ("org.jetbrains.dokka")
}

//repositories {
//    google()
//    mavenCentral()
//    maven ("https://maven.regulaforensics.com/RegulaDocumentReader")
//    maven ("https://jitpack.io")
//}

android {
    namespace = "com.tablutech.modisdk"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.0"
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

    implementation ("com.squareup.retrofit2:retrofit:2.11.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.11.0")
    implementation ("com.squareup.okhttp3:logging-interceptor:4.9.0")
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.5.1")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.5.1")
    implementation ("com.google.accompanist:accompanist-permissions:0.35.1-alpha")
    implementation ("com.prabhatpandey:otp-compose:1.0.1")
    implementation ("io.insert-koin:koin-android:3.3.0")

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
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

afterEvaluate {
    publishing {
        publications {
            create("release", MavenPublication::class.java) {
                from(components["release"])

                groupId = "com.github.inaciosacataria"
                artifactId = "biometrylibrary"
                version = "1.1.6.3"
            }
        }
    }
}