package org.example.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MessageFormatter {

    public static String getSystemDesign(String message) {
        return String.format("[%s] %s", LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss")),
                message);
    }
}
