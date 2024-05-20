package cs.hse.bookservice.dto.requests

data class BookRequest(
    val title: String,
    val author: String,
    val description: String? = null,
)
