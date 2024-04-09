package webserver.request;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("RequestBody 관련 테스트")
class RequestBodyTest {
    @Test
    void RequestBody를_생성한다() {
        String info = "userId=cu&password=password&name=%EC%9D%B4%EB%8F%99%EA%B7%9C&email=brainbackdoor%40gmail.com\r\n";
        FormUrlEncodedParsingStrategy strategy = new FormUrlEncodedParsingStrategy(info);
        RequestBody body = new RequestBody(strategy);
        assertThat(body).isNotNull();
    }
}