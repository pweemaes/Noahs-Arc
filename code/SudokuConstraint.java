
/**
 * SudokuConstraint 
 * 
 */
public class SudokuConstraint extends Constraint
{
    private Variable first;
    private Variable second;
    
    public SudokuConstraint(Variable[] variables)
    {
        super(variables);
    }
    
    public SudokuConstraint(Variable one, Variable two)
    {
        super(one, two);
    }

    public boolean check()
    {
        // we can use this to check if all values in the constraint are different
        return (! getVariable(0).hasValue() || ! getVariable(1).hasValue()) ||
        (getVariable(0).getValue() != getVariable(1).getValue());
    }    
}
