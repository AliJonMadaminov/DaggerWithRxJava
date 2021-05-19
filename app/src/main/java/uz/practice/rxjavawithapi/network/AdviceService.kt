package uz.practice.rxjavawithapi.network

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import uz.practice.rxjavawithapi.model.Root
import javax.inject.Inject

const val BASE_URL = "https://api.adviceslip.com/"

interface AdviceService {

    @GET("advice")
    fun getRoot(): Observable<Root>
}


class AdviceApi @Inject constructor(retrofit: Retrofit) {
    val adviceService: AdviceService? = retrofit.create(AdviceService::class.java)
}

