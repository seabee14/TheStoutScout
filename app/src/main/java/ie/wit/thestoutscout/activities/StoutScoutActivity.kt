package ie.wit.thestoutscout.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import ie.wit.thestoutscout.databinding.ActivityStoutscoutBinding
import ie.wit.thestoutscout.main.MainApp
import ie.wit.thestoutscout.models.PubModel
import timber.log.Timber.i

class StoutScoutActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStoutscoutBinding
    var pub = PubModel()
    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStoutscoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

    app = application as MainApp

    i("StoutScout Activity started...")

        binding.btnAdd.setOnClickListener() {
            pub.title = binding.stoutscoutTitle.text.toString()
            pub.location = binding.location.text.toString()
            if (pub.title.isNotEmpty()) {

                app.pubs.add(pub.copy())
                i("add Button Pressed: ${pub}")
                for (i in app.pubs.indices) {
                    i("Pub[$i]:${this.app.pubs[i]}")
                }
                setResult(RESULT_OK)
                finish()
            }
            else {
                Snackbar
                    .make(it,"Please Enter a title", Snackbar.LENGTH_LONG)
                    .show()
            }
        }
    }
}