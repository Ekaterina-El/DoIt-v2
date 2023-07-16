package el.ka.doit_v2.domain

class DeleteTodoUseCase(private val repo: TodosRepository) {
  suspend operator fun invoke(todo: TodoModel) {
    repo.delete(todo)
  }
}