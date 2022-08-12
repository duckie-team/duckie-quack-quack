plugins {
    id("com.android.application")
    id("kotlin-android")
    id("org.jetbrains.dokka") version Versions.BuildUtil.Dokka
    jacoco
}

android {
    namespace = "land.sungbin.duckie.quackquack.playground"
}