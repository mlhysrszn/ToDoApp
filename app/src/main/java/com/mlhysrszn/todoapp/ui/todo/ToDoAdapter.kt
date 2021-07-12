package com.mlhysrszn.todoapp.ui.todo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mlhysrszn.todoapp.data.ToDoModel
import com.mlhysrszn.todoapp.databinding.TodoCardBinding

class ToDoAdapter(private val toDoList: List<ToDoModel>, private val viewModel: ToDoViewModel) :
    RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder>() {

    class ToDoViewHolder(binding: TodoCardBinding) : RecyclerView.ViewHolder(binding.root) {
        val toDoBinding: TodoCardBinding = binding
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        val binding = TodoCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ToDoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        val toDo = toDoList[position]
        holder.toDoBinding.todo = toDo

        holder.toDoBinding.checkBox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                val toDoName = toDo.toDoName
                val toDoIsImportant = toDo.IsImportant
                val toDoId = toDo.toDoId
                val updatedToDo = ToDoModel(toDoId,toDoName,toDoIsImportant,true)

                viewModel.updateToDo(updatedToDo)
                viewModel.getAllToDos()
            }
        }
    }

    override fun getItemCount(): Int = toDoList.size
}