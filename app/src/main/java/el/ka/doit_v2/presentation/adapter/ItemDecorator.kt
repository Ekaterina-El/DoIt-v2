package el.ka.doit_v2.presentation.adapter

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class ItemDecorator(private val vrtSpace: Int, private val hrzSpace: Int): RecyclerView.ItemDecoration() {
  override fun getItemOffsets(
    outRect: Rect,
    view: View,
    parent: RecyclerView,
    state: RecyclerView.State
  ) {
    with(outRect) {
      if (parent.getChildAdapterPosition(view) == 0) {
        top = vrtSpace
      }

      left = hrzSpace
      right = hrzSpace
      bottom = vrtSpace
    }
  }
}