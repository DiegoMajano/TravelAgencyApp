package com.dsm.agencytravel

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class FormDestinationActivity : AppCompatActivity() {

    private var imageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_destination)

        val etName = findViewById<EditText>(R.id.etName)
        val etPrice = findViewById<EditText>(R.id.etPrice)
        val etDescription = findViewById<EditText>(R.id.etDescription)
        val imgPreview = findViewById<ImageView>(R.id.imgPreview)
        val btnSelect = findViewById<Button>(R.id.btnSelectImage)
        val btnSave = findViewById<Button>(R.id.btnSave)
        val spinner = findViewById<Spinner>(R.id.spCountry)

        // Spinner países
        val countries = resources.getStringArray(R.array.paises)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, countries)
        spinner.adapter = adapter

        // Selector de imagen
        val launcher = registerForActivityResult(ActivityResultContracts.GetContent()) {
            imageUri = it
            imgPreview.setImageURI(it)
        }

        btnSelect.setOnClickListener {
            launcher.launch("image/*")
        }

        btnSave.setOnClickListener {

            val name = etName.text.toString()
            val price = etPrice.text.toString().toDoubleOrNull()
            val description = etDescription.text.toString()

            if (name.isEmpty() || price == null || price <= 0 || description.length < 20 || imageUri == null) {
                Toast.makeText(this, "Validación incorrecta", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Destino guardado (simulado)", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }
}