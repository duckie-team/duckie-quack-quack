/*
 * Designed and developed by Duckie Team, 2022
 *
 * Licensed under the MIT.
 * Please see full license: https://github.com/duckie-team/quack-quack-android/blob/master/LICENSE
 */

@file:Suppress("UnstableApiUsage", "TestFunctionName")

package team.duckie.quackquack.lint.core

import org.junit.Rule
import org.junit.Test
import team.duckie.quackquack.common.lint.test.LintTestRule
import team.duckie.quackquack.common.lint.test.composableTestFile

/**
 * 테스트 성공 조건
 * 1. MutableCollections 의 접근 제어 범위가 public 일 시 경고해야 함 (특정 범위 내에서도 마찬가지)
 *    interface 는 KotlinUMethod 로 인식하므로 제외 (이유: Java 에서는 인터페이스 변수 기능이 없어, 함수로 변환해서 처리함)
 * 2. Immutable, Persistence 등 Minor 한 ImmutableCollections 은 경고하지 않음
 * 3. 접근 제어 범위가 public 이 아닐경우 경고하지 않음
 */
class MutableCollectionPublicTest {

    @get:Rule
    val lintTestRule = LintTestRule()

    @Test
    fun `Using MutableCollections as public throws an error`() {
        lintTestRule.assertErrorCount(
            files = listOf(
                composableTestFile(
                    """
                        val mutableList: MutableList<Any> = mutableListOf()
                        val mutableMap: MutableMap<Any, Any> = mutableMapOf()
                        val mutableSet: MutableSet<Any> = mutableSetOf()
                        val list: List<Any> = listOf()
                        val map: Map<Any, Any> = mapOf()
                        val set: Set<Any> = setOf()

                        class DummyClass {
                            private val mutableListClassPrivate: MutableList<Any> = mutableListOf()
                            protected val mutableMapClassProtected: MutableMap<Any, Any> = mutableMapOf()
                            private val mutableSetClassPrivate: MutableSet<Any> = mutableSetOf()
                            internal val mutableListClassInternal: MutableList<Any> = mutableListOf()
                            val mutableMapClassPublic: MutableMap<Any, Any> = mutableMapOf()
                            val mutableSetClassPublic: MutableSet<Any> = mutableSetOf()
                        }

                        object DummyObject {
                            private val mutableListObjectPrivate: MutableList<Any> = mutableListOf()
                            protected val mutableMapObjectProtected: MutableMap<Any, Any> = mutableMapOf()
                            private val mutableSetObjectPrivate: MutableSet<Any> = mutableSetOf()
                            internal val mutableListObjectInternal: MutableList<Any> = mutableListOf()
                            val mutableMapObjectPublic: MutableMap<Any, Any> = mutableMapOf()
                            val mutableSetObjectPublic: MutableSet<Any> = mutableSetOf()
                        }
                        """
                ),
            ),
            issues = listOf(
                MutableCollectionPublicIssue,
            ),
            expectedCount = 7,
        )
    }

    @Test
    fun `Can use Public at Minor ImmutableCollections`() {
        lintTestRule.assertErrorCount(
            files = listOf(
                composableTestFile(
                    """
                        val mutableList: ImmutableList<Any>
                        val mutableMap: ImmutableMap<Any, Any>
                        val mutableSet: ImmutableSet<Any>
                        val list: PersistentList<Any>
                        val map: PersistentMap<Any, Any>
                        val set: PersistentSet<Any>
                        """
                ),
            ),
            issues = listOf(
                MutableCollectionPublicIssue,
            ),
            expectedCount = 0,
        )
    }

    @Test
    fun `Use private, protected, internal at MutableCollections variable definition`() {
        lintTestRule.assertErrorCount(
            files = listOf(
                composableTestFile(
                    """
                        private val mutableList1: MutableList<Any> = mutableListOf()
                        private val mutableMap1: MutableMap<Any, Any> = mutableMapOf()
                        private val mutableSet1: MutableSet<Any> = mutableSetOf()
                        protected val mutableList2: MutableList<Any> = mutableListOf()
                        protected val mutableMap2: MutableMap<Any, Any> = mutableMapOf()
                        protected val mutableSet2: MutableSet<Any> = mutableSetOf()
                        internal val mutableList3: MutableList<Any> = mutableListOf()
                        internal val mutableMap3: MutableMap<Any, Any> = mutableMapOf()
                        internal val mutableSet3: MutableSet<Any> = mutableSetOf()
                        """
                ),
            ),
            issues = listOf(
                MutableCollectionPublicIssue,
            ),
            expectedCount = 0,
        )
    }
}
