package ru.otus.java.basic.hw23;

import java.util.HashMap;
import java.util.Map;

public class HttpRequest {
    private HttpMethod method;
    private String uri;
    private Map<String, String> params;

    public String getUri() {
        return uri;
    }

    public String getParameter(String name) {
        return params.get(name);
    }

    public boolean containsParameter(String name) {
        return params.containsKey(name);
    }

    public String getRoutingKey() {
        return method + " " + uri;
    }

    public HttpRequest(String rawRequest) {
        params = new HashMap<>();
        parse(rawRequest);
    }

    private void parse(String rawRequest) {
        int left = rawRequest.indexOf(" ");
        int right = rawRequest.indexOf(" ", left + 1);
        method = HttpMethod.valueOf(rawRequest.substring(0, left));
        uri = rawRequest.substring(left + 1, right);
        if (uri.contains("?")) {
            String[] tokens = uri.split("[?]");
            uri = tokens[0];
            String[] keysAndValues = tokens[1].split("[&]");
            for (String o : keysAndValues) {
                String[] keyValue = o.split("[=]");
                params.put(keyValue[0], keyValue[1]);
            }
        }
    }

    public void info() {
        System.out.println("METHOD: " + method);
        System.out.println("URI: " + uri);
        System.out.println("PARAMS: " + params);
    }
}
