package el.ka.doit_v2.domain

class EditTodoUseCase(private val repo: TodosRepository) {
  suspend operator fun invoke(todo: TodoModel) {
    repo.edit(todo)
  }
}