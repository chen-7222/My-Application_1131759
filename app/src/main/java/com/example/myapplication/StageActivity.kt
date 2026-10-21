package com.example.myapplication

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class StageActivity : AppCompatActivity() {

    private lateinit var stageList: List<Stage>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stage)

        val spinner = findViewById<Spinner>(R.id.spinnerStage)
        val tvTitle = findViewById<TextView>(R.id.tvStageTitle)
        val tvGuide = findViewById<TextView>(R.id.tvGuide)
        val tvCats = findViewById<TextView>(R.id.tvCats)
        val enemyContainer = findViewById<LinearLayout>(R.id.enemyContainer)
        val btnBack = findViewById<Button>(R.id.btnBack)

        // ✅ 直接讀 48關資料
        stageList = StageRepository.stages

        // ✅ Spinner 顯示關卡名稱
        spinner.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            stageList.map { it.title }
        )

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val stage = stageList[position]

                // 顯示標題
                tvTitle.text = stage.title
                tvGuide.text = "攻略：${stage.guide}"
                tvCats.text = "推薦貓咪：${stage.recommendedCats.joinToString()}"

                // 清空敵人列表
                enemyContainer.removeAllViews()

                // 動態生成敵人按鈕
                stage.enemies.forEach { enemy ->

                    val btn = Button(this@StageActivity)

                    btn.text = "${enemy.name}\n" +
                            "數量：${enemy.count}\n" +
                            "初登場：${enemy.spawnTime}\n" +
                            "再登場：${enemy.respawn}"

                    btn.setOnClickListener {
                        Toast.makeText(
                            this@StageActivity,
                            "敵人：${enemy.name}",
                            Toast.LENGTH_SHORT
                        ).show()

                        // 👉 之後可以跳 EnemyActivity
                    }

                    enemyContainer.addView(btn)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        btnBack.setOnClickListener {
            finish()
        }
    }
}