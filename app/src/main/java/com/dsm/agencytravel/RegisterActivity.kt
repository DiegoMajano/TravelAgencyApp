package com.dsm.agencytravel

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.dsm.agencytravel.firebase.FirebaseService
import com.google.android.material.textfield.TextInputEditText

class RegisterActivity : AppCompatActivity() {
    private val firebase = FirebaseService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val etEmail = findViewById<TextInputEditText>(R.id.etEmail)
        val etPassword = findViewById<TextInputEditText>(R.id.etPassword)
        val btnRegister = findViewById<Button>(R.id.btnFormRegister)
        val etConfirmPassword = findViewById<TextInputEditText>(R.id.etConfirmPassword)

        btnRegister.setOnClickListener {

            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()
            val confirmPassword = etConfirmPassword.text.toString().trim()

            var isValid = true

            etEmail.error = null
            etPassword.error = null
            etConfirmPassword.error = null

            if (email.isEmpty()) {
                etEmail.error = "Ingrese un correo"
                isValid = false
            } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                etEmail.error = "Correo inválido"
                isValid = false
            }

            if (password.isEmpty()) {
                etPassword.error = "Ingrese una contraseña"
                isValid = false
            } else if (password.length < 6) {
                etPassword.error = "Mínimo 6 caracteres"
                isValid = false
            }

            if (confirmPassword.isEmpty()) {
                etConfirmPassword.error = "Confirme la contraseña"
                isValid = false
            } else if (password != confirmPassword) {
                etConfirmPassword.error = "Las contraseñas no coinciden"
                isValid = false
            }

            if (!isValid) return@setOnClickListener

            firebase.register(email, password) { success, error ->
                if (success) {
                    Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(this, "Correo en uso, utilizar correo válido", Toast.LENGTH_LONG).show()
                }
            }
        }

        findViewById<TextView>(R.id.tvLogin).setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}