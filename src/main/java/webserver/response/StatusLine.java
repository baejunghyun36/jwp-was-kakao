package webserver.response;

import webserver.common.HttpVersion;

public final class StatusLine {
    private final HttpVersion httpVersion;
    private final int statusCode;
    private final String reasonPhrase;

    public StatusLine(HttpVersion httpVersion, int statusCode, String reasonPhrase) {
        this.httpVersion = httpVersion;
        this.statusCode = statusCode;
        this.reasonPhrase = reasonPhrase;
    }

    public String httpVersion() {
        return this.httpVersion.raw();
    }

    public int statusCode() {
        return this.statusCode;
    }

    public String reasonPhrase() {
        return this.reasonPhrase;
    }
}
