public class OptimizationFunction implements Function{
  //Function for the optimization of the absorption column
  private AbsorptionColumn mycolumn;
  private double z;
  private double x;
  double l;
  
  public OptimizationFunction(AbsorptionColumn mycolumn, double l){
    this.l = mycolumn.l_2;
    double x = mycolumn.calculateHeightDifference(l);
    this.mycolumn = new AbsorptionColumn(mycolumn);
    this.x = x;
  }
  //Copy constructor
  public OptimizationFunction(OptimizationFunction source){
    this.mycolumn = new AbsorptionColumn(source.mycolumn);
    this.z = source.z;
    this.x = source.x;
  }
  //Mutators
  public double setX(double l){
    double x = this.mycolumn.calculateHeightDifference(l);
    this.mycolumn = new AbsorptionColumn(mycolumn);
    this.z = mycolumn.z;
    return x;
  }
   public double setColumn(AbsorptionColumn mycolumn){
    double x = mycolumn.calculateHeightDifference(this.x);
    this.mycolumn = new AbsorptionColumn(mycolumn);
    this.z = mycolumn.z;
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
    return new AbsorptionColumn(this.mycolumn);
  }
  public double getl_2(){
    return this.l;
  }
}