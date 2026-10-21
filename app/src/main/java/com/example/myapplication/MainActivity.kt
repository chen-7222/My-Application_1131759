package com.example.myapplication

import android.content.Intent

import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // 🔘 主要按鈕
        val btnEnemy = findViewById<Button>(R.id.btnEnemy)
        val btnStage = findViewById<Button>(R.id.btnStage)
        val btnCat = findViewById<Button>(R.id.btnCat)
        val btnWeb = findViewById<Button>(R.id.btnWeb)

        // 👾 敵人圖鑑
        btnEnemy.setOnClickListener {
            startActivity(Intent(this, EnemyActivity::class.java))
        }

        // 📜 關卡資訊
        btnStage.setOnClickListener {
            startActivity(Intent(this, StageActivity::class.java))
        }

        // 🐱 貓咪圖鑑
        btnCat.setOnClickListener {
            startActivity(Intent(this, CatActivity::class.java))
        }

        // 🌐 外部網站（彈出確認視窗）
        btnWeb.setOnClickListener {

            AlertDialog.Builder(this)
                .setTitle("即將跳轉")
                .setMessage("你即將前往外部網站《貓咪大戰爭》，是否繼續？")

                .setPositiveButton("確認") { _, _ ->
                    val url =
                        "https://play.google.com/store/apps/details?id=jp.co.ponos.battlecatstw&hl=zh_TW"

                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                    startActivity(intent)
                }

                .setNegativeButton("取消") { dialog, _ ->
                    dialog.dismiss()
                }

                .show()
        }
        playMusicOnce()
    }
    private fun playMusicOnce() {

        // 🔊 播放 cat_click.mp3（或你的音樂檔）
        val resId = resources.getIdentifier(
            "cat_click",
            "raw",
            packageName
        )

        if (resId != 0) {

            mediaPlayer = MediaPlayer.create(this, resId)

            mediaPlayer?.isLooping = false   // ❌ 不循環（只播一次）

            mediaPlayer?.start()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.release()
        mediaPlayer = null
    }
}