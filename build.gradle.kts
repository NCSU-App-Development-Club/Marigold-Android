// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    dependencies {
        classpath("io.realm:realm-gradle-plugin:6.0.1")
    }
}
plugins {
    id("com.android.application") version "8.1.0" apply false
    id("org.jetbrains.kotlin.android") version "1.8.0" apply false
    id("com.android.library") version "8.1.0" apply false
    id("io.realm.kotlin") version "1.10.2" apply false
}