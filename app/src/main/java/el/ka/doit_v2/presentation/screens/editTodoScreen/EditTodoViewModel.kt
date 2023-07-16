package el.ka.doit_v2.presentation.screens.editTodoScreen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import el.ka.doit_v2.data.TodosRepositoryImpl
import el.ka.doit_v2.data.db.LocalDatabase
import el.ka.doit_v2.domain.*
import kotlinx.coroutines.launch

class EditTodoViewModel(application: Application) : AndroidViewModel(application) {
    var currentColorNumber: Int = 0

    val dao = LocalDatabase.getInstance(application).getDoItDao()
    private val repo = TodosRepositoryImpl(dao)
    private val editTodoUseCase = EditTodoUseCase(repo)
    private val addTodoUseCase = InsertTodoUseCase(repo)

    fun addTodo(todoModel: TodoModel) {
        viewModelScope.launch() {
            addTodoUseCase(todoModel)
        }
    }

    fun updateTodo(todoModel: TodoModel) {
        viewModelScope.launch() {
            editTodoUseCase(todoModel)
        }
    }
}