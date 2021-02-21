package br.com.recipebook.inappupdate

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class InAppUpdateSafeArgs(
    val appUpdateType: Int,
    val requestCode: Int,
) : Parcelable
