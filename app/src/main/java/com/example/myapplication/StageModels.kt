package com.example.myapplication

data class Stage(
    val title: String,
    val guide: String,
    val recommendedCats: List<String>,
    val enemies: List<EnemyInfo>
)

data class EnemyInfo(
    val name: String,
    val count: String,
    val spawnTime: Int,
    val respawn: String
)