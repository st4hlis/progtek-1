public class Person {
  
  private String name;
  private String address;
  private int age;
  
  public Person () {
    this.name    = "NoName";
    this.address = "NoAddress";
    this.age     = 0;
  }
  
  public Person (String name, String address, int age) {
    this.name    = name;
    this.address = address;
    this.age     = age;
  }
  
  public String toString() {
    return "Name: " + this.name + 
           ", Address: " + this.address + 
           ", Age: " + age;
  }
  
  public String getName () {
    return this.name;
  }
  
  public String getAddress () {
    return this.address;
  }
  
  public int getAge () {
    return this.age;
  }
  
  public void increaseAge () {
    this.age++;
  }
  
  public int getDays() {
    return this.age*365;
  }
  
  public void setName (String name) {
    this.name = name;
  }
  
}