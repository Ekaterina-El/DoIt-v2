package el.ka.doit_v2.presentation.screens.editTodoScreen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import el.ka.doit_v2.data.TodosRepositoryImpl
import el.ka.doit_v2.data.db.LocalDatabase
import el.ka.doit_v2.domain.EditTodoUseCase
import el.ka.doit_v2.domain.InsertTodoUseCase
import el.ka.doit_v2.domain.TodoModel
import kotlinx.coroutines.launch

class EditTodoViewModel(application: Application) : AndroidViewModel(application) {
  private var currentTodoModel: TodoModel? = null
  val todoText = MutableLiveData("")

  private val _colorNumber = MutableLiveData(0)
  val colorNumber: LiveData<Int> = _colorNumber

  private val _onSaved = MutableLiveData(false)
  val onSaved: LiveData<Boolean> = _onSaved

  private val dao = LocalDatabase.getInstance(application).getDoItDao()
  private val repo = TodosRepositoryImpl(dao)
  private val editTodoUseCase = EditTodoUseCase(repo)
  private val addTodoUseCase = InsertTodoUseCase(repo)

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