package webserver.response;

import webserver.common.HttpVersion;

import java.util.Objects;

public final class StatusLine {
    private final HttpVersion httpVersion;
    private final int statusCode;
    private final String reasonPhrase;

    public StatusLine(String httpVersion, int statusCode, String reasonPhrase) {
        this.httpVersion = new HttpVersion(httpVersion);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StatusLine that = (StatusLine) o;
        return statusCode == that.statusCode && Objects.equals(httpVersion, that.httpVersion) && Objects.equals(reasonPhrase, that.reasonPhrase);
    }

    @Override
    public int hashCode() {
        return Objects.hash(httpVersion, statusCode, reasonPhrase);
    }
}