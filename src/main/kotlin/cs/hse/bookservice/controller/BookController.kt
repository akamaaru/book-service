package cs.hse.bookservice.controller

import cs.hse.bookservice.dto.requests.BookRequest
import cs.hse.bookservice.dto.requests.UpdateBookRequest
import cs.hse.bookservice.dto.requests.UpdateRatingRequest
import cs.hse.bookservice.dto.responses.BookResponse
import cs.hse.bookservice.service.BookService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/books")
class BookController (val bookService: BookService) {

    @GetMapping("/get")
    fun get(): List<BookResponse> {
        return bookService.getAllBooks()
    }

    @GetMapping("/get/{id}")
    fun getById(@PathVariable id: Long): BookResponse {
        // IT: check that book is the same as we expect
        return bookService.getBookById(id)
    }

    @PostMapping("/save")
    fun saveBook(@RequestBody book: BookRequest): Long? {
        return bookService.saveNewBook(book)
    }

    @PatchMapping("/edit")
    fun editBook(@RequestBody updateBookRequest: UpdateBookRequest): BookResponse {
        return bookService.updateBookById(updateBookRequest)
    }

    @PatchMapping("/rating")
    fun updateRating(@RequestBody updateRatingRequest: UpdateRatingRequest): BookResponse {
        return bookService.updateRatingById(updateRatingRequest)
    }


}