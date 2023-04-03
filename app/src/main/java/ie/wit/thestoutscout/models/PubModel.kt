package ie.wit.thestoutscout.models

import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PubModel(var id: Long = 0,
                    var title: String = "",
                    var location: String = "",
                    var image: Uri = Uri.EMPTY,
                    var rating: String = "0.0",
                    var lat : Double = 0.0,
                    var lng: Double = 0.0,
                    var zoom: Float = 0f) : Parcelable



@Parcelize
data class Location(var lat: Double = 0.0,
                    var lng: Double = 0.0,
                    var zoom: Float = 0f) : Parcelable

