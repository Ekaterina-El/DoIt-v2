package el.ka.doit_v2.screens.splashScreen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import el.ka.doit_v2.APP
import el.ka.doit_v2.R
import el.ka.doit_v2.REPOSITORY
import el.ka.doit_v2.db.DoItDatabase
import el.ka.doit_v2.db.dao.DoItDao
import el.ka.doit_v2.db.repository.DoItRealisation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel(application: Application) : AndroidViewModel(application) {
    private val context = application

    fun hiddenSplashFragment() {
        CoroutineScope(Dispatchers.Main).launch {
            delay(2000)
            APP.navController.navigate(R.id.action_splashFragment_to_listTodosFragment)
        }
    }

    fun initDatabase() {
        val doItDao = DoItDatabase.getInstance(context).getDoItDao()
        REPOSITORY = DoItRealisation(doItDao)
    }
}