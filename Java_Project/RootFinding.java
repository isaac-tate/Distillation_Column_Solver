import java.util.Scanner;
import java.math.*;

public class RootFinding{
  
  public double ridders(double xal, double yag, double kxa, double kya, double error){
    
    double xu = 1;
    double xl = 0;
    double x = (xl+xu)*0.5;
    double e = 1000;
    
    double fxl, fxu, sign, fx, xnew, y, m, b, xm, fxnew, fxm;
    double[]coefficients = this.equilibriumData();
    
    do{
    fxl = function(kxa,kya,yag,xl,xal,coefficients);
    fxu = function(kxa,kya,yag,xu,xal,coefficients);
    
    sign = fxl-fxu;
    if(sign>0){
      sign = 1;
    }
    else if (sign<0){
      sign = -1;
    }
    else{
      sign = 0;
    }
    
    xm = (xl+xu)*0.5;
    fxm = function(kxa,kya,yag,xm,xal,coefficients);
    
    xnew = xm+(xm-xl)*(sign*fxm)/(Math.pow(Math.pow(fxm,2)-fxl*fxu,0.5));
    
    fxnew = function(kxa,kya,yag,xnew,xal,coefficients);
    
    e = Math.abs((xnew-x)/xnew);
    
    x = xnew;
    
    if(xnew<xm){
      if(fxl*fxnew<0){
        xu = xnew;}
      else if(fxnew*fxm<0){
        xu = xm;
        xl = xnew;
      }
    }
    else if(xnew>xm){
      if(fxm*fxnew<0){
        xu = xnew;
        xl = xm;
      }
      else if(fxnew*fxu<0){
        xl = xnew;
      }
    }
      
    }while(e>error);
    
      return xnew;
    
  }
  
  public double function(double kxa, double kya, double yag, double x, double xal, double []coefficients){
    double y = this.equilibriumDataY(coefficients, x);
    double m = this.slope(kxa, kya, xal, yag, x, y); 
    double b = this.intercept(yag,y,xal,x,kxa,kya);
    double f = y-(m*x+b);
    return f;
  }
  
  public double slope(double kxa, double kya, double xal, double yag, double x, double y){
    double lmlconc =((1-xal)-(1-x))/Math.log((1-xal)/(1-x));///log mean liquid concentration at interface
    double lmgconc =((1-y)-(1-yag))/Math.log((1-y)/(1-yag));//log mean gas concentration at interface
    double m = -((kxa/lmlconc)/(kya/lmgconc));
    return m;
  }
  
  public double intercept(double yag,double y,double xal, double x, double kxa, double kya){
    double m = this.slope(kxa,kya,xal,yag,x,y);
    double b = 0.5*(yag+y-m*(xal+x));
    return b;
  }
  
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