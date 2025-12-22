package com.jb.pixelquest.data.local.android.database

import android.content.Context
import androidx.room.Room

/**
 * Room Database 인스턴스 생성
 */
object DatabaseModule {
    private const val DATABASE_NAME = "pixelquest_database"

    fun createDatabase(context: Context): PixelQuestDatabase {
        return Room.databaseBuilder(
            context,
            PixelQuestDatabase::class.java,
            DATABASE_NAME
        )
            .fallbackToDestructiveMigration() // 개발 중에는 스키마 변경 시 데이터 삭제
            .build()
    }
}

