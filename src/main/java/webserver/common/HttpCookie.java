package webserver.common;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public final class HttpCookie {
    private final Map<String, String> cookie = new HashMap<>();
    private static final String SESSION_KEY = "JSESSIONID";
    private static final String LOGINED_KEY = "LOGINED";
    private static final String COOKIE_ENTRY_DELIMITER = "; ";
    private static final String KEY_VALUE_DELIMITER = "=";
    private static final String LOGINED_VALUE = "TRUE";

    public HttpCookie() {
    }

    public HttpCookie(String cookie, String key, String value) {
        String[] entries = cookie.split(COOKIE_ENTRY_DELIMITER);
        addEntries(entries);
        this.cookie.putIfAbsent(key, value);
    }

    public HttpCookie(String cookie) {
        String[] entries = cookie.split(COOKIE_ENTRY_DELIMITER);
        addEntries(entries);
    }

    public boolean isLogined() {
        return this.cookie.get(LOGINED_KEY) != null;
    }

    private void addEntries(String[] entries) {
        Arrays.stream(entries)
                .forEach(e -> {
                    if (e.isBlank()) return;
                    String[] keyValue = e.split(KEY_VALUE_DELIMITER);
                    this.cookie.putIfAbsent(keyValue[0], keyValue[1]);
                });
    }

    public Map<String, String> all() {
        return this.cookie;
    }

    public void setLogin() {
        this.cookie.put(LOGINED_KEY, LOGINED_VALUE);
    }

    public String getSessionId() {
        return this.cookie.get(SESSION_KEY);
    }
}
