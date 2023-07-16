package el.ka.doit_v2.presentation.adapter

import android.annotation.SuppressLint
import android.app.Application
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import el.ka.doit_v2.R
import el.ka.doit_v2.domain.TodoModel
import kotlinx.android.synthetic.main.todo_item.view.*

class TodosAdapter(
  private val application: Application,
  private val onDeleteTodo: (TodoModel) -> Unit,
  private val onEditTodo: (TodoModel) -> Unit,
  private val onChangeTodo: (TodoModel) -> Unit,

  ) : RecyclerView.Adapter<TodosAdapter.TodoViewHolder>() {
  inner class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

  private var todos = listOf<TodoModel>()

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.todo_item, parent, false)
    return TodoViewHolder(view)
  }

  override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
    holder.itemView.taskCheckbox.text = todos[position].text
    holder.itemView.taskCheckbox.isChecked = todos[position].isDone
    holder.itemView.wrapper.setBackgroundColor(application.getColor(colors[todos[position].colorNumber]))
  }

  override fun onViewAttachedToWindow(holder: TodoViewHolder) {
    super.onViewAttachedToWindow(holder)

    val todoModel = todos[holder.adapterPosition]
    holder.itemView.editTask.setOnClickListener { onEditTodo(todoModel) }
    holder.itemView.deleteTask.setOnClickListener { onDeleteTodo(todoModel) }
    holder.itemView.taskCheckbox.setOnCheckedChangeListener { _, status ->
      todoModel.isDone = status
      onChangeTodo(todoModel)
    }
  }

  override fun onViewDetachedFromWindow(holder: TodoViewHolder) {
    super.onViewDetachedFromWindow(holder)

    holder.itemView.editTask.setOnClickListener(null)
    holder.itemView.deleteTask.setOnClickListener(null)
  }

  override fun getItemCount(): Int = todos.size

  @SuppressLint("NotifyDataSetChanged")
  fun setTodos(newTodos: List<TodoModel>) {
    todos = newTodos
    notifyDataSetChanged()
  }

  companion object {
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