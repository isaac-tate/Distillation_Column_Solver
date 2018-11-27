public class TrapezoidRule extends Integration{
  /*Class: TrapezoidRule
   * 
   * TrapezoidRule is a child of the abstract parent class Integration, and so contains a calculate method that returns 
   * the integrated value of two arrays
   * TrapezoidRule integrates using the trapezoid method, which does not require the distance between values in the x array to be constant
   * 
   */
  
  public double calculate(double [] x, double [] y){
    
    double area = 0;
    //Used when the distance between x values is not constant
    for(int i = 0;i<x.length-1;i++){
      area = area + 0.5*(y[i]+y[i+1])*Math.abs(x[i+1]-x[i]);
    }
    //Return the integrated value below
    return area;
  }
}
      