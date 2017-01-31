import java.util.ArrayList;  

public class Tombola {
  ArrayList<Integer> tickets = new ArrayList<Integer>(0); // Creates an empty arraylist
  
  public 
  
  public Tombola (int n) {
    // Creates an empty ArrayList
    ArrayList<Integer> new_tickets = new ArrayList<Integer>(0); 
    
    // Fills new_tickets with numbers 1 to n
    for (int i=0; i<n; i++) { 
      new_tickets.add(i);
    }
    
    // Removes random element from new_tickets and adds it to this.tickets
    while (!new_tickets.isEmpty()) {
      int i = (int)(Math.random()*new_tickets.size());
      this.tickets.add(new_tickets.get(i));
      new_tickets.remove(i);
    }
    
  }
  
}