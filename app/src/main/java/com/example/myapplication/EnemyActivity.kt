package com.example.myapplication

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.GridView
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class EnemyActivity : AppCompatActivity() {

    private lateinit var enemies: List<Enemy>

    // 🎵 背景音樂
    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enemy)

        // 🎵 播放背景音樂
        mediaPlayer = MediaPlayer.create(this, R.raw.battlecats_bgm)
        mediaPlayer?.isLooping = true
        mediaPlayer?.start()

        val grid = findViewById<GridView>(R.id.gridEnemy)
        val btnBackMain = findViewById<Button>(R.id.btnBackMain)

        enemies = listOf(

            Enemy(
                "狗仔",
                R.drawable.enemy1,
                90,
                8,
                110,
                "敵人的砲灰，可用任何貓咪將其擊潰，不過擊倒得到的金錢也不多"
            ),

            Enemy(
                "扭扭蛇",
                R.drawable.enemy2,
                100,
                15,
                110,
                "攻擊高一點點的砲灰"
            ),

            Enemy(
                "團結筷子幫",
                R.drawable.enemy3,
                200,
                20,
                110,
                "範圍傷害，較強的砲灰，建議使用坦克貓來抵擋傷害，不過擊殺金錢較多"
            ),

            Enemy(
                "河馬將",
                R.drawable.enemy4,
                1000,
                100,
                160,
                "第一隻小boss，建議升級噁心貓以及坦克貓來對付他，貓咪會被一口咬死"
            ),

            Enemy(
                "豬野郎",
                R.drawable.enemy5,
                1500,
                120,
                150,
                "紅色敵人，可用戰士貓來解決，建議不使用貓咪"
            ),

            Enemy(
                "企鵝大哥",
                R.drawable.enemy6,
                1300,
                80,
                150,
                "攻擊速度較快，不過是單體攻擊，可用貓咪(角色)做阻擋"
            ),

            Enemy(
                "大猩猩",
                R.drawable.enemy7,
                1000,
                80,
                150,
                "第二個小boss，攻擊和攻擊速度快，貓咪、坦克貓一同阻擋，使用鳥貓快速解決"
            ),

            Enemy(
                "咩咩",
                R.drawable.enemy8,
                500,
                50,
                110,
                "血量高的砲灰，慢慢打就可以"
            ),

            Enemy(
                "斑海豹桑",
                R.drawable.enemy9,
                2500,
                150,
                170,
                "第三隻小boss，紅色敵人，使用魚貓和戰士貓來解決"
            ),

            Enemy(
                "澎湃",
                R.drawable.enemy10,
                4000,
                645,
                400,
                "第四隻小boss，血厚攻擊高範圍傷害射程遠，但攻擊速度慢，建議使用貓海戰術把它耗死(別用坦克，戰士貓)"
            ),

            Enemy(
                "袋拳",
                R.drawable.enemy11,
                4000,
                250,
                160,
                "第5隻小boss，攻擊力高又快但單體傷害且射程短，用貓咪、坦克阻擋後集火攻擊"
            ),

            Enemy(
                "一角君",
                R.drawable.enemy12,
                15000,
                500,
                40,
                "第6隻小boss，紅色敵人，血厚攻高又快，但移動慢且射程短使用高傷害的貓咪集火攻擊"
            ),

            Enemy(
                "熊熊老師",
                R.drawable.enemy13,
                3000,
                1000,
                280,
                "第7隻小boss，範圍傷害攻擊高又遠，使用貓咪、坦克貓阻擋，並使用蜥蜴貓來對付他"
            ),

            Enemy(
                "鱷魚宅宅",
                R.drawable.enemy14,
                70,
                30,
                110,
                "攻擊速度快傷害多的砲灰，盡快解決，記得用砲灰阻擋"
            ),

            Enemy(
                "臭老兔",
                R.drawable.enemy15,
                500,
                50,
                110,
                "紅色敵人，砲灰"
            ),

            Enemy(
                "松鼠阿嘎",
                R.drawable.enemy16,
                80,
                30,
                110,
                "鱷魚宅宅升級版，應對方式相同"
            ),

            Enemy(
                "臉君",
                R.drawable.enemy17,
                99999,
                2000,
                340,
                "漂浮敵人，最終boss，記得存好錢包後再碰塔，貓咪坦克不能停，攻擊高但慢，可當作加強版澎湃，可透過堆疊龍貓來集火攻擊他，或者使用魚貓巨人貓直接強攻，但要記得自己金錢是否足夠，最優先保障出龍貓和砲灰坦克"
            ),

            Enemy(
                "蛾蛾蛾蛾",
                R.drawable.enemy18,
                5000,
                350,
                340,
                "漂浮敵人，使用龍貓在他射程範圍外攻擊他，攻擊前後搖大，一直出貓咪坦克貓就可以阻擋他"
            )
        )

        grid.adapter = object : BaseAdapter() {

            override fun getCount() = enemies.size

            override fun getItem(position: Int) = enemies[position]

            override fun getItemId(position: Int) = position.toLong()

            override fun getView(
                position: Int,
                convertView: android.view.View?,
                parent: android.view.ViewGroup?
            ): android.view.View {

                val imageView = ImageView(this@EnemyActivity)

                imageView.setImageResource(enemies[position].imageRes)

                imageView.layoutParams =
                    android.widget.AbsListView.LayoutParams(300, 300)

                imageView.scaleType =
                    ImageView.ScaleType.CENTER_CROP

                return imageView
            }
        }

        grid.setOnItemClickListener { _, _, position, _ ->

            val enemy = enemies[position]

            val intent =
                Intent(this, EnemyDetailActivity::class.java)

            intent.putExtra("name", enemy.name)
            intent.putExtra("image", enemy.imageRes)
            intent.putExtra("hp", enemy.hp)
            intent.putExtra("atk", enemy.attack)
            intent.putExtra("range", enemy.range)
            intent.putExtra("desc", enemy.desc)

            startActivity(intent)
        }

        btnBackMain.setOnClickListener {
            finish()
        }
    }

    // 🎵 離開畫面停止音樂
    override fun onDestroy() {
        super.onDestroy()

        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
    }
}