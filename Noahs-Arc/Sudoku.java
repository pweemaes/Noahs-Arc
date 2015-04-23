import java.util.*;
/**
 * Sudoku
 */
public class Sudoku implements Problem
{
    private final static int SIZE = 9;
    private final List<Constraint> constraints;
    private final List<Variable> variables;
    
    public Sudoku()
    {
        constraints = new ArrayList<Constraint>();
        variables = new ArrayList<Variable>();
        
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
        generate();
    }
    
    public Sudoku(int[] board)
    {
        constraints = new ArrayList<Constraint>();
        variables = new ArrayList<Variable>();
        
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
        
        for (int i = 0; i < board.length; i++)
        {
            if (board[i] != 0)
                variables.get(i).setValue(board[i]);
        }
        generate();
    }
        
    @Override
    public void generate() 
    {
        
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
                    // we can use this to check if all values in the constraint are different
                    Set<Integer> seen = new HashSet<Integer>();
                    int needed = SIZE;
                    for (int i = 0; i < SIZE; i++)
                    {
                        // if we have no value, we must pretend we see a new value anyways
                        if (getVariable(i).hasValue())
                            seen.add(getVariable(i).getValue());  
                        else
                            needed--;
                    }
                    return seen.size() >= needed;
                }
            });
            
            constraints.add(new Constraint(cols[i])
            {
                @Override
                public boolean check()
                {
                    // we can use this to check if all values in the constraint are different
                    Set<Integer> seen = new HashSet<Integer>();
                    int needed = SIZE;
                    for (int i = 0; i < SIZE; i++)
                    {
                        // if we have no value, we must pretend we see a new value anyways
                        if (getVariable(i).hasValue())
                            seen.add(getVariable(i).getValue());  
                        else
                            needed--;
                    }
                    return seen.size() >= needed;
                }
            });
            
            constraints.add(new Constraint(boxes[i])
            {
                @Override
                public boolean check()
                {
                    // we can use this to check if all values in the constraint are different
                    Set<Integer> seen = new HashSet<Integer>();
                    int needed = SIZE;
                    for (int i = 0; i < SIZE; i++)
                    {
                        // if we have no value, we must pretend we see a new value anyways
                        if (getVariable(i).hasValue())
                            seen.add(getVariable(i).getValue());  
                        else
                            needed--;
                    }
                    return seen.size() >= needed;
                }
            });
            
        }
    }
    
    @Override
    public List<Constraint> getConstraints() {
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
        solver.printAll();
        System.out.print("\n======================\n");
        if (solver.runSolver())
            System.out.print("========SOLVED========\n");
        else 
            System.out.print("=====NO SOLUTION======\n");
        solver.printAll();
        
        System.out.print("\n========NEW BOARD========\n\n");
        
        int[] board2 = new int[81];
        board2[0] = 4;
        board2[1] = 5;
        board2[2] = 8;
        
        final Problem sudokuProblem2 = new Sudoku(board2);
        final Solver solver2 = new BacktrackSolver(sudokuProblem2);
        solver2.printAll();
        System.out.print("\n======================\n");
        if (solver2.runSolver())
            System.out.print("========SOLVED========\n");
        else 
            System.out.print("======NO SOLUTION=====\n");
        solver2.printAll();
    }
}

