/*
 * Designed and developed by 2022 SungbinLand, Team Duckie
 *
 * Licensed under the MIT.
 * Please see full license: https://github.com/duckie-team/duckie-quack-quack/blob/main/LICENSE
 */

object ConventionEnum {
    private const val prefix = "quack"

    const val AndroidQuackPublish = "$prefix.android.publish"
    const val AndroidLint = "$prefix.android.lint"
    const val AndroidCommonLintPlugin = "$prefix.android.common.lint"
    const val AndroidApplication = "$prefix.android.application"
    const val AndroidApplicationCompose = "$prefix.android.application.compose"
    const val AndroidLibrary = "$prefix.android.library"
    const val AndroidLibraryCompose = "$prefix.android.library.compose"
    const val AndroidLibraryComposeUiTest = "$prefix.android.library.compose.uitest"

    const val AndroidQuackUiComponentsBenchmark = "$prefix.android.quack.ui.components.benchmark"

    const val JvmJUnit4 = "$prefix.jvm.junit4"
    const val JvmLibrary = "$prefix.jvm.library"
    const val JvmDokka = "$prefix.jvm.dokka"
}
