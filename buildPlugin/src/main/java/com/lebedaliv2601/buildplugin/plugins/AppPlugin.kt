package com.lebedaliv2601.buildplugin.plugins

import com.android.build.api.dsl.ApplicationExtension
import com.lebedaliv2601.buildplugin.AppConfig
import com.lebedaliv2601.buildplugin.configureDesugaring
import com.lebedaliv2601.buildplugin.configureJava
import com.lebedaliv2601.buildplugin.configureKotlin
import com.lebedaliv2601.buildplugin.configureSdkVersion
import com.lebedaliv2601.buildplugin.version
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AppPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<ApplicationExtension> {
                configureSdkVersion(this)
                configureJava()
                configureKotlin()
                configureDesugaring()
                configureApp()
                configureBuildTypes()
                configureBuildFeatures()
                configureBuildTypes()
                configurePackaging()
            }
        }
    }
}

private fun ApplicationExtension.configureApp() {
    namespace = "com.lebedaliv2601.nhlapp"
    defaultConfig {
        applicationId = "com.lebedaliv2601.nhlapp"
        targetSdk = AppConfig.TARGET_SDK_VERSION
        versionCode = AppConfig.VERSION_CODE
        versionName = AppConfig.VERSION_NAME

        vectorDrawables.useSupportLibrary = true
    }
}

private fun ApplicationExtension.configureBuildTypes() {
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

private fun Project.configureBuildFeatures() {
    extensions.configure<ApplicationExtension> {
        buildFeatures {
            shaders = false
            compose = true
        }
        composeOptions {
            kotlinCompilerExtensionVersion = version("androidx-compose-compiler")
        }
    }
}

private fun ApplicationExtension.configurePackaging() {
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

