package webserver.response;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("ResponseBody 관련 테스트")
class ResponseBodyTest {
    @Test
    void ResponseBody를_생성한다() {
        String info = "";
        ResponseBody responseBody = new ResponseBody(info.getBytes());
        assertThat(responseBody.contents()).isEqualTo(info.getBytes());
    }
}
