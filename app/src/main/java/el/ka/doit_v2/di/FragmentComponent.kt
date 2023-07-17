package el.ka.doit_v2.di

import dagger.Subcomponent
import el.ka.doit_v2.presentation.screens.editTodo.EditTodoFragment
import el.ka.doit_v2.presentation.screens.listTodos.ListTodosFragment

@Subcomponent(modules = [ViewModelModel::class])
interface FragmentComponent {
  fun inject(listTodosFragment: ListTodosFragment)
  fun inject(listTodosFragment: EditTodoFragment)

  @Subcomponent.Factory
  interface Factory {
    fun create(): FragmentComponent
  }
}