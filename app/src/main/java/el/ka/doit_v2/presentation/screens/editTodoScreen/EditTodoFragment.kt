package el.ka.doit_v2.presentation.screens.editTodoScreen

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import el.ka.doit_v2.R
import el.ka.doit_v2.domain.TodoModel
import kotlinx.android.synthetic.main.fragment_edit_todo.*

class EditTodoFragment : Fragment(R.layout.fragment_edit_todo) {
  private var isEdit: Boolean = false
  private lateinit var currentTodoModel: TodoModel
  private lateinit var viewModel: EditTodoViewModel

  private lateinit var viewColors: List<ImageView>

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    initViewModel()
    initColors()
    addListenerForColorsView()

    findCurrentTodoModel()
    addListeners()
  }

  private fun addListeners() {
    addListenerForSaveBtn()
    addListenerForBackBtn()
  }

  private fun addListenerForBackBtn() {
    this.backBtn.setOnClickListener { findNavController().navigateUp() }
  }

  private fun addListenerForSaveBtn() {
    this.saveTaskBtn.setOnClickListener {
      val currentTodoText = this.taskText.text.toString()
      if (currentTodoText != "") {
        if (isEdit) editTodo(currentTodoText) else addTodo(currentTodoText)
        findNavController().navigateUp()
      }
    }
  }

  private fun addTodo(currentTodoText: String) {
    viewModel.addTodo(
      TodoModel(text = currentTodoText, colorNumber = viewModel.currentColorNumber)
    )
  }

  private fun editTodo(currentTodoText: String) {
    currentTodoModel.text = currentTodoText
    currentTodoModel.colorNumber = viewModel.currentColorNumber
    viewModel.updateTodo(currentTodoModel)
  }

  private fun initViewModel() {
    viewModel = ViewModelProvider(this).get(EditTodoViewModel::class.java)
  }

  private fun findCurrentTodoModel() {
    val argument = arguments?.getSerializable(TODO_KEY)
    try {
      currentTodoModel = argument as TodoModel
      this.taskText.setText(currentTodoModel.text)
      isEdit = true
    } catch (e: Exception) {
      currentTodoModel = TodoModel(isDone = false)
      isEdit = false
    }

    viewModel.currentColorNumber = currentTodoModel.colorNumber
    updateColorSelector()
  }

  private fun updateColorSelector() {
    viewColors.forEach { it.setImageDrawable(null) }
    viewColors[viewModel.currentColorNumber].setImageResource(R.drawable.ic_check)
  }

  private fun initColors() {
    viewColors = listOf<ImageView>(
      this.color0,
      this.color1,
      this.color2,
      this.color3,
      this.color4,
      this.color5,
    )
  }

  private fun addListenerForColorsView() {
    viewColors.forEach { color ->
      color.setOnClickListener {
        viewModel.currentColorNumber = it.tag.toString().toInt()
        updateColorSelector()
      }
    }
  }

  companion object {
    const val TODO_KEY = "todo"
  }
}