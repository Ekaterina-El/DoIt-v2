package el.ka.doit_v2.presentation.screens.listTodos

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import el.ka.doit_v2.R
import el.ka.doit_v2.domain.TodoModel
import el.ka.doit_v2.presentation.adapter.TodosAdapter
import el.ka.doit_v2.presentation.screens.editTodoScreen.EditTodoFragment
import kotlinx.android.synthetic.main.fragment_list_todos.*

class ListTodosFragment : Fragment(R.layout.fragment_list_todos) {
  private lateinit var adapter: TodosAdapter
  private lateinit var viewModel: ListTodosViewModel

  private lateinit var filteredTodos: List<TodoModel>
  private lateinit var allTodos: List<TodoModel>
  private var filterString = ""

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    initViewModel()
    initRecyclerView()
    initButtons()
    initListenerForSearch()
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

  private fun initViewModel() {
    viewModel = ViewModelProvider(this).get(ListTodosViewModel::class.java)
  }

  private fun initButtons() {
    this.fab.setOnClickListener {
      findNavController().navigate(R.id.action_listTodosFragment_to_editTodoFragment)
    }

    this.deleteAllTodosBtn.setOnClickListener {
      viewModel.deleteAllTodos()
    }

    this.empty_box_add_task.setOnClickListener {
      viewModel.addTodo(TodoModel(text = filterString))
    }
  }

  private fun initRecyclerView() {
    adapter = TodosAdapter(
      application = requireActivity().application,
      onDeleteTodo = { todoModel ->
        viewModel.deleteTodo(todoModel)
      },
      onEditTodo = { todoModel ->
        onEditTodo(todoModel)
      },
      onChangeTodo = { todoModel ->
        viewModel.updateTodo(todoModel)
      }
    )
    this.recyclerToDoList.adapter = adapter

    addObserverForTodos()
  }

  private fun addObserverForTodos() {
    viewModel.notes.observe(viewLifecycleOwner) { listTodos ->
      allTodos = listTodos
      filterTodos()
    }
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

  private fun onEditTodo(todoModel: TodoModel) {
    val bundle = Bundle()
    bundle.putSerializable(EditTodoFragment.TODO_KEY, todoModel)
    findNavController().navigate(R.id.action_listTodosFragment_to_editTodoFragment, bundle)
  }
}