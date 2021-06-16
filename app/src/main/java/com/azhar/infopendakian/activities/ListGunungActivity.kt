package com.azhar.infopendakian.activities

import android.app.Activity
import android.content.res.Configuration
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.azhar.infopendakian.R
import com.azhar.infopendakian.activities.ListGunungActivity
import com.azhar.infopendakian.adapter.ListGunungAdapter
import com.azhar.infopendakian.model.ModelGunung
import com.azhar.infopendakian.model.ModelMain
import kotlinx.android.synthetic.main.activity_list_gunung.*
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.StandardCharsets
import java.util.*

class ListGunungActivity : AppCompatActivity() {

    lateinit var listGunungAdapter: ListGunungAdapter
    lateinit var modelMain: ModelMain
    var modelGunung: MutableList<ModelGunung> = ArrayList()
    var strLokasiGunung: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_gunung)

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

        setSupportActionBar(toolbar)
        assert(supportActionBar != null)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        //get data intent
        modelMain = intent.getSerializableExtra(LIST_GUNUNG) as ModelMain
        if (modelMain != null) {
            strLokasiGunung = modelMain.strLokasi

            tvLokasi.setText(strLokasiGunung)

            //set data adapter to recyclerview
            listGunungAdapter = ListGunungAdapter(this, modelGunung)

            if (this.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                rvListGunung.setLayoutManager(GridLayoutManager(this, 2))
            } else {
                rvListGunung.setLayoutManager(GridLayoutManager(this, 3))
            }
            rvListGunung.setAdapter(listGunungAdapter)
            rvListGunung.setHasFixedSize(true)

            //method untuk menampilkan data gunung
            getListGunung()
        }
    }

    private fun getListGunung() {
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
                        val jsonObjectData = jsonArray.getJSONObject(i)
                        if (jsonObjectData.getString("lokasi") == strLokasiGunung) {
                            val jsonArrayMountain = jsonObjectData.getJSONArray("nama_gunung")
                            for (j in 0 until jsonArrayMountain.length()) {
                                val dataApi = ModelGunung()
                                val objectMountain = jsonArrayMountain.getJSONObject(j)
                                dataApi.strImageGunung = objectMountain.getString("image_gunung")
                                dataApi.strNamaGunung = objectMountain.getString("nama")
                                dataApi.strLokasiGunung = objectMountain.getString("lokasi")
                                dataApi.strDeskripsi = objectMountain.getString("deskripsi")
                                dataApi.strInfoGunung = objectMountain.getString("info_gunung")
                                dataApi.strJalurPendakian = objectMountain.getString("jalur_pendakian")
                                dataApi.strLat = objectMountain.getDouble("lat")
                                dataApi.strLong = objectMountain.getDouble("lon")
                                modelGunung.add(dataApi)
                            }
                        }
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            } catch (ignored: IOException) {
                Toast.makeText(this@ListGunungActivity, "Oops, ada yang tidak beres. Coba ulangi beberapa saat lagi.",
                        Toast.LENGTH_SHORT).show()
            }
        }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val LIST_GUNUNG = "LIST_GUNUNG"
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