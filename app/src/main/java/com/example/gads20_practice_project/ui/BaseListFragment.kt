package com.example.gads20_practice_project.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gads20_practice_project.R
import com.example.gads20_practice_project.ui.adapters.AchievementsAdapter

class BaseListFragment(val position: Int) : Fragment() {

    override fun onCreateView (
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragment = inflater.inflate(R.layout.fragment_base, container, false)
        Log.i(TAG, "Fragment $position is created!")

        val viewModel = activity?.run {
            ViewModelProvider(this)[MainViewModel::class.java]
        } ?: throw Exception("Invalid Activity")

        val recyclerView = fragment.findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter =
            AchievementsAdapter(
                position
            )
        val adapter = recyclerView.adapter as AchievementsAdapter
        if (position == 0) {
            Log.i(TAG, "learning hours")
            viewModel.learningLeaders.observe(viewLifecycleOwner) {
                Log.i(TAG, "Submit list of learning hours")
                adapter.submitList(it)
            }
        } else {
            Log.i(TAG, "skill iq")
            /**
             * If this don't work, why when we put it in another fragment it does?
             */
            viewModel.skillIQLeaders.observe(viewLifecycleOwner) {
                Log.i(TAG, "Submit list of skills")
                adapter.submitList(it)
            }
        }
        return fragment
    }

    companion object {
        private const val TAG = "BaseListFragment"
    }
}