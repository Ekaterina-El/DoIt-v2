package el.ka.doit_v2.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component

@Component(modules = [DataModule::class, DomainModule::class])
interface AppComponent {
  fun getFragmentComponentFactory(): FragmentComponent.Factory

  @Component.Factory
  interface Factory {
    fun create(
      @BindsInstance application: Application
    ): AppComponent
  }
}