package webserver;

import java.util.Map;

public class Uri {

    private String method;
    private String path;
    private Map<String, String> params;

    public Uri(){

    }

    public Uri(String method, String path, Map<String, String> params) {
        this.method = method;
        this.path = path;
        this.params = params;
    }

    public String method() {
        return this.method;
    }

    public String path() {
        return this.path;
    }



    public Map<String,String> params() {
        return this.params;
    }
}
