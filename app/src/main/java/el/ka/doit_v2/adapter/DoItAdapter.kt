package el.ka.doit_v2.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import el.ka.doit_v2.APP
import el.ka.doit_v2.R
import el.ka.doit_v2.model.TodoModel
import kotlinx.android.synthetic.main.todo_item.view.*

class DoItAdapter: RecyclerView.Adapter<DoItAdapter.TodoViewHolder>() {
    inner class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    private var todos = listOf<TodoModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.todo_item, parent, false)
        return TodoViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.itemView.taskCheckbox.text = todos[position].text
        holder.itemView.taskCheckbox.isChecked = todos[position].isDone
    }

    override fun onViewAttachedToWindow(holder: TodoViewHolder) {
        super.onViewAttachedToWindow(holder)

        holder.itemView.editTask.setOnClickListener {
            Toast.makeText(APP, "Edit: ${todos[holder.adapterPosition].text}", Toast.LENGTH_SHORT).show()
        }

        holder.itemView.deleteTask.setOnClickListener {
            Toast.makeText(APP, "Delete: ${todos[holder.adapterPosition].text}", Toast.LENGTH_SHORT).show()
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
}