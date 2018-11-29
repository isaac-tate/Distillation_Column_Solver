public class OptimizationFunction implements Function{
  
  /*Class: OptimizationFunction
   * 
   * This class implements the Function interface and so contains a method double setX(double x)
   * This particular class holds the non-linear function for the calculation of the difference between zl and zv
   * 
   * A root finding method may be used with the setX function to determine the root of this function
   * 
   */
  
  private AbsorptionColumn mycolumn;
  private double z;
  private double x;
  double l;
  
  //Constructor
  public OptimizationFunction(AbsorptionColumn mycolumn, double l){
    this.l = mycolumn.getL2();
    double x = mycolumn.calculateHeightDifference(l);
    this.mycolumn = mycolumn.clone();
    this.x = x;
  }
  
  //Copy constructor
  public OptimizationFunction(OptimizationFunction source){
    this.mycolumn = source.mycolumn.clone();
    this.z = source.z;
    this.x = source.x;
  }
  
  //Mutators
  public double setX(double l){
    double x = this.mycolumn.calculateHeightDifference(l);
    this.mycolumn = mycolumn.clone();
    this.z = mycolumn.getZ();
    return x;
  }
  
  public double setColumn(AbsorptionColumn mycolumn){
    double x = mycolumn.calculateHeightDifference(this.x);
    this.mycolumn = mycolumn.clone();
    this.z = mycolumn.getZ();
    return x;
  }
  
  //Accessors
  public double getX(){
    return this.x;
  }
  public double getZ(){
    return this.z;
  }
  public AbsorptionColumn getColumn(){
    return this.mycolumn.clone();
  }
  public double getl_2(){
    return this.l;
  }
}