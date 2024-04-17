package webserver.common;

import java.util.HashMap;
import java.util.Map;

public final class SessionManager {
    private static final Map<String, Session> SESSIONS = new HashMap<>();

    private static SessionManager instance = new SessionManager();

    private SessionManager() {
    }

    public static SessionManager getInstance() {
        return instance;
    }

    public void add(Session session) {
        SESSIONS.put(session.getId(), session);
    }

    public Session getSession(String id) {
        return SESSIONS.get(id);
    }

    public void remove(String id) {
        SESSIONS.remove(id);
    }

    public boolean exists(String id) {
        return SESSIONS.containsKey(id);
    }
}