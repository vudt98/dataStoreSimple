package com.app.datastoresimple.fragment_2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.app.datastoresimple.App
import com.app.datastoresimple.data_store.DataStoreSimple
import com.app.datastoresimple.databinding.FragmentBottomBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class Fragment2 : Fragment() {

    private lateinit var binding: FragmentBottomBinding

    private val dataStore = DataStoreSimple(App.getAppContext()!!)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBottomBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getUser()
    }

    private fun getUser() {
        binding.btnGet.setOnClickListener {
            lifecycleScope.launch {
                launch {
                    dataStore.getPreference(
                        stringPreferencesKey(binding.edKey.text.toString()), ""
                    ).collect {
                        binding.tvName.text = it
                    }
                }
            }
            Toast.makeText(requireContext(), "Get Data", Toast.LENGTH_SHORT).show()
        }
    }
}