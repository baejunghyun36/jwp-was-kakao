package webserver.request;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import webserver.common.HttpHeaders;

import java.io.*;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("HttpRequest 관련 테스트")
class HttpRequestTest {
    @Test
    void request_resttemplate() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void HttpRequest_객체가_생성된다() throws IOException {
        String request = "GET /index.html HTTP/1.1\r\nHost: localhost:8080\r\nConnection: keep-alive\r\nAccept: */*\r\n\r\nSampleContents";
        InputStream in1 = new ByteArrayInputStream(request.getBytes());
        InputStream in2 = new ByteArrayInputStream(request.getBytes());
        HttpRequest httpRequest = new HttpRequest(in1);
        BufferedReader br = new BufferedReader(new InputStreamReader(in2));
        assertThat(httpRequest.requestLine()).isEqualTo(new RequestLine(br));
        assertThat(httpRequest.headers()).isEqualTo(new HttpHeaders(br));
//        assertThat(httpRequest.requestBody()).isEqualTo("");
    }
}
