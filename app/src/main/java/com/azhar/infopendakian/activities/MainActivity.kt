package com.azhar.infopendakian.activities

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.azhar.infopendakian.R
import com.azhar.infopendakian.activities.PeralatanActivity
import com.azhar.infopendakian.adapter.MainAdapter
import com.azhar.infopendakian.model.ModelMain
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.StandardCharsets
import java.util.*

class MainActivity : AppCompatActivity() {

    var modelMain: MutableList<ModelMain> = ArrayList()
    lateinit var mainAdapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //set transparent statusbar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false)
            window.statusBarColor = Color.TRANSPARENT
        }

        //set data adapter to recyclerview
        mainAdapter = MainAdapter(this, modelMain)
        rvLokasi.setLayoutManager(LinearLayoutManager(this))
        rvLokasi.setAdapter(mainAdapter)
        rvLokasi.setHasFixedSize(true)

        //open activity peralatan
        fabPeralatan.setOnClickListener {
            val intent = Intent(this@MainActivity, PeralatanActivity::class.java)
            startActivity(intent)
        }

        //method get data nama gunung
        getLokasiGunung()
    }

    private fun getLokasiGunung() {
            try {
                val stream = assets.open("nama_gunung.json")
                val size = stream.available()
                val buffer = ByteArray(size)
                stream.read(buffer)
                stream.close()
                val strContent = String(buffer, StandardCharsets.UTF_8)
                try {
                    val jsonObject = JSONObject(strContent)
                    val jsonArray = jsonObject.getJSONArray("gunung")
                    for (i in 0 until jsonArray.length()) {
                        val dataApi = ModelMain()
                        val jsonObjectData = jsonArray.getJSONObject(i)
                        dataApi.strLokasi = jsonObjectData.getString("lokasi")
                        modelMain.add(dataApi)
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            } catch (ignored: IOException) {
                Toast.makeText(this@MainActivity,
                        "Oops, ada yang tidak beres. Coba ulangi beberapa saat lagi.", Toast.LENGTH_SHORT).show()
            }
        }

    companion object {
        fun setWindowFlag(activity: Activity, bits: Int, on: Boolean) {
            val window = activity.window
            val layoutParams = window.attributes
            if (on) {
                layoutParams.flags = layoutParams.flags or bits
            } else {
                layoutParams.flags = layoutParams.flags and bits.inv()
            }
            window.attributes = layoutParams
        }
    }

}