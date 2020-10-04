package com.example.sept

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FlexItem(val content : String, val urlimg : String) :
    Parcelable