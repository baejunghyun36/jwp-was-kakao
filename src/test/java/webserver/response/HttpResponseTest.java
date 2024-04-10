package webserver.response;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import webserver.common.HttpHeaders;
import webserver.enums.StatusCode;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("HttpResponse 관련 테스트")
class HttpResponseTest {
    @Test
    void HttpResponse_객체가_생성된다() {
        StatusLine statusLine = new StatusLine("HTTP/1.1", StatusCode.OK);
        String headers = "Host: localhost:8080\r\nConnection: keep-alive\r\nAccept: */*\r\n";
        HttpHeaders httpHeaders = new HttpHeaders(headers);
        HttpResponse response = new HttpResponse();
        response.setStatusLine("HTTP/1.1", StatusCode.OK);
        response.setHeaders(httpHeaders);
        response.setResponseBody("".getBytes());
        assertThat(response).isNotNull();
        assertThat(response.statusLine()).isEqualTo(statusLine);
        assertThat(response.headers()).isEqualTo(httpHeaders);
        assertThat(response.responseBody()).isEqualTo(new ResponseBody("".getBytes()));
    }
}
