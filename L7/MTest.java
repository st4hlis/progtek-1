/* JUnit3
 * 1. Put this class in the same directory as Measurements
 * 2. Compile
 * 3. Run (Measurements.java) with the <Test>-button instead of the <Run>-button
 */

import junit.framework.TestCase;
import java.util.Arrays;

public class MTest extends TestCase {
  
  private Measurements m;  
  
  final double EPSILON = 1E-14; // Error tolerance
  
  public void setUp() {
    m = new Measurements(2);
    m.add(-1);
    m.add(0);
    m.add(1);
    m.add(4);
  }
  
  
  public void testToString() {
    String res= "<-1.0, 0.0, 1.0, 4.0>";
    assertEquals("toString", res, m.toString());
  }
  
  public void testStored() {
    assertEquals("stored", 4, m.stored());  
  }
  
  public void testGet() {
    assertEquals("get(index)", 1., m.get(2));  
  }
  
  public void testMean() {
    assertEquals("mean", 1, m.mean(), EPSILON); 
  }  
  
  public void testMin() {
    assertEquals("min", -1, m.min(), EPSILON);
  }
  
  public void testMax() {
    assertEquals("max", 4, m.max(), EPSILON);
  }
  
  public void testStdDev() {
    assertEquals("stdDev", 1.8708286933869707, m.stdDev(), EPSILON);
  }
  
  public void testConstructor() {
    double[] a = {1., 2., 3.};
    Measurements mn = new Measurements(a);
    assertEquals("Number of stored elements", a.length, mn.stored());
    for (int i=0; i<a.length; i++) {
      assertEquals("Element at position " + i, a[i], mn.get(i)); 
    }
  }
  
  public void testIntegrity() {
    double[] a = {1., 2., 3.};
    Measurements mn = new Measurements(a);
    a[0] = -1;
    assertEquals("Array-constructor not safe!", 1., mn.get(0));
    a = mn.get();
    a[1] = -2;
    assertEquals("Array-get not safe!", 2., mn.get(1)); 
    
  }
  
  
  public void testSmooth() {
    System.out.println("testSmooth");
    
    double[] a = {2., 1., 6., 5., 4., 9., 2.};
    double[] r = {2., 3., 4., 5., 6., 5., 2.};
    
    Measurements mn = new Measurements(a);
    Measurements sm = mn.smooth();
    
    for (int i=0; i<a.length; i++) {
      assertEquals("Error in smooth element number " + i, r[i], sm.get(i));
    }
    
  }
  
  public void testAdd() {
    double [] a = {3., 1., 4.};
    Measurements mn = new Measurements(a);
    mn.add(2.0);
    String res= "<3.0, 1.0, 4.0, 2.0>";
    assertEquals("toString", res, mn.toString());
  }
  
  public void testMin2() {
    double [] a = {3., 1., 4.};
    Measurements mn = new Measurements(a);
    m.add(2.0);
    assertEquals("min", 1.0, mn.min());
  }
  
  public void testMax2() {
    double [] a = {3., 1., 4.};
    Measurements mn = new Measurements(a);
    m.add(2.0);
    assertEquals("max", 4.0, mn.max());
  }
  
  public void testGet2() {
    double [] a = {3., 1., 4.};
    Measurements mn = new Measurements(a);
    mn.add(2.0);
    assertEquals("[3.0, 1.0, 4.0, 2.0]", Arrays.toString(mn.get()));
  }
  
  public void testConstructor2() {
    Measurements mn = new Measurements(3);
    assertEquals("Number of stored elements", 0, mn.stored());
  }
  
}