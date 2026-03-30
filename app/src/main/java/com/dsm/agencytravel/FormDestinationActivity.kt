package com.dsm.agencytravel

import android.net.Uri
import android.os.Bundle
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.dsm.agencytravel.firebase.FirebaseService
import com.dsm.agencytravel.model.Destination

class FormDestinationActivity : AppCompatActivity() {

    private var imageUri: Uri? = null
    private val firebase = FirebaseService()

    private var destinationEdit: Destination? = null

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

        destinationEdit = intent.getSerializableExtra("destino") as? Destination

        val countries = resources.getStringArray(R.array.countries_array)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, countries)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        destinationEdit?.let {
            etName.setText(it.name)
            etPrice.setText(it.price.toString())
            etDescription.setText(it.description)

            val position = countries.indexOf(it.country)
            if (position >= 0) spinner.setSelection(position)

            if (it.imageUrl.isNotEmpty()) {
                Glide.with(this)
                    .load(it.imageUrl)
                    .into(imgPreview)
            }
        }

        val launcher = registerForActivityResult(ActivityResultContracts.GetContent()) {
            imageUri = it
            imgPreview.setImageURI(it)
        }

        btnSelect.setOnClickListener {
            launcher.launch("image/*")
        }

        btnSave.setOnClickListener {

            val name = etName.text.toString().trim()
            val priceText = etPrice.text.toString().trim()
            val description = etDescription.text.toString().trim()
            val country = spinner.selectedItem.toString()

            val price = priceText.toDoubleOrNull()

            if (name.isEmpty()) {
                etName.error = "Nombre requerido"
                return@setOnClickListener
            }

            if (price == null || price <= 0) {
                etPrice.error = "Precio inválido"
                return@setOnClickListener
            }

            if (description.length < 20) {
                etDescription.error = "Mínimo 20 caracteres"
                return@setOnClickListener
            }

            if (destinationEdit == null) {
                if (imageUri == null) {
                    Toast.makeText(this, "Selecciona una imagen", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                val destination = Destination(
                    id = "",
                    name = name,
                    country = country,
                    price = price,
                    description = description,
                    imageUrl = ""
                )

                Toast.makeText(this, "Guardando...", Toast.LENGTH_SHORT).show()
                btnSave.isEnabled = false

                firebase.saveDestination(destination, imageUri!!) { success ->
                    if (success) {
                        Toast.makeText(this, "Destino guardado", Toast.LENGTH_SHORT).show()
                        finish()
                    } else {
                        Toast.makeText(this, "Error al guardar", Toast.LENGTH_SHORT).show()
                    }
                    btnSave.isEnabled = true
                }

            } else {
                val updated = destinationEdit!!.copy(
                    name = name,
                    country = country,
                    price = price,
                    description = description
                )

                Toast.makeText(this, "Actualizando...", Toast.LENGTH_SHORT).show()
                btnSave.isEnabled = false

                if (imageUri != null) {
                    firebase.updateDestinations(updated, imageUri!!) { success ->
                        if (success) {
                            Toast.makeText(this, "Destino actualizado", Toast.LENGTH_SHORT).show()
                            btnSave.isEnabled = true
                            finish()
                        }
                    }
                } else {
                    firebase.updateDestinationWithoutImage(updated) { success ->
                        if (success) {
                            Toast.makeText(this, "Destino actualizado", Toast.LENGTH_SHORT).show()
                            btnSave.isEnabled = true
                            finish()
                        }
                    }
                }
            }
        }
    }
}