package cs.hse.bookservice.service

import cs.hse.bookservice.dto.requests.BookRequest
import cs.hse.bookservice.dto.requests.UpdateBookRequest
import cs.hse.bookservice.dto.requests.UpdateRatingRequest
import jakarta.transaction.Transactional
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest


@SpringBootTest

class BookServiceIT {

    @Autowired
    lateinit var bookService: BookService


    @Test
    @Order(1)
    @Transactional
    fun getAllBooks() {
        Assertions.assertEquals(6, bookService.getAllBooks().size)
    }

    @Test
    @Order(2)
    @Transactional
    fun saveNewBook() {
        val bookInit: BookRequest = BookRequest("Test", "Auth", "Desc")
        val bookInit2: BookRequest = BookRequest("Test", "Auth", "Desc")

        Assertions.assertThrows(IllegalArgumentException::class.java) {
            bookService.saveNewBook(bookInit)
            bookService.saveNewBook(bookInit2)
        }
    }

    @Test
    @Order(3)
    @Transactional
    fun updateBookById_positive() {
        val request = UpdateBookRequest(1, "NewTest", "Auth", "Desc")
        val response = bookService.updateBookById(request)
        Assertions.assertEquals("NewTest", response.title)
    }

    @Test
    @Order(4)
    @Transactional
    fun updateBookById_negative() {
        val request = UpdateBookRequest(
            1,
            "Understanding Physics: Volume 2",
            "Isaac Asimov",
            "Light, Magnetism, and Electricity"
        )
        Assertions.assertThrows(IllegalArgumentException::class.java) { bookService.updateBookById(request) }
    }

    @Test
    @Order(5)
    @Transactional
    fun updateRatingById_positive() {
        val request = UpdateRatingRequest(1, 4.0)
        val response = bookService.updateRatingById(request)
        Assertions.assertEquals(4.0, response.rating)
    }

    @Test
    @Order(6)
    @Transactional
    fun updateRatingById_negative() {
        val request = UpdateRatingRequest(1, 3.33)
        Assertions.assertThrows(IllegalArgumentException::class.java) { bookService.updateRatingById(request) }
    }
}