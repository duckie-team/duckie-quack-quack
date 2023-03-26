/*
 * Designed and developed by Duckie Team 2023.
 *
 * Licensed under the MIT.
 * Please see full license: https://github.com/duckie-team/quack-quack-android/blob/2.x.x/LICENSE
 */

import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSFunctionDeclaration
import com.google.devtools.ksp.validate

internal class CoreAideProcessor(
    private val codeGenerator: CodeGenerator,
    private val logger: KSPLogger,
    private val aidePath: String?,
) {
    fun resolve(resolver: Resolver): List<KSAnnotated> {
        @Suppress("UNCHECKED_CAST")
        val components = resolver
            .getSymbolsWithAnnotation(ComposableFqn)
            .filter { declaration ->
                declaration is KSFunctionDeclaration && declaration.isPublicQuackComponent
            } as Sequence<KSFunctionDeclaration>

        @Suppress("UNCHECKED_CAST")
        val modifiers = resolver
            .getSymbolsWithAnnotation(DecorateModifierFqn)
            .filter { declaration ->
                declaration is KSFunctionDeclaration && declaration.isPublicModifier
            } as Sequence<KSFunctionDeclaration>

        if (components.toList().isNotEmpty()) {
            generateQuackComponents(
                codeGenerator = codeGenerator,
                logger = logger,
                symbols = components,
                aidePath = aidePath,
            )
        }

        if (modifiers.toList().isNotEmpty()) {
            generateAideModifiers(
                codeGenerator = codeGenerator,
                logger = logger,
                symbols = modifiers,
                aidePath = aidePath,
            )
        }

        return components.filterNot(KSAnnotated::validate)
            .plus(modifiers.filterNot(KSAnnotated::validate))
            .toList()
    }
}
