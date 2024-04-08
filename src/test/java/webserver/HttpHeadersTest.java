package webserver;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;

public class HttpHeadersTest {
    @Test
    void HttpHeaders_객체의_필수_인자를_받아서_생성한다() {
        String info = "Host: localhost:8080\r\nConnection: keep-alive\r\nAccept: */*\r\n";
        InputStream bis = new ByteArrayInputStream(info.getBytes());
        HttpHeaders headers = new HttpHeaders(bis);
        assertThat(headers.get().size()).isEqualTo(3);
    }

    @Test
    void HttpHeaders의_빈_문자열은_빈_Map을_갖는다() {
        InputStream bis = new ByteArrayInputStream("".getBytes());
        HttpHeaders headers = new HttpHeaders(bis);
        assertThat(headers.get().size()).isEqualTo(0);
    }

    @Test
    void HttpHeaders의_구분_개행은_빈_Map을_갖는다() {
        InputStream bis = new ByteArrayInputStream("\r\n".getBytes());
        HttpHeaders headers = new HttpHeaders(bis);
        assertThat(headers.get().size()).isEqualTo(0);
    }
}
