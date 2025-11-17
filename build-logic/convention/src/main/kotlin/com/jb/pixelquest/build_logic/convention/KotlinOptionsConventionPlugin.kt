package com.jb.pixelquest.build_logic.convention

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

class KotlinOptionsConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            tasks.withType<KotlinCompile> {
                kotlinOptions {
                    jvmTarget = "11"
                }
            }
        }
    }
}

