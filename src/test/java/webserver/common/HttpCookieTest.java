package webserver.common;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class HttpCookieTest {
    @Test
    void Request_쿠키_정보가_존재하지_않을때_세션아이디를_추가한_HttpCookie_객체를_생성한다() {
        HttpCookie httpCookie = new HttpCookie("", "JSESSIONID", UUID.randomUUID().toString());
        assertThat(httpCookie.all().size()).isOne();
    }

    @Test
    void Request_세션아이디_기반으로_HttpCookie_객체를_생성한다() {
        String cookieInfo = "JSESSIONID=1f6c30ce-1053-4297-8cb1-71b2607de5ab";
        HttpCookie httpCookie = new HttpCookie(cookieInfo);
        assertThat(httpCookie.all().size()).isOne();
        assertThat(httpCookie.getSessionId()).isEqualTo("1f6c30ce-1053-4297-8cb1-71b2607de5ab");
        assertThat(httpCookie.isLogined()).isEqualTo(false);
    }

    @Test
    void Request_세션아이디_로그인_여부를_기반으로_HttpCookie_객체를_생성한다() {
        String cookieInfo = "LOGINED=TRUE; JSESSIONID=1f6c30ce-1053-4297-8cb1-71b2607de5ab";
        HttpCookie httpCookie = new HttpCookie(cookieInfo);
        assertThat(httpCookie.all().size()).isEqualTo(2);
        assertThat(httpCookie.getSessionId()).isEqualTo("1f6c30ce-1053-4297-8cb1-71b2607de5ab");
        assertThat(httpCookie.isLogined()).isEqualTo(true);
    }
}
