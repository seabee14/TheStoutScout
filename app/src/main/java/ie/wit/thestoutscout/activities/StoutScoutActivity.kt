package ie.wit.thestoutscout.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import timber.log.Timber
import timber.log.Timber.i
import ie.wit.thestoutscout.databinding.ActivityStoutscoutBinding
import ie.wit.thestoutscout.models.PubModel



class StoutScoutActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStoutscoutBinding
    var pub = PubModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityStoutscoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Timber.plant(Timber.DebugTree())

        i("Stout Scout Activity started...")

        binding.btnAdd.setOnClickListener() {
            pub.title = binding.stoutscoutTitle.text.toString()
            if (pub.title.isNotEmpty()) {
                i("add Button Pressed: ${pub.title}")
            } else {
                Snackbar
                    .make(it, "Please Enter a title", Snackbar.LENGTH_LONG)
                    .show()
            }


        }
    }
}

