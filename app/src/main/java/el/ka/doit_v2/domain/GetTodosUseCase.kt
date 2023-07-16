package el.ka.doit_v2.domain

import androidx.lifecycle.LiveData

class GetTodosUseCase(private val repo: TodosRepository) {
  operator fun invoke(): LiveData<List<TodoModel>> {
    return repo.getTodos()
  }
}