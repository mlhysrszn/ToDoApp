package com.mlhysrszn.todoapp.ui.done

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mlhysrszn.todoapp.data.ToDoModel
import com.mlhysrszn.todoapp.databinding.TodoCardBinding

class DoneToDoAdapter(private val doneToDoList: List<ToDoModel>, private val viewModel: DoneViewModel) :
    RecyclerView.Adapter<DoneToDoAdapter.DoneToDoViewHolder>() {

    class DoneToDoViewHolder(binding: TodoCardBinding) : RecyclerView.ViewHolder(binding.root) {
        val doneToDoBinding: TodoCardBinding = binding
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoneToDoViewHolder {
        val binding = TodoCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DoneToDoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DoneToDoViewHolder, position: Int) {
        val doneToDo = doneToDoList[position]
        holder.doneToDoBinding.todo = doneToDo

        holder.doneToDoBinding.checkBox.setOnCheckedChangeListener { _, isChecked ->
            if (!isChecked) {
                val toDoName = doneToDo.toDoName
                val toDoIsImportant = doneToDo.IsImportant
                val toDoId = doneToDo.toDoId
                val updatedToDo = ToDoModel(toDoId,toDoName,toDoIsImportant,false)

                viewModel.updateToDo(updatedToDo)
                viewModel.getDoneToDoList()
            }
        }
    }

    override fun getItemCount(): Int = doneToDoList.size
}