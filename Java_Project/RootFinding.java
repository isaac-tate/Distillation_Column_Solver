import java.util.Scanner;
import java.math.*;

public class RootFinding{
  
  int iterations;
  double delta_x;
  AbsorptionColumn myColumn;
  double error;
  double [] coefficients=equilibriumData();
  double [] xal;
  double [] yag;
  Data [] data;
  double [] xai;
  double [] yai;
  double [] dzv;
  double [] dzl;
  double [] zv;
  double [] zl;
  
  RootFinding(AbsorptionColumn myColumn, int iterations){
    this.iterations = iterations;
    this.myColumn = myColumn;
    this.delta_x = myColumn.x_a1/iterations;//calculate delta x
    this.error = 0.001;//maybe have user set it but for now make constant
    //this.coefficients = this.equilibriumData();//determine equilibrium coefficients
    this.xal = new double [iterations];
    for(int j = 0;j<iterations;j++){
      xal[j] = myColumn.x_a1-this.delta_x*j;//calculate an array of xal values at which each height will be calculated
    }
    this.yag = new double [iterations];
    for(int k = 0;k<iterations;k++){
      this.yag[k] = equilibriumDataY(coefficients,xal[k]);//same with yag using eqbm data
    }
    this.data = new Data[iterations];
    for(int l = 0;l<iterations;l++){
      this.data[l] = new Data(xal[l], yag[l], myColumn);//solve for k values etc;//create array to hold L, V, G, MW, k values
    }
    this.xai = new double [iterations];
    this.xai = this.ridders();//solve for xai values using the ridders root finding method
    this.yai = new double [iterations];
    for(int i = 0;i<iterations;i++){//determine yai values with the eqbm coefficients
      this.yai[i] = equilibriumDataY(coefficients, xai[i]);
    }
    this.dzv = new double [iterations];
    for(int m = 0;m<iterations;m++){//calculate the incremental height values
      this.dzv[m] = data[m].getData(3)/(data[m].getData(7)*myColumn.crossArea/(((1-yai[m])-(1-yag[m]))/Math.log((1-yai[m])/(1-yag[m])))*(1-yag[m])*(yag[m]-yai[m]));
    }
    this.dzl = new double [iterations];
    for(int n = 0;n<iterations;n++){//calculate the incremental height values
      this.dzl[n] = data[n].getData(2)/(data[n].getData(6)*myColumn.crossArea/(((1-xal[n])-(1-xai[n]))/Math.log((1-xal[n])/(1-xai[n])))*(1-xal[n])*(xai[n]-xal[n]));
    }
    double [] delyag = new double [iterations-1];
    for(int o = 0;o<iterations-1;o++){//determine the difference in yag values 
      delyag[o] = yag[o]-yag[o+1];
    }
    this.zv = new double [iterations];
    this.zv[0] = 0;
    this.zl = new double [iterations];
    this.zl[0] = 0;
    for(int p = 0;p<iterations-1;p++){//calculate zv using incremental heights
        this.zv[p+1] = zv[p]+delyag[p]*dzv[p];
    }
    for(int q = 0;q<iterations-1;q++){//calculate zl using incremental heights
        this.zl[q+1] = zl[q]+delta_x*dzl[q];
    }
  }
  
  public double [] ridders(){
    double xu = 1;//set upper bound
    double xl = 0;//set lower bound
    double x = (xl+xu)*0.5;//set initial guess as midpoint
    double e = 1000;//set initial error to 1000
    double xal = myColumn.x_a1; //set initial x_al
    double yag = myColumn.y_a1; //set initial y_ag
    int i = 0;
    
    double fxl, fxu, sign, fx, xnew, y, m, b, xm, fxnew, fxm;
    
    do{
      
    fxl = function(data[i].getData(6),data[i].getData(7),yag,xl,xal,coefficients);//find f(xl)
    fxu = function(data[i].getData(6),data[i].getData(7),yag,xu,xal,coefficients);//find f(xu)
    
    
    sign = fxl-fxu;//determine the sign of f(xl)-f(xu) to find new x
    if(sign>0){
      sign = 1;
    }
    else if (sign<0){
      sign = -1;
    }
    else{
      sign = 0;
    }
    
    fx = function(data[i].getData(6),data[i].getData(7),yag,x,xal,coefficients);//find f(x) where x is guess
    
    xnew = x+(x-xl)*(sign*fx)/(Math.pow(Math.pow(fx,2)-fxl*fxu,0.5));//calculate new x
    
    fxnew = function(data[i].getData(6),data[i].getData(7), yag,xnew,xal,coefficients);//calculate f(new x values)
    
    e = Math.abs((xnew-x)/xnew);//find error
    
    x = xnew;//set x = newly found x
    
    if(xnew<x){//determine new bounds based on position of new x within the numerical line
      if(fxl*fxnew<0){
        xu = xnew;}
      else if(fxnew*fx<0){
        xu = x;
        xl = xnew;
      }
    }
    else if(xnew>x){
      if(fx*fxnew<0){
        xu = xnew;
        xl = x;
      }
      else if(fxnew*fxu<0){
        xl = xnew;
      }
    }
    //calculate new yag and new xal
    yag = ((myColumn.lPrime/myColumn.vPrime)*(((xal-delta_x)/(1-(xal-delta_x)))-(xal/(1-xal)))+(yag/(1-yag)))/((myColumn.lPrime/myColumn.vPrime)*(((xal-delta_x)/(1-(xal-delta_x)))-(xal/(1-xal)))+(yag/(1-yag))+1);
    xal = xal-delta_x; 
    xai[i] = xnew;
    i++;
      
    }while(e>error&&i<iterations);//continue until e is less than the desired error and/or more than the desired number of iterations have occured
    
      return xai;//return the new x value
    
  }
  
  public double function(double kxa, double kya, double yag, double x, double xal, double []coefficients){
    double y = this.equilibriumDataY(coefficients, x);//calculate y using the equilibrium data
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
    double[] coefficients = new double[j+1];
    System.out.println("Enter the coefficients of the equilibrium equation in increasing order.");
    for(int i = 0;i<j+1;i++){
      System.out.println("Enter the coefficient of x^"+i+".");
      coefficients[i] = myScan.nextDouble();
    }
    return coefficients;
  } 
  
  public double equilibriumDataY(double [] coefficients, double x){//calculate y using equilibrium data
    double y = 0;
    for(int i = 0;i<coefficients.length;i++){
      y = y+Math.pow(x,i)*coefficients[i];
    }
  return y;
  }
  
  /*public double [] data(double xal, double yag, AbsorptionColumn myColumn){
   
   double [] data = new double[8];
   data[0]= (xal*myColumn.fluid.mW_A + (1-xal)*myColumn.fluid.mW_L); //Calculate MW_av,L
   data[1] = (yag*myColumn.fluid.mW_A + (1-yag)*myColumn.fluid.mW_V); //Calculate MW_av,V
   data[2] = myColumn.lPrime/(1-xal); //Calculating L
   data[3] = myColumn.vPrime/(1-yag); //Calculating V
   data[4] = data[2]*data[0]/myColumn.crossArea;  //Calculating G_L
   data[5] = data[3]*data[1]/myColumn.crossArea; //Calculating G_V
   //Calculating k'xa
   data[6] = Math.pow(((myColumn.crossArea/data[2]) * (0.357/myColumn.packing.fpPacking) * Math.pow( (myColumn.fluid.nsc_L/372), 0.5) * Math.pow((data[4]/myColumn.fluid.mu_L)/(6.782/0.0008937), 0.3)), -1);
   //Calculating k'ya
   data[7] = Math.pow(((myColumn.crossArea/data[3]) * (0.226/myColumn.packing.fpPacking) * Math.pow((myColumn.fluid.nsc_V/0.660), 0.5) * Math.pow((data[4]/6.782), -0.5) * Math.pow((data[5]/0.678), 0.35)), -1);
   return data;
    
  }*/
}