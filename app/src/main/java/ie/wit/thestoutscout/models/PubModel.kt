package ie.wit.thestoutscout.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PubModel(var id: Long = 0,
                    var title: String = "",
                    var location: String = "") : Parcelable
