package el.ka.doit_v2

import android.app.Application
import el.ka.doit_v2.di.DaggerAppComponent

class DoItApplication: Application() {
  val component by lazy {
    DaggerAppComponent.factory().create(this)
  }
}