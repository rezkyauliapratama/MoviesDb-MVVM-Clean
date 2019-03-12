package id.co.rezkyauliapratama.rk_moviesdb_mvvm.di.application

import androidx.room.Room
import androidx.room.RoomDatabase
import dagger.Module
import dagger.Provides
import id.co.rezkyauliapratama.rk_moviesdb_mvvm.App
import id.co.rezkyauliapratama.rk_moviesdb_mvvm.BuildConfig
import javax.inject.Singleton


@Module
class DatabaseModule{

    @Singleton
    @Provides
    fun provideRoomDatabase(): RoomDatabase {
        return Room.databaseBuilder(App.instance, RoomDatabase::class.java, BuildConfig.DATABASE_NAME)
            .setJournalMode(RoomDatabase.JournalMode.TRUNCATE)
            .build()
    }
}