package com.f0x1d.weather.viewmodel.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.f0x1d.weather.utils.Event
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

abstract class BaseViewModel<T : Any>: ViewModel() {
    val stateData = MutableLiveData<DataState<T>>()
    val eventsData = MutableLiveData<Event>()

    private val compositeDisposable = CompositeDisposable()

    protected fun <D : Any> subscribeAndPost(single: Single<D>, mapper: (D) -> T) {
        stateData.value = DataState.Loading()

        single
            .map(mapper)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe(compositeDisposable::add)
            .subscribe({
                stateData.value = DataState.Loaded(it)
            }, {
                it.printStackTrace()
                stateData.value = DataState.Error(it)
            })
    }

    protected fun subscribeAndPost(single: Single<T>) = subscribeAndPost(single) { it }

    @JvmName("subscribeAndPostExtension")
    protected fun <D : Any> Single<D>.subscribeAndPost(mapper: (D) -> T) = subscribeAndPost(this, mapper)

    @JvmName("subscribeAndPostSameExtension")
    protected fun Single<T>.subscribeAndPost() = subscribeAndPost(this)

    protected fun Disposable.addToContainer() = compositeDisposable.add(this)

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}

sealed class DataState<T> {
    class Loading<T>: DataState<T>()
    data class Error<T>(val t: Throwable): DataState<T>()
    data class Loaded<T>(val data: T): DataState<T>()
}