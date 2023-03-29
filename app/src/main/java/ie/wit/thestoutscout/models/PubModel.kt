package ie.wit.thestoutscout.models

import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PubModel(var id: Long = 0,
                    var title: String = "",
                    var location: String = "",
                    var image: Uri = Uri.EMPTY) : Parcelable

