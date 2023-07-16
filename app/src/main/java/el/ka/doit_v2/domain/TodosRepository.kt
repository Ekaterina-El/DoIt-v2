package el.ka.doit_v2.domain

import androidx.lifecycle.LiveData

interface TodosRepository {
  fun getTodos(): LiveData<List<TodoModel>>
  suspend fun insert(todoModel: TodoModel)
  suspend fun delete(todoModel: TodoModel)
  suspend fun edit(todoModel: TodoModel)
  suspend fun deleteAll()
}