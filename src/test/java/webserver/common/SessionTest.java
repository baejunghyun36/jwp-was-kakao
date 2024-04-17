package webserver.common;

import model.User;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SessionTest {
    @Test
    void session_객체를_생성한다() {
        String sessionId = "1f6c30ce-1053-4297-8cb1-71b2607de5ab";
        User user = new User("id", "pw", "freddie", "freddie@naver.com");
        Session session = new Session(sessionId);
        session.setAttribute("user", user);
        assertThat(session.getAttribute("user")).isEqualTo(user);
    }
}