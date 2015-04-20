import java.util.*;
/**
 * SampleProblem
 */
public class SampleProblem implements ProblemGenerator
{
    private final int size;
    private final Collection<Constraint> constraints;
    private final List<Variable> variables;
    
    public SampleProblem(int size) 
    {
        this.size = size;
        constraints = new ArrayList<Constraint>();
        variables = new ArrayList<Variable>();
    }
        
   
    
    @Override
    public void generate() 
    {
        final int[] domain = new int[size];
        for (int i = 0; i < size; i++)
        {
            domain[i] = i;
        }
        for (int i = 0; i < size; i++)
        {
            variables.add(new Variable(domain));
        }
        for (int i = 0; i < size; i++)
        {
            for (int j = i + 1; j < size; j++)
            {
                constraints.add(new Constraint()
                    {
                        @Override
                        public boolean check()
                        {
                            return getValue(0) != getValue(1);
                        }
                    });
            }
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
        final ProblemGenerator generator = new SampleProblem(Integer
                .valueOf(args[0]));
        final Solver solver = new AbstractSolver(Problem.load(generator));
        solver.runSolver();
        for (Variable v : generator.getVariables()) {
            System.out
                    .println(v + ": " + solver.getSolution());
        }
    }
}

