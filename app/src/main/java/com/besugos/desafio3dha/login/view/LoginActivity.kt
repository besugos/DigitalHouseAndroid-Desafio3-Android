package com.besugos.desafio3dha.login.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.besugos.desafio3dha.home.view.HomeActivity
import com.besugos.desafio3dha.R

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val btnLogin = this.findViewById<Button>(R.id.btnLoginLogin)
        val btnRegister = this.findViewById<TextView>(R.id.txtCreateAccount)

        btnLogin.setOnClickListener() {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

        btnRegister.setOnClickListener() {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}