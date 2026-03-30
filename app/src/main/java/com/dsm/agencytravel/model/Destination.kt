package com.dsm.agencytravel.model

import java.io.Serializable

data class Destination (
    var id: String = "",
    var name: String = "",
    var country: String = "",
    var price: Double = 0.0,
    var description: String = "",
    var imageUrl: String = ""
) : Serializable