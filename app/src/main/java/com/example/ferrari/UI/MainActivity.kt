package com.example.ferrari.UI

import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ferrari.HomeViewmodel.HomeViewModel
import com.example.ferrari.HomeViewmodel.SearchViewModelFactory
import com.example.ferrari.Model.data.Search.Tracks
import com.example.ferrari.R
import com.example.ferrari.Retrofit.MyRetrofitBuilder
import com.example.ferrari.Utils.NetworkResponse
import com.example.ferrari.SongRepository.ShazamRepository
import com.example.ferrari.Utils.CommonMethods
import com.example.ferrari.Utils.TrackT
import com.example.ferrari.Utils.animation.CenterFillBackgroundView
import com.example.ferrari.databinding.ActivityMainBinding
import java.net.URLEncoder

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    val tracks: MutableList<TrackT> = mutableListOf()
    private lateinit var  backGroundView: CenterFillBackgroundView
    private lateinit var backgroundcolor:String
    private  var hexcolor :Int = 0
    private lateinit var  myView: CenterFillBackgroundView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val apiService= MyRetrofitBuilder.apiService
        val shazamRepository= ShazamRepository(apiService)
        myView = CenterFillBackgroundView(this, null)
        backGroundView =binding.backgroundView

        val viewModel = ViewModelProvider(this, SearchViewModelFactory(shazamRepository)).get(
            HomeViewModel::class.java)

        binding.button.setOnClickListener(View.OnClickListener {
            hideKeyboard()
            binding.editTextText.clearFocus()

            if(binding.editTextText.text.toString() ==""){
                Toast.makeText(this,"Please enter a song",Toast.LENGTH_SHORT).show()

            }
            else{
                //backGroundView.startAnimation()

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
                        val tracks= songs.tracks
                        showcardview(tracks)

                    }

                    populatetracks()
                    binding.cardRecycleview.apply {
                        layoutManager=LinearLayoutManager(applicationContext,LinearLayoutManager.HORIZONTAL,false)
                        adapter= CardAdapter(tracks)
                    }
                }
                is NetworkResponse.Failure -> {
                    binding.animationView.visibility=View.GONE
                    binding.animationView.cancelAnimation()

                    val errorCode = it.errorCode
                    val errorMessage = it.errorMessage
                    val exception = it.exception

                    //we can set it something like this
                    //if the user is in debug mode we can check it and display a different toast message with http code

                    Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
                    exception?: Log.e("exception",errorCode.toString()+" "+exception)
                }

                else -> {}
            }

        }

    }

    private fun showcardview(tracks: Tracks) {
        binding.textView.text= tracks.hits[0].track.share.subject
        backgroundcolor=tracks.hits[0].track.images.joecolor

        var convert= CommonMethods.customColorToHex(backgroundcolor)
        hexcolor=Color.parseColor(convert)
        myView.setEndColor(hexcolor)
        backGroundView.startAnimation()

    }

    fun populatetracks(){
        val track1= TrackT(layout = "5",
            type = "MUSIC",
            key = "20066955",
            title = "Kiss The Rain",
            subtitle = "Billie Myers",)
        tracks.add(track1)
        tracks.add(track1)
        tracks.add(track1)
    }
    private fun hideKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
    }
}
