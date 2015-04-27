import java.util.*;
/*
 * Write a description of class Variable here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Variable
{
    // instance variables - replace the example below with your own
    private List<Integer> domain;
    private String name;
    private int value;
    private int previousVal;
    
    public Variable(List<Integer> domainParam, int id)
    {
        domain = domainParam;
        name = Integer.toString(id);
        previousVal = 0;
        value = -1;
    }
    
    public void setValue(int valToSet)
    {
        previousVal = value;
        value = valToSet;
    }
    
    public boolean hasValue()
    {
        return value != -1;  
    }
    
    public List<Integer> getDomain()
    {
        return domain;
    }
    
    public void setDomain(List<Integer> newDomain)
    {
        domain = newDomain;
    }
    
    
    public void removeFromDomain(int n)
    {
        domain.remove(n);
    }
    
    
    public String getName() 
    {
        return name;
    }
    
    public int getValue()
    {
        return value;
    }
    
    public int getPrevious()
    {
        return previousVal;
    }

    public void printDomain()
    {
        System.out.print(getName() + ": ");
        System.out.print(Arrays.toString(domain.toArray()) + "\n");
    }
    
}
