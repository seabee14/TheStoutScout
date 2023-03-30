package ie.wit.thestoutscout.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import ie.wit.thestoutscout.R
import ie.wit.thestoutscout.adapters.PubAdapter
import ie.wit.thestoutscout.adapters.PubListener
import ie.wit.thestoutscout.databinding.ActivityPubListBinding
import ie.wit.thestoutscout.main.MainApp
import ie.wit.thestoutscout.models.PubModel

class PubListActivity : AppCompatActivity(), PubListener {

    lateinit var app: MainApp                              //retrieving and storing a reference to the main app
    private lateinit var binding: ActivityPubListBinding
    private lateinit var refreshIntentLauncher : ActivityResultLauncher<Intent>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPubListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbar.title = title
        setSupportActionBar(binding.toolbar)

        app = application as MainApp

        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = PubAdapter(app.pubs.findAll(), this)

        registerRefreshCallback()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_add -> {
                val launcherIntent = Intent(this, StoutScoutActivity::class.java)
                refreshIntentLauncher.launch(launcherIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onPubClick(pub: PubModel) {
        val launcherIntent = Intent(this, StoutScoutActivity::class.java)
        launcherIntent.putExtra("pub_edit", pub)
        refreshIntentLauncher.launch(launcherIntent)
    }

    private fun registerRefreshCallback() {
        refreshIntentLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult())
            { binding.recyclerView.adapter?.notifyDataSetChanged() }
    }



}

