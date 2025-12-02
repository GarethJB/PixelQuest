package com.jb.pixelquest.core.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * 애플리케이션 레벨 의존성을 제공하는 Hilt 모듈
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    // 애플리케이션 레벨 의존성 바인딩을 여기에 추가
}

