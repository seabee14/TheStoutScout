package ie.wit.thestoutscout.models

import timber.log.Timber.i

var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class PubMemStore : PubStore {

    val pubs = ArrayList<PubModel>()

    override fun findAll(): List<PubModel> {
        return pubs
    }

    override fun create(pub: PubModel) {           //logs all pubs
        pub.id = getId()
        pubs.add(pub)
        logAll()
    }

    override fun update(pub: PubModel) {
        var foundPub: PubModel? = pubs.find { p -> p.id == pub.id }
        if (foundPub != null) {
            foundPub.title = pub.title
            foundPub.location = pub.location
            foundPub.image = pub.image
            foundPub.lat = pub.lat
            foundPub.lng = pub.lng
            foundPub.zoom = pub.zoom
            logAll()
        }
    }

    fun logAll() {
        pubs.forEach{ i("${it}") }
    }
}