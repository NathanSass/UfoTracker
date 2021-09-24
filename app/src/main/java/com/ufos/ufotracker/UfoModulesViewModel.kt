package com.ufos.ufotracker

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UfoModulesViewModel : ViewModel() {

    val sightingsLiveData = MutableLiveData<List<SightingData>>()

    var sightingTypes: List<String> = emptyList()

    fun subscribeToData() {
        Repository.sightingData.observeForever { newDatas ->
            val acceptedTypes = newDatas.filter { data ->
                sightingTypes.contains(data.type)
            }
            sightingsLiveData.value = acceptedTypes
        }
    }

    fun addNewRecord() {
        val type = sightingTypes.random()
        Repository.addNewSighting(type)
    }
}