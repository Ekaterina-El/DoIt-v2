package el.ka.doit_v2.presentation.databinding

import android.view.View
import androidx.databinding.BindingAdapter
import el.ka.doit_v2.R

@BindingAdapter("app:todoColor")
fun todoColor(view: View, colorIdx: Int) {
  val colors = view.context.resources.obtainTypedArray(R.array.todoColors)
  val color = colors.getColor(colorIdx, 0)
  colors.recycle()
  view.setBackgroundColor(color)
}