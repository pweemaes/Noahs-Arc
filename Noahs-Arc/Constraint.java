
/**
 * Constraint class
 * 
 */
public class Constraint
{
    private Variable[] appliesTo;
    
    public Constraint(Variable[] variables)
    {
        appliesTo = variables;
    }

    public boolean check() 
    { return false;
    }
    
    public Variable[] getVariables()
    { return appliesTo;
    }
    
    public Variable getVariable(int variablePosition)
    { return appliesTo[variablePosition];
    }
    
}
