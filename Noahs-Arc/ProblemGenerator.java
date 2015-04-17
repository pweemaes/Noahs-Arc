import java.util.*;
/**
 * Write a description of interface ProblemGenerator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public interface ProblemGenerator
{
    void generate();
    List<Variable> getVariables();
    Collection<Constraint> getConstraints();
}
