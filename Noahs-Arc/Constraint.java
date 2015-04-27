
/**
 * Constraint class
 * 
 */
public class Constraint
{
    private Variable first;
    private Variable second;
    
    public Constraint(Variable[] variables)
    {
        Variable first = variables[0];
        Variable second = variables[1];
    }
    
    public Constraint(Variable one, Variable two)
    {
        first = one;
        second = two;
    }

    public boolean check() 
    { return false;
    }
    
    public Variable[] getVariables()
    { return new Variable[]{first, second};
    }
    
    
    public Variable getVariable(int variablePosition)
    {   
        if (variablePosition == 1)
            return second;
        return first;
    }
    
}
