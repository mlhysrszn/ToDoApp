package com.mlhysrszn.todoapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ToDoModel::class], version = 1)
abstract class ToDoDatabase : RoomDatabase() {

    abstract fun toDoDao(): ToDoDAO

    companion object {
        private var instance: ToDoDatabase? = null

        fun getToDoDatabase(context: Context): ToDoDatabase? {
            if (instance == null) {
                instance =
                    Room.databaseBuilder(context,
                        ToDoDatabase::class.java,
                        "todo.db")
                        .allowMainThreadQueries()
                        .build()
            }
            return instance
        }
    }
}