package webserver.response;

import webserver.common.HttpHeaders;
import webserver.common.HttpVersion;
import webserver.enums.MediaType;
import webserver.enums.StatusCode;
import webserver.request.HttpRequest;

public final class HttpResponse {
    public static final String DEFAULT_ENTRY = "index.html";
    private static final String EMPTY = "";
    private static final String NOT_FOUND_MESSAGE = "NOT FOUND";

    private final HttpRequest httpRequest;
    private StatusLine statusLine;
    private HttpHeaders headers;
    private ResponseBody responseBody;

    public HttpResponse(HttpRequest httpRequest) {
        this.httpRequest = httpRequest;
        this.headers = new HttpHeaders(EMPTY);
    }

    public void setStatusOK(byte[] contents, String contentType) {
        setStatusLine(HttpVersion.HTTP_1_1, StatusCode.OK);
        setHeader(HttpHeaders.CONTENT_TYPE, contentType);
        setHeader(HttpHeaders.CONTENT_LENGTH, String.valueOf(contents.length));
        setResponseBody(contents);
    }

    public void setNotFound() {
        setStatusLine(HttpVersion.HTTP_1_1, StatusCode.NOT_FOUND);
        setHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN.value());
        setHeader(HttpHeaders.CONTENT_LENGTH, String.valueOf(NOT_FOUND_MESSAGE.getBytes().length));
        setResponseBody(NOT_FOUND_MESSAGE.getBytes());
    }

    public void setMovedTemporarily(String location) {
        setStatusLine(HttpVersion.HTTP_1_1, StatusCode.MOVED_TEMPORARILY);
        setHeader(HttpHeaders.LOCATION, location);
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
}