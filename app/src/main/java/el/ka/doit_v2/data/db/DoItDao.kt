package el.ka.doit_v2.data.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface DoItDao {
  @Insert(onConflict = OnConflictStrategy.IGNORE)
  suspend fun insert(todoModel: TodoDBModel)

  @Delete
  suspend fun delete(todoModel: TodoDBModel)

  @Query("SELECT * FROM todos_table")
  fun getAllTodos(): LiveData<List<TodoDBModel>>

  @Update
  suspend fun edit(todoModel: TodoDBModel)
}