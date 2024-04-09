package webserver;

import utils.IOUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;

public final class HttpRequest {
    private static final String CONTENT_TYPE = "Content-Type";
    private static final String CONTENT_LENGTH = "Content-Length";

    private final RequestLine requestLine;
    private final HttpHeaders httpHeaders;
    private final RequestBody requestBody;

    public HttpRequest(InputStream in) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        this.requestLine = new RequestLine(br);
        this.httpHeaders = new HttpHeaders(br);
        if (!isRequestBodyAcceptable()) {
            requestBody = new RequestBody();
            return;
        }
        this.requestBody = new RequestBody(ParsingStrategyFactory.create(
                getContentType(httpHeaders),
                IOUtils.readData(br, getContentLength(httpHeaders))));
    }

    private boolean isRequestBodyAcceptable() {
        Method method = requestLine.method();
        return method.isRequestBodyAcceptable();
    }

    private String getContentType(HttpHeaders httpHeaders) {
        return httpHeaders.get(CONTENT_TYPE).orElseThrow(NoSuchElementException::new);
    }

    private int getContentLength(HttpHeaders httpHeaders) {
        return Integer.parseInt(httpHeaders.get(CONTENT_LENGTH).orElseThrow(NoSuchElementException::new));
    }

    public RequestLine requestLine() {
        return this.requestLine;
    }

    public HttpHeaders headers() {
        return this.httpHeaders;
    }

    public RequestBody requestBody() {
        return this.requestBody;
    }
}
