public class TestMeasurements {
  public static void main(String[] args) {
     Measurements m = new Measurements(6);
     
     for (int i=0; i < 6; i++) {
       double e = (int)(Math.sin((i+1)/2.)*1000)/10.;
       m.add(e);
       System.out.println("Test add " + e + ": " + m.toString());
     }
     System.out.println("");
     
     System.out.println("Test stored: " + m.stored());
     System.out.println("");
     
     System.out.println("Test get(3): " + m.get(3));
     System.out.println("");
     
     System.out.println("Test mean: " + m.mean());
     System.out.println("");
     
     System.out.println("Test min: " + m.min());
     System.out.println("");
     
     System.out.println("Test max: " + m.max());
     System.out.println("");
     
     System.out.println("Test stdDev: " + m.stdDev());
     System.out.println("");
     
     System.out.println("TEST ARRAY CONSTRUCTOR");
     double[] a = new double[] {3.314, 13.48, 48., 19.13};
     System.out.println("Array a: " + a.toString());
     m = new Measurements(a);
     System.out.println("New measurements: " + m.toString());
   }
}