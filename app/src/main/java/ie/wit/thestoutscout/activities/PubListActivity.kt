package ie.wit.thestoutscout.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import ie.wit.thestoutscout.R
import ie.wit.thestoutscout.databinding.ActivityPubListBinding
import ie.wit.thestoutscout.databinding.CardPubBinding
import ie.wit.thestoutscout.models.PubModel
import ie.wit.thestoutscout.main.MainApp

class PubListActivity : AppCompatActivity() {

    lateinit var app: MainApp                              //retrieving and storing a reference to the main app
    private lateinit var binding: ActivityPubListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPubListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbar.title = title
        setSupportActionBar(binding.toolbar)

        app = application as MainApp

        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = PubAdapter(app.pubs)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_add -> {
                val launcherIntent = Intent(this, StoutScoutActivity::class.java)
                startActivityForResult(launcherIntent,0)
            }
        }
        return super.onOptionsItemSelected(item)
    }

}

class PubAdapter constructor(private var pubs: List<PubModel>) :
    RecyclerView.Adapter<PubAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val binding = CardPubBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return MainHolder(binding)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val pub = pubs[holder.adapterPosition]
        holder.bind(pub)
    }

    override fun getItemCount(): Int = pubs.size

    class MainHolder(private val binding : CardPubBinding) :
                                 RecyclerView.ViewHolder(binding.root) {

        fun bind(pub: PubModel) {
            binding.pubTitle.text = pub.title
            binding.location.text = pub.location
        }
    }
}