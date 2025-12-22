package com.jb.pixelquest.data.local.android.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jb.pixelquest.shared.domain.model.common.Color
import com.jb.pixelquest.shared.domain.model.user.User

/**
 * Artwork 관련 TypeConverter
 */
class ArtworkTypeConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromStringList(value: String): List<String> {
        val listType = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(value, listType) ?: emptyList()
    }

    @TypeConverter
    fun toStringList(list: List<String>): String {
        return gson.toJson(list)
    }

    @TypeConverter
    fun fromUser(value: String): User {
        return gson.fromJson(value, User::class.java)
    }

    @TypeConverter
    fun toUser(user: User): String {
        return gson.toJson(user)
    }

    @TypeConverter
    fun fromUserList(value: String): List<User> {
        val listType = object : TypeToken<List<User>>() {}.type
        return gson.fromJson(value, listType) ?: emptyList()
    }

    @TypeConverter
    fun toUserList(list: List<User>): String {
        return gson.toJson(list)
    }

    @TypeConverter
    fun fromColorList(value: String?): List<Color>? {
        if (value == null) return null
        val listType = object : TypeToken<List<Color>>() {}.type
        return gson.fromJson(value, listType)
    }

    @TypeConverter
    fun toColorList(list: List<Color>?): String? {
        if (list == null) return null
        return gson.toJson(list)
    }
}

