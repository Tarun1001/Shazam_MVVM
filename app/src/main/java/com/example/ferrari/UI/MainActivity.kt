package com.example.ferrari.UI

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.ferrari.HomeViewmodel.HomeViewModel
import com.example.ferrari.HomeViewmodel.SearchViewModelFactory
import com.example.ferrari.Retrofit.MyRetrofitBuilder
import com.example.ferrari.Utils.NetworkResponse
import com.example.ferrari.SongRepository.ShazamRepository
import com.example.ferrari.databinding.ActivityMainBinding
import java.net.URLEncoder

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val apiService= MyRetrofitBuilder.apiService
        val shazamRepository= ShazamRepository(apiService)

        //animation
        val backGroundView =binding.backgroundView
        //
        val viewModel = ViewModelProvider(this, SearchViewModelFactory(shazamRepository)).get(
            HomeViewModel::class.java)

        //
        //
        //
        binding.button.setOnClickListener(View.OnClickListener {
            hideKeyboard()
            binding.editTextText.clearFocus()

            if(binding.editTextText.text.toString() ==""){
                Toast.makeText(this,"Please enter a song",Toast.LENGTH_SHORT).show()

            }
            else{
                backGroundView.startAnimation()

                val input=binding.editTextText.text.toString()
                val encodedTerm = URLEncoder.encode(input, "UTF-8")
                viewModel.searchForTheSong(encodedTerm,"en-US",0,5)

            }
        })
        viewModel.songsresults.observe(this) { it ->
            when (it) {
                is NetworkResponse.Loading ->{
                    binding.animationView.visibility=View.VISIBLE
                    binding.animationView.playAnimation()
                    //binding.textView.text="Loading..."
                }
                is NetworkResponse.Success ->{
                    binding.animationView.visibility=View.GONE
                    binding.animationView.cancelAnimation()
                    val songs= it.data
                    if (songs != null) {
                        binding.textView.visibility=View.VISIBLE
                        binding.textView.text= songs.tracks.hits[0].track.share.subject
                    }
                }
                is NetworkResponse.Failure -> {
                    binding.animationView.visibility=View.GONE
                    binding.animationView.cancelAnimation()
                    val error = it.exception
                    // Handle the error
                    Toast.makeText(this,"exception $error",Toast.LENGTH_SHORT).show()
                }

            }

        }
        //
        //
        //




    }
    private fun hideKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
    }
}

/*{
  "location": {
    "name": "Boston",
    "region": "Lincolnshire",
    "country": "United Kingdom",
    "lat": 53.1,
    "lon": -0.13,
    "tz_id": "Europe/London",
    "localtime_epoch": 1694855654,
    "localtime": "2023-09-16 10:14"
  },
  "current": {
    "last_updated_epoch": 1694854800,
    "last_updated": "2023-09-16 10:00",
    "temp_c": 16,
    "temp_f": 60.8,
    "is_day": 1,
    "condition": {
      "text": "Sunny",
      "icon": "//cdn.weatherapi.com/weather/64x64/day/113.png",
      "code": 1000
    },
    "wind_mph": 4.3,
    "wind_kph": 6.8,
    "wind_degree": 340,
    "wind_dir": "NNW",
    "pressure_mb": 1015,
    "pressure_in": 29.97,
    "precip_mm": 0,
    "precip_in": 0,
    "humidity": 94,
    "cloud": 0,
    "feelslike_c": 16,
    "feelslike_f": 60.8,
    "vis_km": 10,
    "vis_miles": 6,
    "uv": 5,
    "gust_mph": 11.6,
    "gust_kph": 18.7
  }
}*/