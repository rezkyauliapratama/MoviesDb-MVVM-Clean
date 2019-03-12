package id.co.rezkyauliapratama.rk_moviesdb_mvvm.di.application

import dagger.Component
import id.co.rezkyauliapratama.rk_moviesdb_mvvm.App
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, NetworkModule::class])
interface ApplicationComponent{

    fun inject(baseApplication: App)


}