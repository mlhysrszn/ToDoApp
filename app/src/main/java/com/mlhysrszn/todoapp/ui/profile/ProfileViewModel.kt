package com.mlhysrszn.todoapp.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mlhysrszn.todoapp.data.ToDoDAO

class ProfileViewModel(private val toDoDAO: ToDoDAO?) : ViewModel() {

    private val _allToDoCount = MutableLiveData<Int>()
    val allToDoCount: LiveData<Int>
        get() = _allToDoCount

    private val _allDoneToDoCount = MutableLiveData<Int>()
    val allDoneToDoCount: LiveData<Int>
        get() = _allDoneToDoCount

    private val _allImportantToDoCount = MutableLiveData<Int>()
    val allImportantToDoCount: LiveData<Int>
        get() = _allImportantToDoCount

    init {
        getAllToDoCount()
        getAllDoneToDoCount()
        getAllImportantToDoCount()
    }

    private fun getAllToDoCount(): Int? {
        _allToDoCount.value = toDoDAO?.getAllToDoCount()
        return _allToDoCount.value
    }

    private fun getAllDoneToDoCount(): Int? {
        _allDoneToDoCount.value = toDoDAO?.getAllDoneToDoCount()
        return _allDoneToDoCount.value
    }

    private fun getAllImportantToDoCount(): Int? {
        _allImportantToDoCount.value = toDoDAO?.getAllImportantToDoCount()
        return _allImportantToDoCount.value
    }

    fun deleteAllToDos(){
        toDoDAO?.deleteAllToDoS()
    }
}

class ProfileViewModelFactory(private val toDoDAO: ToDoDAO?) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProfileViewModel(toDoDAO) as T
    }
}