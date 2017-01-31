public class StrollingTurtles {

    public static void main(String[] args) 
           throws InterruptedException 
    {  
       // Create the world and the turtles here and other necessary 
       // initializations.
       
     World w = new World(400,400); 
     Turtle t1 = new Turtle(w);
     Turtle t2 = new Turtle(w);
     
     t1.move(25);
     t2.move(-25);
     
     boolean collided = false;
     
     int collissions = 0;
     int steps = 0;
     
       while (t1.distance(t2) < 200) {
          // Move the turtles and check for collisions
          t1.moveRandom();
          t2.moveRandom();
          
          if(t1.distance(t2) < 20 && !collided) {
            collided = true;
            System.out.println("Krock!");
            collissions++;
          }
          else {
            collided = false;
          }
          
          steps++;
          Thread.sleep(200);  // Make the program sleep for 0.2 sec.
       } 
       
       // Print the number of steps and collisions      
       
       System.out.println("Steg: " + steps);
       System.out.println("Kollissioner: "+ collissions);
       
    } // End of main method

} // End of class 