package uz.practice.rxjavawithapi.viewmodel

import androidx.lifecycle.ViewModel
import uz.practice.rxjavawithapi.repository.MainRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainViewModel @Inject constructor(private val repository: MainRepository) : ViewModel() {
    
    fun getAdviceLive() = repository.adviceLive

    fun fetchAdvice() = repository.getRandomAdvice()

}