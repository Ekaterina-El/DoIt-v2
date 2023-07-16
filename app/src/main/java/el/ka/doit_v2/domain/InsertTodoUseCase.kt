package el.ka.doit_v2.domain

class InsertTodoUseCase(private val repo: TodosRepository) {
  suspend operator fun invoke(todo: TodoModel) {
    repo.insert(todo)
  }
}