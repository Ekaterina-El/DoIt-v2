package el.ka.doit_v2.screens.editTodoScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import el.ka.doit_v2.REPOSITORY
import el.ka.doit_v2.model.TodoModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditTodoViewModel : ViewModel() {
    fun addTodo(todoModel: TodoModel) {
        viewModelScope.launch(Dispatchers.IO) {
            REPOSITORY.insert(todoModel) {}
        }
    }

    fun updateTodo(todoModel: TodoModel) {
        viewModelScope.launch(Dispatchers.IO) {
            REPOSITORY.editTodo(todoModel) {}
        }
    }
}