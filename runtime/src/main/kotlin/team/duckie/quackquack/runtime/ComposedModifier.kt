/*
 * Designed and developed by Duckie Team 2023.
 *
 * Licensed under the MIT.
 * Please see full license: https://github.com/duckie-team/quack-quack-android/blob/main/LICENSE
 */

package team.duckie.quackquack.runtime

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.runtime.Composer
import androidx.compose.ui.platform.InspectorInfo
import androidx.compose.ui.platform.InspectorValueInfo
import androidx.compose.ui.platform.NoInspectorInfo

internal open class QuackComposedModifier(
    inspectorInfo: InspectorInfo.() -> Unit,
    val quackDataModelProducer: Boolean = true,
    val factory: @Composable Modifier.() -> Modifier,
) : Modifier.Element, InspectorValueInfo(inspectorInfo)

@Stable
private class KeyedQuackComposedModifier1(
    quackDataModelProducer: Boolean = true,
    val fqName: String,
    val key1: Any?,
    inspectorInfo: InspectorInfo.() -> Unit,
    factory: @Composable Modifier.() -> Modifier,
) : QuackComposedModifier(
    inspectorInfo = inspectorInfo,
    quackDataModelProducer = quackDataModelProducer,
    factory = factory,
) {
    override fun equals(other: Any?): Boolean {
        return other is KeyedQuackComposedModifier1 &&
                fqName == other.fqName &&
                key1 == other.key1
    }

    override fun hashCode(): Int {
        return 31 * fqName.hashCode() + key1.hashCode()
    }
}

@Stable
private class KeyedQuackComposedModifier2(
    quackDataModelProducer: Boolean = true,
    val fqName: String,
    val key1: Any?,
    val key2: Any?,
    inspectorInfo: InspectorInfo.() -> Unit,
    factory: @Composable Modifier.() -> Modifier,
) : QuackComposedModifier(
    inspectorInfo = inspectorInfo,
    quackDataModelProducer = quackDataModelProducer,
    factory = factory,
) {
    override fun equals(other: Any?): Boolean {
        return other is KeyedQuackComposedModifier2 &&
                fqName == other.fqName &&
                key1 == other.key1 &&
                key2 == other.key2
    }

    override fun hashCode(): Int {
        var result = fqName.hashCode()
        result = 31 * result + key1.hashCode()
        result = 31 * result + key2.hashCode()
        return result
    }
}

@Stable
private class KeyedQuackComposedModifier3(
    quackDataModelProducer: Boolean = true,
    val fqName: String,
    val key1: Any?,
    val key2: Any?,
    val key3: Any?,
    inspectorInfo: InspectorInfo.() -> Unit,
    factory: @Composable Modifier.() -> Modifier,
) : QuackComposedModifier(
    inspectorInfo = inspectorInfo,
    quackDataModelProducer = quackDataModelProducer,
    factory = factory,
) {
    override fun equals(other: Any?): Boolean {
        return other is KeyedQuackComposedModifier3 &&
                fqName == other.fqName &&
                key1 == other.key1 &&
                key2 == other.key2 &&
                key3 == other.key3
    }

    override fun hashCode(): Int {
        var result = fqName.hashCode()
        result = 31 * result + key1.hashCode()
        result = 31 * result + key2.hashCode()
        result = 31 * result + key3.hashCode()
        return result
    }
}

@Stable
private class KeyedQuackComposedModifierN(
    quackDataModelProducer: Boolean = true,
    val fqName: String,
    val keys: Array<out Any?>,
    inspectorInfo: InspectorInfo.() -> Unit,
    factory: @Composable Modifier.() -> Modifier,
) : QuackComposedModifier(
    inspectorInfo = inspectorInfo,
    quackDataModelProducer = quackDataModelProducer,
    factory = factory,
) {
    override fun equals(other: Any?): Boolean {
        return other is KeyedQuackComposedModifierN &&
                fqName == other.fqName &&
                keys.contentEquals(other.keys)
    }

    override fun hashCode(): Int {
        return 31 * fqName.hashCode() + keys.contentHashCode()
    }
}

/**
 * [Modifier.composed]의 꽥꽥 버전을 구현합니다.
 *
 * `composed`의 결과가 [QuackDataModifierModel]일 경우 `acc`를 유지하며
 * folding하기 위해 `ComposedModifier`의 별도 대응이 필요합니다.
 *
 * @see Composer.quackMaterializeOf
 */
@Stable
public fun Modifier.quackComposed(
    quackDataModelProducer: Boolean = true,
    inspectorInfo: InspectorInfo.() -> Unit = NoInspectorInfo,
    factory: @Composable Modifier.() -> Modifier,
): Modifier {
    return then(
        QuackComposedModifier(
            quackDataModelProducer = quackDataModelProducer,
            inspectorInfo = inspectorInfo,
            factory = factory,
        ),
    )
}

/**
 * [Modifier.composed]의 꽥꽥 버전을 구현합니다.
 *
 * `composed`의 결과가 [QuackDataModifierModel]일 경우 `acc`를 유지하며
 * folding하기 위해 `ComposedModifier`의 별도 대응이 필요합니다.
 *
 * `fullyQualifiedName`과 `key`를 통한 안정성 정보가 아직 검증되지 않았습니다.
 * 이는 [Modifier.composed]의 aosp-test 로컬 테스트 결과도 동일합니다.
 * 따라서 인자로 제공되는 안정성에 의존한 로직을 구현하면 안 됩니다.
 *
 * 자세한 정보는 `quack-KeyedComposedModifier는 안정성을 준수하며 re-composition에 한 번만 재실행돼야 함`
 * 테스트를 참고하세요.
 *
 * @see Composer.quackMaterializeOf
 */
@Stable
public fun Modifier.quackComposed(
    quackDataModelProducer: Boolean = true,
    fullyQualifiedName: String,
    key1: Any?,
    inspectorInfo: InspectorInfo.() -> Unit = NoInspectorInfo,
    factory: @Composable Modifier.() -> Modifier,
): Modifier {
    return then(
        KeyedQuackComposedModifier1(
            quackDataModelProducer = quackDataModelProducer,
            fqName = fullyQualifiedName,
            key1 = key1,
            inspectorInfo = inspectorInfo,
            factory = factory,
        ),
    )
}

/**
 * [Modifier.composed]의 꽥꽥 버전을 구현합니다.
 *
 * `composed`의 결과가 [QuackDataModifierModel]일 경우 `acc`를 유지하며
 * folding하기 위해 `ComposedModifier`의 별도 대응이 필요합니다.
 *
 * `fullyQualifiedName`과 `key`를 통한 안정성 정보가 아직 검증되지 않았습니다.
 * 이는 [Modifier.composed]의 aosp-test 로컬 테스트 결과도 동일합니다.
 * 따라서 인자로 제공되는 안정성에 의존한 로직을 구현하면 안 됩니다.
 *
 * 자세한 정보는 `quack-KeyedComposedModifier는 안정성을 준수하며 re-composition에 한 번만 재실행돼야 함`
 * 테스트를 참고하세요.
 *
 * @see Composer.quackMaterializeOf
 */
@Stable
public fun Modifier.quackComposed(
    quackDataModelProducer: Boolean = true,
    fullyQualifiedName: String,
    key1: Any?,
    key2: Any?,
    inspectorInfo: InspectorInfo.() -> Unit = NoInspectorInfo,
    factory: @Composable Modifier.() -> Modifier,
): Modifier {
    return then(
        KeyedQuackComposedModifier2(
            quackDataModelProducer = quackDataModelProducer,
            fqName = fullyQualifiedName,
            key1 = key1,
            key2 = key2,
            inspectorInfo = inspectorInfo,
            factory = factory,
        ),
    )
}

/**
 * [Modifier.composed]의 꽥꽥 버전을 구현합니다.
 *
 * `composed`의 결과가 [QuackDataModifierModel]일 경우 `acc`를 유지하며
 * folding하기 위해 `ComposedModifier`의 별도 대응이 필요합니다.
 *
 * `fullyQualifiedName`과 `key`를 통한 안정성 정보가 아직 검증되지 않았습니다.
 * 이는 [Modifier.composed]의 aosp-test 로컬 테스트 결과도 동일합니다.
 * 따라서 인자로 제공되는 안정성에 의존한 로직을 구현하면 안 됩니다.
 *
 * 자세한 정보는 `quack-KeyedComposedModifier는 안정성을 준수하며 re-composition에 한 번만 재실행돼야 함`
 * 테스트를 참고하세요.
 *
 * @see Composer.quackMaterializeOf
 */
@Stable
public fun Modifier.quackComposed(
    quackDataModelProducer: Boolean = true,
    fullyQualifiedName: String,
    key1: Any?,
    key2: Any?,
    key3: Any?,
    inspectorInfo: InspectorInfo.() -> Unit = NoInspectorInfo,
    factory: @Composable Modifier.() -> Modifier,
): Modifier {
    return then(
        KeyedQuackComposedModifier3(
            quackDataModelProducer = quackDataModelProducer,
            fqName = fullyQualifiedName,
            key1 = key1,
            key2 = key2,
            key3 = key3,
            inspectorInfo = inspectorInfo,
            factory = factory,
        ),
    )
}

/**
 * [Modifier.composed]의 꽥꽥 버전을 구현합니다.
 *
 * `composed`의 결과가 [QuackDataModifierModel]일 경우 `acc`를 유지하며
 * folding하기 위해 `ComposedModifier`의 별도 대응이 필요합니다.
 *
 * `fullyQualifiedName`과 `key`를 통한 안정성 정보가 아직 검증되지 않았습니다.
 * 이는 [Modifier.composed]의 aosp-test 로컬 테스트 결과도 동일합니다.
 * 따라서 인자로 제공되는 안정성에 의존한 로직을 구현하면 안 됩니다.
 *
 * 자세한 정보는 `quack-KeyedComposedModifier는 안정성을 준수하며 re-composition에 한 번만 재실행돼야 함`
 * 테스트를 참고하세요.
 *
 * @see Composer.quackMaterializeOf
 */
@Stable
public fun Modifier.quackComposed(
    quackDataModelProducer: Boolean = true,
    fullyQualifiedName: String,
    vararg keys: Any?,
    inspectorInfo: InspectorInfo.() -> Unit = NoInspectorInfo,
    factory: @Composable Modifier.() -> Modifier,
): Modifier {
    return then(
        KeyedQuackComposedModifierN(
            quackDataModelProducer = quackDataModelProducer,
            fqName = fullyQualifiedName,
            keys = keys,
            inspectorInfo = inspectorInfo,
            factory = factory,
        ),
    )
}
