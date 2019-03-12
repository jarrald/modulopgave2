package com.modulopgave2.service;

import com.modulopgave2.model.Crossword;
import com.modulopgave2.model.Word;

import java.util.Collection;
import java.util.HashSet;

public class CrosswordFactory {
    Collection<Word> wordCollection;
    Collection<Crossword> crosswordCollection = new HashSet<>();


    public CrosswordFactory(Collection<Word> wordCollection) {
        this.wordCollection = wordCollection;
    }

    public Crossword generateCrossword() {
        Crossword result = null;



        return result;
    }

    public Collection<Crossword> generateAllPossibleUnique() {
        Collection<Crossword> result = null;

        return result;
    }
}
