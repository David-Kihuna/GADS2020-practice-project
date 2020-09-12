package com.example.gads20_practice_project.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gads20_practice_project.ui.models.Achievement
import com.example.gads20_practice_project.utils.fromLearningListToAchievementList
import com.example.gads20_practice_project.utils.fromSkillListToAchievementList
import com.example.gads20_practice_project.network.Api
import kotlinx.coroutines.*
import java.lang.Exception

class MainViewModel : ViewModel() {

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(
        viewModelJob + Dispatchers.Main
    )

    private val _learningLeaders = MutableLiveData<List<Achievement>>()
    val learningLeaders: LiveData<List<Achievement>>
        get() = _learningLeaders

    private val _skillIQLeaders = MutableLiveData<List<Achievement>>()
    val skillIQLeaders: LiveData<List<Achievement>>
        get() = _skillIQLeaders

    init {
        coroutineScope.launch {
            withContext(Dispatchers.Main){
                getLearningLeaders()
                getSkillIQLeaders()
            }
        }
    }

    suspend fun getLearningLeaders() {
        try {
            val learningLeadersResponse = Api.retrofitService.getLearningLeaders()
            _learningLeaders.value =
                learningLeadersResponse.fromLearningListToAchievementList().sortedBy { it.value }
                    .asReversed()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    suspend fun getSkillIQLeaders() {
        try {
            val skillIQLeadersResponse = Api.retrofitService.getSkillIQLeaders()
            _skillIQLeaders.value =
                skillIQLeadersResponse.fromSkillListToAchievementList().sortedBy { it.value }
                    .asReversed()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}