package id.co.rezkyauliapratama.rk_moviesdb_mvvm.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import id.co.rezkyauliapratama.rk_moviesdb_mvvm.data.database.entity.UserTbl
import io.reactivex.Maybe
import io.reactivex.Single

@Dao
interface AccountDao :
    BaseDao<UserTbl> {

    @Query("SELECT * FROM ${UserTbl.TABLE_NAME} WHERE ${UserTbl.COLUMN_ID} = :id")
    override fun getItem(id: Long): Maybe<UserTbl>

    @Query("SELECT * FROM ${UserTbl.TABLE_NAME} ORDER BY ${UserTbl.COLUMN_ID} ASC")
    override fun getItems(): Single<List<UserTbl>>

    @Query("DELETE FROM ${UserTbl.TABLE_NAME}")
    override fun deleteAll()
}
