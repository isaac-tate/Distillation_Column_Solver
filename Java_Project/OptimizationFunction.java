public class OptimizationFunction extends Function{
  
  private AbsorptionColumn mycolumn;
  private double z;
  private double x;
  
  public OptimizationFunction(AbsorptionColumn mycolumn, double l){
    double x = mycolumn.recalculateHeightDifference(l);
    this.mycolumn = new AbsorptionColumn(mycolumn);
    this.x = x;
  }
  public double setX(double l){
    double x = this.mycolumn.recalculateHeightDifference(l);
    this.mycolumn = new AbsorptionColumn(mycolumn);
    this.z = mycolumn.z;
    return x;
  }
   public double setColumn(AbsorptionColumn mycolumn){
    double x = mycolumn.recalculateHeightDifference(this.x);
    this.mycolumn = new AbsorptionColumn(mycolumn);
    this.z = mycolumn.z;
    return x;
  }
  public double getX(){
    return this.x;
  }
  public double getZ(){
    return this.z;
  }
  public AbsorptionColumn getColumn(){
    return new AbsorptionColumn(this.mycolumn);
  }
}