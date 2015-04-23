import java.util.*;
public interface Problem
{
    void generate();
    List<Variable> getVariables();
    Collection<Constraint> getConstraints();
}
