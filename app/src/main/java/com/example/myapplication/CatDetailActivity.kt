package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CatDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cat_detail)

        val btnBack = findViewById<Button>(R.id.btnBack)

        val img = findViewById<ImageView>(R.id.catImage)
        val name = findViewById<TextView>(R.id.catName)
        val info = findViewById<TextView>(R.id.catInfo)

        btnBack.setOnClickListener {
            finish()
        }

        img.setImageResource(intent.getIntExtra("image", 0))
        name.text = intent.getStringExtra("name")

        info.text =
            "費用: ${intent.getIntExtra("cost", 0)}\n" +
                    "HP: ${intent.getIntExtra("hp", 0)}\n" +
                    "攻擊: ${intent.getIntExtra("atk", 0)}\n" +
                    "射程: ${intent.getIntExtra("range", 0)}\n\n" +
                    intent.getStringExtra("desc")
    }
}