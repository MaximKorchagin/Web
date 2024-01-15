package org.example.request;

import org.apache.hc.core5.http.NameValuePair;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Request {

    private final String method;
    private final String path;
    private final List<NameValuePair> params;
    private final Map<String, String> headers;
    private final String body;

    public Request(String method, String path, List<NameValuePair> params, Map<String, String> headers, String body) {
        this.method = method;
        this.path = path;
        this.params = params;
        this.headers = headers;
        this.body = body;
    }

    public String getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public String getBody() {
        return body;
    }

    public List<NameValuePair> getQueryParam(String name){
        return params.stream()
                .filter(s -> s.getName().equals(name))
                .collect(Collectors.toList());
    }

    public List<NameValuePair> getQueryParams() {
        return params;
    }

    @Override
    public String toString() {
        return "Request{" +
                "method='" + method + '\'' +
                ", path='" + path + '\'' +
                ", params=" + params +
                ", headers=" + headers +
                ", body='" + body + '\'' +
                '}';
    }
}