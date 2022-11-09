/*
 * Designed and developed by 2022 SungbinLand, Team Duckie
 *
 * Licensed under the MIT.
 * Please see full license: https://github.com/duckie-team/quack-quack-android/blob/master/LICENSE
 */

@file:Suppress(
    "JUnitMalformedDeclaration",
    "TestFunctionName",
)

package team.duckie.quackquack.ui.snapshot

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import com.google.testing.junit.testparameterinjector.TestParameter
import com.google.testing.junit.testparameterinjector.TestParameterInjector
import kotlinx.collections.immutable.persistentListOf
import org.junit.Ignore
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import team.duckie.quackquack.ui.icon.QuackIcon
import team.duckie.quackquack.ui.snapshot.provider.QuackDeviceConfig
import team.duckie.quackquack.ui.snapshot.provider.QuackIconProvider
import team.duckie.quackquack.ui.snapshot.rule.AnimationTestRule
import team.duckie.quackquack.ui.snapshot.util.DeviceOption
import team.duckie.quackquack.ui.snapshot.util.boxSnapshot
import team.duckie.quackquack.ui.snapshot.util.paparazzi
import team.duckie.quackquack.ui.snapshot.wrapper.NamedValue

@RunWith(TestParameterInjector::class)
class QuackTag {
    private val deviceConfigId = "QuackTag"

    @get:Rule
    val paparazzi = paparazzi(
        deviceOption = DeviceOption(
            screenWidth = 400,
            matchFullWidth = false,
        ),
        configId = deviceConfigId,
    )

    @get:Rule
    val animationTest = AnimationTestRule()

    @Suppress(
        "NotConstructor", // https://github.com/duckie-team/duckie-quack-quack/issues/108
        "MemberNameEqualsClassName",
    )
    @Test
    fun QuackTag(
        @TestParameter isSelected: Boolean,
        @TestParameter("1.0", "1.5") fontScale: Float,
        @TestParameter deviceConfig: QuackDeviceConfig,
    ) {
        paparazzi.boxSnapshot(
            parameterNames = listOf(
                "isSelected",
                "fontScale",
                "deviceConfig",
            ),
            fontScale = fontScale,
            configId = deviceConfigId,
            deviceConfig = deviceConfig,
        ) {
            team.duckie.quackquack.ui.component.QuackTag(
                text = "QuackTag",
                isSelected = isSelected,
            )
        }
    }

    @Test
    fun QuackGrayscaleTag(
        @TestParameter("99+", "") trailingText: String,
        @TestParameter("1.0", "1.5") fontScale: Float,
        @TestParameter deviceConfig: QuackDeviceConfig,
    ) {
        paparazzi.boxSnapshot(
            parameterNames = listOf(
                "trailingText",
                "fontScale",
                "deviceConfig",
            ),
            fontScale = fontScale,
            configId = deviceConfigId,
            deviceConfig = deviceConfig,
        ) {
            team.duckie.quackquack.ui.component.QuackGrayscaleTag(
                text = "QuackTag",
                trailingText = trailingText,
            )
        }
    }

    @Test
    fun QuackIconTag(
        @TestParameter isSelected: Boolean,
        @TestParameter(valuesProvider = QuackIconProvider::class) icon: NamedValue<QuackIcon>,
        @TestParameter("1.0", "1.5") fontScale: Float,
        @TestParameter deviceConfig: QuackDeviceConfig,
    ) {
        paparazzi.boxSnapshot(
            parameterNames = listOf(
                "isSelected",
                "icon",
                "fontScale",
                "deviceConfig",
            ),
            fontScale = fontScale,
            configId = deviceConfigId,
            deviceConfig = deviceConfig,
        ) {
            team.duckie.quackquack.ui.component.QuackIconTag(
                text = "QuackTag",
                icon = icon.value,
                isSelected = isSelected,
            )
        }
    }

    @Ignore(
        value = "Needs Redesign",
    )
    @Test
    fun QuackRowTag(
        @TestParameter("1.0", "1.5") fontScale: Float,
        @TestParameter deviceConfig: QuackDeviceConfig,
    ) {
        paparazzi.boxSnapshot(
            parameterNames = listOf(
                "fontScale",
                "deviceConfig",
            ),
            fontScale = fontScale,
            deviceConfig = deviceConfig,
        ) {
            val items = remember {
                persistentListOf(
                    "친절하고 매너가 좋아요",
                    "답장이 빨라요",
                    "입금을 제때 해줘요",
                    "답장이 느려요",
                    "너무 늦은 시간에 연락해요",
                    "무리하게 가격을 깎아요",
                )
            }
            val itemsSelection = remember {
                mutableStateListOf(
                    elements = Array(
                        size = items.size,
                        init = { index ->
                            listOf(true, false)[index / 2]
                        },
                    )
                )
            }

            team.duckie.quackquack.ui.component.QuackRowTag(
                title = "이런 점이 최고였어요",
                items = items,
                itemsSelection = itemsSelection,
                onClick = {},
            )
        }
    }
}

