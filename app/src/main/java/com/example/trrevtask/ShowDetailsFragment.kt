package com.example.trrevtask

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trrevtask.adapter.RVAdapter
import com.example.trrevtask.databinding.FragmentShowDetailsBinding
import com.example.trrevtask.repository.Repository

class ShowDetailsFragment : Fragment() {

    private var _binding: FragmentShowDetailsBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: ShowDetailsViewModel
    private val RVAdapter by lazy { RVAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShowDetailsBinding.inflate(inflater, container, false)

        setupRecyclerView()

        binding.progressBar.visibility = View.VISIBLE
        val repository = Repository()
        val viewModelFactory = ShowDetailsViewModelFactory(repository)
        viewModel = ViewModelProvider(requireActivity(), viewModelFactory)[ShowDetailsViewModel::class.java]
        viewModel.getPost()

        viewModel.myResponse.observe(requireActivity(), Observer { response->
            if(response.isSuccessful) {
                binding.progressBar.visibility = View.GONE
                val body = response?.body()
                val posts = body?.post
                val size = posts?.size
                response.body()?.let { RVAdapter.setData(it.post) }
                Log.d("Response", body.toString())
                Log.d("Response", posts.toString())
                Log.d("Response", size.toString())
                posts?.forEach {
                    Log.d("Posts", it.id.toString())
                    Log.d("Posts", it.name)
                    Log.d("Posts", it.url)
                    Log.d("Posts", it.created_at)
                    Log.d("Posts", it.updated_at)
                    Log.d("Posts", "--------------")
                }

            } else{
                Log.d("Response", "Error")
            }
        })
        return binding.root
    }

    private fun setupRecyclerView() {
        binding.recyclerView.adapter = RVAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireActivity())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}