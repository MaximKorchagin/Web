package org.example.logger;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import static org.example.enums.ErrorMsg.*;
import static org.example.enums.ParametersStrings.*;
import static org.example.services.MessageFormatter.getSystemDesign;
public class Logger {

    private static Logger logger = null;
    private BufferedWriter logFileWriter;
    private PrintWriter outConsole;
    private String pathDirectoryLog;
    private String pathLogFile;

    private Logger() {
        try {
            pathDirectoryLog = PARENT_DIRECTORY.getValue() + DIRECTORY_LOG.getValue();
            Files.createDirectories(Paths.get(pathDirectoryLog));
            pathLogFile = pathDirectoryLog + FILE_SERVER_LOG.getValue();
            logFileWriter = Files.newBufferedWriter(Paths.get(pathLogFile), StandardCharsets.UTF_8,
                    StandardOpenOption.WRITE, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            outConsole = new PrintWriter(System.out, true, StandardCharsets.UTF_8);
        } catch (IOException e) {
            outputErrorMessage(e);
        }
    }

    public static Logger getLogger() {
        if (logger == null) {
            logger = new Logger();
        }
        return logger;
    }

    public String getPathDirectoryLog() {
        return pathDirectoryLog;
    }

    public String getPathLogFile() {
        return pathLogFile;
    }

    public void logFile(String message) {
        try {
            logFileWriter.write(message);
            logFileWriter.newLine();
            logFileWriter.flush();
        } catch (IOException e) {
            outputErrorMessage(e);
        }
    }

    public void logOut(String message) {
        outConsole.println(message);
    }

    public void logFileOut(String message) {
        logFile(message);
        logOut(message);
    }

    public void close() {
        try {
            logFileWriter.close();
            outConsole.close();
        } catch (IOException e) {
            outputErrorMessage(e);
        }
    }

    private void outputErrorMessage(IOException e) {
        outConsole.println(getSystemDesign(ERROR_INPUT_OUTPUT.getMsg() + e));
    }
}