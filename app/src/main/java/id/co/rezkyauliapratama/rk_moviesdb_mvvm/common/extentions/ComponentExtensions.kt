package id.co.rezkyauliapratama.rk_moviesdb_mvvm.common.extentions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T> LifecycleOwner.observe(liveData: LiveData<T>, action: (t: T) -> Unit) {
    liveData.observe(this, Observer { it -> it?.let { action(it) } })
}