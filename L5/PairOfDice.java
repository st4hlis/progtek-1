public class PairOfDice {
  private Dice d1;
  private Dice d2;
  
  public PairOfDice () {
    d1 = new Dice(6);
    d2 = new Dice(6);
  }
  
  public PairOfDice (int nSides) {
    d1 = new Dice(nSides);
    d2 = new Dice(nSides);
  }
  
  public void roll () {
    d1.roll();
    d2.roll();
  }
  
  public int[] getValues () {
    int[] val = new int[2];
    val[0] = d1.getValue();
    val[1] = d2.getValue();
    return val;
  }
  
  public boolean isPair () {
    if (d1.getValue() == d2.getValue()) {
      return true;
    } else {
        return false;
    }
  }
  
  public String toString () {
    return d1.toString() + " " + d2.toString();
  }
}
  
  