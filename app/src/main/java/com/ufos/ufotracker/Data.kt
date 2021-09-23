package com.ufos.ufotracker

data class Sighting(val timestamp: String, val speedKnots: Int, val type: UfoType)

sealed class UfoType(val type: String) {
    object Blob : UfoType("blob")
    object Lampshade : UfoType("lampshade")
    object MysteriousLights : UfoType("mysteriousLights")
}