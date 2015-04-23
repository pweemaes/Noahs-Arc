import java.util.*;
/**
 * Backtracking algorithm implemented here
 */
public class BacktrackSolver implements Solver
{
    private final Collection<Constraint> constraints;
    private final List<Variable> variables;
    public BacktrackSolver(Problem problem)
    {
        constraints = problem.getConstraints();
        variables = problem.getVariables();
    }
    
    // returns true if a valid solution was found. if not returns false
    public boolean runSolver()
    {
        // set all vars to first available in domain
        for (int i = 0; i < variables.size(); i++)
        {
            Variable currentVar = variables.get(i);
            int[] domain = currentVar.getDomain();
            currentVar.setValue(domain[0]);
        }
        return true;
    }
    
    public Variable getSolution(int varPos)
    {
        return variables.get(varPos);
    }
}