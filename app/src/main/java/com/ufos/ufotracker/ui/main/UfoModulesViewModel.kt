package com.ufos.ufotracker.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ufos.ufotracker.Repository
import com.ufos.ufotracker.SightingData

class UfoModulesViewModel : ViewModel() {

    val sightingsLiveData = MutableLiveData<List<SightingData>>()

    var sightingTypes: List<String> = emptyList()

    fun requestData() {
        Repository.sightingData.observeForever { newDatas ->
            val acceptedTypes = newDatas.filter { data ->
                sightingTypes.contains(data.type)
            }
            sightingsLiveData.value = acceptedTypes
        }
    }
}