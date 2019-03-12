package com.modulopgave2.service;

import com.modulopgave2.dal.Repository;
import com.modulopgave2.model.Word;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class WordImporter {

    Repository<Word> wordRepository;

    public WordImporter(Repository<Word> wordRepository) {
        this.wordRepository = wordRepository;
    }

    public int importWords(Collection<Word> wordCollection) {
        int imported = 0;
        filterWords(wordCollection);

        // insert words to db
        for (Word word : wordCollection) {
            try {
                wordRepository.create(word);
                imported++;
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        return imported;
    }

    private void filterWords(Collection<Word> wordCollection) {
        lowerCaseRemoveDuplicateWords(wordCollection);
        removeInvalidWords(wordCollection);
    }

    // Removes words with numbers and words with duplicate letters
    private void removeInvalidWords(Collection<Word> wordCollection) {
        Iterator it = wordCollection.iterator();

        while ( it.hasNext() ) {
            Word word = (Word) it.next();
            char[] splitWord = word.getValue().toCharArray();
            Set<Character> letters = new HashSet<>();

            for (int i = 0; i < splitWord.length; i++) {
                letters.add(splitWord[i]);

                if ( !Character.isLetter(splitWord[i]) || letters.size() != i+1 ) {  //isLetter OR !isDigit
                    it.remove(); // Remove the word!
                    break; // If the word is removed there's no reason to continue looping over the word
                }
            }
        }
    }

    private void lowerCaseRemoveDuplicateWords(Collection<Word> wordCollection) {
        Set<String> usedWords = new HashSet<>();

        Iterator it = wordCollection.iterator();
        while (it.hasNext()) {
            Word word = (Word) it.next();

            // make word lower case
            word.setValue(word.getValue().toLowerCase());


            if (usedWords.contains(word.getValue()))
            { // word used
                // remove word
                it.remove();
            }
            else
            { // word not used
                // mark word as used
                usedWords.add(word.getValue());
            }
        }
    }
}
