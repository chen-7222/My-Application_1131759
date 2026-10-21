package com.example.myapplication

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import android.widget.AbsListView

class CatActivity : AppCompatActivity() {

    private lateinit var cats: List<Cat>
    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cat)

        // 🎵 BGM
        mediaPlayer = MediaPlayer.create(this, R.raw.battlecats_bgm)
        mediaPlayer?.isLooping = true
        mediaPlayer?.start()

        val grid = findViewById<GridView>(R.id.gridCat)
        val btnBack = findViewById<Button>(R.id.btnBackCat)

        // 🐱 9隻貓咪資料
        cats = listOf(
            Cat("貓咪(10級)", R.drawable.cat1, 50, 280, 22, 140, "砲灰，不用升級"),
            Cat("坦克貓(10級)", R.drawable.cat2, 100, 1120, 6, 110, "高血量肉盾，優先升級"),
            Cat("戰士貓(10級)", R.drawable.cat3, 200, 560, 70, 150, "對紅善於攻擊，但前面經驗值若不足可以不練"),
            Cat("噁心貓(10級)", R.drawable.cat4, 400, 1120, 280, 350, "中距離單體輸出，必練，前面經驗值全數給他"),
            Cat("牛貓(10級)", R.drawable.cat5, 500, 1400, 36, 140, "高速輸出，但前面很弱，千萬別練"),
            Cat("鳥貓(10級)", R.drawable.cat6, 650, 840, 504, 150, "中距離範圍輸出，血量少射程短但傷害高，優先升級"),
            Cat("魚貓(10級)", R.drawable.cat7, 800, 1960, 350, 450, "近距離坦攻，對紅善於攻擊，可用於對付豬野郎，有多餘經驗可以升級他"),
            Cat("蜥蜴貓(10級)", R.drawable.cat8, 1000, 2240, 980, 400, "遠距離單體輸出，拿到以後必練，使用它實際得用肉盾砲灰來保護他"),
            Cat("巨人貓(10級)", R.drawable.cat9, 1300, 28000, 784, 150, "範圍坦攻，可以硬扛蛾蛾蛾蛾，有多餘經驗可以升級他")
        )

        // 📌 GridView Adapter
        grid.adapter = object : BaseAdapter() {

            override fun getCount() = cats.size

            override fun getItem(position: Int) = cats[position]

            override fun getItemId(position: Int) = position.toLong()

            override fun getView(
                position: Int,
                convertView: android.view.View?,
                parent: android.view.ViewGroup?
            ): android.view.View {

                val imageView = ImageView(this@CatActivity)

                imageView.setImageResource(cats[position].imageRes)

                // ✅ 修正點（重點在這裡）
                imageView.layoutParams =
                    AbsListView.LayoutParams(300, 300)

                imageView.scaleType = ImageView.ScaleType.CENTER_CROP

                return imageView
            }
        }

        // 📌 點擊進入詳細頁
        grid.setOnItemClickListener { _, _, position, _ ->

            val cat = cats[position]

            val intent = Intent(this, CatDetailActivity::class.java)

            intent.putExtra("name", cat.name)
            intent.putExtra("image", cat.imageRes)
            intent.putExtra("cost", cat.cost)
            intent.putExtra("hp", cat.hp)
            intent.putExtra("atk", cat.attack)
            intent.putExtra("range", cat.range)
            intent.putExtra("desc", cat.desc)

            startActivity(intent)
        }

        // 🔙 返回
        btnBack.setOnClickListener {
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.release()
        mediaPlayer = null
    }
}