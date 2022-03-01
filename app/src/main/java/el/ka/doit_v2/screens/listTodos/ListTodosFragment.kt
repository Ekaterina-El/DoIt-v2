package el.ka.doit_v2.screens.listTodos

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import el.ka.doit_v2.APP
import el.ka.doit_v2.R
import el.ka.doit_v2.TODO_KEY
import el.ka.doit_v2.adapter.DoItAdapter
import el.ka.doit_v2.model.TodoModel
import kotlinx.android.synthetic.main.fragment_list_todos.*

class ListTodosFragment : Fragment(R.layout.fragment_list_todos) {
    private lateinit var adapter: DoItAdapter
    private lateinit var viewModel: ListTodosViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()
        initRecyclerView()
        initButtons()
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
            adapter.setTodos(listTodos)
        }
    }

    companion object {
        fun onEditTodo(todoModel: TodoModel) {
            val bundle = Bundle()
            bundle.putSerializable(TODO_KEY, todoModel)
            APP.navController.navigate(R.id.action_listTodosFragment_to_editTodoFragment, bundle)
        }
    }

}