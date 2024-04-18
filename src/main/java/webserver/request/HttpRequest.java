package webserver.request;

import utils.IOUtils;
import webserver.common.HttpHeaders;
import webserver.enums.Method;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

public final class HttpRequest {
    private static final String ZERO_STRING = "";
    private static final String ZERO_INTEGER = "0";

    private final RequestLine requestLine;
    private final HttpHeaders httpHeaders;
    private final RequestBody requestBody;

    private HttpRequest(RequestLine requestLine, HttpHeaders httpHeaders, RequestBody requestBody) {
        this.requestLine = requestLine;
        this.httpHeaders = httpHeaders;
        this.requestBody = requestBody;
    }

    public static HttpRequest of(InputStream in) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        RequestLine requestLine = new RequestLine(br);
        HttpHeaders httpHeaders = new HttpHeaders(br);
        RequestBody requestBody;

        if (!requestLine.method().isRequestBodyAcceptable()) {
            requestBody = new RequestBody();
        } else {
            String bodyData = IOUtils.readData(br, Integer.parseInt(httpHeaders.contentLength().orElse(ZERO_INTEGER)));
            requestBody = new RequestBody(RequestBodyParsingStrategyFactory.create(httpHeaders.contentType().orElse(ZERO_STRING), bodyData));
        }

        return new HttpRequest(requestLine, httpHeaders, requestBody);
    }

    public String accept() {
        return this.httpHeaders.accept().orElse(ZERO_STRING);
    }

    public String contentType() {
        return this.httpHeaders.contentType().orElse(ZERO_STRING);
    }

    public int contentLength() {
        return Integer.parseInt(this.httpHeaders.contentLength().orElse(ZERO_INTEGER));
    }

    public RequestLine requestLine() {
        return this.requestLine;
    }

    public HttpHeaders headers() {
        return this.httpHeaders;
    }

    public RequestBody body() {
        return this.requestBody;
    }

    public Method method() {
        return this.requestLine.method();
    }

    public String path() {
        return this.requestLine.uri().path();
    }

    public Map<String, Object> params() {
        return this.requestLine().uri().params();
    }

    public Map<String, Object> contents() {
        return this.requestBody.contents();
    }

    public String httpVersion() {
        return this.requestLine.httpVersion().raw();
    }

}
