package com.ufos.ufotracker.data

data class SightingData(val timestamp: String, val speedKnots: Int, val type: String)

enum class UfoType(val type: String, val displayString: String) {
    BLOB("blob", "Blob"),
    LAMPSHADE("lampshade", "Lamp Shade"),
    MYSTERIOUS_LIGHTS("mysteriousLights", "Mysterious Lights")
}