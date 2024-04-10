package webserver.response;

import webserver.common.HttpHeaders;

public final class HttpResponse {
    private final StatusLine statusLine;
    private final HttpHeaders headers;
    private final String responseBody;

    public HttpResponse(StatusLine statusLine, HttpHeaders headers, String responseBody) {
        this.statusLine = statusLine;
        this.headers = headers;
        this.responseBody = responseBody;
    }

    public StatusLine statusLine() {
        return this.statusLine;
    }

    public HttpHeaders headers() {
        return this.headers;
    }

    public String responseBody() {
        return this.responseBody;
    }
}
