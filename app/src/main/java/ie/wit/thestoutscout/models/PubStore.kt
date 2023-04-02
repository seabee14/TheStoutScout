package ie.wit.thestoutscout.models

interface PubStore {
    fun findAll(): List<PubModel>
    fun create(pub: PubModel)
    fun update(pub: PubModel)
    fun delete(pub: PubModel)
}