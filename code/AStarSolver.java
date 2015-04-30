import java.util.*;
/**
 * A-Star algorithm implemented here
 * "Optimized backtracking by scoring every possibility a Variable
 *  can have and thus choosing the optimal value to have."
 */
public class AStarSolver implements Solver
{
    private final List<Constraint> constraints;
    private final List<Variable> variables;
    private final SudokuAStar issue;

    public AStarSolver(SudokuAStar problem)
    {
        constraints = problem.getConstraints();
        variables = problem.getVariables();
        issue = problem;
    }

    // returns true if a valid solution was found. if not returns false
    public boolean runSolver()
    {
        Variable current = getUnassignedVar();

        if (current == null)
        {
            return true;
        }

        for (int i = 0; i < current.getDomain().size(); i++)
        {
            List<Integer> oldDomain = current.getDomain();
            int newval = oldDomain.get(i);
            current.setValue(newval);
            current.setDomain(i);

            if (constraintsSatisfied(constraintsWithAnyVals()))
            {
                if (runSolver())
                {
                    return true;
                }
            }

            current.setDomain(oldDomain);
            current.setValue(current.getPrevious());            
        }
        return false;
    }

    public Variable getVariable(int varPos)
    {
        return variables.get(varPos);
    }

    public Variable getUnassignedVar()
    {
        if (variables.size() == 0)
        {
            return null;
        }

        boolean allset = true;
        Variable best = variables.get(0);

        // check the score of each variable and get the lowest cost/score
        for (int i = 0; i < variables.size(); i++)
        {            
            Variable curr = variables.get(i);

            if (! curr.hasValue())
            {
                if (h(curr) + g(curr) >= h(best) + g(best))
                {
                    best = curr;
                }
                allset = false;
            }
        }

        if (allset || best.hasValue())
        {
            return null;
        }
        else
        {
            return best;
        }
    }

    public boolean constraintHasAnyVals(Constraint c)
    {
        Variable[] vars = c.getVariables();

        for (int i = 0; i < vars.length; i++)
        {
            if (vars[i].hasValue())
            {
                return true;
            }
            else
            {
                continue;
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
        for (int i = 0; i < cList.size(); i++)
        {
            if (cList.get(i).check())
            {
                continue;
            }
            else
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
   
    public void printAll() {}
    
    // first cost function gets how many values are left in current domain
    public int h(Variable n)
    {
        int h =  n.getDomain().size();
        return h;
    }
    
    // second cost function is based on open slots
    public int g(Variable n)
    {       
        int id = n.getID();
        int row_num = id / issue.getSize();
        int col_num = id % issue.getSize();
        int box_num = ((col_num / issue.getSqrtSize()) * 3 +
                       (row_num / issue.getSqrtSize()));
        List<Integer> buffer = new ArrayList<Integer>();

        for (int i = 0; i < issue.getRows()[row_num].length; i++)
        {
           Variable slot = issue.getRows()[row_num][i];

           if (!(slot.hasValue()))
           { 
               buffer.add(slot.getID());
           }
        }

        for (int i = 0; i < issue.getCols()[col_num].length; i++)
        {
           Variable slot = issue.getCols()[col_num][i];
           if (!(slot.hasValue()))
           {
               buffer.add(slot.getID());
           }
        }

        for (int i = 0; i < issue.getBoxes()[box_num].length; i++)
        {
           Variable slot = issue.getBoxes()[box_num][i];
           if ((!(issue.getRows()[row_num][i].hasValue())) &&
               (!(buffer.contains(slot.getID()))))
           {
               buffer.add(slot.getID());
           }
        }

        int cost = 27 - buffer.size();
        return cost;
    }
}