package com.app.datastoresimple.fragment_2

import androidx.lifecycle.ViewModel
import com.app.datastoresimple.App
import com.app.datastoresimple.data_store.DataStoreSimple

class Fragment2ViewModel : ViewModel() {

    private val dataStore = DataStoreSimple(App.getAppContext()!!)


}