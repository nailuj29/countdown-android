package com.nailuj29gaming.countdown

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

import java.util.*

@Entity
data class Countdown(
    @NonNull @ColumnInfo(name = "event_name") val eventName: String,
    @PrimaryKey @ColumnInfo(name = "date") val date: Date
)