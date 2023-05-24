/*
 * Designed and developed by Duckie Team 2023.
 *
 * Licensed under the MIT.
 * Please see full license: https://github.com/duckie-team/quack-quack-android/blob/main/LICENSE
 */

package team.duckie.quackquack.ui.util

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Stable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import team.duckie.quackquack.material.QuackPadding

private object AssertionFail {
  const val NotAllowZeroOrNegativePadding = "Negative numbers or zeros are not allowed as padding values."
}

@Stable
public fun QuackPadding.assertOnlyPositiveNotZero() {
  require(
    horizontal > Dp.Hairline && vertical > Dp.Hairline,
    lazyMessage = AssertionFail::NotAllowZeroOrNegativePadding,
  )
}

@Stable
public fun PaddingValues.assertOnlyPositiveNotZero() {
  require(
    calculateTopPadding() > Dp.Hairline &&
        calculateBottomPadding() > Dp.Hairline &&
        calculateLeftPadding(LayoutDirection.Ltr) > Dp.Hairline &&
        calculateRightPadding(LayoutDirection.Ltr) > Dp.Hairline,
    lazyMessage = AssertionFail::NotAllowZeroOrNegativePadding,
  )
}
