package com.codecool.airbnbclone

import com.google.firebase.database.FirebaseDatabase
import java.util.*

class DataManager {

    val database = FirebaseDatabase.getInstance()
    val myRef = database.getReference("accommodation")

    private fun addAccommodationToDB(accommodation: Accommodation) {
        myRef.child(accommodation.id).setValue(accommodation)
    }

    fun createSampleData() {

        val accommodations: MutableList<Accommodation> = mutableListOf()

        generateListOfSampleData(accommodations)
        accommodations.forEach { accommodation -> addAccommodationToDB(accommodation) }
    }

    private fun generateListOfSampleData(accommodations: MutableList<Accommodation>) {
        accommodations
            .add(
                Accommodation(
                    id = UUID.randomUUID().toString(),
                    description = "Fancy cosy whatever",
                    location = Location(0.0, 0.0),
                    defaultPrice = 250,
                    maxGuests = 5,
                    bed = 2,
                    bedroom = 1,
                    bathroom = 1,
                    childrenAllowed = false,
                    petAllowed = true,
                    smokingAllowed = false
                )
            )

        accommodations
            .add(
                Accommodation(
                    id = UUID.randomUUID().toString(),
                    description = "Huge nagypolgári kicsit sznobi place",
                    location = Location(0.0, 0.0),
                    defaultPrice = 250,
                    maxGuests = 5,
                    bed = 2,
                    bedroom = 1,
                    bathroom = 5,
                    childrenAllowed = false,
                    petAllowed = true,
                    smokingAllowed = false
                )
            )

        accommodations
            .add(
                Accommodation(
                    id = UUID.randomUUID().toString(),
                    description = "Huge nagypolgári kicsit sznobi place",
                    location = Location(0.0, 0.0),
                    defaultPrice = 250,
                    maxGuests = 5,
                    bed = 2,
                    bedroom = 1,
                    bathroom = 1,
                    childrenAllowed = false,
                    petAllowed = true,
                    smokingAllowed = false
                )
            )

        accommodations
            .add(
                Accommodation(
                    id = UUID.randomUUID().toString(),
                    description = "Huge nagypolgári kicsit sznobi place",
                    location = Location(0.0, 0.0),
                    defaultPrice = 250,
                    maxGuests = 5,
                    bed = 2,
                    bedroom = 1,
                    bathroom = 1,
                    childrenAllowed = false,
                    petAllowed = true,
                    smokingAllowed = false
                )
            )

        accommodations
            .add(
                Accommodation(
                    id = UUID.randomUUID().toString(),
                    description = "Kicsi sötét szar",
                    location = Location(0.0, 0.0),
                    defaultPrice = 250,
                    maxGuests = 5,
                    bed = 2,
                    bedroom = 1,
                    bathroom = 1,
                    childrenAllowed = false,
                    petAllowed = true,
                    smokingAllowed = false
                )
            )

        accommodations
            .add(
                Accommodation(
                    id = UUID.randomUUID().toString(),
                    description = "Elmegy",
                    location = Location(0.0, 0.0),
                    defaultPrice = 250,
                    maxGuests = 5,
                    bed = 2,
                    bedroom = 1,
                    bathroom = 1,
                    childrenAllowed = false,
                    petAllowed = true,
                    smokingAllowed = false
                )
            )

        accommodations
            .add(
                Accommodation(
                    id = UUID.randomUUID().toString(),
                    description = "Legjobb",
                    location = Location(0.0, 0.0),
                    defaultPrice = 400,
                    maxGuests = 5,
                    bed = 2,
                    bedroom = 1,
                    bathroom = 1,
                    childrenAllowed = true,
                    petAllowed = true,
                    smokingAllowed = false
                )
            )
    }
}