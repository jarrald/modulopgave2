package com.modulopgave2.service;

import com.modulopgave2.model.Crossword;
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
        Comparator<Word> compareById = (Word o1, Word o2) -> (o2.getId() - o1.getId());
        Collections.sort(wordList, compareById);
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
                            .filter(p -> p.getLetter(0) != null)
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
        /*charList.add(words.get(2).getLetter(0).getValue());
        charList.add(words.get(2).getLetter(1).getValue());
        charList.add(words.get(2).getLetter(2).getValue());
        charList.add(words.get(4).getLetter(0).getValue());
        charList.add(words.get(4).getLetter(1).getValue());
        charList.add(words.get(4).getLetter(2).getValue());*/

        // sort chars ASC
        charList.sort(Comparator.naturalOrder());

        for (Character c : charList) {
            charsASC += c;
        }


        // sort words by Id
        Comparator<Word> compareById = (Word w1, Word w2) -> (w2.getId() - w1.getId());
        Collections.sort(words, compareById);

        for (Word word : words) {
            wordIdsASC += word.getId();
        }

        // if already exists -> return
        if(usedCrosswordsCharacters.contains(charsASC) || foundCrosswords.containsKey(wordIdsASC))
            return;


        usedCrosswordsCharacters.add(charsASC);
        foundCrosswords.put(wordIdsASC, new Crossword(new char[][] {
                new char[] { words.get(0).getLetter(0).getValue(), words.get(0).getLetter(1).getValue(), words.get(0).getLetter(2).getValue() }
                //new char[] { words.get(2).getLetter(0).getValue(), words.get(2).getLetter(1).getValue(), words.get(2).getLetter(2).getValue() },
                //new char[] { words.get(4).getLetter(0).getValue(), words.get(4).getLetter(1).getValue(), words.get(4).getLetter(2).getValue() }
        }));
    }
}