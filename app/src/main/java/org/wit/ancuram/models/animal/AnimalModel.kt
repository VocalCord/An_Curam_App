package org.wit.ancuram.models.animal

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AnimalModel (var id: Long = 0,
                        var commonName: String = "",
                        var irishName: String = "",

                        var image: String = ""): Parcelable