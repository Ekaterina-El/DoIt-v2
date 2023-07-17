package el.ka.doit_v2.di

import android.app.Application
import dagger.Module
import dagger.Provides
import el.ka.doit_v2.data.db.DoItDao
import el.ka.doit_v2.data.db.LocalDatabase

@Module
class DataModule {
  companion object {
    @Provides
    fun provideDao(application: Application): DoItDao {
      return LocalDatabase.getInstance(application).getDoItDao()
    }
  }
}