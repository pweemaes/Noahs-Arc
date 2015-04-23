import java.util.*;
/**
 * Queens
 */
public class Queens implements Problem
{
    private int SIZE;
    private final List<Constraint> constraints;
    private final List<Variable> variables;
    
    public Queens()
    {
        constraints = new ArrayList<Constraint>();
        variables = new ArrayList<Variable>();
        SIZE = 4;
        generate();
    }
    
    public Queens(int n)
    {
        constraints = new ArrayList<Constraint>();
        variables = new ArrayList<Variable>();
        SIZE = n;
        generate();
    }
        
    @Override
    public void generate() 
    {
        // domain goes from 0 to SIZE-1
        final int[] domain = new int[SIZE];
        for (int i = 0; i < SIZE; i++)
        {
            domain[i] = i;
        }
        
        // makes SIZE variables with domain 0 to SIZE-1
        for (int i = 0; i < SIZE; i++)
        {
            variables.add(new Variable(domain, i));
        }
        
        
        for (int i = 0; i < SIZE; i++)
        {   
            for (int j = i + 1; j < SIZE; j++)
            {
                final int difference = Math.abs(j - i);  
                constraints.add(new Constraint(new Variable[]{variables.get(i),variables.get(j)})
                {
                    @Override
                    public boolean check()
                    {
                        int val0 = getVariable(0).getValue();
                        int val1 = getVariable(1).getValue();
                        return (val0 != val1);                      
                    }
                });
                
                constraints.add(new Constraint(new Variable[]{variables.get(i),variables.get(j)})
                {
                    @Override
                    public boolean check()
                    {
                        Variable var0 = getVariable(0);
                        Variable var1 = getVariable(1);
                        if (var0.hasValue() && var1.hasValue())
                            return Math.abs(var1.getValue() - var0.getValue()) != difference; 
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
    
    
    public static void main(String[] args)
    {
        final Problem queensProblem = new Queens();
        final Solver solver = new BacktrackSolver(queensProblem)
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
        solver.printAll();
        System.out.print("\n======================\n");
        if (solver.runSolver())
            System.out.print("========SOLVED========\n");
        else 
            System.out.print("=====NO SOLUTION======\n");
        solver.printAll();               
    }
}

