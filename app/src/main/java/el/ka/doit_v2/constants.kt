package el.ka.doit_v2

import el.ka.doit_v2.db.repository.DoItRepository

lateinit var APP: MainActivity
lateinit var REPOSITORY: DoItRepository

const val TODO_KEY = "todo"
const val CHECK_IMG_TODO = R.drawable.ic_check

val colors = listOf(
    R.color.c0,
    R.color.c1,
    R.color.c2,
    R.color.c3,
    R.color.c4,
    R.color.c5,
)