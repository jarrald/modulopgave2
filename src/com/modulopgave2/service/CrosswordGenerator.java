package com.modulopgave2.service;

import com.modulopgave2.model.Crossword;
import com.modulopgave2.model.Letter;
import com.modulopgave2.model.Word;


import java.util.*;
import java.util.stream.Collectors;

public class CrosswordGenerator {
    List<Word> wordList;
    Crossword currentCrossword;

    Set<String> usedCrosswordsCharacters = new HashSet<>();
    Map<String, Crossword> foundCrosswords = new HashMap<>();

    public Map<String, Crossword> getFoundCrosswords() {
        return foundCrosswords;
    }

    public CrosswordGenerator(Collection<Word> wordCollection) {
        this.wordList = new ArrayList<>(wordCollection);
        this.currentCrossword = new Crossword(new char[3][3]);

        // sort words by Id
        Collections.sort(this.wordList, Comparator.comparing((Word o) -> o.getValue()));
    }

    public List<Crossword> generateCrosswords(int wordsNeeded) {
        for(ListIterator<Word> it = wordList.listIterator(); it.hasNext(); ) {
            Word word = it.next();
            List<Word> usedWords = new ArrayList<Word>();
            usedWords.add(word);

            findNextWord(usedWords, wordsNeeded);
        }

        return List.copyOf(foundCrosswords.values());
    }

    private void findNextWord(List<Word> wordsUsed, int wordsNeeded) {
        if(wordsUsed.size() == wordsNeeded) System.out.println("HEJ");

        int i = wordsUsed.size();
        int relations = (int) Math.floor(i);
        int x = (int) Math.ceil(i);
        int y = (int) Math.floor(i);

        List<Word> wordsToBeUsed = null;

        if(i == 1) {
            wordsToBeUsed = wordList.stream()
                    .filter(p -> p.getId() != wordsUsed.get(0).getId()
                            && p.getLetter(0).getValue() == wordsUsed.get(0).getLetter(0).getValue())
                    .collect(Collectors.toList());
        }

        if(i == 2) {
            wordsToBeUsed = wordList.stream()
                    .filter(p -> p.getId() != wordsUsed.get(0).getId()
                            && p.getId() != wordsUsed.get(1).getId()
                            && p.getLetter(0).getValue() == wordsUsed.get(1).getLetter(1).getValue())
                    .collect(Collectors.toList());
        }

        if(i == 3) {
            wordsToBeUsed = wordList.stream()
                    .filter(p -> p.getId() != wordsUsed.get(0).getId()
                            && p.getId() != wordsUsed.get(1).getId()
                            && p.getId() != wordsUsed.get(2).getId()
                            && p.getLetter(0).getValue() == wordsUsed.get(0).getLetter(1).getValue()
                            && p.getLetter(1).getValue() == wordsUsed.get(2).getLetter(1).getValue())
                    .collect(Collectors.toList());
        }

        if(i == 4) {
            wordsToBeUsed = wordList.stream()
                    .filter(p -> p.getId() != wordsUsed.get(0).getId()
                            && p.getId() != wordsUsed.get(1).getId()
                            && p.getId() != wordsUsed.get(2).getId()
                            && p.getId() != wordsUsed.get(3).getId()
                            && p.getLetter(0).getValue() == wordsUsed.get(1).getLetter(2).getValue()
                            && p.getLetter(1).getValue() == wordsUsed.get(3).getLetter(2).getValue())
                    .collect(Collectors.toList());
        }

        if(i == 5) {
            wordsToBeUsed = wordList.stream()
                    .filter(p -> p.getId() != wordsUsed.get(0).getId()
                            && p.getId() != wordsUsed.get(1).getId()
                            && p.getId() != wordsUsed.get(2).getId()
                            && p.getId() != wordsUsed.get(3).getId()
                            && p.getId() != wordsUsed.get(4).getId()
                            && p.getLetter(0).getValue() == wordsUsed.get(0).getLetter(2).getValue()
                            && p.getLetter(1).getValue() == wordsUsed.get(2).getLetter(2).getValue()
                            && p.getLetter(2).getValue() == wordsUsed.get(4).getLetter(2).getValue())
                    .collect(Collectors.toList());
        }


        for(ListIterator<Word> it = wordsToBeUsed.listIterator(); it.hasNext(); ) {
            Word word = it.next();
            List<Word> wordsUsedCopy = new ArrayList<>(wordsUsed);
            wordsUsedCopy.add(word);

            // if enough words are found
            if(wordsUsedCopy.size() == wordsNeeded) {
                addGeneratedCrossWord(wordsUsedCopy);
            }else {
                findNextWord(wordsUsedCopy, wordsNeeded);
            }
        }
    }


    private void addGeneratedCrossWord(List<Word> words)
    {
        String charsASC = "";
        String wordIdsASC = "";

        List<Character> charList = new ArrayList<>();
        charList.add(words.get(0).getLetter(0).getValue());
        charList.add(words.get(0).getLetter(1).getValue());
        charList.add(words.get(0).getLetter(2).getValue());
        charList.add(words.get(1).getLetter(1).getValue());
        charList.add(words.get(1).getLetter(2).getValue());
        charList.add(words.get(2).getLetter(1).getValue());
        charList.add(words.get(2).getLetter(2).getValue());
        charList.add(words.get(3).getLetter(2).getValue());
        charList.add(words.get(4).getLetter(2).getValue());

        Set<Character> charsFound = new HashSet<>();
        for(Character c : charList) {
            if(charsFound.contains(c))
                return;
            charsFound.add(c);

        }

        // sort chars ASC
        charList.sort(Comparator.naturalOrder());

        for (Character c : charList) {
            charsASC += c;
        }


        /*for (Word word : words) {
            System.out.print(word.getValue() +", ");
        }
        System.out.print("\n");*/

        List<Word> sortedWords = new ArrayList<>(words);
        // sort words by Value
        Collections.sort(sortedWords, Comparator.comparing((Word o) -> o.getValue()));

        for (Word word : sortedWords) {
            wordIdsASC += word.getId();
        }

        // if already exists -> return
        if(usedCrosswordsCharacters.contains(charsASC) || foundCrosswords.containsKey(wordIdsASC))
            return;


        usedCrosswordsCharacters.add(charsASC);
        foundCrosswords.put(wordIdsASC, new Crossword(new char[][] {
                new char[] { words.get(1).getLetter(0).getValue(), words.get(1).getLetter(1).getValue(), words.get(1).getLetter(2).getValue() },
                new char[] { words.get(0).getLetter(1).getValue(), words.get(2).getLetter(1).getValue(), words.get(3).getLetter(2).getValue() },
                new char[] { words.get(0).getLetter(2).getValue(), words.get(2).getLetter(2).getValue(), words.get(4).getLetter(2).getValue() }
        }));
    }
}