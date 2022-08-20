/*
 * Designed and developed by 2022 SungbinLand, Team Duckie
 *
 * [build.gradle.kts] created by Ji Sungbin on 22. 8. 14. 오전 1:00
 *
 * Licensed under the MIT.
 * Please see full license: https://github.com/sungbinland/quack-quack/blob/main/LICENSE
 */

@file:Suppress("UnstableApiUsage")

plugins {
    id(PluginEnum.AndroidLibrary)
    id(PluginEnum.AndroidLibraryCompose)
    id(PluginEnum.AndroidLibraryComposeUiTest)
    id(PluginEnum.ModuleJacoco)
    id(PluginEnum.JvmDokka)
}

android {
    namespace = "team.duckie.quackquack.ui"
}

dependencies {
    implementations(
        projects.common,
        libs.util.profileinstaller,
        libs.compose.material,
        libs.compose.glide,
    )
}
