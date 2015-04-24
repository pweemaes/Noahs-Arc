import java.util.*;
/**
 * Arc Consistency (3 algorithm implemented here
 */
public class AC3Solver implements Solver
{
    private final List<Constraint> constraints;
    private final List<Variable> variables;
    public AC3Solver(Problem problem)
    {
        constraints = problem.getConstraints();
        variables = problem.getVariables();
    }
    
    private boolean AC3() 
    {
        List<Constraint> arcs = constraints;
        boolean consistent = true;
        while (arcs.size() > 0 && consistent)
        {
            Constraint current = arcs.get(0);
            arcs.remove(current);
            consistent = revise(current);
        }
        return consistent;
    }
    
    private boolean revise(Constraint current)
    {
        Variable[] vars = current.getVariables();
        boolean deleted = false;
        for (int i = 0; i < vars.length; i++)
        {
            Variable currentVar = vars[i];
            for (int j = 0; j < currentVar.getDomain().size(); j++)
            {
                for (int k = 0; k < vars.length; k++)
                {
                    if (k != i)
                    {
                        Variable currentChangingVar = vars[k];
                        int changingVarInit = currentChangingVar.getValue();
                        for (int l = 0; l < currentChangingVar.getDomain().size(); l++)
                        {
                            currentChangingVar.setValue(l);
                            for (int m = 0; m < 
                            ArrayList<Constraint> toTest = new ArrayList<Constraint>();
                            toTest.add(current);
                            if (! constraintsSatisfied(toTest))
                            {
                                currentChangingVar.setValue(changingVarInit);
                                currentChangingVar.removeFromDomain(l);
                                deleted = true;
                            }  
                            currentChangingVar.setValue(changingVarInit);
                        }
                    }
                }
            }
        }
        return deleted;
    }
    
    // returns true if a valid solution was found. if not returns false
    public boolean runSolver()
    {
        AC3();
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
    
    public int getVarLength()
    {
        return variables.size();
    }
   
    public void printAll()
    {
    }
}