package com.example.ejercicioindividual33.View

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.ejercicioindividual33.Model.Item
import com.example.ejercicioindividual33.R
import com.example.ejercicioindividual33.ViewModel.ItemViewModel
import com.example.ejercicioindividual33.databinding.FragmentSecondBinding
import com.google.android.material.snackbar.Snackbar


class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private lateinit var itemViewModel: ItemViewModel

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        itemViewModel = ViewModelProvider(requireActivity()).get(ItemViewModel::class.java)
        binding.btnGuardar.setOnClickListener{
            saveData()
        }
    }

    private fun saveData(){
        val item = binding.textFieldItem.editText?.text.toString()
        if (!item.isNullOrEmpty()){
            val item = Item(
                0, item
            )
            itemViewModel.insertItem(item)

            binding.textFieldItem.editText?.text?.clear()
            findNavController().navigateUp()
            Snackbar.make(binding.root, "Datos guardados correctamente...", Snackbar.LENGTH_SHORT).show()

        }else{
            Snackbar.make(binding.root, "Por favor ingrese datos...", Snackbar.LENGTH_SHORT).show()
        }
    }
    private fun editItem(item: Item){

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}