package ru.otus.java.basic.hw24;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class HttpRequest {
    private final String RAW_REQUEST;
    private HttpMethod method;
    private String uri;
    private final Map<String, String> PARAMS;
    private String body;
    private static final Logger logger = LogManager.getLogger(HttpRequest.class.getName());


    public String getUri() {
        return uri;
    }

    public String getParameter(String name) {
        return PARAMS.get(name);
    }

    public String getBody() {
        return body;
    }

    public boolean containsParameter(String name) {
        return PARAMS.containsKey(name);
    }

    public String getRoutingKey() {
        return method + " " + uri;
    }

    public HttpRequest(String rawRequest) {
        this.RAW_REQUEST = rawRequest;
        this.PARAMS = new HashMap<>();
        parse();
    }

    private void parse() {
        int left = RAW_REQUEST.indexOf(" ");
        int right = RAW_REQUEST.indexOf(" ", left + 1);
        method = HttpMethod.valueOf(RAW_REQUEST.substring(0, left));
        uri = RAW_REQUEST.substring(left + 1, right);
        if (uri.contains("?")) {
            String[] tokens = uri.split("[?]");
            uri = tokens[0];
            String[] keysAndValues = tokens[1].split("[&]");
            for (String o : keysAndValues) {
                String[] keyValue = o.split("[=]");
                PARAMS.put(keyValue[0], keyValue[1]);
            }
        }
        if (method == HttpMethod.POST) {
            body = RAW_REQUEST.substring(RAW_REQUEST.indexOf("\r\n\r\n") + 4);
        }
    }

    public void info() {
        logger.trace(method);
        logger.trace(uri);
        logger.trace(PARAMS);
        logger.trace(body);
        logger.debug(RAW_REQUEST);
    }
}
