import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("appPlugin") {
            id = "com.lebedaliv2601.buildplugin.plugins.appPlugin"
            implementationClass = "com.lebedaliv2601.buildplugin.plugins.AppPlugin"
        }
        register("basePlugin") {
            id = "com.lebedaliv2601.buildplugin.plugins.basePlugin"
            implementationClass = "com.lebedaliv2601.buildplugin.plugins.BasePlugin"
        }
    }
}