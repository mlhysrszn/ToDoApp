package com.mlhysrszn.todoapp.ui.done

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.mlhysrszn.todoapp.R
import com.mlhysrszn.todoapp.databinding.DoneFragmentBinding

class DoneFragment : Fragment() {

    private lateinit var rv: RecyclerView
    private lateinit var adapter: DoneToDoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.done_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val application = requireNotNull(this.activity).application
        val viewModel: DoneViewModel by viewModels { DoneViewModelFactory(application) }
        println(viewModel.doneToDoList.value)

        val binding = DoneFragmentBinding.bind(view)
        rv = binding.doneRv

        viewModel.doneToDoList.observe(viewLifecycleOwner, {
            adapter = DoneToDoAdapter(it, viewModel)
            rv.adapter = adapter
        })
    }

}