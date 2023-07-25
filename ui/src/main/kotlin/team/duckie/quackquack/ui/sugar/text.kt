// This file was automatically generated by sugar-core.
// Do not modify it manually.
@file:OptIn(SugarCompilerApi::class, SugarGeneratorUsage::class)
@file:SugarGeneratedFile
@file:Suppress("ktlint", "detekt:all", "ModifierParameter")

package team.duckie.quackquack.ui.sugar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import team.duckie.quackquack.casa.annotation.Casa
import team.duckie.quackquack.casa.annotation.CasaValue
import team.duckie.quackquack.casa.annotation.SugarGeneratorUsage
import team.duckie.quackquack.material.QuackTypography
import team.duckie.quackquack.sugar.material.SugarCompilerApi
import team.duckie.quackquack.sugar.material.SugarGeneratedFile
import team.duckie.quackquack.sugar.material.SugarRefer
import team.duckie.quackquack.sugar.material.sugar
import team.duckie.quackquack.ui.QuackText

/**
 * 텍스트를 그리는 기본적인 컴포저블입니다.
 *
 * This component uses [QuackTypography.Body1] as the token value for `typography`.
 *
 * This document was automatically generated by [QuackText].
 * If any contents are broken or wanna see the entire contents, please check the original document.
 *
 * @param text 그릴 텍스트
 * @param singleLine 텍스트가 한 줄로 그려질 지 여부. 텍스트가 주어진 줄 수를 초과하면
 * [softWrap] 및 [overflow]에 따라 잘립니다.
 * @param softWrap 텍스트에 softwrap break를 적용할지 여부. `false`이면 텍스트 글리프가
 * 가로 공간이 무제한인 것처럼 배치됩니다. 또한 [overflow] 및 [TextAlign]에 예기치 않은
 * 효과가 발생할 수 있습니다.
 * @param overflow 시각적 overflow를 처리하는 방법
 *
 * @sample team.duckie.quackquack.ui.sample.SampleTest
 */
@Casa
@Composable
@NonRestartableComposable
@SugarRefer("team.duckie.quackquack.ui.QuackText")
public fun QuackBody1(
  @CasaValue("\"QuackText\"") text: String,
  modifier: Modifier = sugar(),
  singleLine: Boolean = sugar(),
  softWrap: Boolean = sugar(),
  overflow: TextOverflow = sugar(),
) {
  QuackText(
    text = text,
    typography = QuackTypography.Body1,
    modifier = modifier,
    singleLine = singleLine,
    softWrap = softWrap,
    overflow = overflow,
  )
}

/**
 * 텍스트를 그리는 기본적인 컴포저블입니다.
 *
 * This component uses [QuackTypography.Body2] as the token value for `typography`.
 *
 * This document was automatically generated by [QuackText].
 * If any contents are broken or wanna see the entire contents, please check the original document.
 *
 * @param text 그릴 텍스트
 * @param singleLine 텍스트가 한 줄로 그려질 지 여부. 텍스트가 주어진 줄 수를 초과하면
 * [softWrap] 및 [overflow]에 따라 잘립니다.
 * @param softWrap 텍스트에 softwrap break를 적용할지 여부. `false`이면 텍스트 글리프가
 * 가로 공간이 무제한인 것처럼 배치됩니다. 또한 [overflow] 및 [TextAlign]에 예기치 않은
 * 효과가 발생할 수 있습니다.
 * @param overflow 시각적 overflow를 처리하는 방법
 *
 * @sample team.duckie.quackquack.ui.sample.SampleTest
 */
@Casa
@Composable
@NonRestartableComposable
@SugarRefer("team.duckie.quackquack.ui.QuackText")
public fun QuackBody2(
  @CasaValue("\"QuackText\"") text: String,
  modifier: Modifier = sugar(),
  singleLine: Boolean = sugar(),
  softWrap: Boolean = sugar(),
  overflow: TextOverflow = sugar(),
) {
  QuackText(
    text = text,
    typography = QuackTypography.Body2,
    modifier = modifier,
    singleLine = singleLine,
    softWrap = softWrap,
    overflow = overflow,
  )
}

/**
 * 텍스트를 그리는 기본적인 컴포저블입니다.
 *
 * This component uses [QuackTypography.Body3] as the token value for `typography`.
 *
 * This document was automatically generated by [QuackText].
 * If any contents are broken or wanna see the entire contents, please check the original document.
 *
 * @param text 그릴 텍스트
 * @param singleLine 텍스트가 한 줄로 그려질 지 여부. 텍스트가 주어진 줄 수를 초과하면
 * [softWrap] 및 [overflow]에 따라 잘립니다.
 * @param softWrap 텍스트에 softwrap break를 적용할지 여부. `false`이면 텍스트 글리프가
 * 가로 공간이 무제한인 것처럼 배치됩니다. 또한 [overflow] 및 [TextAlign]에 예기치 않은
 * 효과가 발생할 수 있습니다.
 * @param overflow 시각적 overflow를 처리하는 방법
 *
 * @sample team.duckie.quackquack.ui.sample.SampleTest
 */
@Casa
@Composable
@NonRestartableComposable
@SugarRefer("team.duckie.quackquack.ui.QuackText")
public fun QuackBody3(
  @CasaValue("\"QuackText\"") text: String,
  modifier: Modifier = sugar(),
  singleLine: Boolean = sugar(),
  softWrap: Boolean = sugar(),
  overflow: TextOverflow = sugar(),
) {
  QuackText(
    text = text,
    typography = QuackTypography.Body3,
    modifier = modifier,
    singleLine = singleLine,
    softWrap = softWrap,
    overflow = overflow,
  )
}

/**
 * 텍스트를 그리는 기본적인 컴포저블입니다.
 *
 * This component uses [QuackTypography.HeadLine1] as the token value for `typography`.
 *
 * This document was automatically generated by [QuackText].
 * If any contents are broken or wanna see the entire contents, please check the original document.
 *
 * @param text 그릴 텍스트
 * @param singleLine 텍스트가 한 줄로 그려질 지 여부. 텍스트가 주어진 줄 수를 초과하면
 * [softWrap] 및 [overflow]에 따라 잘립니다.
 * @param softWrap 텍스트에 softwrap break를 적용할지 여부. `false`이면 텍스트 글리프가
 * 가로 공간이 무제한인 것처럼 배치됩니다. 또한 [overflow] 및 [TextAlign]에 예기치 않은
 * 효과가 발생할 수 있습니다.
 * @param overflow 시각적 overflow를 처리하는 방법
 *
 * @sample team.duckie.quackquack.ui.sample.SampleTest
 */
@Casa
@Composable
@NonRestartableComposable
@SugarRefer("team.duckie.quackquack.ui.QuackText")
public fun QuackHeadLine1(
  @CasaValue("\"QuackText\"") text: String,
  modifier: Modifier = sugar(),
  singleLine: Boolean = sugar(),
  softWrap: Boolean = sugar(),
  overflow: TextOverflow = sugar(),
) {
  QuackText(
    text = text,
    typography = QuackTypography.HeadLine1,
    modifier = modifier,
    singleLine = singleLine,
    softWrap = softWrap,
    overflow = overflow,
  )
}

/**
 * 텍스트를 그리는 기본적인 컴포저블입니다.
 *
 * This component uses [QuackTypography.HeadLine2] as the token value for `typography`.
 *
 * This document was automatically generated by [QuackText].
 * If any contents are broken or wanna see the entire contents, please check the original document.
 *
 * @param text 그릴 텍스트
 * @param singleLine 텍스트가 한 줄로 그려질 지 여부. 텍스트가 주어진 줄 수를 초과하면
 * [softWrap] 및 [overflow]에 따라 잘립니다.
 * @param softWrap 텍스트에 softwrap break를 적용할지 여부. `false`이면 텍스트 글리프가
 * 가로 공간이 무제한인 것처럼 배치됩니다. 또한 [overflow] 및 [TextAlign]에 예기치 않은
 * 효과가 발생할 수 있습니다.
 * @param overflow 시각적 overflow를 처리하는 방법
 *
 * @sample team.duckie.quackquack.ui.sample.SampleTest
 */
@Casa
@Composable
@NonRestartableComposable
@SugarRefer("team.duckie.quackquack.ui.QuackText")
public fun QuackHeadLine2(
  @CasaValue("\"QuackText\"") text: String,
  modifier: Modifier = sugar(),
  singleLine: Boolean = sugar(),
  softWrap: Boolean = sugar(),
  overflow: TextOverflow = sugar(),
) {
  QuackText(
    text = text,
    typography = QuackTypography.HeadLine2,
    modifier = modifier,
    singleLine = singleLine,
    softWrap = softWrap,
    overflow = overflow,
  )
}

/**
 * 텍스트를 그리는 기본적인 컴포저블입니다.
 *
 * This component uses [QuackTypography.Large1] as the token value for `typography`.
 *
 * This document was automatically generated by [QuackText].
 * If any contents are broken or wanna see the entire contents, please check the original document.
 *
 * @param text 그릴 텍스트
 * @param singleLine 텍스트가 한 줄로 그려질 지 여부. 텍스트가 주어진 줄 수를 초과하면
 * [softWrap] 및 [overflow]에 따라 잘립니다.
 * @param softWrap 텍스트에 softwrap break를 적용할지 여부. `false`이면 텍스트 글리프가
 * 가로 공간이 무제한인 것처럼 배치됩니다. 또한 [overflow] 및 [TextAlign]에 예기치 않은
 * 효과가 발생할 수 있습니다.
 * @param overflow 시각적 overflow를 처리하는 방법
 *
 * @sample team.duckie.quackquack.ui.sample.SampleTest
 */
@Casa
@Composable
@NonRestartableComposable
@SugarRefer("team.duckie.quackquack.ui.QuackText")
public fun QuackLarge1(
  @CasaValue("\"QuackText\"") text: String,
  modifier: Modifier = sugar(),
  singleLine: Boolean = sugar(),
  softWrap: Boolean = sugar(),
  overflow: TextOverflow = sugar(),
) {
  QuackText(
    text = text,
    typography = QuackTypography.Large1,
    modifier = modifier,
    singleLine = singleLine,
    softWrap = softWrap,
    overflow = overflow,
  )
}

/**
 * 텍스트를 그리는 기본적인 컴포저블입니다.
 *
 * This component uses [QuackTypography.Quote] as the token value for `typography`.
 *
 * This document was automatically generated by [QuackText].
 * If any contents are broken or wanna see the entire contents, please check the original document.
 *
 * @param text 그릴 텍스트
 * @param singleLine 텍스트가 한 줄로 그려질 지 여부. 텍스트가 주어진 줄 수를 초과하면
 * [softWrap] 및 [overflow]에 따라 잘립니다.
 * @param softWrap 텍스트에 softwrap break를 적용할지 여부. `false`이면 텍스트 글리프가
 * 가로 공간이 무제한인 것처럼 배치됩니다. 또한 [overflow] 및 [TextAlign]에 예기치 않은
 * 효과가 발생할 수 있습니다.
 * @param overflow 시각적 overflow를 처리하는 방법
 *
 * @sample team.duckie.quackquack.ui.sample.SampleTest
 */
@Casa
@Composable
@NonRestartableComposable
@SugarRefer("team.duckie.quackquack.ui.QuackText")
public fun QuackQuote(
  @CasaValue("\"QuackText\"") text: String,
  modifier: Modifier = sugar(),
  singleLine: Boolean = sugar(),
  softWrap: Boolean = sugar(),
  overflow: TextOverflow = sugar(),
) {
  QuackText(
    text = text,
    typography = QuackTypography.Quote,
    modifier = modifier,
    singleLine = singleLine,
    softWrap = softWrap,
    overflow = overflow,
  )
}

/**
 * 텍스트를 그리는 기본적인 컴포저블입니다.
 *
 * This component uses [QuackTypography.Subtitle] as the token value for `typography`.
 *
 * This document was automatically generated by [QuackText].
 * If any contents are broken or wanna see the entire contents, please check the original document.
 *
 * @param text 그릴 텍스트
 * @param singleLine 텍스트가 한 줄로 그려질 지 여부. 텍스트가 주어진 줄 수를 초과하면
 * [softWrap] 및 [overflow]에 따라 잘립니다.
 * @param softWrap 텍스트에 softwrap break를 적용할지 여부. `false`이면 텍스트 글리프가
 * 가로 공간이 무제한인 것처럼 배치됩니다. 또한 [overflow] 및 [TextAlign]에 예기치 않은
 * 효과가 발생할 수 있습니다.
 * @param overflow 시각적 overflow를 처리하는 방법
 *
 * @sample team.duckie.quackquack.ui.sample.SampleTest
 */
@Casa
@Composable
@NonRestartableComposable
@SugarRefer("team.duckie.quackquack.ui.QuackText")
public fun QuackSubtitle(
  @CasaValue("\"QuackText\"") text: String,
  modifier: Modifier = sugar(),
  singleLine: Boolean = sugar(),
  softWrap: Boolean = sugar(),
  overflow: TextOverflow = sugar(),
) {
  QuackText(
    text = text,
    typography = QuackTypography.Subtitle,
    modifier = modifier,
    singleLine = singleLine,
    softWrap = softWrap,
    overflow = overflow,
  )
}

/**
 * 텍스트를 그리는 기본적인 컴포저블입니다.
 *
 * This component uses [QuackTypography.Subtitle2] as the token value for `typography`.
 *
 * This document was automatically generated by [QuackText].
 * If any contents are broken or wanna see the entire contents, please check the original document.
 *
 * @param text 그릴 텍스트
 * @param singleLine 텍스트가 한 줄로 그려질 지 여부. 텍스트가 주어진 줄 수를 초과하면
 * [softWrap] 및 [overflow]에 따라 잘립니다.
 * @param softWrap 텍스트에 softwrap break를 적용할지 여부. `false`이면 텍스트 글리프가
 * 가로 공간이 무제한인 것처럼 배치됩니다. 또한 [overflow] 및 [TextAlign]에 예기치 않은
 * 효과가 발생할 수 있습니다.
 * @param overflow 시각적 overflow를 처리하는 방법
 *
 * @sample team.duckie.quackquack.ui.sample.SampleTest
 */
@Casa
@Composable
@NonRestartableComposable
@SugarRefer("team.duckie.quackquack.ui.QuackText")
public fun QuackSubtitle2(
  @CasaValue("\"QuackText\"") text: String,
  modifier: Modifier = sugar(),
  singleLine: Boolean = sugar(),
  softWrap: Boolean = sugar(),
  overflow: TextOverflow = sugar(),
) {
  QuackText(
    text = text,
    typography = QuackTypography.Subtitle2,
    modifier = modifier,
    singleLine = singleLine,
    softWrap = softWrap,
    overflow = overflow,
  )
}

/**
 * 텍스트를 그리는 기본적인 컴포저블입니다.
 *
 * This component uses [QuackTypography.Title1] as the token value for `typography`.
 *
 * This document was automatically generated by [QuackText].
 * If any contents are broken or wanna see the entire contents, please check the original document.
 *
 * @param text 그릴 텍스트
 * @param singleLine 텍스트가 한 줄로 그려질 지 여부. 텍스트가 주어진 줄 수를 초과하면
 * [softWrap] 및 [overflow]에 따라 잘립니다.
 * @param softWrap 텍스트에 softwrap break를 적용할지 여부. `false`이면 텍스트 글리프가
 * 가로 공간이 무제한인 것처럼 배치됩니다. 또한 [overflow] 및 [TextAlign]에 예기치 않은
 * 효과가 발생할 수 있습니다.
 * @param overflow 시각적 overflow를 처리하는 방법
 *
 * @sample team.duckie.quackquack.ui.sample.SampleTest
 */
@Casa
@Composable
@NonRestartableComposable
@SugarRefer("team.duckie.quackquack.ui.QuackText")
public fun QuackTitle1(
  @CasaValue("\"QuackText\"") text: String,
  modifier: Modifier = sugar(),
  singleLine: Boolean = sugar(),
  softWrap: Boolean = sugar(),
  overflow: TextOverflow = sugar(),
) {
  QuackText(
    text = text,
    typography = QuackTypography.Title1,
    modifier = modifier,
    singleLine = singleLine,
    softWrap = softWrap,
    overflow = overflow,
  )
}

/**
 * 텍스트를 그리는 기본적인 컴포저블입니다.
 *
 * This component uses [QuackTypography.Title2] as the token value for `typography`.
 *
 * This document was automatically generated by [QuackText].
 * If any contents are broken or wanna see the entire contents, please check the original document.
 *
 * @param text 그릴 텍스트
 * @param singleLine 텍스트가 한 줄로 그려질 지 여부. 텍스트가 주어진 줄 수를 초과하면
 * [softWrap] 및 [overflow]에 따라 잘립니다.
 * @param softWrap 텍스트에 softwrap break를 적용할지 여부. `false`이면 텍스트 글리프가
 * 가로 공간이 무제한인 것처럼 배치됩니다. 또한 [overflow] 및 [TextAlign]에 예기치 않은
 * 효과가 발생할 수 있습니다.
 * @param overflow 시각적 overflow를 처리하는 방법
 *
 * @sample team.duckie.quackquack.ui.sample.SampleTest
 */
@Casa
@Composable
@NonRestartableComposable
@SugarRefer("team.duckie.quackquack.ui.QuackText")
public fun QuackTitle2(
  @CasaValue("\"QuackText\"") text: String,
  modifier: Modifier = sugar(),
  singleLine: Boolean = sugar(),
  softWrap: Boolean = sugar(),
  overflow: TextOverflow = sugar(),
) {
  QuackText(
    text = text,
    typography = QuackTypography.Title2,
    modifier = modifier,
    singleLine = singleLine,
    softWrap = softWrap,
    overflow = overflow,
  )
}