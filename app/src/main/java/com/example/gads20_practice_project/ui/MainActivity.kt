package com.example.gads20_practice_project.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.gads20_practice_project.R
import com.example.gads20_practice_project.ui.adapters.TopLearnersListPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        setTheme(R.style.AppTheme)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewPager = findViewById<ViewPager2>(R.id.view_pager)
        viewPager.adapter =
            TopLearnersListPagerAdapter(
                supportFragmentManager,
                lifecycle
            )

        val tabLayout = findViewById<TabLayout>(R.id.tab_layout)
        TabLayoutMediator(tabLayout, viewPager) {currentTab, currentPosition ->
            currentTab.text = when (currentPosition) {
                TopLearnersListPagerAdapter.LEARNING_LEADERS_POSITION -> getString(
                    R.string.top_learning_leaders
                )
                TopLearnersListPagerAdapter.SKILL_IQ_LEADERS_POSITION -> getString(
                    R.string.top_iq_leaders
                )
                else -> getString(R.string.no_item)
            }
        }.attach()

        val submitButton = findViewById<Button>(R.id.submit_button)
        submitButton.setOnClickListener{
            val intent = Intent(this, SubmitActivity::class.java)
            startActivity(intent)
        }
    }
}