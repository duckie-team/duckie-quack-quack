/*
 * Designed and developed by Duckie Team, 2022~2023
 *
 * Licensed under the MIT.
 * Please see full license: https://github.com/duckie-team/duckie-quack-quack/blob/main/LICENSE
 */

plugins {
    `android-library`
}

GradleInstallation.with(project) {
    library(namespace = "team.duckie.quackquack.test.screenshot.matcher")
}

dependencies {
    implementation(libs.androidx.annotation)
}
