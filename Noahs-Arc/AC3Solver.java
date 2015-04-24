import java.util.*;
/**
 * Arc Consistency (3) algorithm implemented here
 */
public class AC3Solver extends BacktrackSolver
{
    private final List<Constraint> constraints;
    private final List<Variable> variables;
    public AC3Solver(Problem problem)
    {
        super(problem);
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
            consistent = ! revise(current);
        }
        return consistent;
    }
    
    private boolean revise(Constraint current)
    {
        Variable[] vars = current.getVariables();
        boolean deleted = false;
        for (int i = 0, domain0 = vars[0].getDomain().size(); i < domain0; i++)
        {
            int initVal = vars[1].getValue();
            for (int j = 0; j < vars[1].getDomain().size(); j++)
            {
                vars[1].setValue(vars[1].getDomain().get(j));
                if (! current.check())
                {
                    vars[1].removeFromDomain(j);
                    deleted = true;
                }  
                vars[1].setValue(initVal);
            }
        }
        return deleted;
    }
    
    
    // returns true if a valid solution was found. if not returns false
    // combines ac3 with backtracking
    public boolean runSolver()
    {
        AC3();
        return super.runSolver();
    }
}