
/**
 * Write a description of class Constraint here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Constraint
{
    private Variable[] scope;

    public Constraint(Variable first, Variable second)
    {
    }

    public boolean check() 
    { return false;
    }
    
    public int getValue(int variablePosition)
    { return 0;
    }
    
    public void revise(int variablePosition, int level)
    {
    }
}
