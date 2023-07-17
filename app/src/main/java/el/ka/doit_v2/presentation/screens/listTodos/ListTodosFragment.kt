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
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import el.ka.doit_v2.databinding.FragmentListTodosBinding
import el.ka.doit_v2.domain.TodoModel
import el.ka.doit_v2.presentation.adapter.TodoViewHolder
import el.ka.doit_v2.presentation.adapter.TodosAdapter

class ListTodosFragment : Fragment() {
  private lateinit var binding: FragmentListTodosBinding

  private val recyclerToDoCallback by lazy {
    object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
      override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
      ): Boolean = false

      override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val position = viewHolder.adapterPosition

        when (direction) {
          ItemTouchHelper.LEFT -> adapter.deleteTodo(position)
          ItemTouchHelper.RIGHT -> adapter.editTodo(position)
        }
      }
    }
  }

  private val todosAdapterListener by lazy {
    object : TodoViewHolder.Companion.Listener {
      override fun onDelete(todoModel: TodoModel) {
        viewModel.deleteTodo(todoModel)
      }

      override fun onEdit(todoModel: TodoModel) {
        toEditTodo(todoModel)
      }

      override fun onBoxClick(todoModel: TodoModel, status: Boolean) {
        viewModel.updateStatus(todoModel, status)
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
    initRecyclerView()

    viewModel.notes.observe(viewLifecycleOwner) { listTodos ->
      allTodos = listTodos
      filterTodos()
    }
  }

  private fun initRecyclerView() {
    ItemTouchHelper(recyclerToDoCallback).attachToRecyclerView(binding.recyclerToDoList)
  }

  private fun initListenerForSearch() {
    binding.searchFormInput.addTextChangedListener(object : TextWatcher {
      override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
      override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        filterString = p0.toString()
        filterTodos()
      }

      override fun afterTextChanged(p0: Editable?) {}
    })
  }

  fun clickOnFab() {
    val action = ListTodosFragmentDirections.actionListTodosFragmentToEditTodoFragment(null)
    findNavController().navigate(action)
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
        binding.emptyBoxAddTask.visibility = View.VISIBLE
      } else {
        binding.emptyBoxAddTask.visibility = View.INVISIBLE
      }
    } else {
      currentTodos = allTodos
      binding.emptyBoxAddTask.visibility = View.INVISIBLE
    }

    binding.emptyTodo.visibility = if (currentTodos.isEmpty()) View.VISIBLE else View.INVISIBLE
    adapter.setTodos(currentTodos)
  }

  private fun toEditTodo(todoModel: TodoModel) {
    val action = ListTodosFragmentDirections.actionListTodosFragmentToEditTodoFragment(todoModel)
    findNavController().navigate(action)
  }
}