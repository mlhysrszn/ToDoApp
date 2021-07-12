package com.mlhysrszn.todoapp.ui.todo

import android.app.Application
import androidx.lifecycle.*
import com.mlhysrszn.todoapp.data.ToDoDAO
import com.mlhysrszn.todoapp.data.ToDoDatabase
import com.mlhysrszn.todoapp.data.ToDoModel

class ToDoViewModel(application: Application) : AndroidViewModel(application) {

    private val toDoDB: ToDoDAO? = ToDoDatabase.getToDoDatabase(application)?.toDoDao()
    private val _toDoList = MutableLiveData<List<ToDoModel>>()
    val toDoList: LiveData<List<ToDoModel>>
        get() = _toDoList

    init {
        getAllToDos()
    }

    fun getAllToDos(): List<ToDoModel>? {
        _toDoList.value = toDoDB?.getAllToDo()
        return _toDoList.value
    }

    fun addToDo(text: String, isImportant: Boolean) {
        val todo = ToDoModel(toDoName = text, IsImportant = isImportant)
        toDoDB?.insertToDo(todo)
    }

    fun updateToDo(updatedToDo: ToDoModel) {
        toDoDB?.updateCheckedToDo(updatedToDo)
    }
}

class ToDoViewModelFactory(private val application: Application) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ToDoViewModel(application) as T
    }
}