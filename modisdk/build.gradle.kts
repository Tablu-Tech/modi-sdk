import org.gradle.api.publish.maven.MavenPublication
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("maven-publish")
}

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

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}

afterEvaluate {
    publishing {
        publications {
            create("release", MavenPublication::class.java) {
                from(components["release"])

                groupId = "com.tablutech.modisdk"
                artifactId = "modi_sdk"
                version = "1.0"
            }
        }

        repositories {
            maven {
                url = uri("file://${buildDir}/repo")
            }
        }
    }
}