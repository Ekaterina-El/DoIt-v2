package el.ka.doit_v2.screens.listTodos

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import el.ka.doit_v2.APP
import el.ka.doit_v2.R
import el.ka.doit_v2.model.TodoModel

class ListTodosFragment : Fragment(R.layout.fragment_list_todos) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProvider(this).get(ListTodosViewModel::class.java)

        viewModel.getAllNotes().observe(viewLifecycleOwner) { listTodos ->
            Toast.makeText(APP, "Count of todos: ${listTodos.size}", Toast.LENGTH_SHORT).show()
        }
    }


}