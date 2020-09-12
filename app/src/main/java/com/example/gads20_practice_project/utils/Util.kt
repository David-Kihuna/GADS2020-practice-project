package com.example.gads20_practice_project.utils

import com.example.gads20_practice_project.network.models.LearningResponse
import com.example.gads20_practice_project.network.models.SkillResponse
import com.example.gads20_practice_project.ui.models.Achievement

fun List<SkillResponse>.fromSkillListToAchievementList(): MutableList<Achievement> {
    val list = mutableListOf<Achievement>()
    this.forEach {
        list.add(
            Achievement(
                it.name,
                it.score.toInt(),
                it.country
            )
        )
    }
    return list
}

fun List<LearningResponse>.fromLearningListToAchievementList(): MutableList<Achievement> {
    val list = mutableListOf<Achievement>()
    this.forEach {
        list.add(
            Achievement(
                it.name,
                it.hours.toInt(),
                it.country
            )
        )
    }
    return list
}