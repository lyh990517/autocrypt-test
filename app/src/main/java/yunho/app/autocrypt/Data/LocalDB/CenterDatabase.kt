package yunho.app.autocrypt.Data.LocalDB

import androidx.room.Database
import androidx.room.RoomDatabase
import yunho.app.autocrypt.Data.Entity.CenterEntity
import yunho.app.autocrypt.Data.LocalDB.Dao.CenterDao

@Database(
    entities = [CenterEntity::class],
    version = 1,
    exportSchema = false
)
abstract class CenterDatabase : RoomDatabase() {
    companion object{
        const val DB_NAME = "CenterDatabase.db"
    }
    abstract fun CenterDao() : CenterDao
}