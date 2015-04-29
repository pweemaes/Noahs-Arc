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
    private final Problem issue;
    
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
            return true;
        for (int i = 0; i < current.getDomain().size(); i++)
        {
            int newval = current.getDomain().get(i);
            current.setValue(newval);
            if (constraintsSatisfied(constraintsWithAnyVals()))
            {
                // REMOVE FROM DOMAIN
                current.removeFromDomain(newval); 
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
        boolean done = true;
        Variable best = variables.get(0);
        // check the score of each variable and get the lowest cost/score
        for (int i = 0; i < variables.size(); i++)
        {            
            Variable curr = variables.get(i);
            
            if (curr.hasValue())
            {
                continue;
            }
            else 
            {
                if ((h(curr) + g(curr)) < (h(best) + g(best)))
                {
                    best = curr;
                }
                done = false;
                continue;
            }
        }
        if (done)
          return null;
        else
          return best;
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
    
    // first cost function gets how many values are left in current domain
    public int h(Variable n)
    {
        n.getDomain().size();
    }
    
    // second cost function is based on open slots
    public int g(Variable n)
    {
       int size = (issue.getSize() - 1) * 2 - ((issue.getSqrtSize() - 1) * (issue.getSqrtSize() - 1));
       
       int id = Integer.parseInt(n.getName());
       int row_num = id / issue.getSize();
       int col_num = id % issue.getSize();
       int box_num = (col_num / issue.getSqrtSize()) + (row_num / issue.getSqrtSize()) * 3;
       
       List<int> buffer;
       for (int i = 0; i < n.getRows()[row_num].length; i++)
       {
           int slot = n.getRows()[row_num][i];
           if (!(slot.has_value()))
           { 
               buffer.add(Integer.parseInt(slot.getName()));
           }
       }
       for (int i = 0; i < n.getCols()[col_num].length; i++)
       {
           int slot = n.getCols()[col_num][i];
           if (!(slot.has_value()))
           { 
               buffer.add(Integer.parseInt(slot.getName()));
           }
       }
       for (int i = 0; i < n.getBoxes()[box_num].length; i++)
       {
           int slot = n.getBoxes()[box_num][i];
           if ((!(n.getRows()[row_num][i].has_value())) && (!(buffer.contains(Integer.parseInt(slot.getName())))))
           {
               buffer.add(Integer.parseInt(slot.getName()));
           }
       }
       
       int cost = size - buffer.size();
       return cost;
    }
}