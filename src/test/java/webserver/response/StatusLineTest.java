package webserver.response;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import webserver.common.HttpVersion;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("StatusLine 관련 테스트")
public class StatusLineTest {
    @Test
    void StatusLine_객체가_생성된다() {
        String version = "HTTP/1.1";
        HttpVersion httpVersion = new HttpVersion(version);
        HttpStatus httpStatus = HttpStatus.OK;
        StatusLine statusLine = new StatusLine(httpVersion, httpStatus.value(), httpStatus.getReasonPhrase());
        assertThat(statusLine.httpVersion()).isEqualTo(version);
        assertThat(statusLine.statusCode()).isEqualTo(200);
        assertThat(statusLine.reasonPhrase()).isEqualTo("OK");
    }
}
