package el.ka.doit_v2.screens.splashScreen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import el.ka.doit_v2.APP
import el.ka.doit_v2.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel(application: Application) : AndroidViewModel(application) {
    fun hiddenSplashFragment() {
        CoroutineScope(Dispatchers.Main).launch {
            delay(2000)
            APP.navController.navigate(R.id.action_splashFragment_to_listTodosFragment)
        }
    }
}