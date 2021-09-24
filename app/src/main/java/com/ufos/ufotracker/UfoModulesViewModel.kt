package com.ufos.ufotracker

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UfoModulesViewModel : ViewModel() {

    val sightingsLiveData = MutableLiveData<List<SightingData>>()

    var sightingTypes: List<String> = emptyList()

    val repo = Repository

    fun subscribeToData() {
        // TODO: need to unsubscribe when vm destroyed
        Repository.sightingData.observeForever { newDatas ->
            val acceptedTypes = newDatas.filter { data ->
                sightingTypes.contains(data.type)
            }
            sightingsLiveData.value = acceptedTypes
        }
    }

    fun addNewRecord() {
        val type = sightingTypes.random()
        repo.addNewSighting(type)
    }

    fun removeRecord(data: SightingData) {
        repo.removeSighting(data)
    }
}