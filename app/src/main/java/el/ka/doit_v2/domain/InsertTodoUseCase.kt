package el.ka.doit_v2.domain

import javax.inject.Inject

class InsertTodoUseCase @Inject constructor(private val repo: TodosRepository) {
  suspend operator fun invoke(todo: TodoModel) {
    repo.insert(todo)
  }
}