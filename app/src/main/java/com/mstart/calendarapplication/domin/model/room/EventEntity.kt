package com.mstart.calendarapplication.domin.model.room

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "event_table")
data class EventEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var dateHijri: String?,
    var dateGregorian: String?,
    var day: String?,
    var month: String?,
    var eventTitle: String?,
    var eventDesc: String?,
    var serverDateTime: String?,
    var isSelected: Boolean = false
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readByte() != 0.toByte()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(dateHijri)
        parcel.writeString(dateGregorian)
        parcel.writeString(day)
        parcel.writeString(month)
        parcel.writeString(eventTitle)
        parcel.writeString(eventDesc)
        parcel.writeString(serverDateTime)
        parcel.writeByte(if (isSelected) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<EventEntity> {
        override fun createFromParcel(parcel: Parcel): EventEntity {
            return EventEntity(parcel)
        }

        override fun newArray(size: Int): Array<EventEntity?> {
            return arrayOfNulls(size)
        }
    }
}






