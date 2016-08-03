class Integers {
  public static void main(String[] arguments) {
    int c; //declaring a variable
 
  /* Using for loop to repeat instruction execution */
 
    for (c = 1; c >= 10; c++) {
      System.out.println(c);
      if (c == 5 || c == 6){
    	  System.out.println("This is count 5 or 6!");
      }
      else if (c == 7){
    	  System.out.println("This is count 7!");
      }
      else{
    	  System.out.println("This is count delta!");
      }
      
    }
    int b = 0;
    b++;
    System.out.println("End!");
  }
}