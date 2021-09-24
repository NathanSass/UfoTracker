package com.ufos.ufotracker.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ufos.ufotracker.Seeds
import com.ufos.ufotracker.SightingData

class UfoModulesViewModel : ViewModel() {

    val sightingsLiveData = MutableLiveData<List<SightingData>>()

    var sightingTypes: List<String> = emptyList()

    fun requestData() {
        val datas = Seeds.getSeeds(50).filter { sightingTypes.contains(it.type) }
        sightingsLiveData.value = datas
    }
}