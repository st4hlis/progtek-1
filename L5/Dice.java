public class Dice {
   private int nSides;
   private int value;
   
   public Dice() {
      this(6);      // Refers to the constructor taking an int as
                    // parameter i e the constructor below
   }
  
   public Dice(int nSides) {
     if (nSides<2) {
       System.out.println("Wrong numnber of sides");
       this.nSides = 6;
     } 
     else {
       this.nSides = nSides;
     }
     this.roll();
   }
       
   public int getValue() {
      return this.value;
   }
 
   public void roll() {
      this.value = (int)(Math.random()*this.nSides) + 1;
   } 
   
   public int roll (int n) {
     int i = 0;
     int sum = 0;
     while (i < n) {
       this.roll();
       sum = sum + this.getValue();
       i++;
     }
     return sum;
   }
   
   public String toString() {
     return "Dice(" + this.nSides + ", " + this.value + ")";
   }
   
   public static void main(String[] args) {
     Dice d = new Dice(7);
     for (int i= 1; i<=50; i++) {
       d.roll();
       System.out.print(d.getValue() + "  ");
       if (i%10 == 0) {
         System.out.println();
       }
     }
     
     Dice d2 = new Dice(6);
     System.out.println(d2.roll(10));
   }
}
