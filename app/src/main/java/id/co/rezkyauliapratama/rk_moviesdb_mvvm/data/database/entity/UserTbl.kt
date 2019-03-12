package id.co.rezkyauliapratama.rk_moviesdb_mvvm.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = UserTbl.TABLE_NAME)
data class UserTbl(@PrimaryKey(autoGenerate = true) val id: Long) {

    @ColumnInfo(name = COLUMN_NAME)
    @SerializedName("name")
    lateinit var name: String

    @ColumnInfo(name = COLUMN_AVATAR_URL)
    @SerializedName("avatarUrl")
    lateinit var avatarUrl: String

    @ColumnInfo(name = COLUMN_MASKED_PHONE_NUMBER)
    @SerializedName("maskedPhoneNumber")
    lateinit var maskedPhoneNumber: String

    companion object {
        const val TABLE_NAME = "UserTbl"
        const val COLUMN_ID = "id"
        const val COLUMN_NAME = "name"
        const val COLUMN_AVATAR_URL = "avatarUrl"
        const val COLUMN_MASKED_PHONE_NUMBER = "maskedPhoneNumber"
    }
}