package el.ka.doit_v2.presentation.screens.listTodos

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import el.ka.doit_v2.data.TodosRepositoryImpl
import el.ka.doit_v2.data.db.LocalDatabase
import el.ka.doit_v2.domain.*
import kotlinx.coroutines.launch

class ListTodosViewModel(application: Application) : AndroidViewModel(application) {
  val dao = LocalDatabase.getInstance(application).getDoItDao()
  private val repo = TodosRepositoryImpl(dao)
  private val getAllUseCase = GetTodosUseCase(repo)
  private val editTodoUseCase = EditTodoUseCase(repo)
  private val deleteTodoUseCase = DeleteTodoUseCase(repo)
  private val addTodoUseCase = InsertTodoUseCase(repo)

  val notes: LiveData<List<TodoModel>> = getAllUseCase()

  fun deleteTodo(todoModel: TodoModel) {
    viewModelScope.launch {
      deleteTodoUseCase(todoModel)
    }
  }

  private fun updateTodo(todoModel: TodoModel) {
    viewModelScope.launch {
      editTodoUseCase(todoModel)
    }
  }

  fun addTodo(todoModel: TodoModel) {
    viewModelScope.launch() {
      addTodoUseCase(todoModel)
    }
  }

  fun updateStatus(todoModel: TodoModel, status: Boolean) {
    val todo = todoModel.copy(isDone = status)
    updateTodo(todo)
  }
}