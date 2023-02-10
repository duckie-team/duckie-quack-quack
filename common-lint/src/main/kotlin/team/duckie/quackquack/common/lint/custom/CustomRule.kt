/*
 * Designed and developed by Duckie Team, 2022
 *
 * Licensed under the MIT.
 * Please see full license: https://github.com/duckie-team/quack-quack-android/blob/master/LICENSE
 */

/*
 * Designed and developed by Duckie Team, 2022
 *
 * Licensed under the MIT.
 * Please see full license: https://github.com/duckie-team/quack-quack-android/blob/master/LICENSE
 */

package team.duckie.quackquack.common.lint.custom

import java.io.File
import java.io.IOException
import java.nio.file.Paths
import kotlin.io.path.absolutePathString

internal object CustomRule {
    private val rootPath = Paths.get("").absolutePathString()
    private val defaultPath = "$rootPath/quack-lint-custom-rule"

    @Suppress("UNCHECKED_CAST")
    private fun getAllowList(
        ruleName: String,
    ) = File(defaultPath, "Custom_$ruleName.txt")
        .readText()
        .split("\n")

    val Modifier = try {
        getAllowList("Modifier")
    } catch (ignored: IOException) {
        println("사용자 추가 Modifier 를 찾지 못했습니다. 기본값을 그대로 사용합니다.")
        emptyList()
    }

    val Collection = try {
        getAllowList("Collection")
    } catch (ignored: IOException) {
        println("사용자 추가 MutableCollection 을 찾지 못했습니다. 기본값을 그대로 사용합니다.")
        emptyList()
    }

    val ImmutableCollection = try {
        getAllowList("ImmutableCollection").also {
            println("사용자 추가 ImmutableListWrapper: $it")
        }
    } catch (ignored: IOException) {
        println("사용자 추가 ImmutableCollection 을 찾지 못했습니다. 기본값을 그대로 사용합니다.")
        println(ignored.message)
        emptyList()
    }
}
