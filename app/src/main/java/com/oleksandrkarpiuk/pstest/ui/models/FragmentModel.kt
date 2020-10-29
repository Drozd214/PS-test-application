package com.oleksandrkarpiuk.pstest.ui.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FragmentModel(
    val number: Int
) : Parcelable