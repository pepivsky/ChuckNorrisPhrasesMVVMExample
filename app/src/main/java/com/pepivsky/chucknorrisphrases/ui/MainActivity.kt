package com.pepivsky.chucknorrisphrases.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import com.pepivsky.chucknorrisphrases.R
import com.pepivsky.chucknorrisphrases.core.Resource
import com.pepivsky.chucknorrisphrases.data.remote.PhraseDataSource
import com.pepivsky.chucknorrisphrases.databinding.ActivityMainBinding
import com.pepivsky.chucknorrisphrases.presentation.PhraseViewModel
import com.pepivsky.chucknorrisphrases.presentation.ViewModelFactory
import com.pepivsky.chucknorrisphrases.repository.PhraseRepositoryImpl
import com.pepivsky.chucknorrisphrases.repository.RetrofitClient
import kotlin.math.log

class MainActivity : AppCompatActivity() {

    private val TAG: String = this.javaClass.simpleName
    lateinit var binding: ActivityMainBinding

    private val viewModel by viewModels<PhraseViewModel> { ViewModelFactory(PhraseRepositoryImpl(
        PhraseDataSource(RetrofitClient.webService)
    )) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.container.setOnClickListener {

            // observar el livedata
            viewModel.fetchPhrase().observe(this, { result ->
                when(result) {
                    is Resource.Loading -> {
                        Log.d("TAG", "onCreate: Loading...")
                        binding.spinner.visibility = View.VISIBLE
                    }
                    is Resource.Success -> {
                        Log.d("TAG", "onCreate: ${result.data.phrase}")
                        binding.groupNoInternet.visibility = View.GONE
                        binding.spinner.visibility = View.GONE
                        binding.tvPhrase.visibility = View.VISIBLE
                        binding.tvPhrase.text = result.data.phrase
                    }
                    is Resource.Failure -> {
                        Log.d("TAG", "onCreate: ${result.exception}")
                        binding.tvPhrase.visibility = View.GONE
                        binding.groupNoInternet.visibility = View.VISIBLE
                    }
                }
            })
        }
    }
}