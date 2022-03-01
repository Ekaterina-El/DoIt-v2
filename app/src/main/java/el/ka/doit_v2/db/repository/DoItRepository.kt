package el.ka.doit_v2.db.repository

import androidx.lifecycle.LiveData
import el.ka.doit_v2.model.TodoModel

interface DoItRepository {
    val allTodos: LiveData<List<TodoModel>>

    suspend fun insert(todoModel: TodoModel, onSuccess: () -> Unit)
    suspend fun delete(todoModel: TodoModel, onSuccess: () -> Unit)
    suspend fun editTodo(todoModel: TodoModel, onSuccess: () -> Unit)
    suspend fun deleteAllTodos(onSuccess: () -> Unit)
}