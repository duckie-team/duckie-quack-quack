/*
 * Designed and developed by Duckie Team 2023.
 *
 * Licensed under the MIT.
 * Please see full license: https://github.com/duckie-team/quack-quack-android/blob/main/LICENSE
 */

plugins {
    quackquack("jvm-kotlin")
    quackquack("test-kotest")
    quackquack("quack-publishing")
    alias(libs.plugins.kotlin.ksp)
}

ksp {
    arg("autoserviceKsp.verify", "true")
    arg("autoserviceKsp.verbose", "true")
}

dependencies {
    compileOnly(libs.kotlin.embeddable.compiler)
    implementations(
        libs.google.autoservice.annotation,
        libs.kotlin.kotlinpoet.core,
        projects.casaAnnotation.orArtifact(),
        projects.sugarMaterial.orArtifact(),
    )
    ksp(libs.google.autoservice.ksp.processor)
    testImplementations(
        libs.test.strikt,
        libs.test.kotlin.compilation.core,
    )
}
