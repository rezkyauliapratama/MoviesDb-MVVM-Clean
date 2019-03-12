package id.co.rezkyauliapratama.rk_moviesdb_mvvm

import android.app.Application
import id.co.rezkyauliapratama.rk_moviesdb_mvvm.di.application.ApplicationComponent
import id.co.rezkyauliapratama.rk_moviesdb_mvvm.di.application.ApplicationModule
import id.co.rezkyauliapratama.rk_moviesdb_mvvm.di.application.DaggerApplicationComponent
import id.co.rezkyauliapratama.rk_moviesdb_mvvm.di.application.NetworkModule

class App : Application() {

    companion object {
      lateinit var component : ApplicationComponent
        lateinit var instance: App
        private set
    }

    override fun onCreate() {
        super.onCreate()

        component = initDagger(this)
        component.inject(this)

    }
    private fun initDagger(app: App): ApplicationComponent =
        DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(app))
            .networkModule(NetworkModule())
            .build()


}