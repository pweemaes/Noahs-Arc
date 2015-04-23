import java.util.*;
/**
 * Backtracking algorithm implemented here
 */
public class BacktrackSolver implements Solver
{
    private final List<Constraint> constraints;
    private final List<Variable> variables;
    public BacktrackSolver(Problem problem)
    {
        constraints = problem.getConstraints();
        variables = problem.getVariables();
    }
    
    // returns true if a valid solution was found. if not returns false
    public boolean runSolver()
    {
        Variable current = getUnassignedVar();
        if (current == null)
            return true;
        for (int i = 0; i < current.getDomain().length; i++)
        {
            int newval = current.getDomain()[i];
            current.setValue(newval);
            if (constraintsSatisfied(constraintsWithAnyVals()))
            {
                if (runSolver())
                    return true;  
            }
            current.setValue(current.getPrevious());
        }
        return false;
    }
    
    public Variable getSolution(int varPos)
    {
        return variables.get(varPos);
    }
    
    // returns first unassigned variable, null means all are assigned
    private Variable getUnassignedVar()
    {
        for (int i = 0; i < variables.size(); i++)
        {
            if (variables.get(i).hasValue())
                continue;
            else 
                return variables.get(i);
        }
        return null;
    }
    
    private boolean constraintHasAnyVals(Constraint c)
    {
        Variable[] vars = c.getVariables();
        for (int i = 0; i < vars.length; i++)
        {
            if (vars[i].hasValue())
                return true;
            else
                continue;
        }
        return false;
    }
    
    private List<Constraint> constraintsWithAnyVals()
    {
        List<Constraint> applicable = new ArrayList<Constraint>();
        for (int i = 0; i < constraints.size(); i++)
        {
            if (constraintHasAnyVals(constraints.get(i)))
                applicable.add(constraints.get(i));
        }
        return applicable;
    }
    
    private boolean constraintsSatisfied(List<Constraint> cList)
    {
        for (int i = 0; i < cList.size(); i++)
        {
            if (cList.get(i).check())
                continue;
            else 
                return false;
        }
        return true;
    }
    
    public void printAll()
    {
        int linebreaker = 0;
        for (int i = 0; i < variables.size(); i++)
        {
            String val = Integer.toString(variables.get(i).getValue());
            System.out.print(val + " ");
            linebreaker++;
            if (linebreaker >= 9)
            {
                System.out.print("\n\n");
                linebreaker = 0;
            }
        }
    }
}