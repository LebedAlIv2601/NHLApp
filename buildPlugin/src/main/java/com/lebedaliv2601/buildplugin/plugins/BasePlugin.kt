package com.lebedaliv2601.buildplugin.plugins

import com.android.build.gradle.LibraryExtension
import com.lebedaliv2601.buildplugin.configureDesugaring
import com.lebedaliv2601.buildplugin.configureJava
import com.lebedaliv2601.buildplugin.configureKotlin
import com.lebedaliv2601.buildplugin.configureSdkVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class BasePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target){
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<LibraryExtension> {
                namespace = "com.lebedaliv2601.${project.name}"
                configureSdkVersion(this)
                configureDesugaring()
                configureKotlin()
                configureJava()
            }
        }
    }
}