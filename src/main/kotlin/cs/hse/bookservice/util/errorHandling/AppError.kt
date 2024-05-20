package cs.hse.bookservice.util.errorHandling


class AppError(var statusCode: Int, var message: String?)

class ResourceNotFoundException(message: String?) : RuntimeException(message)