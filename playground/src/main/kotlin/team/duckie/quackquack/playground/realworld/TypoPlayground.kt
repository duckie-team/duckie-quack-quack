/*
 * Designed and developed by 2022 SungbinLand, Team Duckie
 *
 * Licensed under the MIT.
 * Please see full license: https://github.com/sungbinland/quack-quack/blob/main/LICENSE
 */

package team.duckie.quackquack.playground.realworld

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.persistentListOf
import team.duckie.quackquack.playground.base.BaseActivity
import team.duckie.quackquack.playground.base.PlaygroundSection
import team.duckie.quackquack.playground.theme.PlaygroundTheme
import team.duckie.quackquack.ui.color.QuackColor
import team.duckie.quackquack.ui.component.QuackAnnotatedBody2
import team.duckie.quackquack.ui.component.QuackHighlightBody1
import team.duckie.quackquack.ui.component.QuackUnderlineBody3
import team.duckie.quackquack.ui.component.QuackUnderlineHeadLine2

class TypoPlayground : BaseActivity() {
    @Suppress("RemoveExplicitTypeArguments")
    private val items = persistentListOf<Pair<String, @Composable () -> Unit>>(
        "QuackHighlightText" to { QuackHighlightTextDemo() }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PlaygroundTheme {
                PlaygroundSection(
                    title = "QuackTypo",
                    items = items,
                )
            }
        }
    }
}

@Composable
fun QuackHighlightTextDemo() {
    val text = "우주사령관님과 거래를 하셨군요!"

    Column(
        verticalArrangement = Arrangement.spacedBy(
            space = 10.dp,
        ),
    ) {
        QuackUnderlineHeadLine2(
            text = text,
            underlineText = persistentListOf("우주사령관"),
            onClick = {},
        )
        QuackUnderlineBody3(
            text = "가입 시, 덕키의 필수 이용 약관과\n개인정보 수집 및 이용에 동의하게 됩니다.",
            underlineText = persistentListOf(
                "필수 이용 약관",
                "개인정보 수집 및 이용",
            ),
            color = QuackColor.Gray2,
            undelineTextColor = QuackColor.Gray2,
            align = TextAlign.Center,
        )
        QuackHighlightBody1(
            text = "가입 시, 덕키의 필수 이용 약관과\n개인정보 수집 및 이용에 동의하게 됩니다.",
            highlightText = persistentListOf(
                "필수 이용 약관",
                "개인정보 수집 및 이용",
            ),
            align = TextAlign.Center,
        )
        QuackAnnotatedBody2(
            text = "노랑인간님이 내 글에 새 댓글을 달았어요, real 노랑인간",
            highlightTextPairs = persistentListOf(
                "노랑인간" to null,
                "새 댓글" to { Log.i("QuackAnnotatedBody2", "새 댓글"); },
                "노랑인간" to { Log.i("QuackAnnotatedBody2", "real 노랑인간"); },
            ),
            highlightFontWeight = FontWeight.Bold,
            align = TextAlign.Center,
            onClick = { Log.i("QuackAnnotatedBody2", "전체클릭 이벤트"); },
        )
    }
}
