package el.ka.doit_v2.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import el.ka.doit_v2.R

class MainActivity : AppCompatActivity() {
  lateinit var navController: NavController

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    navController = Navigation.findNavController(this, R.id.nav_host_fragment)
  }
}