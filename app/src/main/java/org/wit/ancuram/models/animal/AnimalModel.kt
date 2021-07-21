package org.wit.ancuram.models.animal

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AnimalModel (var id: Long = 0,
                        var commonName: String = "",
                        var irishName: String = "",
                        var image: String = "",
                        var lat : Double = 0.0,
                        var lng: Double = 0.0,
                        var zoom: Float = 0f): Parcelable

@Parcelize
data class Sighting     (var lat: Double = 0.0,
                         var lng: Double = 0.0,
                         var zoom: Float = 0f) : Parcelable