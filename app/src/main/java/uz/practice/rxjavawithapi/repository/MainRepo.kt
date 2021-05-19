package uz.practice.rxjavawithapi.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import uz.practice.rxjavawithapi.model.Advice
import uz.practice.rxjavawithapi.model.Root
import uz.practice.rxjavawithapi.network.AdviceApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepository @Inject constructor(val adviceApi: AdviceApi) {

    private val _adviceLive = MutableLiveData<Advice?>()

    fun getRandomAdvice() {
        adviceApi.adviceService?.getRoot()
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribeWith(object : Observer<Root?> {
                override fun onSubscribe(d: @NonNull Disposable?) {}
                override fun onNext(root: @NonNull Root?) {
                    Log.d("Something", "onNext: " + root!!.slip.advice)
                    _adviceLive.value = root.slip
                }

                override fun onError(e: @NonNull Throwable?) {
                    e?.printStackTrace()
                }

                override fun onComplete() {}
            })
    }

    val adviceLive: LiveData<Advice?> = _adviceLive
}