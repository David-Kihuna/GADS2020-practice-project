package com.example.gads20_practice_project.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.gads20_practice_project.ui.models.Achievement
import com.example.gads20_practice_project.R

class AchievementsAdapter(val fragmentPosition: Int) :
    ListAdapter<Achievement, AchievementsAdapter.AchievementsViewHolder>(
        DiffCallback
    ) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AchievementsViewHolder {
        return AchievementsViewHolder(
            fragmentPosition,
            LayoutInflater.from(parent.context).inflate(
                R.layout.list_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: AchievementsViewHolder, position: Int) {
        val achievement = getItem(position)
        holder.bind(achievement)
    }

    class AchievementsViewHolder(val fragmentPosition: Int, var item: View) :
        RecyclerView.ViewHolder(item) {
        @SuppressLint("SetTextI18n")
        fun bind(achievement: Achievement) {
            val image = item.findViewById<ImageView>(R.id.image)
            image.setImageResource(if (fragmentPosition == 0) R.drawable.top_learner_badge else R.drawable.skill_iq_badge)

            val name = item.findViewById<TextView>(R.id.name)
            name.text = achievement.name

            val achievementSummary = item.findViewById<TextView>(R.id.achievement_summary)
            achievementSummary.text =
                "${achievement.value} ${if (fragmentPosition == 0) "learning hours" else "skill IQ Score"}, ${achievement.country}"
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Achievement>() {
        override fun areItemsTheSame(
            oldItem: Achievement,
            newItem: Achievement
        ): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(
            oldItem: Achievement,
            newItem: Achievement
        ): Boolean {
            return oldItem.name == newItem.name
        }
    }
}