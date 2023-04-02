package ie.wit.thestoutscout.models

import android.content.Context
import android.net.Uri
import com.google.gson.*
import com.google.gson.reflect.TypeToken
import ie.wit.thestoutscout.helpers.*
import timber.log.Timber
import java.lang.reflect.Type
import java.util.*

const val JSON_FILE = "pubs.json"
val gsonBuilder: Gson = GsonBuilder().setPrettyPrinting()
    .registerTypeAdapter(Uri::class.java, UriParser())
    .create()
val listType: Type = object : TypeToken<ArrayList<PubModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}

class PubJSONStore(private val context: Context) : PubStore {

    var pubs = mutableListOf<PubModel>()

    init {
        if (exists(context, JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<PubModel> {
        logAll()
        return pubs
    }

    override fun create(pub: PubModel) {
        pub.id = generateRandomId()
        pubs.add(pub)
        serialize()
    }


    override fun update(pub: PubModel) {
        // todo
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(pubs, listType)
        write(context, JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(context, JSON_FILE)
        pubs = gsonBuilder.fromJson(jsonString, listType)
    }

    private fun logAll() {
        pubs.forEach { Timber.i("$it") }
    }
}

class UriParser : JsonDeserializer<Uri>,JsonSerializer<Uri> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Uri {
        return Uri.parse(json?.asString)
    }

    override fun serialize(
        src: Uri?,
        typeOfSrc: Type?,
        context: JsonSerializationContext?
    ): JsonElement {
        return JsonPrimitive(src.toString())
    }
}