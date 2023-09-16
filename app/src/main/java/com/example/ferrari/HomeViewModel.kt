package com.example.ferrari

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ferrari.Model.data.Search.SongSearch
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class HomeViewModel(private val shazamRepository: ShazamRepository):ViewModel() {

    private val _songresults= MutableLiveData<NetworkResponse<SongSearch>>()

    val songsresults :LiveData<NetworkResponse<SongSearch>> =_songresults

    fun searchForTheSong(term: String,
                         locale: String,
                         offset: Int,
                         limit: Int){
        viewModelScope.launch {
            _songresults.value=NetworkResponse.Loading
            val result = shazamRepository.SearchTrack(term, locale, offset, limit)
            _songresults.value= result
        }
    }


}