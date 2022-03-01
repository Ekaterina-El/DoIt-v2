package el.ka.doit_v2.screens.editTodoScreen

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import el.ka.doit_v2.APP
import el.ka.doit_v2.R
import el.ka.doit_v2.TODO_KEY
import el.ka.doit_v2.model.TodoModel
import kotlinx.android.synthetic.main.fragment_edit_todo.*

class EditTodoFragment : Fragment(R.layout.fragment_edit_todo) {
    private var isEdit: Boolean = false
    private lateinit var currentTodoModel: TodoModel
    private lateinit var viewModel: EditTodoViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        findCurrentTodoModel()
        initViewModel()
        addListeners()
        //Toast.makeText(APP, "IsEdit: $isEdit", Toast.LENGTH_SHORT).show()
    }

    private fun addListeners() {
        addListenerForSaveBtn()
        addListenerForBackBtn()
    }

    private fun addListenerForBackBtn() {
        this.backBtn.setOnClickListener { APP.navController.navigateUp() }
    }

    private fun addListenerForSaveBtn() {
        this.saveTaskBtn.setOnClickListener {
            val currentTodoText = this.taskText.text.toString()
            if (currentTodoText != "") {
                if (isEdit) {
                    // updateTodo
                } else {
                    viewModel.addTodo(
                        TodoModel(text = currentTodoText)
                    )
                }
                APP.navController.navigateUp()
            }
        }
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
            isEdit = false
        }
    }
}