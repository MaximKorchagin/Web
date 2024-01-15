package org.example;

import org.example.logger.Logger;
import org.example.server.Server;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;

import static org.example.enums.ErrorMsg.*;
import static org.example.enums.ParametersStrings.*;
import static org.example.enums.ResponseMsg.*;
import static org.example.services.MessageFormatter.getSystemDesign;

public class Main {

    private static void fillMapHandlersDefault(Server server) {
        final var paths = List.of(PATH_APP_JS.getValue(), PATH_DEFAULT_HTML.getValue(),
                PATH_EVENTS_HTML.getValue(), PATH_EVENTS_JS.getValue(), PATH_FAVICON_ICO.getValue(),
                PATH_FORMS_HTML.getValue(), PATH_INDEX_HTML.getValue(), PATH_LINKS_HTML.getValue(),
                PATH_RESOURCES_HTML.getValue(), PATH_SPRING_PNG.getValue(), PATH_SPRING_SVG.getValue(),
                PATH_STYLES_CSS.getValue());
        for (String path : paths) {
            server.addHandler(METHOD_GET.getValue(), path, (request, responseStream) ->
                    sendDocument(request.getPath(), responseStream));
        }

        server.addHandler(METHOD_GET.getValue(), PATH_CLASSIC_HTML.getValue(), (request, responseStream) -> {
            final var filePath = Path.of(PARENT_DIRECTORY.getValue(), DIRECTORY_PUBLIC.getValue(),
                    request.getPath());
            final var mimeType = Files.probeContentType(filePath);
            final var template = Files.readString(filePath);
            final var content = template.replace(TEMPLATE_TIME.getValue(),
                    LocalDateTime.now().toString()).getBytes();
            responseStream.write((String.format(RESPONSE_200.getMsg(), mimeType, content.length)).getBytes());
            responseStream.write(content);
            responseStream.flush();
        });

        final var methods = List.of(METHOD_GET.getValue(), METHOD_POST.getValue());
        for (String method : methods) {
            server.addHandler(method, PATH_ROOT.getValue(), (request, responseStream) -> {
                if (request.getQueryParams().isEmpty()) {
                    sendError(RESPONSE_404.getMsg(), responseStream);
                } else {
                    sendDocument(PATH_CONFIRMATION_HTML.getValue(), responseStream);
                }
            });
        }
    }

    public static void sendDocument(String path, BufferedOutputStream responseStream) {
        final var logger = Logger.getLogger();
        try {
            final var filePath = Path.of(PARENT_DIRECTORY.getValue(), DIRECTORY_PUBLIC.getValue(), path);
            final var mimeType = Files.probeContentType(filePath);
            final var length = Files.size(filePath);
            responseStream.write((String.format(RESPONSE_200.getMsg(), mimeType, length)).getBytes());
            Files.copy(filePath, responseStream);
            responseStream.flush();
        } catch (IOException e) {
            logger.logFileOut(getSystemDesign(ERROR_INPUT_OUTPUT.getMsg() + e));
        }
    }

    public static void sendError(String error, BufferedOutputStream responseStream) {
        final var logger = Logger.getLogger();
        try {
            responseStream.write(error.getBytes());
            responseStream.flush();
        } catch (IOException e) {
            logger.logFileOut(getSystemDesign(ERROR_INPUT_OUTPUT.getMsg() + e));
        }
    }

    public static void main(String[] args) {
        final var server = new Server();
        fillMapHandlersDefault(server);
        server.start();
    }
}