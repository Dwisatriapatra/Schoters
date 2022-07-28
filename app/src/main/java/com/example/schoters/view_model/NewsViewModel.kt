package com.example.schoters.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.schoters.model.GetAllNewsByKeywordResponse
import com.example.schoters.networking.ApiServices
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(apiServices: ApiServices) : ViewModel() {
    private val liveDataNewsByKeyword = MutableLiveData<GetAllNewsByKeywordResponse>()
    val newsByKeyword: LiveData<GetAllNewsByKeywordResponse> = liveDataNewsByKeyword
    private val api = apiServices

    fun getAllNewsByKeyword(apiKey: String, keyword: String) {
        api.getNewsByKeyword(keyword, apiKey)
            .enqueue(object : Callback<GetAllNewsByKeywordResponse> {
                override fun onResponse(
                    call: Call<GetAllNewsByKeywordResponse>,
                    response: Response<GetAllNewsByKeywordResponse>
                ) {
                    if (response.isSuccessful) {
                        liveDataNewsByKeyword.value = response.body()
                    }
                }

                override fun onFailure(call: Call<GetAllNewsByKeywordResponse>, t: Throwable) {
                    //
                }

            })
    }
}