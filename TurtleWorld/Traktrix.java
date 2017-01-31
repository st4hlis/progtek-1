public class Traktrix { 
   public static void main(String[] args) 
     throws InterruptedException 
   {
 
     World w = new World(400,400); 
     Turtle[] turtles = new Turtle[4];
     
     int[] xCorners = {0,  1,  1,  0};
     int[] yCorners = {0,  0,  1,  1};
     int[] xPadding = {1, -1, -1,  1};
     int[] yPadding = {1,  1, -1, -1};
     int[] angles   = {0, 90, 180, 270,};
     
     for (int i=0; i < turtles.length; i++) {
       turtles[i] = new Turtle(w);
       Turtle turtle = turtles[i];
       turtle.disablePath();
       turtle.moveTo((int)(xCorners[i]*400+xPadding[i]*20), 
                     (int)(yCorners[i]*400+yPadding[i]*20));
       turtle.turn(angles[i]);
       turtle.enablePath();
     }
     
     int i = 0;
     int counter = 0;
     while(turtles[i].distanceTo(200,200) > 20) {
       turtles[i].move(20);
       turtles[i].turn(counter);
       i = ++i%4;
       if (i/3 == 1) {
         counter++;
         Thread.sleep(200);  // Make the program sleep for 0.2 sec.
       }
     }
   }
}