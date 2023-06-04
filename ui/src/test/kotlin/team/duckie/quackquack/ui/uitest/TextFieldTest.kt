/*
 * Designed and developed by Duckie Team 2023.
 *
 * Licensed under the MIT.
 * Please see full license: https://github.com/duckie-team/quack-quack-android/blob/main/LICENSE
 */

@file:OptIn(ExperimentalDesignToken::class, ExperimentalQuackQuackApi::class)

package team.duckie.quackquack.ui.uitest

import androidx.activity.ComponentActivity
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import io.kotest.assertions.throwables.shouldThrowWithMessage
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import team.duckie.quackquack.material.icon.QuackIcon
import team.duckie.quackquack.material.icon.quackicon.Outlined
import team.duckie.quackquack.material.icon.quackicon.outlined.Heart
import team.duckie.quackquack.ui.QuackDefaultTextField
import team.duckie.quackquack.ui.QuackTextFieldStyle
import team.duckie.quackquack.ui.TextFieldErrors.AllColorProvidedAsNull
import team.duckie.quackquack.ui.TextFieldErrors.CannotAppliedCounterAndTrailingIcon
import team.duckie.quackquack.ui.TextFieldErrors.ValidationLabelProvidedButNoBottomDirectionIndicator
import team.duckie.quackquack.ui.TextFieldErrors.sameDirectionIcon
import team.duckie.quackquack.ui.TextFieldValidationState
import team.duckie.quackquack.ui.commonutil.setQuackContent
import team.duckie.quackquack.ui.counter
import team.duckie.quackquack.ui.defaultTextFieldIcon
import team.duckie.quackquack.ui.defaultTextFieldIndicator
import team.duckie.quackquack.ui.optin.ExperimentalDesignToken
import team.duckie.quackquack.ui.token.HorizontalDirection
import team.duckie.quackquack.ui.token.VerticalDirection
import team.duckie.quackquack.ui.util.ExperimentalQuackQuackApi
import team.duckie.quackquack.util.Empty

@RunWith(AndroidJUnit4::class)
class TextFieldTest {
  @get:Rule
  val compose = createAndroidComposeRule<ComponentActivity>()

  @Test
  fun sameDirectionIcon() {
    shouldThrowWithMessage<IllegalArgumentException>(sameDirectionIcon("leading")) {
      compose.setQuackContent {
        QuackDefaultTextField(
          modifier = Modifier
            .defaultTextFieldIcon(
              icon = QuackIcon.Outlined.Heart,
              direction = HorizontalDirection.Left,
            )
            .defaultTextFieldIcon(
              icon = QuackIcon.Outlined.Heart,
              direction = HorizontalDirection.Left,
            ),
          value = String.Empty,
          onValueChange = {},
          style = QuackTextFieldStyle.Default,
        )
      }
    }
  }

  @Test
  fun IndicatorRequestedButNoColor() {
    shouldThrowWithMessage<IllegalArgumentException>(AllColorProvidedAsNull) {
      compose.setQuackContent {
        QuackDefaultTextField(
          modifier = Modifier.defaultTextFieldIndicator(color = null, colorGetter = null),
          value = String.Empty,
          onValueChange = {},
          style = QuackTextFieldStyle.Default,
        )
      }
    }
  }

  @Test
  fun ValidationLabelProvidedButNoDownDirectionIndicator() {
    shouldThrowWithMessage<IllegalArgumentException>(ValidationLabelProvidedButNoBottomDirectionIndicator) {
      compose.setQuackContent {
        QuackDefaultTextField(
          modifier = Modifier.defaultTextFieldIndicator(direction = VerticalDirection.Top),
          value = String.Empty,
          onValueChange = {},
          style = QuackTextFieldStyle.Default,
          validationState = TextFieldValidationState.Success(String.Empty),
        )
      }
    }
  }

  @Test
  fun CannotAppliedCounterAndTrailingIcon() {
    shouldThrowWithMessage<IllegalArgumentException>(CannotAppliedCounterAndTrailingIcon) {
      compose.setQuackContent {
        QuackDefaultTextField(
          modifier = Modifier
            .counter(10)
            .defaultTextFieldIcon(QuackIcon.Outlined.Heart),
          value = String.Empty,
          onValueChange = {},
          style = QuackTextFieldStyle.Default,
        )
      }
    }
  }
}
