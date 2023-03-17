/*
 * Designed and developed by Duckie Team 2023.
 *
 * Licensed under the MIT.
 * Please see full license: https://github.com/duckie-team/quack-quack-android/blob/2.x.x/LICENSE
 */

package poet

import com.squareup.kotlinpoet.AnnotationSpec
import com.squareup.kotlinpoet.FileSpec
import ir.SugarIrData

private const val GeneratedComment = "This file was automatically generated by core-sugar-processor-kotlinc.\n" +
        "Do not modify it arbitrarily."

private val suppressAnnotation = AnnotationSpec
    .builder(Suppress::class)
    .addMember("%S, %S, %S", "NoConsecutiveBlankLines", "PackageDirectoryMismatch", "ktlint")
    .useSiteTarget(AnnotationSpec.UseSiteTarget.FILE)
    .build()

internal fun generateSugarComponents(
    irDatas: List<SugarIrData>,
    sugarPath: FilePath,
    fileName: String,
) {
    val ktSpec = FileSpec
        .builder(
            packageName = sugarPath.bestGuessToKotlinPackageName(),
            fileName = "$fileName.kt"
        )
        .addFileComment(GeneratedComment)
        .addAnnotation(suppressAnnotation)
        .build()
}

/*
private fun SugarIrData.toFunSpecs(): List<FunSpec> {
    return FunSpec
        .builder(name =)
}

private fun SugarIrData.toFunSpec(tokenFqExpression: String): FunSpec {
    val sugarName = toSugarComponentName(tokenFqExpression)
    val sugarBody = buildCodeBlock {
        addStatement("%L(", component.simpleName.asString())
        withIndent {
            component.parameters.forEach { parameter ->
                val parameterName = parameter.name!!.asString()
                if (parameterName != sugarToken) {
                    addStatement("%L = %L", parameterName, parameterName)
                }
            }
        }
        addStatement(")")
    }
    val sugarFunSpec = FunSpec
        .builder(sugarName)
        .addAnnotation(ClassName.bestGuess(ComposableFqn))
        .addModifiers(KModifier.PUBLIC)
        .addParameters(parameters)
        .addCode(sugarBody)
        .applyIf(component.docString != null) {
            addKdoc(component.docString!!)
        }
        .build()
}
*/
