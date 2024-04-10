package webserver.common;

public enum MediaType {
    APPLICATION_FORM_URLENCODED("application/x-www-form-urlencoded"),
    TEXT_HTML("text/html"),
    TEXT_CSS("text/css"),
    ;
    private final String value;

    MediaType(String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }
}
