package org.example.enums;

public enum SystemMsg {
    SERVER_START("Сервер старт, порт: %d"),
    SERVER_STOP("Сервер стоп"),
    CLIENT_CONNECTED("Клиент подключился: %s"),
    CLIENT_DISCONNECTED("Клиент отключился: %s");

    private String msg;

    SystemMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}