/*
 * Designed and developed by Duckie Team 2023.
 *
 * Licensed under the MIT.
 * Please see full license: https://github.com/duckie-team/quack-quack-android/blob/2.x.x/LICENSE
 */

package ir

import ComposableFqn
import Logger
import QuackComponentPrefix
import SugarDefaultName
import SugarImportsFqn
import SugarNameFqn
import SugarTokenFqn
import SugarTokenName
import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.backend.jvm.ir.psiElement
import org.jetbrains.kotlin.ir.declarations.IrFile
import org.jetbrains.kotlin.ir.declarations.IrModuleFragment
import org.jetbrains.kotlin.ir.declarations.IrSimpleFunction
import org.jetbrains.kotlin.ir.declarations.IrValueParameter
import org.jetbrains.kotlin.ir.expressions.IrClassReference
import org.jetbrains.kotlin.ir.expressions.IrConst
import org.jetbrains.kotlin.ir.expressions.IrConstructorCall
import org.jetbrains.kotlin.ir.expressions.IrVararg
import org.jetbrains.kotlin.ir.types.classFqName
import org.jetbrains.kotlin.ir.types.getClass
import org.jetbrains.kotlin.ir.util.companionObject
import org.jetbrains.kotlin.ir.util.fqNameWhenAvailable
import org.jetbrains.kotlin.ir.util.getAnnotation
import org.jetbrains.kotlin.ir.util.hasAnnotation
import org.jetbrains.kotlin.ir.util.properties
import org.jetbrains.kotlin.ir.visitors.IrElementVisitorVoid
import org.jetbrains.kotlin.psi.KtDeclaration
import org.jetbrains.kotlin.utils.addToStdlib.cast
import prependLogPrefix
import toFqnClass

internal class SugarIrVisitor(
    @Suppress("unused") private val context: IrPluginContext,
    private val logger: Logger,
    private val addSugarIrData: (data: SugarIrData) -> Unit,
) : IrElementVisitorVoid {
    override fun visitModuleFragment(declaration: IrModuleFragment) {
        declaration.files.forEach { file ->
            file.accept(this, null)
        }
    }

    override fun visitFile(declaration: IrFile) {
        declaration.declarations.forEach { item ->
            item.accept(this, null)
        }
    }

    override fun visitSimpleFunction(declaration: IrSimpleFunction) {
        if (declaration.isQuackComponent) {
            val sugarAnnotation = declaration.getAnnotation(SugarNameFqn.toFqnClass())
            val sugarName = sugarAnnotation?.getSugarNameOrNull()

            var sugarToken: IrValueParameter? = null
            val sugarParameters = declaration.valueParameters.map { parameter ->
                if (sugarToken == null) {
                    sugarToken = parameter.takeIf {
                        parameter.hasAnnotation(SugarTokenFqn.toFqnClass())
                    }
                }
                parameter.toSugarParameter(isToken = sugarToken != null)
            }
            sugarToken ?: return // not a sugar component -> ignore

            val sugarIrData = SugarIrData(
                refer = declaration.name.asString(),
                kdoc = declaration.getSugarKDoc(),
                sugarName = sugarName,
                sugarToken = sugarToken!!,
                tokenFqExpressions = sugarToken!!.getAllTokenFqExpressions(),
                parameters = sugarParameters,
            )

            logger(sugarIrData.prependLogPrefix(withNewline = true))
            addSugarIrData(sugarIrData)
        }
    }
}

private fun IrConstructorCall.getSugarNameOrNull(): String? {
    // assuming the first argument is always "name"
    val sugarNameExpression = getValueArgument(0)
    return sugarNameExpression.cast<IrConst<String>>().value.takeIf { name ->
        (name != SugarDefaultName).also { isCustomSugarName ->
            if (isCustomSugarName) checkCustomSugarNameIsValid(name)
        }
    }
}

private fun checkCustomSugarNameIsValid(name: String) {
    val prefixIsNotQuackError = "Function names must start with `$QuackComponentPrefix`."
    val tokenNameIsNotUsedError =
        "When specifying the sugar component name directly, `Sugar.TOKEN_NAME (= $SugarTokenName)` must be used."

    if (!name.startsWith(QuackComponentPrefix)) {
        throw IllegalArgumentException(prefixIsNotQuackError)
    }
    if (!name.contains(SugarTokenName)) {
        throw IllegalArgumentException(tokenNameIsNotUsedError)
    }
}

private fun IrValueParameter.toSugarParameter(isToken: Boolean): SugarParameter {
    val sugarImportsAnnotation = getAnnotation(SugarImportsFqn.toFqnClass())
    val sugarImports = sugarImportsAnnotation?.let {
        // assuming the first argument is always "clazz"
        val sugarImportsExpression = sugarImportsAnnotation.getValueArgument(0)
        sugarImportsExpression.cast<IrVararg>().elements.map { element ->
            element.cast<IrClassReference>().classType.classFqName!!
        }
    }

    return SugarParameter(
        name = name,
        type = type,
        isToken = isToken,
        isComposable = hasAnnotation(ComposableFqn.toFqnClass()),
        imports = sugarImports.orEmpty(),
        defaultValue = defaultValue,
    )
}

private fun IrSimpleFunction.getSugarKDoc(): String {
    var kdoc = psiElement?.cast<KtDeclaration>()?.docComment
        ?.getDefaultSection()?.getContent()?.trim()
        ?: return ""
    kdoc += "\n\nThis document was auto-generated. Please see [${fqNameWhenAvailable!!.asString()}] for details."

    return kdoc
}

private fun IrValueParameter.getAllTokenFqExpressions(): List<String> {
    val tokenClass = type.getClass()!!
    val tokenClassName = tokenClass.name.asString()
    return tokenClass.companionObject()?.let { companion ->
        val tokenableProperties = companion.properties.filter { property ->
            property.visibility.isPublicAPI
        }
        val propertyFqExpressions = tokenableProperties.map { property ->
            "$tokenClassName.${property.name.asString()}"
        }
        propertyFqExpressions.toList()
    } ?: error(
        "" +
                "The SugarToken class must include a companion object. " +
                "See the sugar component creation policy for more information. ($tokenClassName)",
    )
}
