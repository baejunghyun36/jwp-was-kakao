package webserver.controller;

import utils.FileIoUtils;
import webserver.enums.Method;
import webserver.request.HttpRequest;
import webserver.response.HttpResponse;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static webserver.enums.MediaType.TEXT_CSS;
import static webserver.enums.MediaType.TEXT_HTML;

public final class RequestMapper {
    private static final Map<Entry, Controller> map = new HashMap<>();
    private static final String TEMPLATE_PATH = "./templates";
    private static final String STATIC_PATH = "./static";

    public static void register(String method, String path, Controller controller) {
        Entry entry = new Entry(Method.of(method), path);
        if (map.containsKey(entry)) {
            throw new UnsupportedOperationException("이미 존재하는 URI의 Controller입니다.");
        }
        map.putIfAbsent(entry, controller);
    }

    public static void doService(HttpRequest req, HttpResponse res) throws IOException, URISyntaxException {
        Entry entry = new Entry(req.method(), req.path());
        if (map.containsKey(entry)) {
            map.get(entry).doService(req, res);
            return;
        }
        staticService(req, res);
    }

    private static void staticService(HttpRequest req, HttpResponse res) {
        try {
            byte[] body = getBody(req);
            String contentType = getContentType(req);
//            200 SetResponse
        } catch (Exception ex) {
//            404 SetResponse
        }
    }

    private static byte[] getBody(HttpRequest httpRequest) throws IOException, URISyntaxException {
        try {
            return getTemplatesBody(httpRequest);
        } catch (NullPointerException e) {
            return getStaticBody(httpRequest);
        } catch (Exception e) {
            throw new IllegalArgumentException("찾을 수 없는 자원입니다.");
        }
    }

    private static byte[] getTemplatesBody(HttpRequest httpRequest) throws IOException, URISyntaxException {
        return FileIoUtils.loadFileFromClasspath(TEMPLATE_PATH + httpRequest.path());
    }

    private static byte[] getStaticBody(HttpRequest httpRequest) throws IOException, URISyntaxException {
        return FileIoUtils.loadFileFromClasspath(STATIC_PATH + httpRequest.path());
    }

    private static String getContentType(HttpRequest httpRequest) {
        String accept = httpRequest.accept();
        String contentType = httpRequest.contentType();
        if (accept.contains(TEXT_CSS.value())) {
            return TEXT_CSS.value();
        }
        if (!contentType.isBlank()) {
            return contentType;
        }
        return TEXT_HTML.value();
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
