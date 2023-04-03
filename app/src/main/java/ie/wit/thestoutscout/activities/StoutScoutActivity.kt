package ie.wit.thestoutscout.activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import ie.wit.thestoutscout.R
import ie.wit.thestoutscout.databinding.ActivityStoutscoutBinding
import ie.wit.thestoutscout.helpers.showImagePicker
import ie.wit.thestoutscout.main.MainApp
import ie.wit.thestoutscout.models.Location
import ie.wit.thestoutscout.models.PubModel
import timber.log.Timber.i

class StoutScoutActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStoutscoutBinding
    var pub = PubModel()
    lateinit var app: MainApp
    private lateinit var imageIntentLauncher: ActivityResultLauncher<Intent>
    private lateinit var mapIntentLauncher: ActivityResultLauncher<Intent>
    var edit = false;
    // var location = Location(52.245696, -7.139102, 15f)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //edit = true
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
            Picasso.get()                                       //display image
                .load(pub.image)
                .into(binding.pubImage)
            if (pub.image != Uri.EMPTY) {
                binding.chooseImage.setText(R.string.change_pub_image)
            }
        }

        binding.btnAdd.setOnClickListener() {
            pub.title = binding.stoutscoutTitle.text.toString()
            pub.location = binding.location.text.toString()
            if (pub.title.isEmpty()) {
                Snackbar
                    .make(it, R.string.enter_pub_title, Snackbar.LENGTH_LONG)
                    .show()
            } else {
                if (edit) {
                    app.pubs.update(pub.copy())
                } else {
                    app.pubs.create(pub.copy())
                }
            }
            setResult(RESULT_OK)
            finish()
        }

        binding.pubLocation.setOnClickListener {
            i("Set Location Pressed")
        }


        binding.chooseImage.setOnClickListener {
            showImagePicker(imageIntentLauncher)
        }


        binding.pubLocation.setOnClickListener {
            val location = Location(52.245696, -7.139102, 15f)
            if (pub.zoom != 0f) {
                location.lat =  pub.lat
                location.lng = pub.lng
                location.zoom = pub.zoom
            }
            val launcherIntent = Intent(this, MapActivity::class.java)
                .putExtra("location", location)
            mapIntentLauncher.launch(launcherIntent)
        }


        registerImagePickerCallback()
        registerMapCallback()

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_pub, menu)
        if (edit && menu != null) menu.getItem(0).setVisible(true)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_delete -> {
                app.pubs.delete(pub)
                finish()
            }
            R.id.item_cancel -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }


    private fun registerImagePickerCallback() {
        imageIntentLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult())
            { result ->
                when (result.resultCode) {
                    RESULT_OK -> {
                        if (result.data != null) {
                            i("Got Result ${result.data!!.data}")
                            pub.image = result.data!!.data!!
                            Picasso.get()
                                .load(pub.image)
                                .into(binding.pubImage)
                            binding.chooseImage.setText(R.string.change_pub_image)
                        } // end of if
                    }
                    RESULT_CANCELED -> {}
                    else -> {}
                }
            }
    }


    private fun registerMapCallback() {
        mapIntentLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult())
            { result ->
                when (result.resultCode) {
                    RESULT_OK -> {
                        if (result.data != null) {
                            i("Got Location ${result.data.toString()}")
                            val location = result.data!!.extras?.getParcelable<Location>("location")!!
                            i("Location == $location")
                            pub.lat = location.lat
                            pub.lng = location.lng
                            pub.zoom = location.zoom
                        } // end of if
                    }
                    RESULT_CANCELED -> {}
                    else -> {}
                }
            }


    }
}