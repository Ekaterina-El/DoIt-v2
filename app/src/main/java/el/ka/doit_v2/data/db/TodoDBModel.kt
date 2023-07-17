package el.ka.doit_v2.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todos_table")
data class TodoDBModel(
  @PrimaryKey(autoGenerate = true) val id: Int = 0,
  @ColumnInfo var text: String = "",
  @ColumnInfo var isDone: Boolean = false,
  @ColumnInfo var colorNumber: Int = 0
)