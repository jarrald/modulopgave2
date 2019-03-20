package com.modulopgave2.client2;

import com.modulopgave2.dal.Repository;
import com.modulopgave2.dal.WordRepository;
import com.modulopgave2.dal.WordRepositoryV2;
import com.modulopgave2.model.Crossword;
import com.modulopgave2.model.Letter;
import com.modulopgave2.model.Word;
import com.modulopgave2.service.CrosswordGenerator;

import java.util.*;

public class Application {
    public static void main(String[] args) throws Exception {

        Repository<Word> wordRepository = new WordRepositoryV2();
        Collection<Word> wordCollection = wordRepository.list();


        CrosswordGenerator cwg = new CrosswordGenerator(wordCollection);
        cwg.generateCrosswords(2);

        System.out.println(cwg.getFoundCrosswords().size());




        /*// build letter criteria
        List<Letter> letterCriteria = new ArrayList<>();
        letterCriteria.add(new Letter(0, 'h', 0));
        letterCriteria.add(new Letter(0, 'e', 1));
        letterCriteria.add(new Letter(0, 'j', 2));

        // add criteria to a word
        Word criteria = new Word(0, "", letterCriteria);

        // find words from criteria
        Collection<Word> wordList = wordRepositoryV2.find(criteria);

        System.out.println("Words found: "+ wordList.size());
        // print found words
        Iterator<Word> it = wordList.iterator();
        while (it.hasNext()) {
            Word word = it.next();
            System.out.println(word.getValue());
        }*/
    }
}
