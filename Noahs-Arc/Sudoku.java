import java.util.*;
/**
 * Sudoku
 */
public class Sudoku implements Problem
{
    private final static int SIZE = 9;
    private final Collection<Constraint> constraints;
    private final List<Variable> variables;
    
    public Sudoku()
    {
        constraints = new ArrayList<Constraint>();
        variables = new ArrayList<Variable>();
        generate();
    }
        
    @Override
    public void generate() 
    {
        // domain goes from 1 to SIZE (9)
        final int[] domain = new int[SIZE];
        for (int i = 1; i <= SIZE; i++)
        {
            domain[i - 1] = i;
        }
        
        // makes 0-80 variables with domain 1-9
        for (int i = 0; i < (SIZE * SIZE); i++)
        {
            variables.add(new Variable(domain, i));
        }
        
        Variable[][] rows = new Variable[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++)
        {
            for (int j = 0; j < SIZE; j++)
            {
                rows[i][j] = variables.get(i * 9 + j);
            }
        }
        
        Variable[][] cols = new Variable[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++)
        {
            for (int j = 0; j < SIZE; j++)
            {
                cols[i][j] = variables.get(i + j * 9);
            }
        }
        
        Variable[][] boxes = new Variable[SIZE][SIZE];
        for (int boxrow = 0; boxrow < 3; boxrow++)
        {
            for (int boxcol = 0; boxcol < 3; boxcol++)
            {   
                for (int k = 0; k < 3; k++)
                {
                    for (int l = 0; l < 3; l++)
                    {
                        int v = (k * 9 + l) + (boxrow * 27 + boxcol * 3);
                        int w = k + l * 3;
                        boxes[boxrow + boxcol * 3][w] = variables.get(v);
                    }
                }
            }
        }
        
        for (int i = 0; i < SIZE; i++)
        {
            constraints.add(new Constraint(rows[i])
            {
                @Override
                public boolean check()
                {
                    // Set.add returns false if the value already exists in the set.
                    // we can use this to check if all values in the constraint are different
                    Set<Integer> seen = new HashSet<Integer>();
                    for (int i = 0; i < SIZE; i++)
                    {
                        if (seen.add(getVariable(i).getValue()))
                            continue;
                        else
                            return false;
                    }
                    return true;
                }
            });
            constraints.add(new Constraint(cols[i])
            {
                @Override
                public boolean check()
                {
                    // Set.add returns false if the value already exists in the set.
                    // we can use this to check if all values in the constraint are different
                    Set<Integer> seen = new HashSet<Integer>();
                    for (int i = 0; i < SIZE; i++)
                    {
                        if (seen.add(getVariable(i).getValue()))
                            continue;
                        else
                            return false;
                    }
                    return true;
                }
            });
            constraints.add(new Constraint(boxes[i])
            {
                @Override
                public boolean check()
                {
                    // Set.add returns false if the value already exists in the set.
                    // we can use this to check if all values in the constraint are different
                    Set<Integer> seen = new HashSet<Integer>();
                    for (int i = 0; i < SIZE; i++)
                    {
                        if (seen.add(getVariable(i).getValue()))
                            continue;
                        else
                            return false;
                    }
                    return true;
                }
            });
        }
    }
    
    @Override
    public Collection<Constraint> getConstraints() {
        return constraints;
    }
    @Override
    public List<Variable> getVariables() {
        return variables;
    }
    public static void main(String[] args)
    {
        final Problem sudokuProblem = new Sudoku();
        final Solver solver = new BacktrackSolver(sudokuProblem);
        solver.runSolver();
        
        /** need better way to display
        for (Variable v : sudokuProblem.getVariables()) {
            System.out
                    .println(v + ": " + solver.getSolution());
        }
        */
    }
}

