package ie.wit.thestoutscout.main

import android.app.Application
import ie.wit.thestoutscout.models.PubJSONStore
import ie.wit.thestoutscout.models.PubMemStore
import ie.wit.thestoutscout.models.PubStore
import timber.log.Timber
import timber.log.Timber.i

class MainApp : Application() {

    lateinit var pubs: PubStore

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        pubs = PubJSONStore(applicationContext)
        i("StoutScout started")
    }
}