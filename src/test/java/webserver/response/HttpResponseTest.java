package webserver.response;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import webserver.common.HttpHeaders;
import webserver.enums.MediaType;
import webserver.enums.StatusCode;
import webserver.request.HttpRequest;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("HttpResponse 관련 테스트")
class HttpResponseTest {
    @Test
    void HttpResponse_객체가_생성된다() throws IOException {
        StatusLine statusLine = new StatusLine("HTTP/1.1", StatusCode.OK);
        HttpHeaders httpHeaders = new HttpHeaders("Content-Type: text/html\r\nContent-Length: 0");
        HttpResponse response = new HttpResponse(new HttpRequest());
        response.setStatusOK("".getBytes(), MediaType.TEXT_HTML.value());
        assertThat(response).isNotNull();
        assertThat(response.statusLine()).isEqualTo(statusLine);
        assertThat(response.headers()).isEqualTo(httpHeaders);
        assertThat(response.responseBody()).isEqualTo(new ResponseBody("".getBytes()));
    }
}
