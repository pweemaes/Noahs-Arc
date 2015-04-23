
/**
 * Solver
 * 
 */
public interface Solver
{
    public boolean runSolver();
    
    public Variable getVariable(int varPos);
    
    public void printAll();
}
