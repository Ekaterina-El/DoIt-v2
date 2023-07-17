package el.ka.doit_v2.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import el.ka.doit_v2.databinding.TodoItemBinding
import el.ka.doit_v2.domain.TodoModel

class TodosAdapter(private val listener: TodoViewHolder.Companion.Listener? = null) :
  ListAdapter<TodoModel, TodoViewHolder>(TodosDiffCallback()) {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
    val binding = TodoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    return TodoViewHolder(binding)
  }

  override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
    holder.bind(getItem(position))
  }

  override fun onViewAttachedToWindow(holder: TodoViewHolder) {
    super.onViewAttachedToWindow(holder)
    listener?.let { holder.onAttached(it) }
  }

  override fun onViewDetachedFromWindow(holder: TodoViewHolder) {
    super.onViewDetachedFromWindow(holder)
    holder.onDetached()
  }

  fun setTodos(newTodos: List<TodoModel>) {
    submitList(newTodos)
  }

  fun deleteTodo(position: Int) {
    listener?.onDelete(getItem(position))
  }

  fun editTodo(position: Int) {
    listener?.onEdit(getItem(position))
  }
}