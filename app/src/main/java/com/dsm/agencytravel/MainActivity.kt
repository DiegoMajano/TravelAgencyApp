package com.dsm.agencytravel

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dsm.agencytravel.firebase.FirebaseService

class MainActivity : AppCompatActivity() {

    private lateinit var recycler: RecyclerView
    private lateinit var btnAdd: Button

    private val firebase = FirebaseService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        recycler = findViewById(R.id.recyclerDestinations)
        btnAdd = findViewById(R.id.btnAdd)

        recycler.layoutManager = LinearLayoutManager(this)

        loadDestinations()

        btnAdd.setOnClickListener {
            startActivity(Intent(this, FormDestinationActivity::class.java))
        }
    }

    override fun onStart() {
        super.onStart()

        val user = firebase.getCurrentUser()

        // ✅ aquí sí va al login si NO hay sesión
        if (user == null) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    override fun onResume() {
        super.onResume()
        loadDestinations()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {

            R.id.menu_logout -> {
                logOut()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun loadDestinations() {
        firebase.getDestinations { list ->

            recycler.adapter = DestinationAdapter(
                list,
                onEdit = { destination ->
                    val intent = Intent(this, FormDestinationActivity::class.java)
                    intent.putExtra("destino", destination)
                    startActivity(intent)
                },
                onDelete = { destination ->

                    AlertDialog.Builder(this)
                        .setTitle("Eliminar destino")
                        .setMessage("¿Seguro que deseas eliminar ${destination.name}?")
                        .setPositiveButton("Sí") { _, _ ->

                            firebase.deleteDestinations(destination.id) { success ->
                                if (success) {
                                    Toast.makeText(this, "Eliminado", Toast.LENGTH_SHORT).show()
                                    loadDestinations()
                                } else {
                                    Toast.makeText(this, "Error al eliminar", Toast.LENGTH_SHORT).show()
                                }
                            }

                        }
                        .setNegativeButton("Cancelar", null)
                        .show()
                }
            )
        }
    }

    private fun logOut() {

        AlertDialog.Builder(this)
            .setTitle("Cerrar sesión")
            .setMessage("¿Seguro que deseas cerrar sesión?")
            .setPositiveButton("Sí") { _, _ ->

                firebase.logout()

                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
            .setNegativeButton("Cancelar", null)
            .show()
    }
}