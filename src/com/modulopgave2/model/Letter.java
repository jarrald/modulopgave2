package com.modulopgave2.model;

public class Letter {
    private int id;
    private char value;
    private int offset;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public char getValue() {
        return value;
    }
    public void setValue(char value) {
        this.value = value;
    }

    public int getOffset() { return offset; }
    public void setOffset(int offset) { this.offset = offset; }


    public Letter(int id, char value, int offset) {
        setId(id);
        setValue(value);
        setOffset(offset);
    }
}
