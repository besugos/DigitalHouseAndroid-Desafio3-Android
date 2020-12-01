package com.besugos.desafio3dha.home.view

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.besugos.desafio3dha.R
import com.besugos.desafio3dha.home.viewmodel.ComicViewModel
import com.squareup.picasso.Picasso



class DetailActivity : AppCompatActivity() {

    private lateinit var _viewModel: ComicViewModel

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val id = intent.getStringExtra("COMICS_ID")
        val issueTitle = intent.getStringExtra("COMICS_TITLE")
//        val issueNumber = intent.getStringExtra("COMICS_EDITION")
        val description = intent.getStringExtra("COMICS_DESCRIPTION")
        val pageCount = intent.getStringExtra("COMICS_PAGES")
        val date = intent.getStringExtra("COMICS_DATE")
        val price = intent.getStringExtra("COMICS_PRECO")
        val thumbnail = intent.getStringExtra("COMICS_THUMBNAIL")

//        val image = intent.getStringExtra("COMICS_IMAGEM")

        findViewById<TextView>(R.id.txtIssueTitle).text = issueTitle
//        findViewById<TextView>(R.id.txtIssueNumber).text = issueNumber
        findViewById<TextView>(R.id.txtBody).text = formatDescription(description)
        findViewById<TextView>(R.id.txtPublished).text = formatDate(date!!)
        findViewById<TextView>(R.id.txtPrice).text = formatPrice(price!!)
        findViewById<TextView>(R.id.txtPages).text = formatPageCount(pageCount!!)

        Picasso.get()
            .load(thumbnail)
            .into(findViewById<ImageView>(R.id.imgCover))







        val btnBack = findViewById<ImageView>(R.id.imgBack)

        btnBack.setOnClickListener {
            finish()
        }
    }

    private fun formatDate(date: String): String {
        if (date[0] == '-') {
            return "Not Available"
        } else {
            val year = date.substring(0,4)
            val month = date.substring(5,7)
            val day = date.substring(8,10)
            val monthNumber = month.toInt()
            val monthNames = listOf("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December")

            return monthNames[monthNumber] + " " + day + ", " + year
        }
    }

    private fun formatPrice(price: String): String {
        if (price.toDouble() == 0.0) {
            return "Not Available"
        } else if (price.length < 4) {
            return ("$" + price + "0")
        } else {
            return ("$$price")
        }
    }

    private fun formatPageCount(pageCount: String): String {
        if (pageCount == "0") {
            return "Not Available"
        } else {
            return (pageCount)
        }
    }

    private fun formatDescription(description: String?): String {
        if (description.isNullOrEmpty()) {
            return "Not Available"
        } else {
            val onlyDescription = description.lines()
            val formattedDescription = onlyDescription[0].split("<")
            return formattedDescription[0]
        }

    }


}