package yunho.app.autocrypt.Data.LocalDB

import android.content.Context
import androidx.room.Room

internal fun provideCenterDB(context: Context) = Room.databaseBuilder(context,CenterDatabase::class.java,CenterDatabase.DB_NAME).build()

internal fun provideCenterDao(centerDatabase: CenterDatabase) = centerDatabase.CenterDao()