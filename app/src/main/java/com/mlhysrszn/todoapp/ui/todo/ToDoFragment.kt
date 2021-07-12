package com.mlhysrszn.todoapp.ui.todo

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import com.mlhysrszn.todoapp.R
import com.mlhysrszn.todoapp.data.ToDoModel
import com.mlhysrszn.todoapp.databinding.ToDoFragmentBinding

class ToDoFragment : Fragment() {

    private lateinit var rv: RecyclerView
    private lateinit var adapter: ToDoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.to_do_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val application = requireNotNull(this.activity).application
        val viewModel: ToDoViewModel by viewModels { ToDoViewModelFactory(application) }

        val binding = ToDoFragmentBinding.bind(view)
        rv = binding.toDoRv

        toDoListObserver(viewModel)

        binding.addButton.setOnClickListener {
            val addAlert = LayoutInflater.from(context).inflate(R.layout.add_alert, null, false)
            val alertDialogBuilder = AlertDialog.Builder(context)
            alertDialogBuilder.setView(addAlert)
            val dialog = alertDialogBuilder.create()
            dialog.show()

            val alertAddButton: Button = addAlert.findViewById(R.id.alertAddButton)
            val alertDoneButton: Button = addAlert.findViewById(R.id.alertDoneButton)
            val editTextToDo: EditText = addAlert.findViewById(R.id.editTextToDo)
            val textInputLayout: TextInputLayout = addAlert.findViewById(R.id.inputLayoutToDo)
            val checkBoxImportant: CheckBox = addAlert.findViewById(R.id.checkBoxImportant)

            alertAddButton.setOnClickListener {
                val text = editTextToDo.text.toString()
                val isImportant = checkBoxImportant.isChecked

                if (text.isEmpty()) {
                    textInputLayout.error = "Bu alan boş bırakılamaz"
                }
                else {
                    viewModel.addToDo(text, isImportant)

                    viewModel.getAllToDos()
                    toDoListObserver(viewModel)
                    dialog.dismiss()

                    Snackbar.make(view, "ToDo Added", Snackbar.LENGTH_SHORT).show()
                }
            }

            alertDoneButton.setOnClickListener {
                dialog.dismiss()
            }

        }

    }

    private fun toDoListObserver(viewModel: ToDoViewModel){
        viewModel.toDoList.observe(viewLifecycleOwner, {
            adapter = ToDoAdapter(it, viewModel)
            rv.adapter = adapter
        })
    }

}