package uz.practice.rxjavawithapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import uz.practice.rxjavawithapi.di.DaggerApplicationGraph
import uz.practice.rxjavawithapi.databinding.ActivityMainBinding
import uz.practice.rxjavawithapi.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val daggerApplicationGraph = DaggerApplicationGraph.create()

        val viewModelFactory = daggerApplicationGraph.getMainViewModelFactory()
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(MainViewModel::class.java)

        binding.btnGetAdvice.setOnClickListener {
            viewModel.fetchAdvice()
        }

        viewModel.getAdviceLive().observe(this, Observer { advice ->
            binding.txtAdvice.text = advice?.advice;
        })

    }
}