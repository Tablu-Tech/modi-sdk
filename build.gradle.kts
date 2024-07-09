// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
   // alias(libs.plugins.google.gms.google.services) apply false
    alias(libs.plugins.android.library) apply false
}

buildscript {
    dependencies {
        // ... other dependencies
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.51")
        classpath ("org.jetbrains.dokka:dokka-android-gradle-plugin:0.9.17")
    }
}

