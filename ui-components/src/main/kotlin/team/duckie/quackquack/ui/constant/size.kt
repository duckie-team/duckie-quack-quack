/*
 * Designed and developed by 2022 SungbinLand, Team Duckie
 *
 * Licensed under the MIT.
 * Please see full license: https://github.com/duckie-team/quack-quack-android/blob/master/LICENSE
 */

package team.duckie.quackquack.ui.constant

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import team.duckie.quackquack.ui.modifier.applyQuackSize

/**
 * 컴포넌트의 추상화를 위해 [Modifier] 를 그대로 노출하는게 아닌,
 * Modifier 을 통한 조정이 필요할 것이라고 예상되는 옵션들만
 * 인자로 받을 수 있게 따로 클래스를 만들어서 노출시킵니다.
 *
 * width 와 같은 경우에는 조정이 필요한 옵션이라고 예상되어
 * 이 클래스를 통해 조정할 수 있습니다.
 *
 * @see Modifier.applyQuackSize
 */
@Immutable
public sealed class QuackWidth {
    /**
     * 현재 보이는 화면의 가로 사이즈로 꽉 채웁니다.
     * Modifier.fillMaxWidth() 와 동일합니다.
     *
     * @see Modifier.fillMaxWidth
     */
    @Immutable
    public object Fill : QuackWidth()

    /**
     * 현재 컴포넌트의 가로 사이즈로 꽉 채웁니다.
     * Modifier.wrapContentWidth() 와 동일합니다.
     *
     * @see Modifier.wrapContentWidth
     */
    @Immutable
    public object Wrap : QuackWidth()

    /**
     * width 로 지정할 사이즈를 직접 지정합니다.
     * 주어진 사이즈로 Modifier.width() 를 수행합니다.
     *
     * @see Modifier.width
     *
     * @param width width 로 지정할 사이즈
     */
    @Immutable
    public class Custom(public val width: Dp) : QuackWidth()
}

/**
 * 컴포넌트의 추상화를 위해 [Modifier] 를 그대로 노출하는게 아닌,
 * Modifier 을 통한 조정이 필요할 것이라고 예상되는 옵션들만
 * 인자로 받을 수 있게 따로 클래스를 만들어서 노출시킵니다.
 *
 * height 와 같은 경우에는 조정이 필요한 옵션이라고 예상되어
 * 이 클래스를 통해 조정할 수 있습니다.
 *
 * @see Modifier.applyQuackSize
 */
@Immutable
public sealed class QuackHeight {
    /**
     * 현재 보이는 화면의 세로 사이즈로 꽉 채웁니다.
     * Modifier.fillMaxHeight() 와 동일합니다.
     *
     * @see Modifier.fillMaxHeight
     */
    @Immutable
    public object Fill : QuackHeight()

    /**
     * 현재 컴포넌트의 세로 사이즈로 꽉 채웁니다.
     * Modifier.wrapContentHeight() 와 동일합니다.
     *
     * @see Modifier.wrapContentHeight
     */
    @Immutable
    public object Wrap : QuackHeight()

    /**
     * height 로 지정할 사이즈를 직접 지정합니다.
     * 주어진 사이즈로 Modifier.height() 를 수행합니다.
     *
     * @see Modifier.height
     *
     * @param height height 로 지정할 사이즈
     */
    @Immutable
    public class Custom(public val height: Dp) : QuackHeight()
}
