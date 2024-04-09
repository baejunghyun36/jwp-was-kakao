package webserver;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public final class HttpRequest {
    private final RequestLine requestLine;
    private final HttpHeaders httpHeaders;
    private final String requestBody;

    public HttpRequest(InputStream in) {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        this.requestLine = new RequestLine(br);
        this.httpHeaders = new HttpHeaders(br);
        this.requestBody = "";
    }

    public RequestLine requestLine() {
        return this.requestLine;
    }

    public HttpHeaders headers() {
        return this.httpHeaders;
    }

    public String requestBody() {
        return this.requestBody;
    }
}
