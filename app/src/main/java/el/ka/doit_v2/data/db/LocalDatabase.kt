package el.ka.doit_v2.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import el.ka.doit_v2.domain.TodoModel

@Database(entities = [TodoDBModel::class], version = 2)
abstract class LocalDatabase: RoomDatabase() {
    abstract fun getDoItDao(): DoItDao

    companion object {
        private var database: LocalDatabase? = null

        @Synchronized
        fun getInstance(context: Context): LocalDatabase {
            return if (database == null) {
                database = Room.databaseBuilder(context, LocalDatabase::class.java, "db").build()
                database as LocalDatabase
            } else {
                database as LocalDatabase
            }
        }
    }
}