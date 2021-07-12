package com.mlhysrszn.todoapp.ui.done

import android.app.Application
import androidx.lifecycle.*
import com.mlhysrszn.todoapp.data.ToDoDAO
import com.mlhysrszn.todoapp.data.ToDoDatabase
import com.mlhysrszn.todoapp.data.ToDoModel

class DoneViewModel(application: Application) : AndroidViewModel(application) {

    private val toDoDB: ToDoDAO? = ToDoDatabase.getToDoDatabase(application)?.toDoDao()
    private val _doneToDoList = MutableLiveData<List<ToDoModel>>()
    val doneToDoList: LiveData<List<ToDoModel>>
        get() = _doneToDoList

    init {
        getDoneToDoList()
    }

    fun getDoneToDoList(): List<ToDoModel>? {
        _doneToDoList.value = toDoDB?.getDoneToDo()
        return _doneToDoList.value
    }

    fun updateToDo(updatedToDo: ToDoModel) {
        toDoDB?.updateCheckedToDo(updatedToDo)
    }

}

class DoneViewModelFactory(private val application: Application) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DoneViewModel(application) as T
    }
}