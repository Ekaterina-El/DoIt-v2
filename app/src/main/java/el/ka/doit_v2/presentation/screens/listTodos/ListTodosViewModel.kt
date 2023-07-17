package el.ka.doit_v2.presentation.screens.listTodos

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import el.ka.doit_v2.data.TodosRepositoryImpl
import el.ka.doit_v2.data.db.LocalDatabase
import el.ka.doit_v2.domain.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class ListTodosViewModel @Inject constructor(
  private val getAllUseCase: GetTodosUseCase,
  private val editTodoUseCase: EditTodoUseCase,
  private val deleteTodoUseCase: DeleteTodoUseCase,
  private val addTodoUseCase: InsertTodoUseCase,
) : ViewModel() {
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
    viewModelScope.launch {
      addTodoUseCase(todoModel)
    }
  }

  fun updateStatus(todoModel: TodoModel, status: Boolean) {
    val todo = todoModel.copy(isDone = status)
    updateTodo(todo)
  }
}