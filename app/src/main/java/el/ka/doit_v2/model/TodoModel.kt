package el.ka.doit_v2.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "todos_table")
data class TodoModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo
    var text: String = "",

    @ColumnInfo
    var isDone: Boolean = false,

    @ColumnInfo
    var colorNumber: Int = 0
): Serializable