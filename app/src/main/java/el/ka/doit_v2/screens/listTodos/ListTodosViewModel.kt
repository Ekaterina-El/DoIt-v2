package el.ka.doit_v2.screens.listTodos

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import el.ka.doit_v2.REPOSITORY
import el.ka.doit_v2.model.TodoModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListTodosViewModel: ViewModel() {
    fun getAllNotes(): LiveData<List<TodoModel>> = REPOSITORY.allTodos
}