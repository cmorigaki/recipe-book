package br.com.recipebook.inappupdate

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class InAppUpdateSafeArgs(
    val appUpdateType: Int,
) : Parcelable
