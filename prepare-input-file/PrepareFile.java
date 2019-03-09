import java.io.IOException;
import java.io.File;
import java.io.FileWriter;

import java.io.FileReader;
import java.io.BufferedReader;

import java.util.Set;
import java.util.HashSet;

// For writeCollectionToFile
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.util.Collection;

// For removeInvalidWords
import java.util.Iterator;

public class PrepareFile {

  private Set<String> words = new HashSet<>();

  public static void main(String[] args) {
    new PrepareFile().run();
  }

  public void run() {
    lowerCaseRemoveDuplicateWords();
    removeInvalidWords(words);
  }


  private void writeCollectionToFile(Collection<String> cs, String name) {
    var fileOut = name;
    try (
      var bw = new BufferedWriter(new FileWriter(fileOut));
    )
    {
      for (String c : cs) {
        bw.write(c + "\n");
      }
      bw.flush();
    }
    catch (IOException e) { System.out.println(e); }
  }

  private void printCollection(Collection<String> s) {
     for (String word : s) {
       System.out.println(word);
     }
  }

  // Removes words with numbers and words with duplicate letters
  private void removeInvalidWords(Collection<String> cs) {
    Iterator iter = cs.iterator();

    while ( iter.hasNext() ) {
      String word = (String) iter.next();
      char[] splitWord = word.toCharArray();
      Set<Character> letters = new HashSet<>();

      for (int i = 0; i < splitWord.length; i++) {
        letters.add(splitWord[i]);

        if ( !Character.isLetter(splitWord[i]) || letters.size() != i+1 ) {  //isLetter OR !isDigit
          iter.remove(); // Remove the word! 
          break; // If the word is removed there's no reason to continue looping over the word
        }
      }
    }
    writeCollectionToFile(words, "out3.txt" );
  }

  private void lowerCaseRemoveDuplicateWords() {
    var fileIn = "3LetterWord_Unicode.txt";
    try (
      var fr = new FileReader(fileIn);
      var br = new BufferedReader(fr);
    )
    {

      while ( br.ready() ) {
        words.add( br.readLine().toLowerCase() ); // Lowercases and removes duplicates automatically by inserting into a Set
      }
      System.out.println( words.size() );
    }
    catch (IOException e) { System.out.println(e); }
  }

}
