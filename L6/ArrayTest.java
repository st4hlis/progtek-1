public class ArrayTest {
  
  public static void main(String[] arg) {
    
    int [] x = {5, 7, 0, 2, 8, 23, -4, 0, -7, 9, 12};
    
    System.out.println("The array object  : " + x);
    System.out.print("The array contents: ");
    for (int i=0; i<x.length; i++) {
      System.out.print(x[i]+ " ");
    }
    System.out.println();
    
    /*************
     * Excercise 1
     * Print the elements in reverse order i.e. with the last element first");
     */
    
    System.out.print("Reverse order: ");
    String str = new String();
    for (int e : x) {
      str = e + " " + str;
    }
    System.out.println(str);
    
    /************
      * Excercise 2 
      * Compute and print the sum of all elements in the array  
      */ 
    System.out.print("Sum:");
    int sum = 0;
    for (int e : x) {
      sum = sum + e;
    }
    System.out.println(sum);
    
    
    int tal=5;
    /***********
      * Excercise 3
      * Compute and print the number of values greater than 'tal'.
      */
    
    System.out.print("Elements larger than " + tal + ": ");
    for (int e : x) {
      if (e > tal) {
        System.out.print(e + " ");
      }
    }
    System.out.println();
    
    
    /***********
      * Exercise 4
      * Find the largest element and print it together with it's index.
      */
    
    int lrg = x[0];
    for (int e : x) {
      if (e > tal) {
        lrg = e;
      }
    }
    System.out.println("Largest element: " + lrg);
   
    
    int [] y = new int[x.length];
    /**********
      * Excersice 5
      * Copy all elements from x to y in reverse order so that the last
      * element in x becomes the first element in y and so on.
      * Print the contents of y.
      */
    
    for (int i=0; i<x.length; i++) {
      y[i] = x[x.length - (i + 1)];
    }
    
    System.out.print("Contents of y: ");
    for (int i=0; i<y.length; i++) {
      System.out.print(y[i]+ " ");
    }
    System.out.println();
    
    /**********
      * Excersice 6
      * Exchange the first and last element in y. 
      * Print y to verify.
      */

    int temp = y[0];
    y[0] = y[y.length - 1];
    y[y.length - 1] = temp;
    
    System.out.print("Contents of y: ");
    for (int i=0; i<y.length; i++) {
      System.out.print(y[i]+ " ");
    }
    System.out.println();
    
    int [] z = x;
    /**********
      * Excersice 7
      * Print out z.
      */

    System.out.print("Contents of z: ");
    for (int i=0; i<z.length; i++) {
      System.out.print(z[i]+ " ");
    }
    System.out.println();
   
     z[0] = 99;
     x[x.length-1]=-88;
    /**********
      * Excersice 8
      * Print both the values in x and and z and compare.
      * Explain the may be somewhat unexpected result!
      */
    
    System.out.print("Contents of x: ");
    for (int i=0; i<x.length; i++) {
      System.out.print(z[i]+ " ");
    }
    System.out.println();
     
    System.out.print("Contents of z: ");
    for (int i=0; i<z.length; i++) {
      System.out.print(z[i]+ " ");
    }
    System.out.println();
    
  }  
} 