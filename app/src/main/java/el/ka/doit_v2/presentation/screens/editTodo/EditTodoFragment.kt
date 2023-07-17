package el.ka.doit_v2.presentation.screens.editTodo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import el.ka.doit_v2.DoItApplication
import el.ka.doit_v2.databinding.FragmentEditTodoBinding
import el.ka.doit_v2.presentation.ViewModelFactory
import javax.inject.Inject

class EditTodoFragment : Fragment() {
  private lateinit var binding: FragmentEditTodoBinding

  @Inject
  lateinit var viewModelFactory: ViewModelFactory
  private val viewModel: EditTodoViewModel by lazy {
    ViewModelProvider(this, viewModelFactory)[EditTodoViewModel::class.java]
  }
  private val component by lazy {
    (requireActivity().application as DoItApplication).component.getFragmentComponentFactory().create()
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    val binding = FragmentEditTodoBinding.inflate(inflater, container, false)
    component.inject(this)
    binding.apply {
      lifecycleOwner = viewLifecycleOwner
      viewModel = this@EditTodoFragment.viewModel
      master = this@EditTodoFragment
    }
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    requireActivity().onBackPressedDispatcher.addCallback(
      this,
      object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
          goBack()
        }
      })

    viewModel.onSaved.observe(this) {
      if (it) goBack()
    }

    findCurrentTodoModel()
  }

  fun goBack() {
    findNavController().navigateUp()
  }

  private fun findCurrentTodoModel() {
    val todo = EditTodoFragmentArgs.fromBundle(requireArguments()).todo
    todo?.let {viewModel.setTodoToEdit(todo) }
  }
}