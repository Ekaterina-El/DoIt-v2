package el.ka.doit_v2.db

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import el.ka.doit_v2.db.dao.DoItDao
import el.ka.doit_v2.model.TodoModel

@Database(entities = [TodoModel::class], version = 2)
abstract class DoItDatabase: RoomDatabase() {
    abstract fun getDoItDao(): DoItDao

    companion object {
        private var database: DoItDatabase? = null

        @Synchronized
        fun getInstance(context: Context): DoItDatabase {
            return if (database == null) {
                database = Room.databaseBuilder(context, DoItDatabase::class.java, "db").build()
                database as DoItDatabase
            } else {
                database as DoItDatabase
            }
        }
    }
}