package data.models

import kotlinx.serialization.Serializable

@Serializable
data class Greetings(
    val message: String? = null
)
