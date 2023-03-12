/*
 * Designed and developed by Duckie Team 2023.
 *
 * Licensed under the MIT.
 * Please see full license: https://github.com/duckie-team/quack-quack-android/blob/2.x.x/LICENSE
 */

@file:Suppress("UnstableApiUsage", "DSL_SCOPE_VIOLATION")

import org.jetbrains.dokka.gradle.DokkaTask

plugins {
    `buildlogic-android-library`
    `buildlogic-android-compose`
    `buildlogic-android-compose-metrics`
    `buildlogic-jvm-junit`
    `buildlogic-jvm-dokka`
    `buildlogic-kotlin-explicitapi`
    alias(libs.plugins.kotlin.ksp)
    alias(libs.plugins.test.paparazzi)
}

tasks.withType<DokkaTask> {
    moduleName.set("QuackQuack-Core")
    notCompatibleWithConfigurationCache("https://github.com/Kotlin/dokka/issues/1217")
}

android {
    namespace = "team.duckie.quackquack.core"
    resourcePrefix = "quack_"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

ksp {
    arg("AidePath", "$rootDir/core-aide/src/main/kotlin/rule")
    arg("CorePath", "$rootDir/core-sugar/src/main/kotlin/team/duckie/quackquack/sugar")
}

dependencies {
    api(libs.kotlin.collections.immutable)
    implementations(
        libs.androidx.core.ktx,
        libs.compose.uiutil,
        libs.compose.coil,
        libs.compose.animation,
        libs.compose.material,
        projects.coreAideAnnotation,
        projects.coreSugarAnnotation,
    )

    testImplementation(libs.test.strikt)
    androidTestImplementations(
        libs.test.strikt,
        libs.test.junit.compose,
        libs.bundles.test.mockito,
        projects.screenshotMatcher,
    )

    ksp(projects.coreAideProcessor)
    // ksp(projects.coreSugarProcessor)

    // lintPublish(projects.coreAide)
}
