package el.ka.doit_v2.presentation.screens.splashScreen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import el.ka.doit_v2.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashFragment : Fragment(R.layout.fragment_splash) {
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    changeScreen()
  }

  private fun changeScreen() {
    lifecycleScope.launch {
      delay(2000)
      val action = R.id.action_splashFragment_to_listTodosFragment
      findNavController().navigate(action)
    }
  }
}