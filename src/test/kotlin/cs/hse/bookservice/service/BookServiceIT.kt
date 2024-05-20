package cs.hse.bookservice.service

import cs.hse.bookservice.dto.requests.BookRequest
import cs.hse.bookservice.entity.Book
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.lang.IllegalArgumentException


@SpringBootTest

class BookServiceIT {

    @Autowired
    lateinit var bookService: BookService


    @Test
    @Order(1)
    fun getAllBooks() {
        Assertions.assertEquals(6, bookService.getAllBooks().size )
    }

    @Test
    @Order(2)
    fun saveNewBook() {
        val bookInit: BookRequest = BookRequest( "Test", "Auth", "Desc")
        val bookInit2: BookRequest = BookRequest( "Test", "Auth", "Desc")

        Assertions.assertThrows(IllegalArgumentException::class.java) {
            bookService.saveNewBook(bookInit)
            bookService.saveNewBook(bookInit2)
        }


    }



}