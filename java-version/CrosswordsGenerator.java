import java.io.IOException;

import java.io.FileReader;
import java.io.BufferedReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Collection;
import java.util.HashSet;

import java.util.Arrays;

public class CrosswordsGenerator {

  Map<Character, ArrayList<String>> words = new HashMap<Character, ArrayList<String>>();
  Collection<Crossword> crosswords = new ArrayList<>();
  // Collection<Crossword> crosswords = new HashSet<>();

  private void initWordMap() {
    for (char letter = 'a'; letter<='z'; letter++) {
      words.put(letter, new ArrayList<String>());
    }
    // Not immediately succeeding a-z in ASCII/UTF-8, so:
    words.put('æ', new ArrayList<String>());
    words.put('ø', new ArrayList<String>());
    words.put('å', new ArrayList<String>());
  }

  public void run() {
    initWordMap();

    var fileIn = "../prepare-input-file/out3-without-ole.txt";
    try (
      var fr = new FileReader(fileIn);
      var br = new BufferedReader(fr);
    ) {

      while ( br.ready() ) {
        var line = br.readLine();
        words.get( line.charAt(0)).add(line);
      }
    }
    catch (IOException e) { System.out.println(e); }
  }

  // Steps:
  // Loop through all words in the first row: x, y, z
  // Loop through all possible completions of that word for each column
  // Discard crosswords with meaningless words in the second and third row, either simultaneously or afterwards
  // Discard crosswords with duplicate letters anywhere either simultaneously or afterwards (crossword duplicate method)
  // Discard crosswords, if any (?), that are transposes of each other (crossword equals method)
  public void generate() {
    for ( Map.Entry<Character, ArrayList<String>> entry : words.entrySet() ) {

      for (String w1 : entry.getValue() ) {
        var cross = new Crossword();
        // WORD 1
        char[] w1c = w1.toCharArray();
        cross.setRow(1, w1c );
        var ws2 = getWordsByFirstLetter(w1c[0]);

        // WORD 2
        for (String w2 : ws2) {
          char[] w2c = w2.toCharArray();
          cross.setCol(1, w2c);
          if (cross.invalidChars()) continue; // If the word is the same as any of the others or there are duplicate letters skip to the next word

          // WORD 3
          var ws3 = getWordsByFirstLetter(w1c[1]);

          for (String w3 : ws3) {
            char[] w3c = w3.toCharArray();
            cross.setCol(2, w3c);
            if (cross.invalidChars() || invalidWordsRows(cross, true )) continue; // If the word is the same as any of the others or there are duplicate letters skip to the next word + check valid words

            // WORD 4
            var ws4 = getWordsByFirstLetter(w1c[2]);
            for (String w4 : ws4) {
              char[] w4c = w4.toCharArray();
              cross.setCol(3, w4c);
              if (cross.invalidChars() || invalidWordsRows(cross, false )) continue;
              else crosswords.add(cross);
            }
          }
        }
      }
    }
  }

  public boolean invalidWordsRows(Crossword cross, boolean two) {
    boolean wordRow2Found = false,
            wordRow3Found = false;
    for ( Map.Entry<Character, ArrayList<String>> entry : words.entrySet() ) {
      for (String w : entry.getValue() ) {
        if (two) { // 2-letter comparison
          var cw2 = w.substring(0,2).toCharArray();
          var r2s = new String(cross.getRow(2)).substring(0,2);
          var r3s = new String(cross.getRow(3)).substring(0,2);
          if ( Arrays.equals(cw2, r2s.toCharArray() )) wordRow2Found = true;
          else if (Arrays.equals(cw2, r3s.toCharArray() )) wordRow3Found = true;
        }
        else { // Full, 3-letter comparison
          var cw = w.toCharArray();
          if (Arrays.equals(cw, cross.getRow(2))) wordRow2Found = true;
          else if (Arrays.equals(cw, cross.getRow(3))) wordRow3Found = true;
        }

        if (wordRow2Found && wordRow3Found) {
          return false;
        }
      }
    }
    return true;
  }

  private ArrayList<String> getWordsByFirstLetter(char letter) {
    return words.get( letter );
  }

  public String toString() {
    var str = "";
    for (var cw : crosswords) {
      str += cw.toString() + "\n\n";
    }
    return str;
  }

  // For testing
  public void printAllWords() {
    //System.out.println(words.size());
    for ( Map.Entry<Character, ArrayList<String>> entry : words.entrySet() ) {
      System.out.println(entry.getKey() + ":");
      System.out.print( String.join(", ", entry.getValue()) );
      System.out.println("\n");
    }
  }

  // For testing
  public void size() {
    System.out.println(crosswords.size());
  }


  // For testing
  public void print(int num) {
    int i = 0;
    for (var cw : crosswords) {
      System.out.println(cw);

      if (++i == num) break;
    }
  }

  // For testing
  private void printArray(char arr[], int num) {
    int n = (num > 0)? num : arr.length;

    for (int i=0; i<n; i++) {
      System.out.print(arr[i]);
      if (i % 3 == 0) System.out.println();
    }
  }

}
