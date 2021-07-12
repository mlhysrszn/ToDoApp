package com.mlhysrszn.todoapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo")
data class ToDoModel(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var toDoId: Int = 0,

    @ColumnInfo(name = "name")
    var toDoName: String,

    @ColumnInfo(name = "is_important")
    var IsImportant: Boolean = false,

    @ColumnInfo(name = "is_done")
    var IsDone: Boolean = false

)
