package com.dsm.agencytravel

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dsm.agencytravel.model.Destination

class MainActivity : AppCompatActivity() {

    private lateinit var recycler: RecyclerView
    private lateinit var btnAdd: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler = findViewById(R.id.recyclerDestinations)
        btnAdd = findViewById(R.id.btnAdd)

        recycler.layoutManager = LinearLayoutManager(this)

        val list = listOf(
            Destination("1", "París", "Francia", 1200.0, "Viaje increíble a París con tours completos", ""),
            Destination("2", "Cancún", "México", 900.0, "Playa y diversión todo incluido", "")
        )

        recycler.adapter = DestinationAdapter(list)

        btnAdd.setOnClickListener {
            startActivity(Intent(this, FormDestinationActivity::class.java))
        }
    }
}