package com.lebedaliv2601.buildplugin

import com.android.build.api.dsl.CommonExtension
import com.android.build.gradle.BaseExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

fun Project.configureSdkVersion(commonExtension: CommonExtension<*, *, *, *, *>) {
    commonExtension.apply {
        compileSdk = AppConfig.COMPILE_SDK_VERSION
        defaultConfig {
            minSdk = AppConfig.MIN_SDK_VERSION
        }
    }
}

fun Project.configureDesugaring() {
    extensions.configure<BaseExtension>() {
        dependencies {
            "coreLibraryDesugaring"(libs("androidx-desugar"))
        }
        compileOptions {
            isCoreLibraryDesugaringEnabled = true
        }
    }
}

fun Project.configureJava() {
    extensions.configure<BaseExtension> {
        compileOptions {
            sourceCompatibility = JavaVersion.toVersion(AppConfig.JAVA_VERSION)
            targetCompatibility = JavaVersion.toVersion(AppConfig.JAVA_VERSION)
        }
    }
}

fun Project.configureKotlin() {
    tasks.withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = AppConfig.JAVA_VERSION
            freeCompilerArgs = listOf("-Xinline-classes")
        }
    }
}

fun Project.libs(name: String) = rootProject.extensions
    .getByType(VersionCatalogsExtension::class)
    .named("libs")
    .findLibrary(name)
    .get()

fun Project.version(name: String): String = rootProject.extensions
    .getByType(VersionCatalogsExtension::class)
    .named("libs")
    .findVersion(name)
    .get()
    .displayName