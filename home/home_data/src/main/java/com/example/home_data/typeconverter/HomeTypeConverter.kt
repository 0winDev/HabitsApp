package com.example.home_data.typeconverter

import android.util.Log
import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter

import com.squareup.moshi.Moshi

@ProvidedTypeConverter
class HomeTypeConverter{
    @TypeConverter
    fun fromFrequency(days: List<Int>): String {
        return  days?.joinToString(",") ?: ""
    }

    @TypeConverter
    fun toFrequency(value: String): List<Int> {
        if (value.isBlank()) return emptyList()
        return value.split(",").mapNotNull { it.toIntOrNull() }
    }
    @TypeConverter
    fun fromcompletedDates(days: List<Long>): String {
        return joinIntoString(days) ?: ""
    }

    @TypeConverter
    fun tocompletedDates(value: String): List<Long> {
        return splitToLongList(value) ?: emptyList()
    }

    private fun splitToLongList(input: String?): List<Long>? {
        return input?.split(',')?.mapNotNull { item ->
            try {
                item.toLong()
            } catch (ex: NumberFormatException) {
                null
            }
        }
    }
    private fun joinIntoString(input: List<Long>?): String? {
        return input?.joinToString(",")
    }
}