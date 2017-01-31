public class Rectangle {
  
  private double width;
  private double height;
  
  public Rectangle() {
    width = 1;
    height = 1;
  }
  
  public Rectangle(double width, double height) {
    this.width = width;
    this.height = height;
  }
  
  public double getWidth() {
    return width;
  }
  
  public double getHeight() {
    return height;
  }
  
  public double getArea() { 
    return width*height;  
  }
   
  public void setWidth(double width) {
    width = this.width; 
  }
  
  public void setHeight(double height) {
    this.height = height; 
  }
  

  public String toString() {
    return "Rectangle(" + width + "x" + height + ")";  
  }
  
  public static void main(String [] arg) {
    Rectangle rec1 = new Rectangle();
    
    rec1.setWidth(4);
    rec1.setHeight(5);
    System.out.print(rec1);
    System.out.println(" has area " + rec1.getArea());
    
    Rectangle rec2 = new Rectangle(3, 2);
    System.out.print(rec2);
    System.out.println(" has area " + rec2.getArea());                
  }
  
  public double getCircumference() {
    return this.width*2 + this.height*2;
  }
}