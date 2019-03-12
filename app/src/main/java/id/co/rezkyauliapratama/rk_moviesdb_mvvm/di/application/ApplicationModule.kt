package id.co.rezkyauliapratama.rk_moviesdb_mvvm.di.application

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule(val application:Application){

    @Provides
    @ApplicationContext
    fun provideContext(): Context {
        return application
    }

    @Provides
    fun provideApplication(): Application{
        return application
    }

}