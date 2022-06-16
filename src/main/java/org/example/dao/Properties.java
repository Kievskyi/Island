package org.example.dao;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Properties {

    @JsonProperty("Area length")
    private int fieldLength;
    @JsonProperty("Area width")
    private int fieldWidth;

    public Properties(int fieldLength, int fieldWidth) {
        this.fieldLength = fieldLength;
        this.fieldWidth = fieldWidth;
    }

    public Properties() {
    }

    public int getFieldLength() {
        return fieldLength;
    }

    public void setFieldLength(int fieldLength) {
        this.fieldLength = fieldLength;
    }

    public int getFieldWidth() {
        return fieldWidth;
    }

    public void setFieldWidth(int fieldWidth) {
        this.fieldWidth = fieldWidth;
    }

    @Override
    public String toString() {
        return "Properties{" +
                "fieldLength=" + fieldLength +
                ", fieldWidth=" + fieldWidth +
                '}';
    }
}