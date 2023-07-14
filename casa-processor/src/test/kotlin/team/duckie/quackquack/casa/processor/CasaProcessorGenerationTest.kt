/*
 * Designed and developed by Duckie Team 2023.
 *
 * Licensed under the MIT.
 * Please see full license: https://github.com/duckie-team/quack-quack-android/blob/main/LICENSE
 */

@file:Suppress("RedundantVisibilityModifier")

package team.duckie.quackquack.casa.processor

import com.tschuchort.compiletesting.KotlinCompilation
import com.tschuchort.compiletesting.SourceFile
import com.tschuchort.compiletesting.SourceFile.Companion.kotlin
import com.tschuchort.compiletesting.kspIncremental
import com.tschuchort.compiletesting.symbolProcessorProviders
import io.kotest.core.spec.style.StringSpec
import io.kotest.engine.spec.tempdir
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain
import org.intellij.lang.annotations.Language
import org.jetbrains.kotlin.config.JvmTarget
import team.duckie.quackquack.util.backend.test.findGeneratedFileOrNull

class CasaProcessorGenerationTest : StringSpec() {
  private val useKspIncrementals = listOf(true, false)
  private val tempDir = tempdir()

  init {
    useKspIncrementals.forEach { kspIncremental ->
      """
      CasaModel이 다양한 케이스에서 정상적으로 생성됨
      - KDoc 여부
      - KDoc 방식
      - 인자 여부
      - 다중 인자 타입
      - 함수형 인자 타입
      - 인자에 default value 여부
      - @CasaValue 여부
      - 다중 도메인
      - [kspIncremental: $kspIncremental]
      """ {
        val result = compile(
          useKspIncremental = kspIncremental,
          kotlin(
            "text.kt",
            """
import androidx.compose.runtime.Composable
import team.duckie.quackquack.casa.`annotation`.Casa
import team.duckie.quackquack.casa.`annotation`.CasaValue
import team.duckie.quackquack.sugar.material.SugarRefer

/** KDOC! */
@Composable
@Casa
@SugarRefer("team.duckie.quackquack.ui.QuackText")
fun Text1(
  @CasaValue("\"HelloWorld\"") text: String,
  text2: String?,
) = Unit

/**
 * 두 번째 casa의 kdoc은 CasaModel에 보여지면 안 됩니다.
 */
@Composable
@Casa
@SugarRefer("team.duckie.quackquack.ui.QuackText")
fun Text2(
  @CasaValue("\"ByeWorld\"") text: String,
  @CasaValue("true") boolean: Boolean,
  text2: String?,
) = Unit
            """,
          ),
          kotlin(
            "button.kt",
            """
import androidx.compose.runtime.Composable
import team.duckie.quackquack.casa.`annotation`.Casa
import team.duckie.quackquack.casa.`annotation`.CasaValue
import team.duckie.quackquack.sugar.material.SugarRefer

/**
 * 이 영역은 KDoc의 Default Section 입니다. 
 * KDoc Tag 영역은 표시되면 안 됩니다.
 * 
 * This document was automatically generated
 * 
 * @param text text
 * @param boolean boolean
 */
@Composable
@Casa
@SugarRefer("team.duckie.quackquack.ui.QuackButton")
fun Button1(
  text: String = "default value",
  boolean: Boolean = true,
) = Unit

@Composable
@Casa
@SugarRefer("team.duckie.quackquack.ui.QuackButton")
fun Button2(
  @CasaValue("\"HelloWorld\"") text: String,
  @CasaValue("false") boolean: Boolean,
  @CasaValue("{}") onClick: () -> Unit,
) = Unit
            """,
          ),
        )

        @Language("kotlin")
        val expect = """
// This file was automatically generated by casa-processor.
// Do not modify it manually.
import androidx.compose.runtime.Composable
import kotlin.Boolean
import kotlin.String
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import team.duckie.quackquack.casa.material.CasaModel
import team.duckie.quackquack.ui.sugar.Button1
import team.duckie.quackquack.ui.sugar.Button2
import team.duckie.quackquack.ui.sugar.Text1
import team.duckie.quackquack.ui.sugar.Text2

private val buttonQuackButtonCasaModel: CasaModel = CasaModel(
  name = "QuackButton",
  domain = "button",
  kdocDefaultSection = ""${'"'}
      |이 영역은 KDoc의 Default Section 입니다. 
      |KDoc Tag 영역은 표시되면 안 됩니다.
      |""${'"'}.trimMargin(),
  components = persistentListOf<Pair<String, @Composable () -> Unit>>(
      "Button1" to { Button1(
      ) },
      "Button2" to { Button2(
        text = "HelloWorld",
        boolean = false,
        onClick = {},
      ) },
      ).toImmutableList(),
)


private val textQuackTextCasaModel: CasaModel = CasaModel(
  name = "QuackText",
  domain = "text",
  kdocDefaultSection = "KDOC! ",
  components = persistentListOf<Pair<String, @Composable () -> Unit>>(
      "Text1" to { Text1(
        text = "HelloWorld",
        text2 = null,
      ) },
      "Text2" to { Text2(
        text = "ByeWorld",
        boolean = true,
        text2 = null,
      ) },
      ).toImmutableList(),
)


public val casaModels: ImmutableList<CasaModel> = persistentListOf(
  buttonQuackButtonCasaModel,
  textQuackTextCasaModel,
)


        """.trimIndent()

        result.exitCode shouldBe KotlinCompilation.ExitCode.OK
        tempDir.findGeneratedFileOrNull("CasaModels.kt")?.readText() shouldBe expect
      }

      """
      조건에 맞지 않을 때는 CasaModel을 생성할 수 없음
      - casa 컴포넌트인데 @SugarRefer가 없음
      - default value가 없는 인자가 NonNull하고 @CasaValue가 없음
      - [kspIncremental: $kspIncremental]
      """ {
        val result = compile(
          useKspIncremental = kspIncremental,
          kotlin(
            "text.kt",
            """
import androidx.compose.runtime.Composable
import team.duckie.quackquack.casa.`annotation`.Casa

@Composable
@Casa
fun Text() = Unit
            """,
          ),
        )
        val result2 = compile(
          useKspIncremental = kspIncremental,
          kotlin(
            "text.kt",
            """
import androidx.compose.runtime.Composable
import team.duckie.quackquack.casa.`annotation`.Casa
import team.duckie.quackquack.sugar.material.SugarRefer

@Composable
@Casa
@SugarRefer("team.duckie.quackquack.ui.Text")
fun Text(text: String) = Unit
            """,
          ),
        )

        result.exitCode shouldBe KotlinCompilation.ExitCode.COMPILATION_ERROR
        result2.exitCode shouldBe KotlinCompilation.ExitCode.COMPILATION_ERROR

        result.messages shouldContain "casa-processor only supports sugar components."
        result2.messages shouldContain "Argument text is non-null and no `CasaValue` was provided."
      }
    }
  }

  private fun compile(useKspIncremental: Boolean, vararg sourceFiles: SourceFile) =
    prepareCompilation(useKspIncremental, *sourceFiles).compile()

  private fun prepareCompilation(useKspIncremental: Boolean, vararg sourceFiles: SourceFile) =
    KotlinCompilation().apply {
      workingDir = tempDir
      sources = sourceFiles.asList() + stubs
      jvmTarget = JvmTarget.JVM_17.toString()
      kspIncremental = useKspIncremental
      symbolProcessorProviders = listOf(CasaProcessorProvider())
    }
}
