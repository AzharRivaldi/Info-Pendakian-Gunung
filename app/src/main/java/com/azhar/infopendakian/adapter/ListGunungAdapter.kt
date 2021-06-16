package com.azhar.infopendakian.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.azhar.infopendakian.R
import com.azhar.infopendakian.activities.DetailGunungActivity
import com.azhar.infopendakian.model.ModelGunung
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.list_item_gunung.view.*

/**
 * Created by Azhar Rivaldi on 02-06-2021
 * Youtube Channel : https://bit.ly/2PJMowZ
 * Github : https://github.com/AzharRivaldi
 * Twitter : https://twitter.com/azharrvldi_
 * Instagram : https://www.instagram.com/azhardvls_
 * Linkedin : https://www.linkedin.com/in/azhar-rivaldi
 */

class ListGunungAdapter(private val context: Context?, private val modelGunung:
List<ModelGunung>) : RecyclerView.Adapter<ListGunungAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_gunung, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = modelGunung[position]

        Glide.with(context)
                .load(data.strImageGunung)
                .into(holder.imageGunung)

        holder.tvNamaGunung.text = data.strNamaGunung
        holder.tvLokasiGunung.text = data.strLokasiGunung

        holder.cvListGunung.setOnClickListener {
            val intent = Intent(context, DetailGunungActivity::class.java)
            intent.putExtra(DetailGunungActivity.DETAIL_GUNUNG, modelGunung[position])
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return modelGunung.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var cvListGunung: CardView
        var imageGunung: ImageView
        var tvNamaGunung: TextView
        var tvLokasiGunung: TextView

        init {
            cvListGunung = itemView.cvListGunung
            imageGunung = itemView.imageGunung
            tvNamaGunung = itemView.tvNamaGunung
            tvLokasiGunung = itemView.tvLokasiGunung
        }
    }

}