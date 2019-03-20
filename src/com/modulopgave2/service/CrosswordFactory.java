package com.modulopgave2.service;

import com.modulopgave2.model.Crossword;
import com.modulopgave2.model.Word;


import java.util.*;

public class CrosswordFactory {
    Collection<Word> wordCollection;
    Crossword crossword;

    Set<String> usedWordCombos = new HashSet<>();
    Set<String> usedCharacterCombos = new HashSet<>();



    public CrosswordFactory(Collection<Word> wordCollection, Crossword crossword) {
        this.wordCollection = wordCollection;
        this.crossword = crossword;
        Comparator<Word> compareById = (Word o1, Word o2) -> (o2.getId() - o1.getId());

        Collections.sort((List) wordCollection, (Comparator<Word>) (Word w1, Word w2) -> w1.getId().compareTo( w2.getId() ));
    }

    public Crossword generateCrossword() {
        Crossword result = null;





        return result;
    }

    private boolean isCrosswordValid() {

    }
}
