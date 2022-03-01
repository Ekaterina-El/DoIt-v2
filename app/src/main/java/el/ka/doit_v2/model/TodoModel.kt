package el.ka.doit_v2.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todos_table")
data class TodoModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo
    val text: String = "",

    @ColumnInfo
    val isDone: Boolean = false,
)