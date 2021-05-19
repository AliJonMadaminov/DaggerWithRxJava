package uz.practice.rxjavawithapi.di

import dagger.Component
import uz.practice.rxjavawithapi.viewmodel.MainViewModel
import uz.practice.rxjavawithapi.viewmodel.MainViewModelFactory
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationGraph {

    @Singleton
    fun getMainViewModelFactory():MainViewModelFactory

}