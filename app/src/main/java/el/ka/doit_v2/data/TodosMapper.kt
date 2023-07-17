package el.ka.doit_v2.data

import androidx.lifecycle.LiveData
import el.ka.doit_v2.data.db.TodoDBModel
import el.ka.doit_v2.domain.TodoModel
import javax.inject.Inject

class TodosMapper @Inject constructor() {
  fun todoModelToDbModel(todoModel: TodoModel): TodoDBModel {
    return TodoDBModel(todoModel.id, todoModel.text, todoModel.isDone, todoModel.colorNumber)
  }

  fun mapListDbModelsToModel(list: List<TodoDBModel>) = list.map {todoDBModelToModel(it) }

  private fun todoDBModelToModel(todo: TodoDBModel) = TodoModel(
    todo.id, todo.text, todo.isDone, todo.colorNumber
  )
}