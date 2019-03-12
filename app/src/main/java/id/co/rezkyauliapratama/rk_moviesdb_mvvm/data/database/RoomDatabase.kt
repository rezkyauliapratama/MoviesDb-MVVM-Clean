package id.co.rezkyauliapratama.rk_moviesdb_mvvm.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import id.co.rezkyauliapratama.rk_moviesdb_mvvm.BuildConfig
import id.co.rezkyauliapratama.rk_moviesdb_mvvm.data.database.dao.AccountDao
import id.co.rezkyauliapratama.rk_moviesdb_mvvm.data.database.entity.UserTbl

@Database(
    entities = [UserTbl::class],
    version = BuildConfig.VERSION_CODE,
    exportSchema = false
)
abstract class RoomDatabase : RoomDatabase() {
    abstract fun accountDao(): AccountDao
}
