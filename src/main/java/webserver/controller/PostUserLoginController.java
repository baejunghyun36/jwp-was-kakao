package webserver.controller;

import db.DataBase;
import model.User;
import webserver.common.Session;
import webserver.common.SessionManager;
import webserver.request.HttpRequest;
import webserver.response.HttpResponse;


public class PostUserLoginController implements Controller {

    private static final String USERID = "userId";
    private static final String PASSWORD = "password";

    @Override
    public void doService(HttpRequest req, HttpResponse res) {
        checkUserInfo(req, res);
        res.setMovedTemporarily(HttpResponse.DEFAULT_ENTRY);
    }

    private void checkUserInfo(HttpRequest req, HttpResponse res) {
        String userId = (String) req.body().get(USERID);
        String password = (String) req.body().get(PASSWORD);
        User user = DataBase.findUserById(userId);
        if (user != null && user.getPassword().equals(password)) {
            addSession(user, res);
            return;
        }
        throw new IllegalArgumentException("아이디/패스워드가 일치하지 않습니다.");
    }

    private void addSession(User user, HttpResponse res) {
        SessionManager sessionManager = SessionManager.getInstance();
        String sessionId = res.cookie().getSessionId();
        Session session = new Session(sessionId);
        session.setAttribute("user", user);
        sessionManager.add(session);
        res.cookie().setLogin();
    }
}
