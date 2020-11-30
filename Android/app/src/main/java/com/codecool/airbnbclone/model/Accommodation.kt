package com.codecool.airbnbclone

data class Location(val lat: Double, val lon: Double)

data class Accommodation(
    val id: String = "",
    var description: String = "",
    val location: Location = Location(0.0, 0.0),
    var defaultPrice: Int = 0,
    var maxGuests: Int = 0,
    var bed: Int = 0,
    var bedroom: Int = 0,
    var bathroom: Int = 0,
    var childrenAllowed: Boolean = false,
    var petAllowed: Boolean = false,
    var smokingAllowed: Boolean = false
)