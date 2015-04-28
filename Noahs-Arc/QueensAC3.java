import java.util.*;
/**
 * QueensAC3
 */
public class QueensAC3 implements Problem
{
    private int SIZE;
    private final List<Constraint> constraints;
    private final List<Variable> variables;
    static double start = 0.0;
    static double stop = 0.0;
    
    public QueensAC3()
    {
        constraints = new ArrayList<Constraint>();
        variables = new ArrayList<Variable>();
        SIZE = 4;
        generate();
    }
    
    public QueensAC3(int n)
    {
        constraints = new ArrayList<Constraint>();
        variables = new ArrayList<Variable>();
        SIZE = n;
        generate();
    }
        
    @Override
    public void generate() 
    {
        // makes SIZE variables with domain 0 to SIZE-1
        for (int i = 0; i < SIZE; i++)
        {
            // domain goes from 0 to SIZE-1        
            List<Integer> domain = new ArrayList<Integer>();
            for (int j = 0; j < SIZE; j++)
            {
                domain.add(j);
            }
            variables.add(new Variable(domain, i));
        }
        
        
        for (int i = 0; i < SIZE; i++)
        {   
            for (int j = i + 1; j < SIZE; j++)
            {
                final int difference = Math.abs(j - i);  
                
                constraints.add(new Constraint(variables.get(i),variables.get(j))
                {
                    @Override
                    public boolean check()
                    {
                        Variable var0 = getVariable(0);
                        Variable var1 = getVariable(1);
                        if (var0.hasValue() && var1.hasValue())
                            return (var0.getValue() != var1.getValue()) &&
                                (Math.abs(var1.getValue() - var0.getValue()) != difference); 
                        return true;
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
    
    public static double solveAndPrint(Problem queensProblem)
    {
        final Solver solver = new AC3Solver(queensProblem)
        {
            @Override
            public void printAll()
            {
                int n = getVarLength();
                char[][] board= new char[n][n];
                for (int row = 0; row < n; row++)
                {
                    for (int col = 0; col < n; col++)
                    {
                        board[row][col] = '+';
                        int value = getVariable(row).getValue();
                        if (value != -1)
                            board[row][value] = '@';
                    }
                }
                
                for (int i = 0; i < n; i++)
                {
                    for (int j = 0; j < n; j++)
                    {
                        System.out.print(board[i][j]);
                        System.out.print(' ');
                    }
                    System.out.print("\n\n");
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
        final Problem queensProblem = new QueensAC3();
        solveAndPrint(queensProblem);
        /**
        final Problem queensProblem2 = new QueensAC3(2);
        solveAndPrint(queensProblem2);
        
        final Problem queensProblem3 = new QueensAC3(3);
        solveAndPrint(queensProblem3);
        
        final Problem queensProblem4 = new QueensAC3(4);
        solveAndPrint(queensProblem4);
        
        final Problem queensProblem8 = new QueensAC3(18);
        solveAndPrint(queensProblem8);
        */
    }
}

