package org.example.enums;

public enum ErrorMsg {
    ERROR_INPUT_OUTPUT("Ошибка ввода-вывода ");

    private String msg;

    ErrorMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg(){
        return msg;
    }
}