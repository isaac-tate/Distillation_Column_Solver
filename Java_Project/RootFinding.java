import java.util.Scanner;
import java.math.*;

public class RootFinding{
  
  public double [] equilibriumData(){//enter the equilibrium equation data
    Scanner myScan = new Scanner(System.in);
    //currently assuming no negative powers in the equilibrium equation
    System.out.println("Enter the highest power of x within the equilibrium data.");
    int j = myScan.nextInt();
    while(j<0){
      System.out.println("That is not a valid power, please reenter a positive integer or exit the program.");
      j = myScan.nextInt();
    }
    double[] coefficients = new double[j];
    System.out.println("Enter the coefficients of the equilibrium equation in increasing order.");
    for(int i = 0;i<j;i++){
      System.out.println("Enter the coefficient of x^"+i+".");
      coefficients[i] = myScan.nextDouble();
    }
    return coefficients;
  } 
  
  public double equilibriumDataY(double [] coefficients, double x){
    double y = 0;
    for(int i = 0;i<coefficients.length;i++){
      y = y+Math.pow(x,i)*coefficients[i];
    }
  return y;
  }
}