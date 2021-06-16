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
import com.azhar.infopendakian.activities.ListGunungActivity
import com.azhar.infopendakian.model.ModelMain
import kotlinx.android.synthetic.main.list_item_main.view.*

/**
 * Created by Azhar Rivaldi on 01-06-2021
 * Youtube Channel : https://bit.ly/2PJMowZ
 * Github : https://github.com/AzharRivaldi
 * Twitter : https://twitter.com/azharrvldi_
 * Instagram : https://www.instagram.com/azhardvls_
 * Linkedin : https://www.linkedin.com/in/azhar-rivaldi
 */

class MainAdapter(private val context: Context?, private val modelMain:
List<ModelMain>) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    var imageLokasiDrawable = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_main, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = modelMain[position]

        holder.tvLokasi.text = data.strLokasi

        if (data.strLokasi == "Jawa Timur")
            imageLokasiDrawable = R.drawable.ic_jatim
        else if (data.strLokasi == "Jawa Tengah")
            imageLokasiDrawable = R.drawable.ic_jateng
        else if (data.strLokasi == "Jawa Barat")
            imageLokasiDrawable = R.drawable.ic_jabar
        else if (data.strLokasi == "Luar Pulau Jawa")
            imageLokasiDrawable = R.drawable.ic_luar_jawa

        holder.imageLokasi.setImageResource(imageLokasiDrawable)

        holder.cvListLokasi.setOnClickListener {
            val intent = Intent(context, ListGunungActivity::class.java)
            intent.putExtra(ListGunungActivity.LIST_GUNUNG, modelMain[position])
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return modelMain.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var cvListLokasi: CardView
        var tvLokasi: TextView
        var imageLokasi: ImageView

        init {
            cvListLokasi = itemView.cvListLokasi
            tvLokasi = itemView.tvLokasi
            imageLokasi = itemView.imageLokasi
        }
    }

}