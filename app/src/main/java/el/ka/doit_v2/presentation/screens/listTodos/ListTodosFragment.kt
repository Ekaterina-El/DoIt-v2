package el.ka.doit_v2.presentation.screens.listTodos

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import el.ka.doit_v2.R
import el.ka.doit_v2.databinding.FragmentListTodosBinding
import el.ka.doit_v2.domain.TodoModel
import el.ka.doit_v2.presentation.adapter.TodoViewHolder
import el.ka.doit_v2.presentation.adapter.TodosAdapter
import el.ka.doit_v2.presentation.screens.editTodoScreen.EditTodoFragment
import kotlinx.android.synthetic.main.fragment_list_todos.*

class ListTodosFragment : Fragment() {
  private lateinit var binding: FragmentListTodosBinding


  private val todosAdapterListener by lazy {
    object : TodoViewHolder.Companion.Listener {
      override fun onDeleteClick(todoModel: TodoModel) {
        viewModel.deleteTodo(todoModel)
      }

      override fun onItemClick(todoModel: TodoModel) {
        toEditTodo(todoModel)
      }

      override fun onBoxClick(todoModel: TodoModel, status: Boolean) {
        viewModel.updateTodo(todoModel)
      }
    }
  }

  private val adapter by lazy {
    TodosAdapter(todosAdapterListener)
  }
  private val viewModel by lazy {
    ViewModelProvider(this).get(ListTodosViewModel::class.java)
  }

  private lateinit var filteredTodos: List<TodoModel>
  private lateinit var allTodos: List<TodoModel>
  private var filterString = ""

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
  ): View {
    binding = FragmentListTodosBinding.inflate(inflater, container, false)
    binding.apply {
      lifecycleOwner = viewLifecycleOwner
      viewModel = this@ListTodosFragment.viewModel
      adapter = this@ListTodosFragment.adapter
      master = this@ListTodosFragment
    }
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    initListenerForSearch()

    viewModel.notes.observe(viewLifecycleOwner) { listTodos ->
      allTodos = listTodos
      filterTodos()
    }
  }

  private fun initListenerForSearch() {
    this.searchFormInput.addTextChangedListener(object : TextWatcher {
      override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
      override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        filterString = p0.toString()
        filterTodos()
      }

      override fun afterTextChanged(p0: Editable?) {}
    })
  }

  fun clickOnFab() {
    findNavController().navigate(R.id.action_listTodosFragment_to_editTodoFragment)
  }

  fun clickOnDeleteAllTodos() {
    viewModel.deleteAllTodos()
  }

  fun clickOnEmptyBoxAddTask() {
    viewModel.addTodo(TodoModel(text = filterString))
  }

  private fun filterTodos() {
    val currentTodos: List<TodoModel>

    if (filterString != "") {
      filteredTodos = allTodos.filter { todoModel ->
        todoModel.text.contains(filterString, true)
      }
      currentTodos = filteredTodos

      if (filteredTodos.isEmpty()) {
        this.empty_box_add_task.visibility = View.VISIBLE
      } else {
        this.empty_box_add_task.visibility = View.INVISIBLE
      }
    } else {
      currentTodos = allTodos
      this.empty_box_add_task.visibility = View.INVISIBLE
    }

    this.emptyTodo.visibility = if (currentTodos.isEmpty()) View.VISIBLE else View.INVISIBLE
    adapter.setTodos(currentTodos)
  }

  private fun toEditTodo(todoModel: TodoModel) {
    val bundle = Bundle()
    bundle.putSerializable(EditTodoFragment.TODO_KEY, todoModel)
    findNavController().navigate(R.id.action_listTodosFragment_to_editTodoFragment, bundle)
  }
}