package com.jb.pixelquest.data.remote.android.di

import com.jb.pixelquest.data.local.android.database.DatabaseModule
import com.jb.pixelquest.data.local.android.datasource.ArtworkLocalDataSource
import com.jb.pixelquest.data.local.android.datasource.CanvasLocalDataSource
import com.jb.pixelquest.data.local.android.datasource.QuestLocalDataSource
import com.jb.pixelquest.data.local.android.datasource.UserLocalDataSource
import com.jb.pixelquest.data.remote.android.RetrofitModule
import com.jb.pixelquest.data.remote.android.impl.ArtworkRemoteDataSourceImpl
import com.jb.pixelquest.data.remote.android.impl.InventoryRemoteDataSourceImpl
import com.jb.pixelquest.data.remote.android.impl.QuestRemoteDataSourceImpl
import com.jb.pixelquest.data.remote.android.impl.StudioRemoteDataSourceImpl
import com.jb.pixelquest.data.remote.android.impl.UserRemoteDataSourceImpl
import com.jb.pixelquest.shared.data.remote.datasource.ArtworkRemoteDataSource
import com.jb.pixelquest.shared.data.remote.datasource.InventoryRemoteDataSource
import com.jb.pixelquest.shared.data.remote.datasource.QuestRemoteDataSource
import com.jb.pixelquest.shared.data.remote.datasource.StudioRemoteDataSource
import com.jb.pixelquest.shared.data.remote.datasource.UserRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * DataSource 의존성 주입 모듈
 */
@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {
    
    // Remote DataSources
    @Provides
    @Singleton
    fun provideQuestRemoteDataSource(): QuestRemoteDataSource {
        return QuestRemoteDataSourceImpl(RetrofitModule.pixelQuestApi)
    }
    
    @Provides
    @Singleton
    fun provideArtworkRemoteDataSource(): ArtworkRemoteDataSource {
        return ArtworkRemoteDataSourceImpl(RetrofitModule.pixelQuestApi)
    }
    
    @Provides
    @Singleton
    fun provideStudioRemoteDataSource(): StudioRemoteDataSource {
        return StudioRemoteDataSourceImpl(RetrofitModule.pixelQuestApi)
    }
    
    @Provides
    @Singleton
    fun provideUserRemoteDataSource(): UserRemoteDataSource {
        return UserRemoteDataSourceImpl(RetrofitModule.pixelQuestApi)
    }
    
    @Provides
    @Singleton
    fun provideInventoryRemoteDataSource(): InventoryRemoteDataSource {
        return InventoryRemoteDataSourceImpl(RetrofitModule.pixelQuestApi)
    }
    
    // Local DataSources
    @Provides
    @Singleton
    fun provideQuestLocalDataSource(
        @ApplicationContext context: android.content.Context
    ): QuestLocalDataSource {
        val database = DatabaseModule.createDatabase(context)
        return QuestLocalDataSource(database.questDao())
    }
    
    @Provides
    @Singleton
    fun provideArtworkLocalDataSource(
        @ApplicationContext context: android.content.Context
    ): ArtworkLocalDataSource {
        val database = DatabaseModule.createDatabase(context)
        return ArtworkLocalDataSource(database.artworkDao())
    }
    
    @Provides
    @Singleton
    fun provideCanvasLocalDataSource(
        @ApplicationContext context: android.content.Context
    ): CanvasLocalDataSource {
        val database = DatabaseModule.createDatabase(context)
        return CanvasLocalDataSource(database.canvasDao())
    }
    
    @Provides
    @Singleton
    fun provideUserLocalDataSource(
        @ApplicationContext context: android.content.Context
    ): UserLocalDataSource {
        val database = DatabaseModule.createDatabase(context)
        return UserLocalDataSource(database.userDao())
    }
}

