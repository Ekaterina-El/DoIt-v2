package el.ka.doit_v2.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import el.ka.doit_v2.domain.TodoModel

class TodosDiffCallback: DiffUtil.ItemCallback<TodoModel>() {
  override fun areItemsTheSame(oldItem: TodoModel, newItem: TodoModel): Boolean {
    return oldItem == newItem
  }

  override fun areContentsTheSame(oldItem: TodoModel, newItem: TodoModel): Boolean {
    return oldItem.id == newItem.id
  }
}