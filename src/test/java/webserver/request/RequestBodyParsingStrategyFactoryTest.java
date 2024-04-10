package webserver.request;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


@DisplayName("RequestBodyParsingStrategyFactory 관련 테스트")
class RequestBodyParsingStrategyFactoryTest {
    @Test
    void FormUrlEncodedParsingStrategy를_생성하여_이용할_수_있다() {
        String body = "userId=cu&password=password&name=%EC%9D%B4%EB%8F%99%EA%B7%9C&email=brainbackdoor%40gmail.com";
        RequestBodyParsingStrategy strategy = RequestBodyParsingStrategyFactory.create(MediaType.APPLICATION_FORM_URLENCODED_VALUE, body);
        strategy.perform();
        assertThat(strategy.requestBody().size()).isEqualTo(4);
        assertThat(strategy.requestBody().get("userId")).isEqualTo("cu");
        assertThat(strategy.requestBody().get("name")).isEqualTo("이동규");
    }

    @Test
    void 올바르지_않은_Content_Type을_이용하여_파싱_전략을_생성할_수_없다() {
        assertThatThrownBy(() -> RequestBodyParsingStrategyFactory.create("asdf", ""))
                .isInstanceOf(IllegalArgumentException.class);
    }
}