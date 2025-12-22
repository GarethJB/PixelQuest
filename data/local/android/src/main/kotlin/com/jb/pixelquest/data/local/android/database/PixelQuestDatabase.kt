package com.jb.pixelquest.data.local.android.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jb.pixelquest.data.local.android.converter.ArtworkTypeConverter
import com.jb.pixelquest.data.local.android.converter.QuestTypeConverter
import com.jb.pixelquest.data.local.android.dao.ArtworkDao
import com.jb.pixelquest.data.local.android.dao.CanvasDao
import com.jb.pixelquest.data.local.android.dao.QuestDao
import com.jb.pixelquest.data.local.android.dao.UserDao
import com.jb.pixelquest.data.local.android.entity.ArtworkEntity
import com.jb.pixelquest.data.local.android.entity.CanvasEntity
import com.jb.pixelquest.data.local.android.entity.QuestEntity
import com.jb.pixelquest.data.local.android.entity.UserEntity

/**
 * PixelQuest Room Database
 */
@Database(
    entities = [
        QuestEntity::class,
        ArtworkEntity::class,
        UserEntity::class,
        CanvasEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(
    QuestTypeConverter::class,
    ArtworkTypeConverter::class
)
abstract class PixelQuestDatabase : RoomDatabase() {
    abstract fun questDao(): QuestDao
    abstract fun artworkDao(): ArtworkDao
    abstract fun userDao(): UserDao
    abstract fun canvasDao(): CanvasDao
}

