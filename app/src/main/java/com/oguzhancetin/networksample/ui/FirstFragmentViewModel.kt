package com.oguzhancetin.networksample.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oguzhancetin.networksample.model.Civilization
import com.oguzhancetin.networksample.repository.AgeOfRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FirstFragmentViewModel @Inject constructor(val ageOfRepository: AgeOfRepository) :
    ViewModel() {
    val civilizationList = MutableLiveData<List<Civilization>>()

    init {
        viewModelScope.launch {
            civilizationList.value = ageOfRepository.getCivilizations()

        }
    }
}