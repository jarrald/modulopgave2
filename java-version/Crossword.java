import java.util.Set;
import java.util.HashSet;

import java.util.Arrays;

public class Crossword {

  final int CROSSWORD_AXIS = 3;
  final int CROSSWORD_SIZE = CROSSWORD_AXIS * CROSSWORD_AXIS;

  char[] crosswordValues = new char[CROSSWORD_SIZE];

  public Crossword(char... chars) {
    assert chars.length == CROSSWORD_SIZE;

    for (int i = 0; i < CROSSWORD_SIZE; i++) {
      crosswordValues[i] = chars[i];
    }
  }

  public Crossword() {} // Leaves all crosswordValues at \u0000, the default  

  // Using this getter as much as possible so changing the internal implementation is easier if needed
  public char get(int position) {
    return crosswordValues[position-1];
  }

  // Gets a single char from the crossword
  public char get(int x, int y) {
    assert x <= CROSSWORD_AXIS && y <= CROSSWORD_AXIS;

    int pos = x + (y-1)*CROSSWORD_AXIS-1; // y-1 because I want this method 1-indexed
    return crosswordValues[pos]; 
  }

  public void set(char letter, int position) {
    crosswordValues[position-1] = letter;
  }

  // 1 -> 0, 2->3, 3->6
  public char[] getRow(int row) {
    int startIndex = CROSSWORD_AXIS*(row-1);
    return Arrays.copyOfRange(crosswordValues, startIndex, startIndex+CROSSWORD_AXIS);
  }

  // 1 -> 0, 3, 6
  // 2 -> 1, 4, 7
  public char[] getCol(int col) {

    char[] arr = new char[CROSSWORD_AXIS];

    int index = col;

    for (int i = 0; i<CROSSWORD_AXIS; i++) {
      arr[i] = get(index);
      index += CROSSWORD_AXIS;
    }
      
    return arr;
  }

  // 1 -> 0, 2->3, 3->6
  public void setRow(int row, char... chars) {
    assert chars.length == CROSSWORD_AXIS;

    int index = CROSSWORD_AXIS*(row-1)+1;
    for (int i=0; i<CROSSWORD_AXIS; i++) {
      set(chars[i], index);
      index++;
    }
  }

  public void setCol(int col, char... chars) {
    assert chars.length == CROSSWORD_AXIS;

    int index = col;
    for (int i=0; i<CROSSWORD_AXIS; i++) {
      set(chars[i], index);
      index += CROSSWORD_AXIS;
    }
  }

  private void printCrosswordValues() {
    for (int i = 0; i<CROSSWORD_SIZE; i++) {
      System.out.print(crosswordValues[i]);
      if (i % CROSSWORD_AXIS == 0) System.out.println();
    }
  }

  // 1 2 3 -> 1 4 7
  // 4 5 6 -> 2 5 8
  // 7 8 9 -> 3 6 9
  // Totally hardcoded for now because it's simpler to write
  private Crossword transpose() {

    // char[] transposed = new char[CROSSWORD_SIZE];
    // System.arraycopy( c, 0, temp, 0, CROSSWORD_SIZE );

    // final int ROW_SIZE = (int) Math.sqrt(CROSSWORD_SIZE);

    char pos2 = get(4);
    char pos3 = get(7);
    char pos4 = get(2);
    char pos6 = get(8);
    char pos7 = get(3);
    char pos8 = get(6);

    // Create a new transposed crossword. The first, last and center indexes (ie. 1, 5 and 9 for 3x3 crossword) are unaffected by a transpose therefore they're just copied directly
    Crossword transposed = new Crossword( get(0), pos2, pos3, pos4, get(5), pos6, pos7, pos8, get(9) );

    return transposed;
  }

  // Checks if the crosswords has any duplicate letters
  public boolean invalidChars() {
    Set<Character> cw = new HashSet<>();

    var count = 0;
    for (int i = 1; i <= CROSSWORD_SIZE; i++) {
      if ( get(i) != '\u0000' ) { 
        cw.add(get(i));
        count++;
      }
    }

    return ( cw.size() != count );
  }

  // Checks only second and third rows since those are the only ones needing to be checked according to the current method of constructing it

  public boolean equals(Crossword c) {
    for (int i = 1; i <= CROSSWORD_SIZE; i++) {
      if ( this.get(i) != c.get(i) ) return false;
      // Also try the transposed version
      Crossword transposed = c.transpose();
      if ( this.get(i) != transposed.get(i) ) return false;
    }
    return true;
  }

  public String toString() {
    String str = "";
    for (int i = 1; i<=CROSSWORD_SIZE; i++) {
      str += get(i);
      if ( i % CROSSWORD_AXIS == 0 ) str += "\n";
    }
    return str;
  }

}
