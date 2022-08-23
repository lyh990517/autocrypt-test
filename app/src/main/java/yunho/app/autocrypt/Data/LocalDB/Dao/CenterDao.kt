package yunho.app.autocrypt.Data.LocalDB.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import yunho.app.autocrypt.Data.Entity.CenterEntity

@Dao
interface CenterDao {
    @Query("SELECT * FROM CenterEntity")
    suspend fun getAll() : List<CenterEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOne(centerEntity: CenterEntity)


}