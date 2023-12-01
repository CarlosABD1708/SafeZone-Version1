package com.safezone.safezoneprueba

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class SplashScreen : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        // Inicializar sharedPreferences después de onCreate
        sharedPreferences = getSharedPreferences("myPrefs", Context.MODE_PRIVATE)

        if (sharedPreferences.getBoolean("isLogged", false)) {
            startActivity(Intent(this, MainActivity::class.java))
        } else {
            startActivity(Intent(this, Login::class.java))
        }
        finish() // Puedes agregar esto si deseas finalizar la actividad actual después de iniciar la nueva actividad.
    }
}
