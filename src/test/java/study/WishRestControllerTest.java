package study;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import study.domain.product.model.Product;
import study.domain.product.service.ProductService;
import study.domain.product.service.ProductServiceImpl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(WishController.class)
public class WishRestControllerTest {

    static {
        System.setProperty(
                "logging.level.org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping",
                "TRACE");          // DEBUG보다 TRACE가 경로까지 전부 찍어줌
    }

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private WishService wishService;
    @MockitoBean
    private MemberService memberService;

    @Test
    void test2() throws Exception {
        //given
        var request = new CreateWishRequest(1L, 1);
        var content = objectMapper.writeValueAsString(request);
        given(wishService.create(any(), any(), any())).willReturn(new CreateWishResponse(1L, 1L, 1L));
        //when
        MockHttpServletRequestBuilder requestBuilder = post("/api/wishes")
                .contentType(MediaType.APPLICATION_JSON)
            .content(content);

        MockHttpServletResponse actual = mockMvc.perform(requestBuilder)
            .andDo(print())
            .andReturn()
            .getResponse();

        //then
        assertThat(actual.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(actual.getHeader(HttpHeaders.LOCATION)).isNotBlank();
    }

    @Test
    void test3() throws Exception {
        //given
        var request = new CreateWishRequest(1L, 1);
        var content = objectMapper.writeValueAsString(request);

        //when
        MockHttpServletRequestBuilder requestBuilder = post("/api/wishes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(content);

        MockHttpServletResponse actual = mockMvc.perform(requestBuilder)
            .andDo(print())
            .andReturn()
            .getResponse();

        //then
        assertThat(actual.getStatus()).isEqualTo(HttpStatus.UNAUTHORIZED.value());
    }

}
