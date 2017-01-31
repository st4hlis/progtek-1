public class Measurements {
  private double data[];
  private int next = 0;
  
  public Measurements (int max) {
    this.data = new double[max];
  }
  
  public Measurements(double[] values) {
    this.data = new double[values.length];
    //System.arraycopy(values, 0, this.data, 0, values.length);
    for (int i=0; i<data.length; i++) {
        this.data[i] = values[i];
    }
    this.next = values.length;
  }
  
  public String toString() {
    String str = "<";
    for (int i=0; i< this.next; i++) {
      str = str + this.data[i] + ", ";
    }
    if (str.length() > 2) {
      str = str.substring(0, str.length() - 2);
    }
    return str + ">";
  }
  
  public void add (double value) {
    if (this.data.length == this.next) {                      // Checks if data is large enough
      double new_data[] = new double[this.data.length*2];     // Creates a new array of double
      for (int i=0; i<next; i++) {
        new_data[i] = this.data[i];
      }
      this.data = new_data;
    } 
    this.data[next] = value;
    next++;
  }
  
  public int stored () {
    return this.next;
  }
  
  public double get (int index) {
    return this.data[index];
  }
  
  public double mean() {
    double sum = 0.;
    for (int i=0; i<this.next; i++) {
      sum = sum + data[i];
    }
    return sum/this.next;
  }
  
  public double min() {
    double min = this.data[0];
    for (int i=1; i<this.next; i++) {
      if (data[i] < min) {
        min = data[i];
      }
    }
    return min;
  }
  
  public double max() {
    double max = this.data[0];
    for (int i=1; i<this.next; i++) {
      if (data[i] > max) {
        max = data[i];
      }
    }
    return max;
  }
  
  public double stdDev() {
    double mean = this.mean();
    double sum = 0.;
    for (int i=0; i<this.next; i++) {
      sum = sum + (data[i] - mean)*(data[i] - mean);
    }
    return Math.sqrt(sum/this.data.length);
  }
  
  public double[] get() {
    double[] new_data = new double[next];
    for (int i=0; i<this.next; i++) {
        new_data[i] = this.data[i];
      }
    return new_data;
  }
  
  public Measurements smooth() {
    double[] new_data = new double[next];
    new_data[0] = this.data[0];
    for (int i=1; i<this.next - 1; i++) {
        new_data[i] = (this.data[i-1] + this.data[i] + this.data[i+1]) / 3;
      }
    new_data[next-1] = this.data[next-1];
    Measurements m = new Measurements(new_data);
    return m;
  }
}