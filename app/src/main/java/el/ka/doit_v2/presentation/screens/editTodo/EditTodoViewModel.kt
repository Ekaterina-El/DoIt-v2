package el.ka.doit_v2.presentation.screens.editTodo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import el.ka.doit_v2.domain.EditTodoUseCase
import el.ka.doit_v2.domain.InsertTodoUseCase
import el.ka.doit_v2.domain.TodoModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class EditTodoViewModel @Inject constructor(
  private val editTodoUseCase: EditTodoUseCase,
  private val addTodoUseCase: InsertTodoUseCase
) : ViewModel() {

  private var currentTodoModel: TodoModel? = null
  val todoText = MutableLiveData("")

  private val _colorNumber = MutableLiveData(0)
  val colorNumber: LiveData<Int> = _colorNumber

  private val _onSaved = MutableLiveData(false)
  val onSaved: LiveData<Boolean> = _onSaved

  fun setColorNumber(number: Int) {
    _colorNumber.value = number
  }

  private fun onSaved() {
    _onSaved.value = true
  }

  private fun addTodo() {
    val todo = TodoModel(text = todoText.value!!, colorNumber = _colorNumber.value!!)
    viewModelScope.launch {
      addTodoUseCase(todo)
    }
  }

  private fun updateTodo() {
    val todo = currentTodoModel!!.copy(text = todoText.value!!, colorNumber = _colorNumber.value!!)
    viewModelScope.launch {
      editTodoUseCase(todo)
    }
  }

  fun save() {
    val todoText = todoText.value!!
    if (todoText != "") {
      if (currentTodoModel != null) updateTodo() else addTodo()
      onSaved()
    }
  }

  fun setTodoToEdit(todoModel: TodoModel) {
    todoText.value = todoModel.text
    _colorNumber.value = todoModel.colorNumber
    currentTodoModel = todoModel
  }
}