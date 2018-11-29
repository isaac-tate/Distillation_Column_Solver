public interface RootFinding{
  
  /*Class: RootFinding
   * 
   * RootFinding is a generalized root finding interface that holds a calculate method used for different root finding methods
   * All classes that implement RootFinding contain a method of calculate that takes in a Function object and returns a double
   * 
   */
  
  public double calculate(Function f);
  
}