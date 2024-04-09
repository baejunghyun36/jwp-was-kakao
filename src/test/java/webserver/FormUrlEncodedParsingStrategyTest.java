package webserver;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


@DisplayName("FormUrlEncodedParsingStrategy 관련 테스트")
class FormUrlEncodedParsingStrategyTest {
    @Test
    void 파싱을_수행해서_key_value를_검증한다() {
        String body = "userId=cu&password=password&name=%EC%9D%B4%EB%8F%99%EA%B7%9C&email=brainbackdoor%40gmail.com";
        RequestBodyParsingStrategy requestBodyParsingStrategy = new FormUrlEncodedParsingStrategy(body);
        requestBodyParsingStrategy.perform();
        assertThat(requestBodyParsingStrategy.requestBody().size()).isEqualTo(4);
        assertThat(requestBodyParsingStrategy.requestBody().get("userId")).isEqualTo("cu");
        assertThat(requestBodyParsingStrategy.requestBody().get("name")).isEqualTo("이동규");
    }
}