import java.util.*;
/**
 * Arc Consistency (3) algorithm implemented here
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
        while (arcs.size() > 0)
        {
            Constraint current = arcs.get(0);
            arcs.remove(current);
            if (revise(current))
            {
                //add opposite constraint to arcs... yikes
                arcs.add(current);
            }
            else 
                consistent = false;
        }
        return consistent;
    }
    
    private boolean revise(Constraint current)
    {
        Variable var0 = current.getVariable(0);
        Variable var1 = current.getVariable(1);
        boolean deleted = false;
        int initVal0 = var0.getValue();
        for (int i = 0; i < var0.getDomain().size(); i++)
        {
            int newVal0 = var0.getDomain().get(i);
            var0.setValue(newVal0);
            int initVal1 = var1.getValue();
            for (int j = 0; j < var1.getDomain().size(); j++)
            {
                int newVal1 = var1.getDomain().get(j);
                var1.setValue(newVal1);
                if (current.check())
                {
                    var1.removeFromDomain(j);
                    deleted = true;
                }  
                var1.setValue(initVal1);
            }
            var0.setValue(initVal0);
        }
        var0.printDomain();
        var1.printDomain();
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