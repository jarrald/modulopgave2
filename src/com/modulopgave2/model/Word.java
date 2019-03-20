package com.modulopgave2.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;

public class Word {
    private int id;
    private String value;
    private Collection<Letter> letters;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Collection<Letter> getLetters() {
        return letters;
    }

    public void setLetters(Collection<Letter> letters) {
        this.letters = letters;
    }


    public Word(int id, String value, Collection<Letter> letters) {
        setId(id);
        setValue(value);
        setLetters(letters);
    }

    public Word(int id, String value) { this(id, value, new ArrayList<>()); }

    public Word(String value) {
        this(0, value);
    }


    public Letter getLetter(int offset) {
        Letter result = null;

        Iterator<Letter> it = letters.iterator();
        while (it.hasNext()) {
            Letter letter = it.next();
            if(letter.getOffset() == offset)
                result = letter;
        }

        return result;
    }


}