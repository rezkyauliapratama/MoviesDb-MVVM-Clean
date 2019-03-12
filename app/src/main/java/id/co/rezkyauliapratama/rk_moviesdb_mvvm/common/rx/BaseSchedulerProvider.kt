package id.co.rezkyauliapratama.rk_moviesdb_mvvm.common.rx

import io.reactivex.Scheduler

interface BaseSchedulerProvider {
    fun computation(): Scheduler

    fun io(): Scheduler

    fun ui(): Scheduler
}
