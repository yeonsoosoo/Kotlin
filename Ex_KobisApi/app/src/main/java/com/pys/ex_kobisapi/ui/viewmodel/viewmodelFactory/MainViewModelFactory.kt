package com.pys.ex_kobisapi.ui.viewmodel.viewmodelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pys.ex_kobisapi.data.repository.MainRepository
import com.pys.ex_kobisapi.ui.viewmodel.MainViewModel

class MainViewModelFactory(private val repository: MainRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }
}