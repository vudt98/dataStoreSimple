package com.app.datastoresimple

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.app.datastoresimple.databinding.ActivityMainBinding
import com.app.datastoresimple.fragment_1.Fragment1
import com.app.datastoresimple.fragment_2.Fragment2

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
    }

    private fun initView() {
        supportFragmentManager.beginTransaction()
            .add(R.id.container1, Fragment1())
            .addToBackStack(null)
            .commit()

        supportFragmentManager.beginTransaction()
            .add(R.id.container2, Fragment2())
            .addToBackStack(null)
            .commit()

    }

}