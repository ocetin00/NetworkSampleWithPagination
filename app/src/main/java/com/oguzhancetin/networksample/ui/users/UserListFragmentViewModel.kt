package com.oguzhancetin.networksample.ui.users

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.oguzhancetin.networksample.data.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserListFragmentViewModel @Inject constructor(userRepository: UserRepository) :
    ViewModel() {

    val usersPagingDataFlow  = userRepository.getUserResult().cachedIn(viewModelScope)

}