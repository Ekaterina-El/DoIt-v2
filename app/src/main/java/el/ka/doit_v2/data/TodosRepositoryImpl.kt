package el.ka.doit_v2.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import el.ka.doit_v2.data.db.DoItDao
import el.ka.doit_v2.domain.TodoModel
import el.ka.doit_v2.domain.TodosRepository
import javax.inject.Inject

class TodosRepositoryImpl @Inject constructor(
  private val dao: DoItDao,
  private val mapper: TodosMapper
) : TodosRepository {
  override fun getTodos(): LiveData<List<TodoModel>> {
    val todos = dao.getAllTodos().value ?: return MutableLiveData(listOf())
    val list = mapper.mapListDbModelsToModel(todos)
    return MutableLiveData(list)
  }

  override suspend fun insert(todoModel: TodoModel) {
    return dao.insert(mapper.todoModelToDbModel(todoModel))
  }

  override suspend fun delete(todoModel: TodoModel) {
    return dao.delete(mapper.todoModelToDbModel(todoModel))
  }

  override suspend fun edit(todoModel: TodoModel) {
    return dao.edit(mapper.todoModelToDbModel(todoModel))
  }
}