package com.ufos.ufotracker

data class SightingData(val timestamp: String, val speedKnots: Int, val type: String)

enum class UfoType(val type: String) {
    BLOB("blob"),
    LAMPSHADE("lampshade"),
    MYSTERIOUS_LIGHTS("mysteriousLights")
}