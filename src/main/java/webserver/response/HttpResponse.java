package webserver.response;

import webserver.common.HttpCookie;
import webserver.common.HttpHeaders;
import webserver.common.HttpVersion;
import webserver.enums.MediaType;
import webserver.enums.StatusCode;

public final class HttpResponse {
    public static final String DEFAULT_ENTRY = "/index.html";
    public static final String LOGIN_PAGE = "/user/login.html";
    public static final String LOGIN_FAIL = "/user/login_failed.html";
    public static final String USER_LIST_PAGE_PATH = "/user/list.html";
    private static final String EMPTY = "";
    private static final String NOT_FOUND_MESSAGE = "NOT FOUND";
    private StatusLine statusLine;
    private HttpHeaders headers;
    private ResponseBody responseBody;
    private HttpCookie cookie;

    public HttpResponse() {
        this.headers = new HttpHeaders(EMPTY);
        this.responseBody = new ResponseBody(EMPTY.getBytes());
        this.cookie = new HttpCookie();
    }

    public void setStatusOK(byte[] contents, String contentType) {
        setStatusLine(HttpVersion.HTTP_1_1_RAW, StatusCode.OK);
        setHeader(HttpHeaders.CONTENT_TYPE, contentType);
        setHeader(HttpHeaders.CONTENT_LENGTH, String.valueOf(contents.length));
        setResponseBody(contents);
    }

    public void setNotFound() {
        setStatusLine(HttpVersion.HTTP_1_1_RAW, StatusCode.NOT_FOUND);
        setHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN.value());
        setHeader(HttpHeaders.CONTENT_LENGTH, String.valueOf(NOT_FOUND_MESSAGE.getBytes().length));
        setResponseBody(NOT_FOUND_MESSAGE.getBytes());
    }

    public void setMovedTemporarily(String location) {
        setStatusLine(HttpVersion.HTTP_1_1_RAW, StatusCode.MOVED_TEMPORARILY);
        setHeader(HttpHeaders.LOCATION, location);
    }

    public void setConflict(byte[] contents, String contentType) {
        setStatusLine(HttpVersion.HTTP_1_1_RAW, StatusCode.CONFLICT);
        setHeader(HttpHeaders.CONTENT_TYPE, contentType);
        setHeader(HttpHeaders.CONTENT_LENGTH, String.valueOf(contents.length));
        setResponseBody(contents);
    }

    private void setStatusLine(String httpVersion, StatusCode statusCode) {
        this.statusLine = new StatusLine(httpVersion, statusCode);
    }

    private void setHeader(String key, String value) {
        this.headers.put(key, value);
    }

    private void setResponseBody(byte[] contents) {
        this.responseBody = new ResponseBody(contents);
    }

    public StatusLine statusLine() {
        return this.statusLine;
    }

    public HttpHeaders headers() {
        return this.headers;
    }

    public ResponseBody responseBody() {
        return this.responseBody;
    }

    public byte[] contents() {
        return responseBody().contents();
    }

    public void setCookie(HttpCookie cookie) {
        this.cookie = cookie;
    }

    public HttpCookie cookie() {
        return this.cookie;
    }
}