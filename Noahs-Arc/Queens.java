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
        // domain goes from 1 to SIZE 
        final int[] domain = new int[SIZE];
        for (int i = 1; i <= SIZE; i++)
        {
            domain[i - 1] = i;
        }
        
        // makes SIZE variables with domain 0-SIZE-1
        for (int i = 0; i < SIZE; i++)
        {
            variables.add(new Variable(domain, i, 0));
        }
        
        
        for (int i = 0; i < SIZE; i++)
        {   
            for (int j = i + 1; j < SIZE; j++)
            {
                final int difference = j - i;            
                constraints.add(new Constraint(new Variable[]{variables.get(i),variables.get(j)})
                {
                    @Override
                    public boolean check()
                    {
                        int val0 = getVariable(0).getValue();
                        int val1 = getVariable(1).getValue();
                        return (val0 != val1) && val1 - val0 != difference;
                        
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
        final Problem queensProblem = new Queens(8);
        final Solver solver = new BacktrackSolver(queensProblem);
        solver.printAll();
        System.out.print("\n======================\n");
        if (solver.runSolver())
            System.out.print("========SOLVED========\n");
        else 
            System.out.print("=====NO SOLUTION======\n");
        solver.printAll();               
    }
}

