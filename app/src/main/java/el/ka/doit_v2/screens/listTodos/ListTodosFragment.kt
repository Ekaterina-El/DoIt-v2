package el.ka.doit_v2.screens.listTodos

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import el.ka.doit_v2.APP
import el.ka.doit_v2.R
import el.ka.doit_v2.adapter.DoItAdapter
import el.ka.doit_v2.model.TodoModel
import kotlinx.android.synthetic.main.fragment_list_todos.*

class ListTodosFragment : Fragment(R.layout.fragment_list_todos) {
    private lateinit var adapter: DoItAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProvider(this).get(ListTodosViewModel::class.java)

        adapter = DoItAdapter(
            onDeleteTodo = { todoModel ->
                viewModel.deleteTodo(todoModel)
            })
        this.recyclerToDoList.adapter = adapter

        viewModel.getAllNotes().observe(viewLifecycleOwner) { listTodos ->
            adapter.setTodos(listTodos)
        }
    }


}