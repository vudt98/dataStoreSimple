package com.app.datastoresimple.fragment_1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.app.datastoresimple.App
import com.app.datastoresimple.data_store.DataStoreSimple
import com.app.datastoresimple.databinding.FragmentTopBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Fragment1 : Fragment() {

    lateinit var binding : FragmentTopBinding

    private val viewModel: Fragment1ViewModel by viewModels()

    private val dataStore = DataStoreSimple(App.getAppContext()!!)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTopBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindAction()
    }

    private fun bindAction() {
        binding.btnSave.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                dataStore.saveUser(binding.edName.text.toString(), binding.edAge.text.toString())
            }
//            viewModel.saveData(binding.edName.text.toString(), binding.edAge.text.toString().toInt())
            Toast.makeText(requireContext(), "Save Data", Toast.LENGTH_SHORT).show()
        }
    }
}