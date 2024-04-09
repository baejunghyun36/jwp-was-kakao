package webserver;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import static org.assertj.core.api.Assertions.assertThat;

public class HttpRequestTest {
    @Test
    void request_resttemplate() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
//* [X] RequestLine과 HttpHeaders와 HttpContents를 가진 HttpRequest를 만든다.

    @Test
    void HttpRequest_객체가_생성된다() {
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
