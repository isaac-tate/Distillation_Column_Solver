import java.util.Scanner;
public class EquilibriumData{
  
  double [] eqdata;
  
  public EquilibriumData(){
    Scanner myScan = new Scanner(System.in);
    //currently assuming no negative powers in the equilibrium equation
    System.out.println("Enter the highest power of x within the equilibrium data.");
    int j = myScan.nextInt();
    while(j<0){
      System.out.println("That is not a valid power, please reenter a positive integer or exit the program.");
      j = myScan.nextInt();
    }
    double[] coefficients = new double[j+1];
    System.out.println("Enter the coefficients of the equilibrium equation in increasing order.");
    for(int i = 0;i<j+1;i++){
      System.out.println("Enter the coefficient of x^"+i+".");
      coefficients[i] = myScan.nextDouble();
    }
    this.eqdata=new double [coefficients.length];
    for(int i = 0;i<coefficients.length;i++){
      this.eqdata[i] = coefficients[i];
    }
  }
  public double equilibriumDataY(double x){//calculate y using equilibrium data
    double y = 0;
    for(int i = 0;i<this.eqdata.length;i++){
      y = y+Math.pow(x,i)*this.eqdata[i];
    }
  return y;
  }
}