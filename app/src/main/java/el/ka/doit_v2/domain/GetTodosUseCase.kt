package el.ka.doit_v2.domain

import androidx.lifecycle.LiveData
import javax.inject.Inject

class GetTodosUseCase @Inject constructor(private val repo: TodosRepository) {
  operator fun invoke(): LiveData<List<TodoModel>> {
    return repo.getTodos()
  }
}