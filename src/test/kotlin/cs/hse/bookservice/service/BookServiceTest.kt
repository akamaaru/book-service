package cs.hse.bookservice.service

import cs.hse.bookservice.dto.requests.BookRequest
import cs.hse.bookservice.dto.requests.UpdateBookRequest
import cs.hse.bookservice.dto.requests.UpdateRatingRequest
import cs.hse.bookservice.dto.responses.BookResponse
import cs.hse.bookservice.entity.Book
import cs.hse.bookservice.repository.BookRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.util.*
import kotlin.IllegalArgumentException

class BookServiceTest {

    @InjectMocks
    lateinit var bookService: BookService

    @Mock
    lateinit var bookRepository: BookRepository

    @BeforeEach
    fun setup() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun getAllBooks() {
        val book: Book = Book(1, "Test", "Auth", "Description")
        Mockito.`when`(bookRepository.findAll()).thenReturn(mutableListOf(book))

        Assertions.assertEquals(1, bookService.getAllBooks().size)

        Mockito.verify(bookRepository, Mockito.times(1)).findAll()

    }

    @Test
    fun saveNewBook() {
        val bookInit: BookRequest = BookRequest("Test", "Auth", "Desc")
        val book: Book = Book(1, "Test", "Auth", "Desc")
        Mockito.`when`(bookRepository.findAll()).thenReturn(mutableListOf(book))

        Assertions.assertThrows(IllegalArgumentException::class.java) { bookService.saveNewBook(bookInit) }
        Mockito.verify(bookRepository, Mockito.times(0)).save(book)

    }

    @Test
    fun updateBookById_positive() {
        val book = Book(1, "Test", "Auth", "Description")
        Mockito.`when`(bookRepository.findById(1)).thenReturn(Optional.of(book))
        Mockito.`when`(bookRepository.save(book)).thenReturn(book)

        val request = UpdateBookRequest(1, "NewTest", "Auth", "Desc")
        val response = bookService.updateBookById(request)
        Assertions.assertEquals("NewTest", response.title)
    }

    @Test
    fun updateBookById_negative() {
        val book = Book(1, "Test", "Auth", "Description")
        Mockito.`when`(bookRepository.findById(1)).thenReturn(Optional.of(book))
        Mockito.`when`(bookRepository.save(book)).thenReturn(book)

        val request = UpdateBookRequest(1, "Test", "Auth", "Description")
        Assertions.assertThrows(IllegalArgumentException::class.java) { bookService.updateBookById(request) }
    }

    @Test
    fun updateRatingById_positive() {
        val book = Book(1, "Test", "Auth", "Description", 5.0)
        Mockito.`when`(bookRepository.findById(1)).thenReturn(Optional.of(book))
        Mockito.`when`(bookRepository.save(book)).thenReturn(book)

        val request = UpdateRatingRequest(1, 4.0)
        val response = bookService.updateRatingById(request)
        Assertions.assertEquals(4.0, response.rating)
    }

    @Test
    fun updateRatingById_negative() {
        val book = Book(1, "Test", "Auth", "Description", 5.0)
        Mockito.`when`(bookRepository.findById(1)).thenReturn(Optional.of(book))
        Mockito.`when`(bookRepository.save(book)).thenReturn(book)

        val request = UpdateRatingRequest(1, 5.0)
        Assertions.assertThrows(IllegalArgumentException::class.java) { bookService.updateRatingById(request) }
    }
}