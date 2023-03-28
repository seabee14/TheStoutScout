package ie.wit.thestoutscout.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ie.wit.thestoutscout.databinding.CardPubBinding
import ie.wit.thestoutscout.models.PubModel


interface PubListener {
    fun onPubClick(pub: PubModel)
}

class PubAdapter constructor(private var pubs: List<PubModel>,
                             private val listener: PubListener) :
    RecyclerView.Adapter<PubAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val binding = CardPubBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return MainHolder(binding)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val pub = pubs[holder.adapterPosition]
        holder.bind(pub, listener)
    }

    override fun getItemCount(): Int = pubs.size

    class MainHolder(private val binding : CardPubBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(pub: PubModel, listener: PubListener) {
            binding.pubTitle.text = pub.title
            binding.location.text = pub.location
            binding.root.setOnClickListener { listener.onPubClick(pub)}
        }
    }
}

