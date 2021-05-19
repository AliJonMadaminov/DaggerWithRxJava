package uz.practice.rxjavawithapi.di

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import uz.practice.rxjavawithapi.network.AdviceApi
import uz.practice.rxjavawithapi.network.BASE_URL
import uz.practice.rxjavawithapi.repository.MainRepository
import uz.practice.rxjavawithapi.viewmodel.MainViewModel
import javax.inject.Inject
import javax.inject.Singleton

@Module
class ApplicationModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }

    @Provides
    @Inject
    fun getAdviceApi (retrofit: Retrofit): AdviceApi {
        return AdviceApi(retrofit)
    }

    @Provides
    @Inject
    fun getMainRepository(adviceApi: AdviceApi): MainRepository {
        return MainRepository(adviceApi)
    }

    @Provides
    @Inject
    fun provideMainViewModel(mainRepository: MainRepository):MainViewModel {
        return MainViewModel(mainRepository)
    }
}