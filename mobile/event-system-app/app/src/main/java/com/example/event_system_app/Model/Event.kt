package com.example.event_system_app.Model

import android.icu.text.CaseMap.Title
import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

data class Event(
    val id: Long,
    val title: String,
    val description: String,
    val imgUrl: String,
    val tags: String,
    val date: String,
    val location: String,
    val humanCount: Int
) : Serializable