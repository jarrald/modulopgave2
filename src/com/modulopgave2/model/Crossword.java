package com.modulopgave2.model;

import com.modulopgave2.model.Word;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Crossword {
    List<Word> horizontalWords;
    List<Word> verticalWords;

    public Crossword(List<Word> horizontalWords, List<Word> verticalWords) {
        this.horizontalWords = horizontalWords;
        this.verticalWords = verticalWords;
    }

    public int countRows() { return horizontalWords.size(); }
    public int countColumns() { return horizontalWords.size(); }

    public Letter getLetter(int x, int y) {

        Letter result = null;
        Word word = null;

        if((word = horizontalWords.get(y)) != null)
            result = word.getLetter(x);
        else if((word = verticalWords.get(x)) != null) {
            result = word.getLetter(y);
        }

        return result;
    }
}
