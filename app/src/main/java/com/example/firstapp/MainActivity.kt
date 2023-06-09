package com.example.firstapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.firstapp.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.searchButton.setOnClickListener{
            val countryName = binding.countryNameEditText.text.toString()

            lifecycleScope.launch{
                val countries = restCountriesApi.getCountryByName(countryName)
                val country: Country = countries[0]

                binding.countryNameTextView.text = country.name
                binding.capitalTextView.text = country.capital
                binding.populationTextView.text = formatNumber(country.population)
                binding.areaTextView.text = formatNumber(country.area)
                binding.languagesTextView.text = languagesToString(country.languages)

                loadSvg(binding.imageView, country.flag)
            }
        }


        }
    }
