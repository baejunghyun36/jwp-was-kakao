package webserver;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("HttpHeaders 관련 테스트")
class HttpHeadersTest {
    @Test
    void HttpHeaders_객체의_필수_인자를_받아서_생성한다() {
        String info = "Host: localhost:8080\r\nConnection: keep-alive\r\nAccept: */*\r\n";
        BufferedReader br = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(info.getBytes())));
        HttpHeaders headers = new HttpHeaders(br);
        assertThat(headers.get("Connection").get()).isEqualTo("keep-alive");
    }

    @Test
    void HttpHeaders에_존재하지_않는_키는_빈_Optional을_반환() {
        String info = "";
        BufferedReader br = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(info.getBytes())));
        HttpHeaders headers = new HttpHeaders(br);
        assertThat(headers.get("Content-Type").isEmpty()).isTrue();
    }
}
