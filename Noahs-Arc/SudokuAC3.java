import java.util.*;
/**
 * SudokuAC3
 */
public class SudokuAC3 implements Problem
{
    private final static int SIZE = 9;
    private final static int SQRTSIZE = 3;
    private final List<Constraint> constraints;
    private final List<Variable> variables;
    static double start = 0.0;
    static double stop = 0.0;
    
    public SudokuAC3()
    {
        constraints = new ArrayList<Constraint>();
        variables = new ArrayList<Variable>();
        
        // makes 0-80 variables with domain 1-SIZE
        for (int i = 0; i < (SIZE * SIZE); i++)
        {
            // domain goes from 1 to SIZE
            List<Integer> domain = new ArrayList<Integer>();
            for (int j = 1; j <= SIZE; j++)
            {
                domain.add(j);
            }
            variables.add(new Variable(domain, i));
        }
        generate();
    }
    
    public SudokuAC3(int[] board)
    {
        constraints = new ArrayList<Constraint>();
        variables = new ArrayList<Variable>();
        
        // makes 0-80 variables with domain 1-SIZE
        for (int i = 0; i < (SIZE * SIZE); i++)
        {
            // domain goes from 1 to SIZE
            List<Integer> domain = new ArrayList<Integer>();
            for (int j = 1; j <= SIZE; j++)
            {
                domain.add(j);
            }
            variables.add(new Variable(domain, i));
        }
        
        for (int i = 0; i < board.length; i++)
        {
            if (board[i] != 0)
            {
                Variable current = variables.get(i);
                current.setValue(board[i]);
                List<Integer> domain = new ArrayList<Integer>();
                domain.add(board[i]);
                current.setDomain(domain);
            }
        }
        generate();
    }
        
    @Override
    public void generate() 
    {              
        for (int i = 0; i < SIZE; i++)
        {
            makeBinaryConstraints(i);
        }
    }
    
    private void makeBinaryConstraints(int n)
    {
        Variable[][] rows = new Variable[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++)
        {
            for (int j = 0; j < SIZE; j++)
            {
                rows[i][j] = variables.get(i * SIZE + j);
            }
        }
        
        Variable[][] cols = new Variable[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++)
        {
            for (int j = 0; j < SIZE; j++)
            {
                cols[i][j] = variables.get(i + j * SIZE);
            }
        }
        
        Variable[][] boxes = new Variable[SIZE][SIZE];
        for (int boxrow = 0; boxrow < SQRTSIZE; boxrow++)
        {
            for (int boxcol = 0; boxcol < SQRTSIZE; boxcol++)
            {   
                for (int k = 0; k < SQRTSIZE; k++)
                {
                    for (int l = 0; l < SQRTSIZE; l++)
                    {
                        int v = (k * SIZE + l) + (boxrow * SIZE * SQRTSIZE + boxcol * SQRTSIZE);
                        int w = k + l * SQRTSIZE;
                        boxes[boxrow + boxcol * SQRTSIZE][w] = variables.get(v);
                    }
                }
            }
        }
        
        for (int i = 0; i < SIZE; i++)
        {   
            for (int j = i + 1; j < SIZE; j++)
            {
                constraints.add(new Constraint(rows[n][i], rows[n][j])
                {
                    @Override
                    public boolean check()
                    {
                        // we can use this to check if all values in the constraint are different
                        return (! getVariable(0).hasValue() || ! getVariable(1).hasValue()) ||
                        (getVariable(0).getValue() != getVariable(1).getValue());
                    }
                });
                constraints.add(new Constraint(cols[n][i], cols[n][j])
                {
                    @Override
                    public boolean check()
                    {
                        // we can use this to check if all values in the constraint are different
                        return (! getVariable(0).hasValue() || ! getVariable(1).hasValue()) ||
                        (getVariable(0).getValue() != getVariable(1).getValue());
                    }
                });
                
                constraints.add(new Constraint(boxes[n][i], boxes[n][j])
                {
                    @Override
                    public boolean check()
                    {
                        // we can use this to check if all values in the constraint are different
                        return (! getVariable(0).hasValue() || ! getVariable(1).hasValue()) ||
                        (getVariable(0).getValue() != getVariable(1).getValue());
                    }
                });
            }
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
    
    public static void startClock()
    {
        start = System.nanoTime(); 
    }
    
    public static void stopClock()
    {
        stop = System.nanoTime();
    }
    
    public static double solveAndPrint(Problem sudokuProblem)
    {
        final Solver solver = new AC3Solver(sudokuProblem)
        {
            @Override
            public void printAll()
            {
                int linebreaker = 0;
                for (int i = 0; i < getVarLength(); i++)
                {
                    String val = Integer.toString(getVariable(i).getValue());
                    if (val.equals("-1"))
                        val = "_";
                    System.out.print(val + " ");
                    linebreaker++;
                    if (linebreaker >= SIZE)
                    {
                        System.out.print("\n\n");
                        linebreaker = 0;
                    }
                }
            }
        };
        System.out.println("=========BOARD============");
        solver.printAll();
        startClock();
        if (solver.runSolver())
        {
            stopClock();
            double runTime = (stop - start) / 1000000000;
            System.out.print("========SOLVED========\n");
            
            System.out.print("====Time (seconds): " + Double.toString(runTime) + " =====\n\n");
            solver.printAll();
            return runTime;
        } 
        System.out.print("=====NO SOLUTION======\n");
        return 0.0;        
    }
    
    public static void main(String[] args)
    {              
        /**
         *
        final Problem sudokuProblem = new SudokuAC3();
        solveAndPrint(sudokuProblem);

        System.out.print("\n========NEW BOARD========\n\n");
        */
        
        int[] board2 = new int[81];
        board2[0] = 4;
        board2[1] = 5;
        board2[2] = 8;
        
        final Problem sudokuProblem2 = new Sudoku(board2);
        solveAndPrint(sudokuProblem2);
        
    }
}

