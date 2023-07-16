package el.ka.doit_v2.data

import androidx.lifecycle.LiveData
import el.ka.doit_v2.data.db.dao.DoItDao
import el.ka.doit_v2.domain.TodoModel
import el.ka.doit_v2.domain.TodosRepository

class TodosRepositoryImpl(private val dao: DoItDao): TodosRepository {
  override fun getTodos(): LiveData<List<TodoModel>> {
    return dao.getAllTodos()
  }

  override suspend fun insert(todoModel: TodoModel) {
    return dao.insert(todoModel)
  }

  override suspend fun delete(todoModel: TodoModel) {
    return dao.delete(todoModel)
  }

  override suspend fun edit(todoModel: TodoModel) {
    return dao.edit(todoModel)
  }

  override suspend fun deleteAll() {
    dao.deleteAllTodos()
  }

}