package ie.wit.thestoutscout.main

import android.app.Application
import ie.wit.thestoutscout.models.PubModel
import timber.log.Timber
import timber.log.Timber.i

class MainApp : Application() {

    val pubs = ArrayList<PubModel>()

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        i("StoutScout started")
        //pubs.add(PubModel("One", "About one..."))
        //pubs.add(PubModel("Two", "About two..."))
        //pubs.add(PubModel("Three", "About three..."))
    }
}