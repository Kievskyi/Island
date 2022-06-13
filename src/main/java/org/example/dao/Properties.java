package org.example.dao;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Properties {

    @JsonProperty("Field length")
    private int fieldLength;
    @JsonProperty("Field width")
    private int fieldWidth;

    public Properties(int fieldLength, int fieldWidth) {
        this.fieldLength = fieldLength;
        this.fieldWidth = fieldWidth;
    }

    public Properties() {
    }

    @Override
    public String toString() {
        return "Properties{" +
                "fieldLength=" + fieldLength +
                ", fieldWidth=" + fieldWidth +
                '}';
    }
}
