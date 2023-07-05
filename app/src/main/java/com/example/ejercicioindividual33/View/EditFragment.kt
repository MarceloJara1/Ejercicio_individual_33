import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.ejercicioindividual33.Model.Item
import com.example.ejercicioindividual33.View.ItemAdapter
import com.example.ejercicioindividual33.ViewModel.ItemViewModel
import com.example.ejercicioindividual33.databinding.FragmentEditBinding
import com.google.android.material.snackbar.Snackbar

class EditFragment : Fragment() {

    private var _binding: FragmentEditBinding? = null
    private val binding get() = _binding!!

    private lateinit var itemViewModel: ItemViewModel
    private lateinit var itemAdapter: ItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        itemViewModel = ViewModelProvider(requireActivity()).get(ItemViewModel::class.java)

        val itemId = EditFragmentArgs.fromBundle(requireArguments()).id

        itemViewModel.getItemById(itemId).observe(viewLifecycleOwner) { item ->
            item?.let {
                binding.textFieldItem.editText?.setText(item.itemName)
            }
        }
        itemAdapter = ItemAdapter(itemViewModel)

        binding.btnGuardar.setOnClickListener {
            updateItem(itemId)
        }
    }

    private fun updateItem(itemId: Int) {
        val newItemName = binding.textFieldItem.editText?.text.toString()
        if (newItemName.isNotEmpty()) {
            val item = Item(itemId, newItemName)
            itemViewModel.updateItem(item)

            // Pasar el nuevo valor al ItemAdapter
            itemAdapter.setItems(listOf(item))

            Snackbar.make(binding.root, "Dato actualizado correctamente...", Snackbar.LENGTH_SHORT).show()

            findNavController().navigateUp()
        } else {
            Snackbar.make(binding.root, "Por favor, ingrese un dato...", Snackbar.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}