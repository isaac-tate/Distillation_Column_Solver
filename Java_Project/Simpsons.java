public class Simpsons extends Integration{
  
  /*Class: Simpsons
   * 
   * Simpsons is a child of the abstract parent class Integration, and so contains a calculate method that returns 
   * the integrated value of two arrays
   * Simpsons integrates using the simpsons method, which requires the distance between values in the x array to be constant
   * 
   */
  
  public double calculate(double [] x, double [] y){
    
    double intapprox,a,b,delx,b2;
    
    //Bubble sort to make sure the array is in increasing order
    double tempx=0;
    double tempy=0;
    for(int i = 0;i<x.length;i++){
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
    
    a = x[0];
    b = x[x.length-1];
    
    //If the array length is not even integrate using the following approach
    if(x.length%2!=0){
      delx = (b-a)/(x.length-1);
      intapprox = y[0];
      
      for(int i = 1;i<x.length-1;i++){
        if(i%2 == 0){
          intapprox = intapprox+4*y[i];
        }
        else{
          intapprox = intapprox+2*y[i];
        }
      }
      intapprox = intapprox+y[y.length-1];
      intapprox = intapprox*delx/3;
    }
    //If the array length is even integrate using the following approach
    else{
      b2 = x[x.length-3];
      delx = (b2-a)/(x.length-4);
      intapprox = y[0];
      
      for(int i = 1;i<x.length-4;i++){
        if(i%2 == 0){
          intapprox = intapprox+4*y[i];
        }
        else{
          intapprox = intapprox+2*y[i];
        }
      }
      intapprox = intapprox+y[y.length-3];
      intapprox = intapprox*delx/3;
      
      intapprox = intapprox+(3/8)*(y[y.length-4]+3*y[y.length-3]+3*y[y.length-2]+y[y.length-1]);
      
    }
    //Return the integrated value below
    return intapprox;
  }
}