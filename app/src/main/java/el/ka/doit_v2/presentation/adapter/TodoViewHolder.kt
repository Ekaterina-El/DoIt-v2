package el.ka.doit_v2.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import el.ka.doit_v2.domain.TodoModel
import kotlinx.android.synthetic.main.todo_item.view.*

class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
  private lateinit var todo: TodoModel

  fun bind(todoModel: TodoModel) {
    todo = todoModel
    itemView.taskCheckbox.text = todoModel.text
    itemView.taskCheckbox.isChecked = todoModel.isDone
//    itemView.wrapper.setBackgroundColor(application.getColor(TodosAdapter.colors[todos[position].colorNumber]))
  }

  fun onAttached(listener: TodosAdapter.Companion.Listener) {
    itemView.editTask.setOnClickListener { listener.onItemClick(todo) }
    itemView.deleteTask.setOnClickListener { listener.onDeleteClick(todo) }
    itemView.taskCheckbox.setOnCheckedChangeListener { _, status ->
      listener.onBoxClick(todo, status)
    }
  }

  fun onDetached() {
    itemView.editTask.setOnClickListener(null)
    itemView.deleteTask.setOnClickListener(null)
  }

}
