package webserver.controller;

import webserver.common.HttpCookie;
import webserver.enums.Method;
import webserver.request.HttpRequest;
import webserver.response.HttpResponse;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public final class RequestMapper {
    private static final String DEFAULT_FILE_SERVE_PATH = "thisisdefaultroutepath";
    private static final Map<Entry, Controller> map = new HashMap<>();
    private static final String JSESSIONID = "JSESSIONID";
    private static final String COOKIE_PATH_POSTFIX = "; Path=/";

    static {
        register(Method.GET, DEFAULT_FILE_SERVE_PATH, new StaticServingController());
    }

    public static void register(Method method, String path, Controller controller) {
        Entry entry = new Entry(method, path);
        if (map.containsKey(entry)) {
            throw new UnsupportedOperationException("이미 존재하는 URI의 Controller입니다.");
        }
        map.putIfAbsent(entry, controller);
    }

    public static void doService(HttpRequest req, HttpResponse res) throws IOException, URISyntaxException {

        setCookie(req, res);

        if (isRedirection(req, res)) {
            return;
        }

        Controller controller = getController(req);
        controller.doService(req, res);
    }

    private static void setCookie(HttpRequest req, HttpResponse res) {
        if (req.headers().cookie() == null) {
            HttpCookie cookie = new HttpCookie("", JSESSIONID, UUID.randomUUID() + COOKIE_PATH_POSTFIX);
            res.setCookie(cookie);
        }
    }

    private static boolean isRedirection(HttpRequest req, HttpResponse res) {
        if (req.path().equals(HttpResponse.LOGIN_PAGE) && req.headers().cookie().isLogined()) {
            res.setMovedTemporarily(HttpResponse.DEFAULT_ENTRY);
            return true;
        }

        if (req.path().equals(HttpResponse.USER_LIST_PAGE_PATH) && !req.headers().cookie().isLogined()) {
            res.setMovedTemporarily(HttpResponse.LOGIN_PAGE);
            return true;
        }

        return false;
    }

    public static Controller getController(HttpRequest req) {
        Entry entry = new Entry(req.method(), req.path());
        if (map.containsKey(entry)) {
            return map.get(entry);
        }
        return map.get(new Entry(Method.GET, DEFAULT_FILE_SERVE_PATH));
    }

    private static final class Entry {
        private final Method method;
        private final String path;

        Entry(Method method, String path) {
            this.method = method;
            this.path = path;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Entry entry = (Entry) o;
            return method == entry.method && Objects.equals(path, entry.path);
        }

        @Override
        public int hashCode() {
            return Objects.hash(method, path);
        }
    }
}
