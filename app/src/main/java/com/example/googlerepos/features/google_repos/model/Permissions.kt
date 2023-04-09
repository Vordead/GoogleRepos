package com.example.googlerepos.features.google_repos.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Permissions(
    val admin: Boolean?,
    val maintain: Boolean?,
    val pull: Boolean?,
    val push: Boolean?,
    val triage: Boolean?
) : Parcelable