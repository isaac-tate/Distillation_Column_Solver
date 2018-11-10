import java.util.Scanner;
public class EquilibriumData{
  
  double [] eqdata;
  
  public EquilibriumData(){
    Scanner myScan = new Scanner(System.in);
    //currently assuming no negative powers in the equilibrium equation
    System.out.println("Enter the highest power of x within the equilibrium data.");
    //int j = myScan.nextInt();
    int j = 3;
    while(j<0){
      System.out.println("That is not a valid power, please reenter a positive integer or exit the program.");
      j = myScan.nextInt();
    }
    this.eqdata = new double[j+1];
    System.out.println("Enter the coefficients of the equilibrium equation in increasing order.");
    //for(int i = 0;i<j+1;i++){
      //System.out.println("Enter the coefficient of x^"+i+".");
      //this.eqdata[i] = myScan.nextDouble();
    //}
    this.eqdata[0] = 0;
    this.eqdata[1] = 0.01384;
    this.eqdata[2] = 4.068;
    this.eqdata[3] = -13.135;
  }
  public double equilibriumDataY(double x){//calculate y using equilibrium data
    double y = 0;
    for(int i = 0;i<this.eqdata.length;i++){
      y = y+Math.pow(x,i)*this.eqdata[i];
    }
  return y;
  }
}