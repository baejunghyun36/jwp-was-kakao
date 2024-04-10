package webserver.response;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import webserver.common.HttpHeaders;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("HttpResponse 관련 테스트")
class HttpResponseTest {
    @Test
    void HttpResponse_객체가_생성된다() {
        StatusLine statusLine = new StatusLine("HTTP/1.1", 200, "OK");
        String headers = "Host: localhost:8080\r\nConnection: keep-alive\r\nAccept: */*\r\n";
        HttpHeaders httpHeaders = new HttpHeaders(headers);
        HttpResponse response = new HttpResponse(statusLine, httpHeaders, "");
        assertThat(response).isNotNull();
        assertThat(response.statusLine()).isEqualTo(new StatusLine("HTTP/1.1", 200, "OK"));
        assertThat(response.headers()).isEqualTo(httpHeaders);
        assertThat(response.responseBody()).isEqualTo("");
    }
}
