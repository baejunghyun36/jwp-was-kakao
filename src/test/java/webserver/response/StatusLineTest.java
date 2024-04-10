package webserver.response;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("StatusLine 관련 테스트")
class StatusLineTest {
    @Test
    void StatusLine_객체가_생성된다() {
        String version = "HTTP/1.1";
        HttpStatus httpStatus = HttpStatus.OK;
        StatusLine statusLine = new StatusLine(version, httpStatus.value(), httpStatus.getReasonPhrase());
        assertThat(statusLine.httpVersion()).isEqualTo(version);
        assertThat(statusLine.statusCode()).isEqualTo(200);
        assertThat(statusLine.reasonPhrase()).isEqualTo("OK");
    }
}