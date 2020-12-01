package com.besugos.desafio3dha.home.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.besugos.desafio3dha.R

class FullCoverActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_cover)



        val btnClose = findViewById<ImageView>(R.id.imgBack)
        btnClose.setOnClickListener {
            finish()
        }
    }
}