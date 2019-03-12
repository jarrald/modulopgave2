package com.modulopgave2.model;

import com.modulopgave2.model.Word;
import java.util.Objects;

public class Crossword {
    public Crossword() {

    }

    @Override
    public int hashCode() {
        return new Word[][] {}.hashCode() ;//grid.hashCode();
    }
}
