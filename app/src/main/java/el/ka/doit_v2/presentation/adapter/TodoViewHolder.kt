package el.ka.doit_v2.presentation.adapter

import androidx.recyclerview.widget.RecyclerView
import el.ka.doit_v2.R
import el.ka.doit_v2.databinding.TodoItemBinding
import el.ka.doit_v2.domain.TodoModel

class TodoViewHolder(private val binding: TodoItemBinding) : RecyclerView.ViewHolder(binding.root) {
  fun bind(todoModel: TodoModel) {
    binding.todo = todoModel
  }

  fun onAttached(listener: Listener) {
    val todo = binding.todo!!
    binding.editTask.setOnClickListener { listener.onEdit(todo) }
//    binding.deleteTask.setOnClickListener { listener.onDeleteClick(todo) }
    binding.taskCheckbox.setOnCheckedChangeListener { _, status ->
      listener.onBoxClick(todo, status)
    }
  }

  fun onDetached() {
    binding.editTask.setOnClickListener(null)
//    binding.deleteTask.setOnClickListener(null)
  }

  companion object {
    interface Listener {
      fun onDelete(todoModel: TodoModel)
      fun onEdit(todoModel: TodoModel)
      fun onBoxClick(todoModel: TodoModel, status: Boolean)
    }

    val colors = listOf(
      R.color.c0,
      R.color.c1,
      R.color.c2,
      R.color.c3,
      R.color.c4,
      R.color.c5,
    )
  }

}
