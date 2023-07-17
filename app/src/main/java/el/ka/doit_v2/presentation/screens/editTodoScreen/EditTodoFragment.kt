package el.ka.doit_v2.presentation.screens.editTodoScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import el.ka.doit_v2.databinding.FragmentEditTodoBinding
import el.ka.doit_v2.domain.TodoModel

class EditTodoFragment : Fragment() {
  private lateinit var binding: FragmentEditTodoBinding
  private val viewModel: EditTodoViewModel by lazy {
    ViewModelProvider(this).get(EditTodoViewModel::class.java)
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    val binding = FragmentEditTodoBinding.inflate(inflater, container, false)
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
    viewModel.setTodoToEdit(todo)
  }
}