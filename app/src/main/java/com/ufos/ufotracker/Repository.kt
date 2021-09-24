package com.ufos.ufotracker

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.util.Calendar

object Repository {
    private val _sightingData = MutableLiveData<List<SightingData>>()
    val sightingData: LiveData<List<SightingData>> = _sightingData

    fun fetchData() {
        _sightingData.value = Seeds.getSeeds(50)
    }
}

private object Seeds {
    private fun getDate(): String {
        val year = (1990 until 2021).random()
        val month = (1..12).random()
        val day = (1..30).random()
        val hour = (1..24).random()
        val minute = (1..60).random()

        val date = Calendar.Builder()
        date.setDate(year, month, day)
        date.setTimeOfDay(hour, minute, 0)
        val time = date.build().timeInMillis
        return time.toString()
    }

    fun getRandomSightingData(): SightingData {
        val speed = (1..100000).random()
        val icon = UfoType.values().random().type
        return SightingData(getDate(), speed, icon)
    }

    fun getSeeds(count: Int): List<SightingData> {
        val datas = mutableListOf<SightingData>()
        repeat(count) {
            datas.add(getRandomSightingData())
        }
        return datas
    }
}