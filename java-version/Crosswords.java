public class Crosswords {

  public static void main(String[] args) {
    var cwg = new CrosswordsGenerator();
    cwg.run();
    cwg.generate();
    cwg.print(100);
    //System.out.println(cwg);
    cwg.size();
    //cwg.printAllWords();
  }

  // For testing
  private void test() {
    Crossword cw = new Crossword('a', 'b', 'c', 'd','e','f','g','h', 'i');
    System.out.print(cw);
    cw.setRow(2, 'j','a','a');
    System.out.print(cw);
    cw.setCol(3, 'n','e','j');
    System.out.print(cw);
    //System.out.println( cw.getCol(2) );

  }

}
