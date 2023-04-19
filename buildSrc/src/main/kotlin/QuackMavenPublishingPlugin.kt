/*
 * Designed and developed by Duckie Team 2023.
 *
 * Licensed under the MIT.
 * Please see full license: https://github.com/duckie-team/quack-quack-android/blob/2.x.x/LICENSE
 */

import com.vanniktech.maven.publish.MavenPublishBaseExtension
import com.vanniktech.maven.publish.SonatypeHost
import internal.applyPlugins
import internal.libs
import internal.parseArtifactVersion
import java.time.LocalDate
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPom
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.withType
import org.jetbrains.annotations.VisibleForTesting

private const val RepositoryName = "duckie-team/quack-quack-android"
private const val QuackBaseGroupId = "team.duckie.quackquack"

class QuackMavenPublishingPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            applyPlugins(
                libs.findPlugin("gradle-publish-maven-core").get().get().pluginId,
                libs.findPlugin("gradle-publish-maven-base").get().get().pluginId,
            )

            tasks.matching { task ->
                task.name.contains("signMavenPublication")
            }.configureEach {
                notCompatibleWithConfigurationCache("https://github.com/vanniktech/gradle-maven-publish-plugin/issues/259")
            }

            val (group, module, version) = ArtifactConfig.from(this)
            this.group = group
            this.version = version

            extensions.configure<PublishingExtension> {
                publications.withType<MavenPublication> {
                    artifactId = module
                }
            }

            extensions.configure<MavenPublishBaseExtension> {
                publishToMavenCentral(
                    host = SonatypeHost.S01,
                    automaticRelease = true,
                )

                signAllPublications()

                pom {
                    configureMavenPom(module)
                }
            }
        }
    }
}

private fun MavenPom.configureMavenPom(artifactName: String) {
    name.set(artifactName)
    description.set("https://github.com/$RepositoryName")
    inceptionYear.set("2023")
    url.set("https://github.com/$RepositoryName")
    licenses {
        license {
            name.set("MIT License")
            url.set("https://github.com/$RepositoryName/blob/2.x.x/LICENSE")
        }
    }
    developers {
        developer {
            id.set("jisungbin")
            name.set("Ji Sungbin")
            url.set("https://sungb.in")
            email.set("ji@sungb.in")
        }
    }
    scm {
        url.set("https://github.com/$RepositoryName")
        connection.set("scm:git:github.com/$RepositoryName.git")
        developerConnection.set("scm:git:ssh://github.com/$RepositoryName.git")
    }
}

// TOOD: Testing
// Testing ref: https://discuss.gradle.org/t/testing-and-mocking-techniques/7064/2
@VisibleForTesting
internal data class ArtifactConfig(
    val group: String,
    val module: String,
    val version: String,
) {
    companion object {
        fun from(project: Project): ArtifactConfig {
            val groupSuffix = project.name.split("-").first()
            val module = project.name
            val version = project.parseArtifactVersion()

            return ArtifactConfig(
                group = "$QuackBaseGroupId.$groupSuffix",
                module = module,
                version = version,
            )
        }
    }
}
