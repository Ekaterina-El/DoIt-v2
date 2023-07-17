package el.ka.doit_v2.di

import dagger.Binds
import dagger.Module
import el.ka.doit_v2.data.TodosRepositoryImpl
import el.ka.doit_v2.domain.TodosRepository

@Module
interface DomainModule {
  @Binds
  fun bindTodosRepository(impl: TodosRepositoryImpl): TodosRepository
}