import java.util.*;
/**
 * A-Star algorithm implemented here
 * Notes: Okay first of all, Variable class may need a small bit of edits but nothing major, the way Pieter
 *        has already constructed the Variable class it pretty much has all the functionality needed to run 
 *        AStar effectively; there are just some minor naming conventions. Look here:
 *        http://iammyownorg.org/wp-content/uploads/2009/03/mjw-sudokusolver-astar.pdf
 *        
 *        As for this AStarSolver it's very similar to implementing backtracking we just need to score each 
 *        domain value before we set the value. SHOULDN'T BE TOO BAD. TOMO AND I WILL CRUSH TOMORROW.
 *        
 *        "Optimized backtracking by scoring every possibility a Variable can have and thus choosing the 
 *          optimal value to have."
 */
public class AStarSolver implements Solver
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
        for (int i = 0; i < current.getDomain().size(); i++)
        {
            int newval = current.getDomain().get(i);
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
    
    public Variable getVariable(int varPos)
    {
        return variables.get(varPos);
    }
    
    // returns first unassigned variable, null means all are assigned
    public Variable getUnassignedVar()
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
    
    public boolean constraintHasAnyVals(Constraint c)
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
    
    public List<Constraint> constraintsWithAnyVals()
    {
        List<Constraint> applicable = new ArrayList<Constraint>();
        for (int i = 0; i < constraints.size(); i++)
        {
            if (constraintHasAnyVals(constraints.get(i)))
                applicable.add(constraints.get(i));
        }
        return applicable;
    }
    
    public boolean constraintsSatisfied(List<Constraint> cList)
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
    
    public int getVarLength()
    {
        return variables.size();
    }
   
    public void printAll()
    {
    }
}