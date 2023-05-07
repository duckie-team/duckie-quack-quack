/*
 * Designed and developed by Duckie Team 2023.
 *
 * Licensed under the MIT.
 * Please see full license: https://github.com/duckie-team/quack-quack-android/blob/main/LICENSE
 */

package team.duckie.quackquack.aide.processor

import com.google.auto.service.AutoService
import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.processing.SymbolProcessorProvider
import com.google.devtools.ksp.symbol.KSAnnotated

private const val AidePathArg = "AidePath"

@AutoService(SymbolProcessorProvider::class)
class AideSymbolProcessorProvider : SymbolProcessorProvider {
  override fun create(environment: SymbolProcessorEnvironment): SymbolProcessor {
    return AideSymbolProcessor(
      codeGenerator = environment.codeGenerator,
      logger = environment.logger,
      options = environment.options,
    )
  }
}

private class AideSymbolProcessor(
  codeGenerator: CodeGenerator,
  logger: KSPLogger,
  options: Map<String, Any>,
) : SymbolProcessor {
  private val processor = AideProcessor(
    codeGenerator = codeGenerator,
    logger = logger,
    aidePath = options[AidePathArg]?.toString(),
  )

  override fun process(resolver: Resolver): List<KSAnnotated> {
    return processor.resolve(resolver)
  }
}
