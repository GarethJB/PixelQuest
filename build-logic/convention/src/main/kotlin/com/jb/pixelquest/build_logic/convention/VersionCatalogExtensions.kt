package com.jb.pixelquest.build_logic.convention

import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType

/**
 * Version Catalog에 접근하기 위한 확장 함수
 * convention 모듈 내에서만 사용
 */
internal val Project.libs
    get(): VersionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

internal fun VersionCatalog.versionInt(name: String): Int =
    findVersion(name).get().requiredVersion.toInt()


