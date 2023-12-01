package com.safezone.safezoneprueba

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.safezone.safezoneprueba.databinding.ActivityLoginBinding
import android.content.Context

class Login : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityLoginBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.buttonLogin.setOnClickListener {
            val email = binding.editTextEmail.text;
            val pass = binding.editTextPassword.text;
            if (email.isEmpty() || pass.isEmpty()) {
                // Muestra un mensaje de error o realiza alguna acción en caso de campos vacíos
                return@setOnClickListener
            }

            // Iniciar sesión con Firebase
            auth.signInWithEmailAndPassword(email.toString(), pass.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        sharedPreferences = getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
                        val user = auth.currentUser
                        val editor = sharedPreferences.edit()
                        editor.putString("uid", user?.uid.toString())
                        editor.putBoolean("isLogged", true)
                        editor.apply()

                        startActivity(Intent(this, MainActivity::class.java))
                    } else {

                        Toast.makeText(this, "Error: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                    }
                }



        }


    }
}
