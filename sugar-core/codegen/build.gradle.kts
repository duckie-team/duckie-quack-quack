/*
 * Designed and developed by Duckie Team 2023.
 *
 * Licensed under the MIT.
 * Please see full license: https://github.com/duckie-team/quack-quack-android/blob/main/LICENSE
 */

plugins {
  quackquack("jvm-kotlin")
}

dependencies {
  implementations(
    libs.kotlin.embeddable.compiler,
    libs.kotlin.kotlinpoet.core,
    projects.sugarCore.node,
    projects.sugarCore.names,
    projects.sugarCore.error,
    projects.utilBackendKotlinc,
    projects.utilBackendKotlinpoet,
  )
}
