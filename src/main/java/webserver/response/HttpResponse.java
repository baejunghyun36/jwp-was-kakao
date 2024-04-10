package webserver.response;

import webserver.common.HttpHeaders;
import webserver.enums.StatusCode;

public final class HttpResponse {
    private StatusLine statusLine;
    private HttpHeaders headers;
    private ResponseBody responseBody;

    public void setStatusLine(String httpVersion, StatusCode statusCode) {
        this.statusLine = new StatusLine(httpVersion, statusCode);
    }

    public void setHeaders(HttpHeaders headers) {
        this.headers = headers;
    }

    public void setResponseBody(byte[] contents) {
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
