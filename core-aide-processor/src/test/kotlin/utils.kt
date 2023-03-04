/*
 * Designed and developed by Duckie Team, 2022~2023
 *
 * Licensed under the MIT.
 * Please see full license: https://github.com/duckie-team/duckie-quack-quack/blob/main/LICENSE
 */

import org.intellij.lang.annotations.Language
import strikt.api.Assertion
import strikt.assertions.isEqualTo

fun Assertion.Builder<String>.isKtEqualTo(@Language("kotlin") code: String): Assertion.Builder<String> {
    return isEqualTo(code)
}
