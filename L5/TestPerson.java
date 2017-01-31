public class TestPerson { 
   public static void main(String[] args) {
     Person p = new Person("Ragnar", "Jordgubbsgatan", 36);
     
     
     System.out.println(p);
     System.out.println(p.getName());
     System.out.println(p.getAge());
     p.increaseAge();
     System.out.println(p.getAge());
     
   }
}