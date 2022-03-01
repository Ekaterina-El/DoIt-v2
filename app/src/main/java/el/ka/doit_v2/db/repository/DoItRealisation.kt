package el.ka.doit_v2.db.repository

import androidx.lifecycle.LiveData
import el.ka.doit_v2.db.dao.DoItDao
import el.ka.doit_v2.model.TodoModel

class DoItRealisation(private val doItDao: DoItDao): DoItRepository {
    override val allTodos: LiveData<List<TodoModel>>
        get() = doItDao.getAllTodos()

    override suspend fun insert(todoModel: TodoModel, onSuccess: () -> Unit) {
        doItDao.insert(todoModel)
        onSuccess()
    }

    override suspend fun delete(todoModel: TodoModel, onSuccess: () -> Unit) {
        doItDao.delete(todoModel)
        onSuccess()
    }

    override suspend fun editTodo(todoModel: TodoModel, onSuccess: () -> Unit) {
        doItDao.updateTodo(todoModel)
        onSuccess()
    }

}