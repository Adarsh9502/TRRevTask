package com.example.trrevtask

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.trrevtask.databinding.FragmentDetailsBinding
import com.example.trrevtask.model.Push
import com.example.trrevtask.repository.Repository

class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: DetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)

        val repository = Repository()
        val viewModelFactory = DetailsViewModelFactory(repository)
        viewModel =
            ViewModelProvider(requireActivity(), viewModelFactory)[DetailsViewModel::class.java]

        binding.nextButton.setOnClickListener {

            if (binding.fullNameEdit.text.toString().isEmpty()) {
                Toast.makeText(requireActivity(), "Enter Name", Toast.LENGTH_SHORT).show()
            } else if (binding.phoneEdit.text.toString().isEmpty()) {
                Toast.makeText(requireActivity(), "Enter Phone", Toast.LENGTH_SHORT).show()
            } else if (binding.emailEdit.text.toString().isEmpty()) {
                Toast.makeText(requireActivity(), "Enter Email", Toast.LENGTH_SHORT).show()
            } else if (binding.addressEdit.text.toString().isEmpty()) {
                Toast.makeText(requireActivity(), "Enter Address", Toast.LENGTH_SHORT).show()
            }  else {
                val selectedId = binding.radioGroup.checkedRadioButtonId
                val gender = when (selectedId) {
                    R.id.Male -> "Male"
                    R.id.Female -> "Female"
                    R.id.Others -> "Others"
                    else -> "Gender Not Specified"
                }
                val name = binding.fullNameEdit.text.toString()
                val email = binding.emailEdit.text.toString()
                val phone = binding.phoneEdit.text.toString()
                val address = binding.addressEdit.text.toString()

                val push = Push(2, name, phone, gender, email, address)

                viewModel.pushPost(push)
                viewModel.myResponse.observe(requireActivity(), Observer { response ->
                    if (response.isSuccessful) {
                        Toast.makeText(
                            requireActivity(),
                            "Submitted Successfully",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(
                            requireActivity(),
                            response.code().toString(),
                            Toast.LENGTH_SHORT
                        ).show()
                        Log.d("Response", "Error")
                    }
                })
            }
        }

        binding.moveToDetails.setOnClickListener {
            findNavController().navigate(R.id.action_detailsFragment_to_showDetailsFragment)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}