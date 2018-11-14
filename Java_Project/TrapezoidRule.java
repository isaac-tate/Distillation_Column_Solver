public class TrapezoidRule implements Integration{
  
  public double calculate(double [] x, double [] y){
    
  double tempx=0;
    double tempy=0;
    for(int i = 0;i<x.length;i++){//bubble sort to make sure the array is in increasing order
      for(int j = 1;j<(x.length-1);j++){
        if(x[j-1]>x[j]){
          tempx = x[j-1];
          x[j-1] = x[j];
          x[j] = tempx;
          tempy = y[j-1];
          y[j-1] = y[j];
          y[j] = tempy;
        }
      }
    }
    double area = 0;
    
    for(int i = 0;i<x.length-1;i++){
      area += (y[i]+y[i+1])/2*(x[i+1]-x[i]);
    }
    return area;
  }
}
      