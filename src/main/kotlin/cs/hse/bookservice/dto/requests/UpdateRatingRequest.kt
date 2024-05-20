package cs.hse.bookservice.dto.requests

data class UpdateRatingRequest(
    val id: Long,
    val rating: Double
)
