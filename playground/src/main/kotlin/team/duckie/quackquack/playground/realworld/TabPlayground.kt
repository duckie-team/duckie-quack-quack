/*
 * Designed and developed by 2022 SungbinLand, Team Duckie
 *
 * Licensed under the MIT.
 * Please see full license: https://github.com/duckie-team/quack-quack-android/blob/master/LICENSE
 */

package team.duckie.quackquack.playground.realworld

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import kotlinx.collections.immutable.persistentListOf
import team.duckie.quackquack.playground.base.BaseActivity
import team.duckie.quackquack.playground.base.PlaygroundSection
import team.duckie.quackquack.playground.theme.PlaygroundTheme
import team.duckie.quackquack.ui.component.QuackMainTab
import team.duckie.quackquack.ui.component.QuackSubTab

class TabPlayground : BaseActivity() {
    @Suppress("RemoveExplicitTypeArguments")
    private val items = persistentListOf<Pair<String, @Composable () -> Unit>>(
        "QuackMainTab" to { QuackMainTabDemo() },
        "QuackSubTabDemo" to { QuackSubTabDemo() },
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PlaygroundTheme {
                PlaygroundSection(
                    title = "Tab",
                    items = items,
                )
            }
        }
    }
}

@Composable
fun QuackMainTabDemo() {
    var selectedMainTabIndex by remember { mutableStateOf(0) }

    QuackMainTab(
        titles = persistentListOf(
            "판매중",
            "거래완료",
            "숨김",
        ),
        selectedTabIndex = selectedMainTabIndex,
        onTabSelected = { tabIndex ->
            selectedMainTabIndex = tabIndex
        },
    )
}

@Composable
fun QuackSubTabDemo() {
    var selectedMainTabIndex by remember { mutableStateOf(0) }

    QuackSubTab(
        titles = persistentListOf(
            "피드",
            "컬렉션",
            "좋아요",
        ),
        selectedTabIndex = selectedMainTabIndex,
        onTabSelected = { tabIndex ->
            selectedMainTabIndex = tabIndex
        },
    )
}
