/*
 * Designed and developed by Duckie Team 2023.
 *
 * Licensed under the MIT.
 * Please see full license: https://github.com/duckie-team/quack-quack-android/blob/main/LICENSE
 */

@file:Suppress("INLINE_FROM_HIGHER_PLATFORM")

plugins {
  quackquack("jvm-kotlin")
  quackquack("kotlin-explicit-api")
  quackquack("quack-publishing")
}

dependencies {
  api(projects.utilBackend.orArtifact())
  implementations(
    libs.kotlin.ksp.api,
    libs.kotlin.kotlinpoet.core,
  )
}
