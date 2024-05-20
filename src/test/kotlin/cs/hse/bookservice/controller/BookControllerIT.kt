package cs.hse.bookservice.controller

import cs.hse.bookservice.dto.responses.BookResponse
import org.hamcrest.Matchers
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@SpringBootTest
@AutoConfigureMockMvc
class BookControllerIT {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    fun get() {
        mockMvc
            .perform(MockMvcRequestBuilders.get("/api/books/get"))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers
                .jsonPath("$", Matchers.hasSize<List<BookResponse>>(6)))

    }

    @Test
    fun getById() {
        mockMvc
            .perform(MockMvcRequestBuilders.get("/api/books/get/1"))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers
                .jsonPath("$.id").value(1))

    }

}