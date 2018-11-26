public class TrapezoidRule extends Integration{
  //integration method that uses trapezoid rule to derive
  public double calculate(double [] x, double [] y){
    
    double area = 0;
    //used when the distance between x values is not constant
    for(int i = 0;i<x.length-1;i++){
      area = area + 0.5*(y[i]+y[i+1])*Math.abs(x[i+1]-x[i]);
    }
    return area;
  }
}
      