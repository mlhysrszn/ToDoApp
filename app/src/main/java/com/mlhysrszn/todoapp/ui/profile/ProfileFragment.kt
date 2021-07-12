package com.mlhysrszn.todoapp.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mlhysrszn.todoapp.R
import com.mlhysrszn.todoapp.data.ToDoDAO
import com.mlhysrszn.todoapp.data.ToDoDatabase
import com.mlhysrszn.todoapp.databinding.ProfileFragmentBinding

class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.profile_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val toDoDAO: ToDoDAO? = ToDoDatabase.getToDoDatabase(requireContext())?.toDoDao()
        val viewModel: ProfileViewModel by viewModels { ProfileViewModelFactory(toDoDAO) }

        val binding = ProfileFragmentBinding.bind(view)

        viewModel.allToDoCount.observe(viewLifecycleOwner, {
            binding.activeCount.text = it.toString()
        })

        viewModel.allDoneToDoCount.observe(viewLifecycleOwner, {
            binding.doneCount.text = it.toString()
        })

        viewModel.allImportantToDoCount.observe(viewLifecycleOwner, {
            binding.importantCount.text = "Önemli $it"
        })

        binding.resetButton.setOnClickListener{
            viewModel.deleteAllToDos()
            binding.activeCount.text = "0"
            binding.doneCount.text = "0"
            binding.importantCount.text = "Önemli 0"
        }
    }

}