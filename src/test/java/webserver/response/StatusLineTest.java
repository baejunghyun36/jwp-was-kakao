package webserver.response;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import webserver.enums.StatusCode;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("StatusLine 관련 테스트")
class StatusLineTest {
    @Test
    void StatusLine_객체가_생성된다() {
        String version = "HTTP/1.1";
        StatusLine statusLine = new StatusLine(version, StatusCode.OK);
        assertThat(statusLine.httpVersion()).isEqualTo(version);
        assertThat(statusLine.statusCode()).isEqualTo(200);
        assertThat(statusLine.reasonPhrase()).isEqualTo("OK");
    }
}