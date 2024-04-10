package webserver.enums;

public enum StatusCode {
    OK(200, StatusCode.Series.SUCCESSFUL, "OK"),
    CREATED(201, StatusCode.Series.SUCCESSFUL, "Created"),
    MOVED_TEMPORARILY(302, StatusCode.Series.REDIRECTION, "Moved Temporarily"),
    NOT_FOUND(404, StatusCode.Series.CLIENT_ERROR, "Not Found"),
    ;

    private final int value;
    private final StatusCode.Series series;
    private final String reasonPhrase;

    StatusCode(int value, StatusCode.Series series, String reasonPhrase) {
        this.value = value;
        this.series = series;
        this.reasonPhrase = reasonPhrase;
    }

    public int value() {
        return this.value;
    }

    public StatusCode.Series series() {
        return this.series;
    }

    public String reasonPhrase() {
        return this.reasonPhrase;
    }

    public enum Series {
        INFORMATIONAL(1),
        SUCCESSFUL(2),
        REDIRECTION(3),
        CLIENT_ERROR(4),
        SERVER_ERROR(5);

        private final int value;

        Series(int value) {
            this.value = value;
        }

        public int value() {
            return value;
        }
    }
}
