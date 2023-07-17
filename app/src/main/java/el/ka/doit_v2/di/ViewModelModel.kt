package el.ka.doit_v2.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import el.ka.doit_v2.presentation.screens.editTodo.EditTodoViewModel
import el.ka.doit_v2.presentation.screens.listTodos.ListTodosViewModel

@Module
interface ViewModelModel {
  @Binds
  @IntoMap
  @ViewModelKey(ListTodosViewModel::class)
  fun listTodosViewModel(impl: ListTodosViewModel): ViewModel

  @Binds
  @IntoMap
  @ViewModelKey(EditTodoViewModel::class)
  fun editTodoViewModel(impl: EditTodoViewModel): ViewModel
}