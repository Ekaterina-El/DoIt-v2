package el.ka.doit_v2.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import el.ka.doit_v2.model.TodoModel

@Dao
interface DoItDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(todoModel: TodoModel)

    @Delete
    suspend fun delete(todoModel: TodoModel)

    @Query("SELECT * FROM todos_table")
    fun getAllTodos(): LiveData<List<TodoModel>>
}