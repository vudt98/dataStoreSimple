package com.app.datastoresimple.fragment_1

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.datastoresimple.App
import com.app.datastoresimple.data_store.DataStoreSimple
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Fragment1ViewModel : ViewModel() {

    private val dataStore = DataStoreSimple(App.getAppContext()!!)

    fun saveData(name: String?, age: String?) {
        viewModelScope.launch(Dispatchers.IO) {
            dataStore.saveUser(name, age)
        }
    }
}