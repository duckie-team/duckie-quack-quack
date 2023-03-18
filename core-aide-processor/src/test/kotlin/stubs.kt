/*
 * Designed and developed by Duckie Team 2023.
 *
 * Licensed under the MIT.
 * Please see full license: https://github.com/duckie-team/quack-quack-android/blob/2.x.x/LICENSE
 */

import com.tschuchort.compiletesting.SourceFile.Companion.kotlin

val stubs = listOf(
    kotlin(
        "Modifier.kt",
        """
        package androidx.compose.ui
        interface Modifier { companion object : Modifier }
        """,
    ),
    kotlin(
        "TypedModifier.kt",
        """
        package team.duckie.quackquack.aide.annotation
        @MustBeDocumented
        @Target(AnnotationTarget.FUNCTION)
        @Retention(AnnotationRetention.SOURCE)
        annotation class TypedModifier
        """,
    ),
    kotlin(
        "Composable.kt",
        """
        package androidx.compose.runtime
        @Retention(AnnotationRetention.BINARY)
        @Target(
            AnnotationTarget.FUNCTION,
            AnnotationTarget.TYPE,
            AnnotationTarget.TYPE_PARAMETER,
            AnnotationTarget.PROPERTY_GETTER,
        )
        annotation class Composable
        """,
    ),
)
