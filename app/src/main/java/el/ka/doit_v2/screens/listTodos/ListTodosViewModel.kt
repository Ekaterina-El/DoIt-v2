package el.ka.doit_v2.screens.listTodos

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import el.ka.doit_v2.APP
import el.ka.doit_v2.REPOSITORY
import el.ka.doit_v2.model.TodoModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListTodosViewModel: ViewModel() {
    fun getAllNotes(): LiveData<List<TodoModel>> = REPOSITORY.allTodos

    fun deleteTodo(todoModel: TodoModel) {
        viewModelScope.launch(Dispatchers.IO) {
            REPOSITORY.delete(todoModel) {
                Toast.makeText(APP, "Todo '${todoModel.text} was deleted'", Toast.LENGTH_SHORT).show()
            }
        }
    }
}