package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.text.SpannableString
import android.text.Spanned
import android.text.style.UnderlineSpan

class EnemyDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enemy_detail)

        val img = findViewById<ImageView>(R.id.imgEnemy)
        val name = findViewById<TextView>(R.id.tvEnemyName)
        val info = findViewById<TextView>(R.id.tvEnemyInfo)
        val desc = findViewById<TextView>(R.id.tvEnemyDesc)
        val btnBack = findViewById<Button>(R.id.btnBack)

        img.setImageResource(intent.getIntExtra("image", 0))

        name.text = intent.getStringExtra("name")

        info.text =
            "血量：" + intent.getIntExtra("hp", 0) +
                    " 攻擊力：" + intent.getIntExtra("atk", 0) +
                    " 射程：" + intent.getIntExtra("range", 0)

        val descText = intent.getStringExtra("desc") ?: ""

        val spannable = SpannableString(descText)
        spannable.setSpan(
            UnderlineSpan(),
            0,
            descText.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        desc.text = spannable

        btnBack.setOnClickListener {
            finish()
        }
    }
}