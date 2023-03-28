package ie.wit.thestoutscout.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.snackbar.Snackbar
import ie.wit.thestoutscout.R
import ie.wit.thestoutscout.databinding.ActivityStoutscoutBinding
import ie.wit.thestoutscout.main.MainApp
import ie.wit.thestoutscout.models.PubModel
import timber.log.Timber.i
import timber.log.Timber

class StoutScoutActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStoutscoutBinding
    var pub = PubModel()
    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var edit = false
        binding = ActivityStoutscoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbarAdd.title = title
        setSupportActionBar(binding.toolbarAdd)
        app = application as MainApp

    i("StoutScout Activity started...")

        if (intent.hasExtra("pub_edit")) {                        //read back the pubs added and place its title/location into the view controls
            edit = true
            pub = intent.extras?.getParcelable("pub_edit")!!
            binding.stoutscoutTitle.setText(pub.title)
            binding.location.setText(pub.location)
            binding.btnAdd.setText(R.string.save_pub)
        }

        binding.btnAdd.setOnClickListener() {
            pub.title = binding.stoutscoutTitle.text.toString()
            pub.location = binding.location.text.toString()
            if (pub.title.isEmpty()) {
                Snackbar
                    .make(it, R.string.enter_pub_title, Snackbar.LENGTH_LONG)
                    .show()
            }else {
                if (edit) {
                    app.pubs.update(pub.copy())
                } else {
                    app.pubs.create(pub.copy())
                }
            }
            setResult(RESULT_OK)
            finish()
            }
        }



    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_pub, menu)
        return super.onCreateOptionsMenu(menu)
 }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_cancel -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}