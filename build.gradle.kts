/*
 * Designed and developed by 2022 SungbinLand, Team Duckie
 *
 * [build.gradle.kts] created by Ji Sungbin on 22. 8. 14. 오전 12:49
 *
 * Licensed under the MIT.
 * Please see full license: https://github.com/sungbinland/quack-quack/blob/main/LICENSE
 */

import org.jetbrains.dokka.base.DokkaBase
import org.jetbrains.dokka.base.DokkaBaseConfiguration
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jlleitschuh.gradle.ktlint.KtlintExtension

plugins {
    alias(libs.plugins.detekt)
    alias(libs.plugins.ktlint)
    alias(libs.plugins.dokka)
    alias(libs.plugins.kover)
}

koverMerged {
    enable()
    htmlReport {
        reportDir.set(file("$rootDir/report/test-coverage"))
    }
    xmlReport {
        reportFile.set(file("$rootDir/report/test-coverage/report.xml"))
    }
}

buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath(libs.build.gradle)
        classpath(libs.build.kotlin)
        classpath(libs.build.dokka.base)
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }

    afterEvaluate {
        detekt {
            buildUponDefaultConfig = true
            toolVersion = libs.versions.detekt.get()
            config.setFrom(files("$rootDir/detekt-config.yml"))
        }

        tasks.withType<KotlinCompile> {
            kotlinOptions {
                freeCompilerArgs = freeCompilerArgs + listOf(
                    "-Xopt-in=kotlin.OptIn",
                    "-Xopt-in=kotlin.RequiresOptIn",
                )
                freeCompilerArgs = freeCompilerArgs + listOf(
                    "-P",
                    "plugin:androidx.compose.compiler.plugins.kotlin:metricsDestination=$rootDir/report/compose-metrics",
                )
                freeCompilerArgs = freeCompilerArgs + listOf(
                    "-P",
                    "plugin:androidx.compose.compiler.plugins.kotlin:reportsDestination=$rootDir/report/compose-reports",
                )
            }
        }
    }

    if (pluginManager.hasPlugin(rootProject.libs.plugins.dokka.get().pluginId)) {
        tasks.dokkaHtmlMultiModule.configure {
            moduleName.set("Duckie-QuackQuack")
            outputDirectory.set(file("$rootDir/documents/dokka"))

            pluginConfiguration<DokkaBase, DokkaBaseConfiguration> {
                footerMessage =
                    """made with <span style="color: #ff8300;">❤</span> by <a href="https://duckie.team/">Duckie</a>"""
                customAssets = listOf(file("assets/logo-icon.svg"))
            }
        }
    }

    apply {
        plugin(rootProject.libs.plugins.detekt.get().pluginId)
        plugin(rootProject.libs.plugins.ktlint.get().pluginId)
    }
}

subprojects {
    // https://github.com/gradle/gradle/issues/4823#issuecomment-715615422
    @Suppress("UnstableApiUsage")
    if (
        gradle.startParameter.isConfigureOnDemand &&
        buildscript.sourceFile?.extension?.toLowerCase() == "kts" &&
        parent != rootProject
    ) {
        generateSequence(parent) { project ->
            project.parent.takeIf { parent ->
                parent != rootProject
            }
        }.forEach { project ->
            evaluationDependsOn(project.path)
        }
    }

    configure<KtlintExtension> {
        version.set(rootProject.libs.versions.ktlint.source.get())
        android.set(true)
        outputToConsole.set(true)
        additionalEditorconfigFile.set(file("$rootDir/.editorconfig"))
    }
}

tasks.register("cleanAll", Delete::class) {
    allprojects.map { it.buildDir }.forEach(::delete)
}

tasks.register("configurationTestCoverageHtmlReport") {
    File("$rootDir/report/test-coverage").let { rootFolder ->
        if (!rootFolder.exists()) {
            rootFolder.mkdirs()
        }
    }
    val cname = File("$rootDir/report/test-coverage/CNAME")
    val readme = File("$rootDir/report/test-coverage/README.md")
    cname.writeText(
        """
        quack-test.duckie.team
        """.trimIndent()
    )
    readme.writeText(
        """
        ## duckie-quack-test-coverage-deploy

        [duckie-quack-quack](https://github.com/sungbinland/duckie-quack-quack)의 테스트 커버리지에 변동이 생기면 자동으로 푸시되고 커버리지 리포트가 깃허브 페이지로 배포됩니다.

        배포 주소: [quack-test.duckie.team](https://quack-test.duckie.team)

        ---

        #### 자동 배포

        [duckie-quack-quack](https://github.com/sungbinland/duckie-quack-quack)에 `test` 와 `deploy` label 이 붙은 PR 이 올라오면 [Android CI](https://github.com/sungbinland/duckie-quack-quack/blob/develop/.github/workflows/android-ci.yml) 과정을 거치면서 이 저장소로 [Kover](https://github.com/Kotlin/kotlinx-kover)에 의해 생성된 테스트 커버리지 HTML 리포트가 자동 푸시됩니다. 이후 푸시된 파일들을 기준으로 깃허브 페이지 빌드가 시작됩니다.
        """.trimIndent()
    )
    cname.createNewFile()
    readme.createNewFile()
}

apply(from = "gradle/projectDependencyGraph.gradle")
