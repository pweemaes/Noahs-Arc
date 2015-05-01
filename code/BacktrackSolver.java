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
        {
            return true;
        }
        for (int i = 0, size = current.getDomain().size(); i < size; i++)
        {
            int newval = current.getDomain().get(i);
            current.setValue(newval);
            if (constraintsSatisfied(constraintsWithAnyVals()))
            {
                if (runSolver())
                {
                    return true;  
                }
            }
            current.setValue(current.getPrevious());
        }
        return false;
    }
    
    public Variable getVariable(int varPos)
    {
        return variables.get(varPos);
    }
    
    // returns first unassigned variable, null means all are assigned
    public Variable getUnassignedVar()
    {
        for (int i = 0, length = variables.size(); i < length; i++)
        {
            if (! variables.get(i).hasValue())
            {
                return variables.get(i);
            }
        }
        return null;
    }
    
    public boolean constraintHasAnyVals(Constraint c)
    {
        Variable[] vars = c.getVariables();
        for (int i = 0, length = vars.length; i < length; i++)
        {
            if (vars[i].hasValue())
            {
                return true;
            }
        }
        return false;
    }
    
    public List<Constraint> constraintsWithAnyVals()
    {
        List<Constraint> applicable = new ArrayList<Constraint>();
        for (int i = 0; i < constraints.size(); i++)
        {
            if (constraintHasAnyVals(constraints.get(i)))
            {
                applicable.add(constraints.get(i));
            }
        }
        return applicable;
    }
    
    public boolean constraintsSatisfied(List<Constraint> cList)
    {
        for (int i = 0, size = cList.size(); i < size; i++)
        {
            if (! cList.get(i).check())
            {  
                return false;
            }
        }
        return true;
    }
    
    public int getVarLength()
    {
        return variables.size();
    }
   
    public void printAll(){}
}