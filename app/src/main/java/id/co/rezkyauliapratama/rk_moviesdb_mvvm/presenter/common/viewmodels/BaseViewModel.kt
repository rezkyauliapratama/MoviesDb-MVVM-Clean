package id.co.rezkyauliapratama.rk_moviesdb_mvvm.presenter.common.viewmodels

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
abstract class BaseViewModel : ViewModel(){

    private val compositeDisposable = CompositeDisposable()


    protected fun Disposable.track() {
        compositeDisposable.add(this)
    }

    override fun onCleared() {
        compositeDisposable.clear()
    }
}