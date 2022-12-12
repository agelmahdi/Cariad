package com.agelmahdi.cariad

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.agelmahdi.cariad.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import com.agelmahdi.cariad.poi_feature.presentation.MapsFragment

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MapsFragment.newInstance())
                .commitNow()
        }
    }
}