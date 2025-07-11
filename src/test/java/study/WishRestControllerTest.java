package study;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

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
                "TRACE");
    }

    @TestConfiguration
    static class TestConfig {
        @Bean
        @Primary
        public LoginMemberArgumentResolver testLoginMemberArgumentResolver() {
            return new LoginMemberArgumentResolver() {
                @Override
                public boolean supportsParameter(MethodParameter parameter) {
                    return parameter.hasParameterAnnotation(LoginMember.class) 
                           && parameter.getParameterType() == Member.class;
                }

                @Override
                public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                              NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
                    // 테스트용 고정 Member 반환
                    return new Member(1L, "testUser", 25, "test@example.com");
                }
            };
        }
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
        assertThat(actual.getStatus()).isEqualTo(HttpStatus.CREATED.value());
    }

}
