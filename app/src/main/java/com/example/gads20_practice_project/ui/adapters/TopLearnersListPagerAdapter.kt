package com.example.gads20_practice_project.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.gads20_practice_project.ui.BaseListFragment

class TopLearnersListPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun createFragment(position: Int): Fragment = when (position) {
        LEARNING_LEADERS_POSITION -> BaseListFragment(
            position
        )
        SKILL_IQ_LEADERS_POSITION -> BaseListFragment(
            position
        )
        else -> throw IllegalArgumentException("No item")
    }

    override fun getItemCount(): Int =
        VIEWS_NUMBER

    companion object {
        const val VIEWS_NUMBER = 2

        const val LEARNING_LEADERS_POSITION = 0
        const val SKILL_IQ_LEADERS_POSITION = 1
    }

}