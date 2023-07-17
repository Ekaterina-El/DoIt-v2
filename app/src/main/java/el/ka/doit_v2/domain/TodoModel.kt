package el.ka.doit_v2.domain

import java.io.Serializable

data class TodoModel(
  val id: Int = 0,
  var text: String = "",
  var isDone: Boolean = false,
  var colorNumber: Int = 0
) : Serializable