package com.pys.ex_kobisapi.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.pys.ex_kobisapi.R
import com.pys.ex_kobisapi.data.repository.MainRepository
import com.pys.ex_kobisapi.databinding.ActivityMainBinding
import com.pys.ex_kobisapi.ui.viewmodel.MainViewModel
import com.pys.ex_kobisapi.ui.viewmodel.viewmodelFactory.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding // viewBinding
    private lateinit var mainViewModel: MainViewModel // viewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = MainRepository(this)
        val mainViewModelFactory = MainViewModelFactory(repository)
        mainViewModel = ViewModelProvider(this, mainViewModelFactory)[MainViewModel::class.java]


    }
}