/*
 * Designed and developed by 2022 SungbinLand, Team Duckie
 *
 * Licensed under the MIT.
 * Please see full license: https://github.com/sungbinland/quack-quack/blob/main/LICENSE
 */

BundleInsideHelper.forInsideLintJar(project)

plugins {
    id(PluginEnum.AndroidLint)
    id(PluginEnum.JvmKover)
    id(PluginEnum.JvmDokka)
}

dependencies {
    bundleInsides(
        projects.common,
        projects.commonLint,
        projects.commonLintCompose,
    )
}
