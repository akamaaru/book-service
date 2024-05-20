package cs.hse.bookservice.service

import cs.hse.bookservice.dto.requests.BookRequest
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
import java.lang.IllegalArgumentException

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
        val bookInit: BookRequest = BookRequest( "Test", "Auth", "Desc")
        val book: Book = Book( 1,"Test", "Auth", "Desc")
        Mockito.`when`(bookRepository.findAll()).thenReturn(mutableListOf(book))

        Assertions.assertThrows(IllegalArgumentException::class.java) { bookService.saveNewBook(bookInit)}
        Mockito.verify(bookRepository, Mockito.times(0)).save(book)

    }
}