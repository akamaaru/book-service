package cs.hse.bookservice.dto.responses

import jakarta.persistence.Column

data class BookResponse (
    val id: Long? = null,
    val title: String,
    val author: String,
    val description: String? = null,
    val rating: Double? = null
)