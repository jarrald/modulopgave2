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

        System.out.println("Words loaded: " + wordCollection.size());

        CrosswordGenerator cwg = new CrosswordGenerator(wordCollection);

        System.out.println("Finding possible combinations where no crossword have the same set of characters or words "
                +"\nand letters can only appear once in each crossword\n");

        long startTime = System.nanoTime();
        cwg.generateCrosswords(6);
        long endTime = System.nanoTime();

        long duration = (endTime - startTime); //divide by 1000000 to get milliseconds.
        long durationMs = ((duration / 1000000));

        // Display all crosswords
        /*int i = 0;
        Iterator<Map.Entry<String, Crossword>> it = cwg.getFoundCrosswords().entrySet().iterator();
        while(it.hasNext()) {
            Map.Entry<String, Crossword> entry = it.next();
            System.out.println((++i) + " " + entry.getKey() + " :\n" + entry.getValue().toString() + "\n");
        }*/

        System.out.println("Found: " + cwg.getFoundCrosswords().size() + "\nExecution time: "+ durationMs +" ms");
    }
}
