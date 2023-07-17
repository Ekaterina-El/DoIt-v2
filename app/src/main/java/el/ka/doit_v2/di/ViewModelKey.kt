package el.ka.doit_v2.di

import androidx.lifecycle.ViewModel
import dagger.MapKey
import el.ka.doit_v2.domain.TodoModel
import kotlin.reflect.KClass

@MapKey
@Retention(AnnotationRetention.RUNTIME)
annotation class ViewModelKey(val value: KClass<out ViewModel>)