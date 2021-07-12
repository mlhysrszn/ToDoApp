package com.mlhysrszn.todoapp.data

import androidx.room.*

@Dao
interface ToDoDAO {

    @Insert
    fun insertToDo(toDo: ToDoModel)

    @Delete
    fun deleteToDo(toDo: ToDoModel)

    @Update
    fun updateCheckedToDo(toDo: ToDoModel)

    @Query("SELECT * FROM todo WHERE is_done LIKE 0 ORDER BY is_important DESC")
    fun getAllToDo(): List<ToDoModel>

    @Query("SELECT * FROM todo WHERE is_done LIKE 1 ORDER BY is_important DESC")
    fun getDoneToDo(): List<ToDoModel>

    @Query("SELECT COUNT(*) FROM todo WHERE is_done LIKE 0")
    fun getAllToDoCount(): Int?

    @Query("SELECT COUNT(*) FROM todo WHERE is_done LIKE 1")
    fun getAllDoneToDoCount(): Int?

    @Query("SELECT COUNT(*) FROM todo WHERE is_done = 0 and is_important = 1")
    fun getAllImportantToDoCount(): Int?

    @Query("DELETE FROM todo")
    fun deleteAllToDoS()

}