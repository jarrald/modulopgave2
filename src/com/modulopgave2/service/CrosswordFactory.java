package com.modulopgave2.service;

import com.modulopgave2.model.Crossword;
import com.modulopgave2.model.Word;


import java.util.*;

public class CrosswordFactory {
    List<Word> wordList;
    Crossword crossword;

    Set<String> usedWordCombos = new HashSet<>();
    Set<String> usedCharacterCombos = new HashSet<>();



    public CrosswordFactory(Collection<Word> wordCollection, Crossword crossword) {
        this.wordList = new ArrayList<>(wordCollection);
        this.crossword = crossword;

        // sort words by Id
        Comparator<Word> compareById = (Word o1, Word o2) -> (o2.getId() - o1.getId());
        Collections.sort(wordList, compareById);
    }

    public Crossword generateCrossword() {
        Crossword result = null;



        return result;
    }

    private boolean isCrosswordValid() {
        return false;
    }
}