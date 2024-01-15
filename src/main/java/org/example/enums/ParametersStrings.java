package org.example.enums;

public enum ParametersStrings {
    METHOD_GET("GET"),
    METHOD_POST("POST"),
    DIRECTORY_LOG("/logs"),
    DIRECTORY_PUBLIC("/public"),
    FILE_SERVER_LOG("/server.log"),
    PARENT_DIRECTORY(System.getProperty("user.dir")),
    PATH_APP_JS("/app.js"),
    PATH_CLASSIC_HTML("/classic.html"),
    PATH_CONFIRMATION_HTML("/confirmation.html.html"),
    PATH_DEFAULT_HTML("/default.html"),
    PATH_EVENTS_HTML("/events.html"),
    PATH_EVENTS_JS("/events.html"),
    PATH_FAVICON_ICO("/favicon.ico"),
    PATH_FORMS_HTML("/forms.html"),
    PATH_INDEX_HTML("/index.html"),
    PATH_LINKS_HTML("/links.html"),
    PATH_RESOURCES_HTML("/resources.html"),
    PATH_ROOT("/"),
    PATH_SPRING_PNG("/spring.png"),
    PATH_SPRING_SVG("/spring.svg"),
    PATH_STYLES_CSS("/styles.css"),
    TEMPLATE_TIME("{time}");

    private String value;

    ParametersStrings(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}