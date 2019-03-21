package com.modulopgave2.client1;

import com.modulopgave2.dal.ImportedWordRepository;
import com.modulopgave2.dal.WordRepository;
import com.modulopgave2.dal.WordRepositoryV2;
import com.modulopgave2.model.Word;
import com.modulopgave2.service.WordImporter;

import java.util.*;

public class Application {


    public static void main(String args[])
    {
        // instantiate and initialize repositories
        ImportedWordRepository importedWordRepository = new ImportedWordRepository();

        WordRepository wordRepository = new WordRepositoryV2();

        // old wordRepository - doesn't split word to letters on creation
        //WordRepository wordRepository = new WordRepository();

        // instantiate and initialize WordImporter object
        WordImporter importer = new WordImporter(wordRepository);

        // this will store the words to be imported
        Collection<Word> wordsToImport;

        try {
            // fetch words to be imported
            wordsToImport = importedWordRepository.list();

            System.out.println("Words to be imported: " + wordsToImport.size());

            // import words
            int importedWordCount = importer.importWords(wordsToImport);

            System.out.println("Words imported: " + importedWordCount);
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
}
