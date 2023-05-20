/*
 * Designed and developed by Duckie Team 2023.
 *
 * Licensed under the MIT.
 * Please see full license: https://github.com/duckie-team/quack-quack-android/blob/main/LICENSE
 */

package team.duckie.quackquack.ui

import android.annotation.SuppressLint
import androidx.annotation.IntRange
import androidx.annotation.RestrictTo
import androidx.compose.foundation.interaction.FocusInteraction
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.currentComposer
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.layout.LayoutModifier
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.platform.inspectable
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import team.duckie.quackquack.casa.annotation.CasaValue
import team.duckie.quackquack.material.QuackColor
import team.duckie.quackquack.material.QuackIcon
import team.duckie.quackquack.material.QuackTypography
import team.duckie.quackquack.material.quackSurface
import team.duckie.quackquack.material.theme.LocalQuackTextFieldTheme
import team.duckie.quackquack.runtime.QuackDataModifierModel
import team.duckie.quackquack.runtime.quackMaterializeOf
import team.duckie.quackquack.sugar.material.NoSugar
import team.duckie.quackquack.ui.optin.ExperimentalDesignToken
import team.duckie.quackquack.ui.token.HorizontalDirection
import team.duckie.quackquack.ui.token.IconRole
import team.duckie.quackquack.ui.token.VerticalDirection
import team.duckie.quackquack.ui.util.ExperimentalQuackQuackApi
import team.duckie.quackquack.ui.util.QuackDsl
import team.duckie.quackquack.ui.util.wrappedDebugInspectable
import team.duckie.quackquack.util.fastFilterIsInstanceOrNull
import team.duckie.quackquack.util.fastFirstIsInstanceOrNull

@Immutable
public enum class TextFieldValidationState {
  Error, Success, Default,
}

@Immutable
public enum class PlaceholderStrategy {
  Always, Hidable,
}

@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
@Stable
public data class TextFieldIconData(
  val icon: QuackIcon,
  val iconSize: Dp,
  val tint: QuackColor?,
  val tintGetter: ((text: String) -> QuackColor)?,
  val role: IconRole,
  val direction: HorizontalDirection,
  val onClick: ((text: String) -> Unit)?,
) : QuackDataModifierModel

@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
@Stable
public data class TextFieldIndicatorData(
  val thickness: Dp,
  val color: QuackColor?,
  val colorGetter: ((text: String) -> QuackColor)?,
  val direction: VerticalDirection,
) : QuackDataModifierModel

@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
@Stable
public data class TextFieldCountableData(
  val baseColor: QuackColor,
  val highlightColor: QuackColor,
  val typography: QuackTypography,
  val bodySpacedBy: Dp,
  @IntRange(from = 1) val maxCount: Int,
) : QuackDataModifierModel

@QuackDsl
@Immutable
public interface TextFieldStyleMarker

@Immutable
public interface TextFieldColorMarker

public interface QuackDefaultTextFieldStyle : TextFieldStyleMarker {
  public data class TextFieldColors internal constructor(
    internal val backgroundColor: QuackColor,
    internal val contentColor: QuackColor,
    internal val placeholderColor: QuackColor,
    internal val errorColor: QuackColor,
    internal val successColor: QuackColor,
  ) : TextFieldColorMarker

  public val validationTypography: QuackTypography

  @Stable
  public fun textFieldColors(
    backgroundColor: QuackColor,
    contentColor: QuackColor,
    placeholderColor: QuackColor,
    errorColor: QuackColor,
    successColor: QuackColor,
  ): TextFieldColors =
    TextFieldColors(
      backgroundColor = backgroundColor,
      contentColor = contentColor,
      placeholderColor = placeholderColor,
      errorColor = errorColor,
      successColor = successColor,
    )
}

public interface QuackFilledTextFieldStyle : TextFieldStyleMarker {
  public data class TextFieldColors internal constructor(
    internal val backgroundColor: QuackColor?,
    internal val backgroundColorGetter: ((interaction: Interaction, text: String) -> QuackColor)?,
    internal val contentColor: QuackColor,
    internal val placeholderColor: QuackColor,
  ) : TextFieldColorMarker

  public val radius: Dp

  @Stable
  public fun textFieldColors(
    backgroundColor: QuackColor?,
    backgroundColorGetter: ((interaction: Interaction, text: String) -> QuackColor)?,
    contentColor: QuackColor,
    placeholderColor: QuackColor,
  ): TextFieldColors =
    TextFieldColors(
      backgroundColor = backgroundColor,
      backgroundColorGetter = backgroundColorGetter,
      contentColor = contentColor,
      placeholderColor = placeholderColor,
    )
}

// TODO(1): 이 컴포넌트가 사용되는 디자인은 아직 개발 범위가 아니므로 TODO 처리
public interface QuackOutlinedTextFieldStyle : TextFieldStyleMarker

@ExperimentalDesignToken
@Immutable
public interface QuackTextFieldStyle<
  StyleMarker : TextFieldStyleMarker, ColorMarker : TextFieldColorMarker,
  > {
  /** 사용할 색상들 */
  public val colors: ColorMarker

  /** 컨텐츠 주변에 들어갈 패딩 */
  public val contentPadding: PaddingValues

  /** 배치되는 아이콘과 텍스트 사이의 간격 */
  public val contentSpacedBy: Dp

  /** 활성화 상태에서 표시될 텍스트의 타이포그래피 */
  public val typography: QuackTypography

  /** 디자인 스펙을 변경하는 람다 */
  @Stable
  public operator fun invoke(styleBuilder: StyleMarker.() -> Unit): StyleMarker

  @Stable
  public fun Modifier.wrappedDebugInspectable(): Modifier

  // TODO(3): 싱글톤으로 관리해도 되지 않을까?
  public companion object {
    @Stable
    public val Default: QuackTextFieldStyle<
      QuackDefaultTextFieldDefaults, QuackDefaultTextFieldStyle.TextFieldColors,
      >
      get() = QuackDefaultTextFieldDefaults()

    @Stable
    public val DefaultLarge: QuackTextFieldStyle<
      QuackDefaultLargeTextFieldDefaults, QuackDefaultTextFieldStyle.TextFieldColors,
      >
      get() = QuackDefaultLargeTextFieldDefaults()

    @Stable
    public val FilledLarge: QuackTextFieldStyle<
      QuackFilledLargeTextFieldDefaults, QuackFilledTextFieldStyle.TextFieldColors,
      >
      get() = QuackFilledLargeTextFieldDefaults()

    @Stable
    public val FilledFlat: QuackTextFieldStyle<
      QuackFilledFlatTextFieldDefaults, QuackFilledTextFieldStyle.TextFieldColors,
      >
      get() = QuackFilledFlatTextFieldDefaults()
  }
}

@ExperimentalDesignToken
public class QuackDefaultTextFieldDefaults internal constructor() :
  QuackTextFieldStyle<
    QuackDefaultTextFieldDefaults, QuackDefaultTextFieldStyle.TextFieldColors,
    >, QuackDefaultTextFieldStyle {

  override var colors: QuackDefaultTextFieldStyle.TextFieldColors =
    textFieldColors(
      backgroundColor = QuackColor.White,
      contentColor = QuackColor.Black,
      placeholderColor = QuackColor.Gray2,
      errorColor = QuackColor.Alert,
      successColor = QuackColor.Success,
    )

  override var contentPadding: PaddingValues =
    PaddingValues(
      top = 16.dp,
      bottom = 8.dp,
    )

  override var contentSpacedBy: Dp = 8.dp

  override var typography: QuackTypography = QuackTypography.Body1
  override val validationTypography: QuackTypography = QuackTypography.Body1

  override fun invoke(styleBuilder: QuackDefaultTextFieldDefaults.() -> Unit): QuackDefaultTextFieldDefaults =
    apply(styleBuilder)

  override fun Modifier.wrappedDebugInspectable(): Modifier =
    wrappedDebugInspectable {
      name = toString()
      properties["colors"] = colors
      properties["contentPadding"] = contentPadding
      properties["contentSpacedBy"] = contentSpacedBy
      properties["typography"] = typography
    }

  override fun toString(): String = this::class.java.simpleName
}

@ExperimentalDesignToken
public class QuackDefaultLargeTextFieldDefaults :
  QuackTextFieldStyle<
    QuackDefaultLargeTextFieldDefaults, QuackDefaultTextFieldStyle.TextFieldColors,
    >, QuackDefaultTextFieldStyle {

  override var colors: QuackDefaultTextFieldStyle.TextFieldColors =
    textFieldColors(
      backgroundColor = QuackColor.White,
      contentColor = QuackColor.Black,
      placeholderColor = QuackColor.Gray2,
      errorColor = QuackColor.Alert,
      successColor = QuackColor.Success,
    )

  override var contentPadding: PaddingValues = PaddingValues(all = 16.dp)

  override var contentSpacedBy: Dp = 8.dp

  override var typography: QuackTypography = QuackTypography.Body1
  override val validationTypography: QuackTypography = QuackTypography.Body1

  override fun invoke(styleBuilder: QuackDefaultLargeTextFieldDefaults.() -> Unit): QuackDefaultLargeTextFieldDefaults =
    apply(styleBuilder)

  override fun Modifier.wrappedDebugInspectable(): Modifier =
    wrappedDebugInspectable {
      name = toString()
      properties["colors"] = colors
      properties["contentPadding"] = contentPadding
      properties["contentSpacedBy"] = contentSpacedBy
      properties["typography"] = typography
    }

  override fun toString(): String = this::class.java.simpleName
}

@ExperimentalDesignToken
public class QuackFilledLargeTextFieldDefaults :
  QuackTextFieldStyle<
    QuackFilledLargeTextFieldDefaults,
    QuackFilledTextFieldStyle.TextFieldColors,
    >,
  QuackFilledTextFieldStyle {

  override var radius: Dp = 8.dp

  override var colors: QuackFilledTextFieldStyle.TextFieldColors =
    textFieldColors(
      backgroundColor = QuackColor.Gray4,
      backgroundColorGetter = null,
      contentColor = QuackColor.Black,
      placeholderColor = QuackColor.Gray2,
    )

  override var contentPadding: PaddingValues =
    PaddingValues(
      horizontal = 20.dp,
      vertical = 17.dp,
    )

  override var contentSpacedBy: Dp = 10.dp

  // TODO(3): 폰트 확인
  override var typography: QuackTypography = QuackTypography.Subtitle

  override fun invoke(styleBuilder: QuackFilledLargeTextFieldDefaults.() -> Unit): QuackFilledLargeTextFieldDefaults =
    apply(styleBuilder)

  override fun Modifier.wrappedDebugInspectable(): Modifier =
    wrappedDebugInspectable {
      name = toString()
      properties["radius"] = radius
      properties["colors"] = colors
      properties["contentPadding"] = contentPadding
      properties["contentSpacedBy"] = contentSpacedBy
      properties["typography"] = typography
    }

  override fun toString(): String = this::class.java.simpleName
}

@ExperimentalDesignToken
public class QuackFilledFlatTextFieldDefaults :
  QuackTextFieldStyle<
    QuackFilledFlatTextFieldDefaults,
    QuackFilledTextFieldStyle.TextFieldColors,
    >,
  QuackFilledTextFieldStyle {

  override var radius: Dp = 8.dp

  override var colors: QuackFilledTextFieldStyle.TextFieldColors =
    textFieldColors(
      backgroundColor = null,
      backgroundColorGetter = { interaction, text ->
        if (text.isNotEmpty()) QuackColor.White
        else when (interaction) {
          is FocusInteraction.Focus -> QuackColor.White
          else -> QuackColor.Gray4
        }
      },
      contentColor = QuackColor.Black,
      placeholderColor = QuackColor.Gray2,
    )

  override var contentPadding: PaddingValues =
    PaddingValues(
      horizontal = 12.dp,
      vertical = 8.dp,
    )

  override var contentSpacedBy: Dp = 10.dp

  override var typography: QuackTypography = QuackTypography.Body1

  override fun invoke(styleBuilder: QuackFilledFlatTextFieldDefaults.() -> Unit): QuackFilledFlatTextFieldDefaults =
    apply(styleBuilder)

  override fun Modifier.wrappedDebugInspectable(): Modifier =
    wrappedDebugInspectable {
      name = toString()
      properties["radius"] = radius
      properties["colors"] = colors
      properties["contentPadding"] = contentPadding
      properties["contentSpacedBy"] = contentSpacedBy
      properties["typography"] = typography
    }

  override fun toString(): String = this::class.java.simpleName
}

@Stable
public fun Modifier.showIcon(
  icon: QuackIcon,
  iconSize: Dp,
  tint: QuackColor?,
  tintGetter: ((text: String) -> QuackColor)?,
  role: IconRole,
  direction: HorizontalDirection,
  onClick: ((text: String) -> Unit)?,
): Modifier =
  inspectable(
    inspectorInfo = debugInspectorInfo {
      name = "showIcon"
      properties["icon"] = icon
      properties["iconSize"] = iconSize
      properties["tint"] = tint
      properties["tintGetter"] = tintGetter
      properties["role"] = role
      properties["direction"] = direction
      properties["onClick"] = onClick
    },
  ) {
    TextFieldIconData(
      icon = icon,
      iconSize = iconSize,
      tint = tint,
      tintGetter = tintGetter,
      role = role,
      direction = direction,
      onClick = onClick,
    )
  }

@Suppress("ModifierFactoryExtensionFunction")
@Stable
public fun TextFieldIndicatorData.toDrawModifier(text: String): Modifier =
  Modifier.drawWithCache {
    val currentBorderColor = (colorGetter?.invoke(text) ?: color)?.value
    val startOffset = when (direction) {
      VerticalDirection.Top -> Offset(x = 0f, y = 0f)
      VerticalDirection.Down -> Offset(x = 0f, y = size.height)
    }
    val endOffset = when (direction) {
      VerticalDirection.Top -> Offset(x = size.width, y = 0f)
      VerticalDirection.Down -> Offset(x = size.width, y = size.height)
    }

    onDrawWithContent {
      drawContent()
      if (currentBorderColor != null) {
        drawLine(
          color = currentBorderColor,
          start = startOffset,
          end = endOffset,
          strokeWidth = thickness.value,
        )
      }
    }
  }

@Stable
public fun TextFieldCountableData.toDrawModifier(): Modifier =
  Modifier.drawWithCache {
    onDrawBehind {
      drawContent()
      drawText()
    }
  }

@Stable
public val TextFieldIconData.isButtonRole: Boolean
  inline get() = role == IconRole.Button

@ExperimentalDesignToken
@ExperimentalQuackQuackApi
@NonRestartableComposable
@Composable
public fun <
  TextFieldStyle : QuackTextFieldStyle<QuackDefaultTextFieldStyle, QuackDefaultTextFieldStyle.TextFieldColors>,
  > QuackTextField(
  @CasaValue("\"QuackTextFieldPreview\"") value: String,
  @CasaValue("{}") onValueChange: (value: String) -> Unit,
  @CasaValue("QuackTextFieldStyle.Default") style: TextFieldStyle,
  modifier: Modifier = Modifier,
  enabled: Boolean = true,
  readOnly: Boolean = false,
  placeholderValue: String? = null,
  placeholderStrategy: PlaceholderStrategy = PlaceholderStrategy.Hidable,
  keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
  keyboardActions: KeyboardActions = KeyboardActions.Default,
  singleLine: Boolean = false,
  minLines: Int = 1,
  maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
  visualTransformation: VisualTransformation = VisualTransformation.None,
  onTextLayout: (layoutResult: TextLayoutResult) -> Unit = {},
  validationState: TextFieldValidationState = TextFieldValidationState.Default,
  interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) {
  // start --- COPIED FROM BasicTextField (string value version)

  // 최신 내부 텍스트 필드 값 상태를 보유합니다. 컴포지션의 올바른 값을 갖기 위해 이 값을 유지해야 합니다.
  var textFieldValueState by remember { mutableStateOf(TextFieldValue(text = value)) }

  // 재구성된 최신 TextFieldValue를 보유합니다. 컴포지션을 보존해야 하기 때문에 `TextFieldValue(text = value)`를
  // CoreTextField에 단순히 전달할 수 없었습니다.
  val textFieldValue = textFieldValueState.copy(text = value)

  SideEffect {
    if (textFieldValue.selection != textFieldValueState.selection ||
      textFieldValue.composition != textFieldValueState.composition
    ) {
      textFieldValueState = textFieldValue
    }
  }

  // 텍스트 필드가 재구성되었거나 onValueChange 콜백에서 업데이트된 마지막 문자열 값입니다.
  // 이 값을 추적하여 다음과 같은 경우 동일한 문자열에 대해 onValueChange(String)를 호출하지 않도록 합니다.
  // CoreTextField의 onValueChange가 중간에 재구성되지 않고 여러 번 호출되는 것을 방지합니다.
  var lastTextValue by remember(value) { mutableStateOf(value) }

  QuackTextField(
    value = textFieldValue,
    onValueChange = { newTextFieldValueState ->
      textFieldValueState = newTextFieldValueState

      val stringChangedSinceLastInvocation = lastTextValue != newTextFieldValueState.text
      lastTextValue = newTextFieldValueState.text

      if (stringChangedSinceLastInvocation) {
        onValueChange(newTextFieldValueState.text)
      }
    },
    // end --- COPIED FROM BasicTextField (string value version)
    style = style,
    modifier = modifier,
    enabled = enabled,
    readOnly = readOnly,
    placeholderValue = placeholderValue,
    placeholderStrategy = placeholderStrategy,
    keyboardOptions = keyboardOptions,
    keyboardActions = keyboardActions,
    singleLine = singleLine,
    minLines = minLines,
    maxLines = maxLines,
    visualTransformation = visualTransformation,
    onTextLayout = onTextLayout,
    validationState = validationState,
    interactionSource = interactionSource,
  )
}

// TODO(2): casa support for value state
@ExperimentalDesignToken
@ExperimentalQuackQuackApi
@NonRestartableComposable
@Composable
public fun <
  TextFieldStyle : QuackTextFieldStyle<QuackDefaultTextFieldStyle, QuackDefaultTextFieldStyle.TextFieldColors>,
  > QuackTextField(
  @CasaValue("\"QuackTextFieldPreview\"") value: TextFieldValue,
  @CasaValue("{}") onValueChange: (value: TextFieldValue) -> Unit,
  @CasaValue("QuackTextFieldStyle.Default") style: TextFieldStyle,
  modifier: Modifier = Modifier,
  enabled: Boolean = true,
  readOnly: Boolean = false,
  placeholderValue: String? = null,
  placeholderStrategy: PlaceholderStrategy = PlaceholderStrategy.Hidable,
  keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
  keyboardActions: KeyboardActions = KeyboardActions.Default,
  singleLine: Boolean = false,
  minLines: Int = 1,
  maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
  visualTransformation: VisualTransformation = VisualTransformation.None,
  onTextLayout: (layoutResult: TextLayoutResult) -> Unit = {},
  validationState: TextFieldValidationState = TextFieldValidationState.Default,
  interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) {
  var isSizeSpecified = false
  val (composeModifier, quackDataModels) = currentComposer.quackMaterializeOf(modifier) { currentModifier ->
    if (!isSizeSpecified) {
      isSizeSpecified = currentModifier is LayoutModifier
    }
  }
  val iconDatas = remember(quackDataModels) {
    quackDataModels.fastFilterIsInstanceOrNull<TextFieldIconData>()
  }
  val indicatorData = remember(quackDataModels) {
    quackDataModels.fastFirstIsInstanceOrNull<TextFieldIndicatorData>()
  }
  val countableData = remember(quackDataModels) {
    quackDataModels.fastFirstIsInstanceOrNull<TextFieldCountableData>()
  }

  val backgroundColor = style.colors.backgroundColor
  val contentColor = style.colors.contentColor
  val placeholderColor = style.colors.placeholderColor
  val errorColor = style.colors.errorColor
  val successColor = style.colors.successColor

  val contentPadding = style.contentPadding
  val currentContentPadding = if (isSizeSpecified) null else contentPadding

  val contentSpacedBy = style.contentSpacedBy

  val typography = remember(style.typography, contentColor) {
    style.typography.change(color = contentColor)
  }
  val placeholderTypography = remember(style.typography, placeholderColor) {
    style.typography.change(color = placeholderColor)
  }
  val validationTypography = (style as QuackDefaultTextFieldStyle).validationTypography
  val errorTypography = remember(validationTypography, errorColor) {
    validationTypography.change(color = errorColor)
  }
  val successTypography = remember(validationTypography, successColor) {
    validationTypography.change(color = successColor)
  }

  // TODO(2): InspectableModifier의 올바른 제공법 연구 필요
  //          지금은 너무 많은 정보를 보내는 거 같음
  val inspectableModifier =
    with(style) { composeModifier.wrappedDebugInspectable() }
      .wrappedDebugInspectable {
        name = "QuackTextField"
        properties["value"] = value
        properties["onValueChange"] = onValueChange
        properties["composeModifier"] = composeModifier
        properties["enabled"] = enabled
        properties["readOnly"] = readOnly
        properties["iconDatas"] = iconDatas
        properties["indicatorData"] = indicatorData
        properties["countableData"] = countableData
        properties["placeholderValue"] = placeholderValue
        properties["placeholderStrategy"] = placeholderStrategy
        properties["keyboardOptions"] = keyboardOptions
        properties["keyboardActions"] = keyboardActions
        properties["singleLine"] = singleLine
        properties["minLines"] = minLines
        properties["maxLines"] = maxLines
        properties["visualTransformation"] = visualTransformation
        properties["onTextLayout"] = onTextLayout
        properties["validationState"] = validationState
        properties["interactionSource"] = interactionSource
        properties["isSizeSpecified"] = isSizeSpecified
        properties["backgroundColor"] = backgroundColor
        properties["contentPadding"] = currentContentPadding
        properties["contentSpacedBy"] = contentSpacedBy
        properties["typography"] = typography
        properties["placeholderTypography"] = placeholderTypography
        properties["errorTypography"] = errorTypography
        properties["successTypography"] = successTypography
      }

  QuackBaseTextField(
    value = value,
    onValueChange = onValueChange,
    modifier = inspectableModifier,
    enabled = enabled,
    readOnly = readOnly,
    iconDatas = iconDatas,
    indicatorData = indicatorData,
    countableData = countableData,
    placeholderValue = placeholderValue,
    placeholderStrategy = placeholderStrategy,
    keyboardOptions = keyboardOptions,
    keyboardActions = keyboardActions,
    singleLine = singleLine,
    minLines = minLines,
    maxLines = maxLines,
    visualTransformation = visualTransformation,
    onTextLayout = onTextLayout,
    validationState = validationState,
    interactionSource = interactionSource,
    backgroundColor = backgroundColor,
    contentPadding = contentPadding,
    contentSpacedBy = contentSpacedBy,
    typography = typography,
    placeholderTypography = placeholderTypography,
    errorTypography = errorTypography,
    successTypography = successTypography,
  )
}

private const val LeadingIconLayoutId = "QuackBaseTextFieldLeadingIconLayoutId"
private const val TrailingContentLayoutId = "QuackBaseTextFieldTrailingContentLayoutId"

@ExperimentalQuackQuackApi
@Composable
public fun QuackBaseTextField(
  value: TextFieldValue,
  onValueChange: (value: TextFieldValue) -> Unit,
  modifier: Modifier,
  enabled: Boolean,
  readOnly: Boolean,
  iconDatas: List<TextFieldIconData>?,
  indicatorData: TextFieldIndicatorData?,
  countableData: TextFieldCountableData?,
  placeholderValue: String?,
  placeholderStrategy: PlaceholderStrategy,
  keyboardOptions: KeyboardOptions,
  keyboardActions: KeyboardActions,
  singleLine: Boolean,
  minLines: Int,
  maxLines: Int,
  visualTransformation: VisualTransformation,
  onTextLayout: (layoutResult: TextLayoutResult) -> Unit,
  validationState: TextFieldValidationState,
  interactionSource: MutableInteractionSource,
  backgroundColor: QuackColor,
  contentPadding: PaddingValues?,
  contentSpacedBy: Dp,
  typography: QuackTypography,
  placeholderTypography: QuackTypography,
  errorTypography: QuackTypography,
  successTypography: QuackTypography,
) {
  val currentCursorColor = LocalQuackTextFieldTheme.current.cursor
  val currentCursorBrush = remember(currentCursorColor, calculation = currentCursorColor::toBrush)
  val currentTextStyle = remember(typography, calculation = typography::asComposeStyle)

  BasicTextField(
    value = value,
    onValueChange = onValueChange,
    modifier = modifier
      .testTag("BaseTextField")
      .quackSurface(backgroundColor = backgroundColor)
      .then(indicatorData?.toDrawModifier(text = value.text) ?: Modifier),
    enabled = enabled,
    readOnly = readOnly,
    textStyle = currentTextStyle,
    keyboardOptions = keyboardOptions,
    keyboardActions = keyboardActions,
    singleLine = singleLine,
    minLines = minLines,
    maxLines = maxLines,
    visualTransformation = visualTransformation,
    onTextLayout = onTextLayout,
    interactionSource = interactionSource,
    cursorBrush = currentCursorBrush,
  ) { coreTextField ->

  }
}

@ExperimentalQuackQuackApi
@NonRestartableComposable
@Composable
public fun QuackFilledTextField() {
  // LaunchedEffect(interactionSource) {
  //   interactionSource.interactions.collect { interaction ->
  //   }
  // }
}

@SuppressLint("ComposableNaming")
@NoSugar
@ExperimentalQuackQuackApi
@NonRestartableComposable
@Composable
public fun QuackOutlinedTextField(): Nothing {
  throw NotImplementedError(
    "The design that this component is used for is not in development scope, " +
      "so it this component is not yet developed.",
  )
}
