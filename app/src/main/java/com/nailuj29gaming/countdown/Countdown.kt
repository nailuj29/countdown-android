package com.nailuj29gaming.countdown

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Countdown(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "event_name") val eventName: String?,
    @ColumnInfo(name = "date") val date: Date?
)