package webserver.common;

import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SessionManagerTest {
    SessionManager sessionManager;
    Session session;
    String sessionId;
    User user;

    @BeforeEach
    void setUp() {
        sessionManager = SessionManager.getInstance();
        sessionId = "1f6c30ce-1053-4297-8cb1-71b2607de5ab";
        user = new User("id", "pw", "freddie", "freddie@naver.com");
        session = new Session(sessionId);
        session.setAttribute("user", user);
    }

    @Test
    void 세션_아이디를_통해_세션의_존재여부를_확인할_수_있다() {
        sessionManager.add(session);
        assertThat(sessionManager.exists(sessionId)).isNotNull();
    }

    @Test
    void 세션_아이디를_통해_유저_객체_정보를_가지고_올_수_있다() {
        sessionManager.add(session);
        Session session = sessionManager.getSession(sessionId);
        assertThat(session.getAttribute("user")).isEqualTo(user);
    }

    @Test
    void 로그아웃을_하면_세션을_삭제한다() {
        sessionManager.add(session);
        sessionManager.remove(sessionId);
        assertThat(sessionManager.getSession(sessionId)).isNull();
    }
}