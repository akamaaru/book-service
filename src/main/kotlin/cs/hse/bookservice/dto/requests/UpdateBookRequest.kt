package cs.hse.bookservice.dto.requests

data class UpdateBookRequest(
    val id: Long,
    val title: String,
    val author: String,
    val description: String,
)
