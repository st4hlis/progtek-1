public class TestPairOfDice { 
   public static void main(String[] args) {
     PairOfDice d = new PairOfDice(6);

     int rolls = 10;
     int i = 0;
     while(i < rolls) {
       d.roll();
       System.out.print(d);
     
       if (d.isPair()) {
         System.out.print(" It's a pair!");
       }
       System.out.println("");
       i++;
     }
   }
}