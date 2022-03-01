package el.ka.doit_v2.screens.listTodos

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import el.ka.doit_v2.APP
import el.ka.doit_v2.R
import el.ka.doit_v2.TODO_KEY
import el.ka.doit_v2.adapter.DoItAdapter
import el.ka.doit_v2.model.TodoModel
import kotlinx.android.synthetic.main.fragment_list_todos.*
import java.util.*

class ListTodosFragment : Fragment(R.layout.fragment_list_todos) {
    private lateinit var adapter: DoItAdapter
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
        this.searchFormInput.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {            }
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
            APP.navController.navigate(R.id.action_listTodosFragment_to_editTodoFragment)
        }

        this.deleteAllTodosBtn.setOnClickListener {
            viewModel.deleteAllTodos()
        }
    }

    private fun initRecyclerView() {
        adapter = DoItAdapter(
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
        viewModel.getAllNotes().observe(viewLifecycleOwner) { listTodos ->
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
        } else {
            currentTodos = allTodos
        }

        this.emptyTodo.visibility = if (currentTodos.isEmpty()) View.VISIBLE else View.INVISIBLE
        adapter.setTodos(currentTodos)
    }

    companion object {
        fun onEditTodo(todoModel: TodoModel) {
            val bundle = Bundle()
            bundle.putSerializable(TODO_KEY, todoModel)
            APP.navController.navigate(R.id.action_listTodosFragment_to_editTodoFragment, bundle)
        }
    }

}