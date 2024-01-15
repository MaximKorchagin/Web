package org.example.enums;

public enum ParametersInt {
    PORT_DEFAULT(24321),
    N_TREADS_DEFAULT(64),
    BUFFER_LIMIT(4096);
    private int value;

    ParametersInt(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}