package el.ka.doit_v2.presentation.adapter

import android.annotation.SuppressLint
import android.app.Application
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import el.ka.doit_v2.R
import el.ka.doit_v2.domain.TodoModel
import kotlinx.android.synthetic.main.todo_item.view.*

class TodosAdapter : RecyclerView.Adapter<TodoViewHolder>() {
  var listener: Listener? = null
  private var todos = listOf<TodoModel>()

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.todo_item, parent, false)
    return TodoViewHolder(view)
  }

  override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
    holder.bind(todos[position])
  }

  override fun onViewAttachedToWindow(holder: TodoViewHolder) {
    super.onViewAttachedToWindow(holder)
    listener?.let { holder.onAttached(it) }
  }

  override fun onViewDetachedFromWindow(holder: TodoViewHolder) {
    super.onViewDetachedFromWindow(holder)
    holder.onDetached()
  }

  override fun getItemCount(): Int = todos.size

  @SuppressLint("NotifyDataSetChanged")
  fun setTodos(newTodos: List<TodoModel>) {
    todos = newTodos
    notifyDataSetChanged()
  }

  companion object {
    interface Listener {
      fun onDeleteClick(todoModel: TodoModel)
      fun onItemClick(todoModel: TodoModel)
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