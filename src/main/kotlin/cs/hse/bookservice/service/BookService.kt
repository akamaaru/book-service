package cs.hse.bookservice.service

import cs.hse.bookservice.dto.requests.BookRequest
import cs.hse.bookservice.dto.responses.BookResponse
import cs.hse.bookservice.dto.requests.UpdateBookRequest
import cs.hse.bookservice.dto.requests.UpdateRatingRequest
import cs.hse.bookservice.entity.Book
import cs.hse.bookservice.repository.BookRepository
import cs.hse.bookservice.util.errorHandling.ResourceNotFoundException
import org.springframework.stereotype.Service

@Service
class BookService (val bookRepository: BookRepository) {

    fun getAllBooks(): List<BookResponse> {
        // Unit test: test that findAll is called once
        // IT test: test that the initial number of books is the same
        return bookRepository.findAll().map { BookResponse(it.id, it.title, it.author) }.toList()
    }

    fun getBookById(id: Long): BookResponse {
        // Unit test: check that ids are same and verify that findById is called with needed id
        // Unit test: check that the method throws
        // IT test: test that Book object is the same as BookResponse

        val book = bookRepository.findById(id).orElseThrow{ ResourceNotFoundException("Book with id $id was not found!") }
        return BookResponse(book.id, book.title, book.author, book.description, book.rating)
    }

    fun saveNewBook(bookRequest: BookRequest): Long? {
        // Unit test: check that id is not null
        // Unit test: check that the repository is not called if book exists
        // Unit test: check that save method saves all given data

        // IT test: test that book saves in DB
        // IT test: test that the saved book and data in BookRequest are the same

        val matchingBook = bookRepository.findAll().find { it.author == bookRequest.author &&  it.title == bookRequest.title}

        if (matchingBook != null) {
           throw IllegalArgumentException("Book ${bookRequest.title} by ${bookRequest.author} already exists!")
        }

        // if book exists we should not call this
        return bookRepository.save(
            Book(
                title = bookRequest.title,
                author = bookRequest.author,
                description = bookRequest.description
            )
        ).id
    }

    fun updateBookById(updateRequest: UpdateBookRequest): BookResponse {
        // TODO: Add unit tests for different test cases
        // TODO: Add IT tests for different test cases

        // if book does not exist we should throw
        val book = bookRepository.findById(updateRequest.id).orElseThrow{ ResourceNotFoundException("Book with id ${updateRequest.id} was not found!") }

        // if there is no changes we should not call the db again
        if (book.title == updateRequest.title && book.author == updateRequest.author && book.description == updateRequest.description) {
            throw IllegalArgumentException("Data does not need to be updated!")
        }

        book.author = updateRequest.author
        book.description = updateRequest.description
        book.title = updateRequest.title

        val updated = bookRepository.save(book)
        return BookResponse(updated.id, updated.title, updated.author, updated.description)
    }

    fun updateRatingById(updateRequest: UpdateRatingRequest): BookResponse {
        // TODO: Add unit tests for different test cases
        // TODO: Add IT tests for different test cases

        // if book does not exist we should throw
        val book = bookRepository.findById(updateRequest.id).orElseThrow{ ResourceNotFoundException("Book with id ${updateRequest.id} was not found!") }

        // if the rating is the same we should not call the db again
        if (updateRequest.rating == book.rating) {
            throw IllegalArgumentException("Data does not need to be updated!")
        }

        book.rating = updateRequest.rating
        val updated = bookRepository.save(book)

        return BookResponse(updated.id, updated.title, updated.author, updated.description, updated.rating)
    }
}