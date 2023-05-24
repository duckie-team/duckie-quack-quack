/*
 * Designed and developed by Duckie Team 2023.
 *
 * Licensed under the MIT.
 * Please see full license: https://github.com/duckie-team/quack-quack-android/blob/main/LICENSE
 */

package team.duckie.quackquack.ui.utiltest

import io.kotest.matchers.shouldBe
import org.junit.Test
import team.duckie.quackquack.ui.util.buildFloat
import team.duckie.quackquack.ui.util.buildInt
import team.duckie.quackquack.ui.util.minus
import team.duckie.quackquack.ui.util.plus

class NumberBuilderTest {
  @Test
  fun buildInt_logical() {
    val condition1 = true
    val condition2 = false

    val actual = buildInt {
      plus(1)
      if (condition1) {
        plus(2)
        plus(2)
      }
      if (condition2) {
        plus(3)
        plus(3)
      }
      plus(1)
      plus(1)
    }
    val expect = 7

    actual shouldBe expect
  }

  @Test
  fun buildFloat_int_extensions() {
    val actual = buildFloat {
      plus(10)
      minus(1)
      minus(1)
      plus(10)
    }
    val expect = 18f

    actual shouldBe expect
  }
}
