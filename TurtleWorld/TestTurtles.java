public class TestTurtles { 
   public static void main(String[] args) {
 
     World w = new World(400,400); 
     Turtle t1 = new Turtle(w);
     Turtle t2 = new Turtle(w);

     t1.moveRandom();
     t2.moveRandom();
     
     System.out.println(t1.distance(t2));
   }
}